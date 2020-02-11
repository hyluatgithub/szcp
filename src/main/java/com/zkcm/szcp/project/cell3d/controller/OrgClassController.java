package com.zkcm.szcp.project.cell3d.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.constant.SysRoleConstants;
import com.zkcm.szcp.common.utils.DateUtils;
import com.zkcm.szcp.common.utils.SecurityUtils;
import com.zkcm.szcp.framework.aspectj.lang.annotation.Log;
import com.zkcm.szcp.framework.aspectj.lang.enums.BusinessType;
import com.zkcm.szcp.framework.security.LoginUser;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.ClassCode;
import com.zkcm.szcp.project.cell3d.domain.Org;
import com.zkcm.szcp.project.cell3d.domain.OrgClass;
import com.zkcm.szcp.project.cell3d.domain.vo.OrgVO;
import com.zkcm.szcp.project.cell3d.service.IClassCodeService;
import com.zkcm.szcp.project.cell3d.service.IOrgClassService;
import com.zkcm.szcp.project.cell3d.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 细胞3d 班级管理
 *
 * @author hylu
 */
@RestController
@RequestMapping("/orgClass")
public class OrgClassController extends BaseController {
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IOrgClassService orgClassService;
    @Autowired
    private IClassCodeService classCodeService;

    /**
     * 查询当前组织机构的激活码概览信息
     */
    @PreAuthorize("@ss.hasPermi('orgClass:orgClass:list')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo() {
        //查询当前用户所属组织机构
        Org currentOrg = this.getCurrentOrg();
        if (currentOrg == null) {
            return AjaxResult.error("未查询到账号归属机构，请检查账号信息");
        }
        //查询已分配的激活码总数
        int activateSum = orgClassService.countActivateSum(currentOrg.getOrgCode());
        return AjaxResult.success(new OrgVO(currentOrg, activateSum));
    }

    /**
     * 查询当前组织机构的激活码概览信息
     */
    @PreAuthorize("@ss.hasPermi('orgClass:orgClass:list')")
    @GetMapping(value = "/{orgClassId}")
    public AjaxResult getById(@PathVariable Long orgClassId) {
        OrgClass orgClass = orgClassService.selectOrgClassById(orgClassId);
        return AjaxResult.success(orgClass);
    }


    /**
     * 获取班级列表
     */
    @PreAuthorize("@ss.hasPermi('orgClass:orgClass:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrgClass orgClass) {
        // 查询当前登录用户所属机构的班级列表(超管账号忽略)
        if (!this.isAdmin()) {
            Org currentOrg = this.getCurrentOrg();
            if (currentOrg != null) {
                orgClass.setOrgCode(currentOrg.getOrgCode());
            }
        }

        startPage();
        List<OrgClass> list = orgClassService.selectOrgClassList(orgClass);
        return getDataTable(list);
    }

    /**
     * 分配激活码
     */
    @PreAuthorize("@ss.hasPermi('orgClass:orgClass:add')")
    @Log(title = "分配激活码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addActivateCode(@RequestBody OrgClass orgClass) {
        //获取当前操作人所属机构信息
        Org currentOrg = this.getCurrentOrg();
        if (currentOrg == null) {
            return AjaxResult.error("未查询到账号归属机构，请检查账号信息");
        }
        //校验分配激活码
        int newMaxActivate = orgClass.getMaxActivate();
        if (!this.verify(currentOrg, newMaxActivate)) {
            return AjaxResult.error(500, "无法分配新的激活码，请检查是否需要续费或扩容");
        }

        //初始化数据
        OrgClass orgClassNew = this.initWebData(orgClass, currentOrg.getOrgCode());
        if (orgClassService.saveOrgClass(orgClassNew) > 0) {
            //更新机构已分配激活码数量
            currentOrg.setActivateSum(currentOrg.getActivateSum() + newMaxActivate);
            orgService.updateOrg(currentOrg);

            orgClassNew.setMaxActivate(newMaxActivate);
            //初始化激活码账号,批量保存
            this.initCodeCount(orgClassNew, currentOrg.getEndDate());
        }

        return AjaxResult.success("操作成功", orgClassNew);
    }

