package com.zkcm.szcp.project.cell3d.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.constant.UserConstants;
import com.zkcm.szcp.common.utils.SecurityUtils;
import com.zkcm.szcp.framework.aspectj.lang.annotation.Log;
import com.zkcm.szcp.framework.aspectj.lang.enums.BusinessType;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.Org;
import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import com.zkcm.szcp.project.cell3d.service.IOrgService;
import com.zkcm.szcp.project.system.domain.SysUser;
import com.zkcm.szcp.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 细胞3d 组织机构处理
 *
 * @author hylu
 */
@RestController
@RequestMapping("/org/org")
public class OrgController extends BaseController {
    @Autowired
    private IOrgService orgService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取组织机构列表
     */
//    @PreAuthorize("@ss.hasPermi('org:org:list')")
    @GetMapping("/list")
    public TableDataInfo list(Org org) {
        startPage();
        List<Org> list = orgService.selectOrgList(org);
        return getDataTable(list);
    }

    /**
     * 根据编号获取详细信息
     */
    /*@PreAuthorize("@ss.hasPermi('org:org:query')")*/
    @GetMapping(value = "/{orgId}")
    public AjaxResult getInfo(@PathVariable Long orgId) {
        return AjaxResult.success(orgService.selectOrgById(orgId));
    }

    /**
     * 根据键名查询参数值
     */
//    @PreAuthorize("@ss.hasPermi('org:org:query')")
    @GetMapping(value = "/orgCode/{orgCode}")
    public AjaxResult getOrgKey(@PathVariable String orgCode) {
        return AjaxResult.success(orgService.selectOrgByCode(orgCode));
    }

    /**
     * 新增组织机构
     */
    @Transactional
//    @PreAuthorize("@ss.hasPermi('org:org:add')")
    @Log(title = "组织机构管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Org org) {
        if (UserConstants.NOT_UNIQUE.equals(orgService.checkOrgKeyUnique(org))) {
            return AjaxResult.error("新增组织机构'" + org.getOrgName() + "'失败，组织机构键名已存在");
        }
        //校验并创建机构管理员账号
        String adminUserName = org.getAdminUserName();
        String adminUserMobile = org.getAdminUserMobile();

        if (StrUtil.isEmpty(adminUserName) || StrUtil.isEmpty(adminUserMobile)) {
            return AjaxResult.error("不合法的参数");
        }
        org.setCreateBy(SecurityUtils.getUsername());
        org.setDelFlag(Constants.NO);
        org.setOrgCode(IdUtil.simpleUUID());
        org.setStartDate(DateUtil.date().toDateStr());
        //新增机构管理员账号
        //初始化机构管理员账号
        SysUser sysUser = this.initSysAdminUser(adminUserName, adminUserMobile);
        if (orgService.insertOrg(org) > 0) {
            //账号所属机构与新建组织机构保持一致
            sysUser.setDeptId(org.getId());
            return toAjax(sysUserService.insertUser(sysUser));
        } else {
            return AjaxResult.error("保存失败");
        }
    }

    /**
     * 修改组织机构
     */
//    @PreAuthorize("@ss.hasPermi('org:org:edit')")
    @Log(title = "组织机构管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Org org) {
        if (UserConstants.NOT_UNIQUE.equals(orgService.checkOrgKeyUnique(org))) {
            return AjaxResult.error("修改组织机构'" + org.getOrgName() + "'失败，组织机构键名已存在");
        }
        org.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(orgService.updateOrg(org));
    }

    /**
     * 删除组织机构
     *
     * 删除组织机构的同时，会删除机构管理员账号，禁用机构下所有的激活码
     */
//    @PreAuthorize("@ss.hasPermi('org:org:remove')")
    @Log(title = "组织机构管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgId}")
    public AjaxResult remove(@PathVariable Long orgId) {
        Org org = orgService.selectOrgById(orgId);
        if (org != null) {

        }
        return toAjax(orgService.deleteOrgById(orgId));
    }

    /**
     * 禁用组织机构
     */
//    @PreAuthorize("@ss.hasPermi('org:org:disable')")
    @Log(title = "组织机构管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/disable/{orgId}")
    public AjaxResult disable(@PathVariable Long orgId) {
        return toAjax(orgService.disableOrgById(orgId));
    }


    private SysUser initSysAdminUser(String adminUserName, String adminUserMobile) {
        SysUser sysUser = new SysUser();
        //固定在细胞项目组
//        sysUser.setDeptId(101L);
        sysUser.setNickName(adminUserName);
        sysUser.setUserName(adminUserMobile);
        sysUser.setEmail("");
        sysUser.setPhonenumber(adminUserMobile);
        //密码默认为手机号的后六位
        String password = adminUserMobile.substring(adminUserMobile.length() - 6);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setStatus("0");
        sysUser.setDelFlag(Constants.NO);
        sysUser.setRemark("机构管理员");
        //固定为机构管理员角色
        sysUser.setRoleIds(new Long[]{101L});
        return sysUser;
    }

}