    /**
     * 删除班级
     */
    @PreAuthorize("@ss.hasPermi('orgClass:orgClass:remove')")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgClassId}")
    public AjaxResult remove(@PathVariable Long orgClassId) {
        //检查是否有已经激活的账号
        if (classCodeService.checkHasActivateByClassId(orgClassId)) {
            return AjaxResult.error("存在已经激活的账号，无法删除！");
        }
        return toAjax(orgClassService.deleteOrgClassById(orgClassId));
    }

    /**
     * 查询当前登录账号的所属组织机构
     */
    private Org getCurrentOrg() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String phonenumber = loginUser.getUser().getPhonenumber();
        Org org = orgService.selectOrgByAdminMobile(phonenumber);
        return org;
    }

    /**
     * 校验分配激活码操作是否可执行
     * 检验内容：是否仍在有效期，激活数量是否足够
     *
     * @param org
     * @param newMaxActivate
     * @return
     */
    private boolean verify(Org org, int newMaxActivate) {
        //校验是否失效
        String endDateStr = org.getEndDate();
        Date endDate = DateUtil.parse(endDateStr);
        if (DateUtil.compare(endDate, DateUtils.getNowDate()) < 0) {
            return false;
        }

        //校验验证码容量
        //该账号支持的最大激活数
        Integer maxActivate = org.getMaxActivate();
        //该机构目前已经分配的激活码数量
        Integer activateSum = org.getActivateSum();
        if (activateSum + newMaxActivate > maxActivate) {
            return false;
        }
        return true;
    }

    private OrgClass initWebData(OrgClass orgClassDO, String orgCode) {
        if (orgClassDO.getId() != null) {
            //编辑
            OrgClass orgClassDB = orgClassService.selectOrgClassById(orgClassDO.getId());
            orgClassDB.setMaxActivate(orgClassDB.getMaxActivate() + orgClassDO.getMaxActivate());
            return orgClassDB;
        } else {
            //新增
            orgClassDO.setOrgCode(orgCode);
            orgClassDO.setCreateTime(new Date());
            //初始化信息
            orgClassDO.setDelFlag(Constants.NO);
            orgClassDO.setActivate(0);
            String classCode = orgClassDO.getClassCode();
            if (StrUtil.isEmpty(classCode)) {
                classCode = orgClassDO.getOrgCode() + "_" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
                orgClassDO.setClassCode(classCode);
            }
            return orgClassDO;
        }
    }

    /**
     * 初始化激活码账号
     *
     * @param orgClass
     * @return
     */
    private void initCodeCount(OrgClass orgClass, String activateInvalidTime) {
        ClassCode classCode;
        Integer maxActivate = orgClass.getMaxActivate();
        for (int i = 0; i < maxActivate; i++) {
            classCode = new ClassCode();
            classCode.setOrgCode(orgClass.getOrgCode());
            classCode.setClassCode(orgClass.getClassCode());
            classCode.setCode(RandomUtil.randomString(8));
            classCode.setActivateInvalidTime(activateInvalidTime);
            classCode.setActivateFlag(Constants.NO);
            classCode.setCreateTime(new Date());
            classCode.setDeleteFlag(Constants.NO);
            classCodeService.insertClassCode(classCode);
        }

    }

    /**
     * 校验是否超管（含系统超管 和 细胞项目超管）
     *
     * @return
     */
    private boolean isAdmin() {
        String username = SecurityUtils.getUsername();
        if (SysRoleConstants.SYS_ADMIN_NAME.equals(username) || SysRoleConstants.CELL_ADMIN_NAME.equals(username)) {
            return true;
        }
        return false;
    }
}
