package com.zkcm.szcp.project.cell3d.controller.mobile;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.utils.SecurityUtils;
import com.zkcm.szcp.common.utils.StringUtils;
import com.zkcm.szcp.common.utils.security.Md5Utils;
import com.zkcm.szcp.framework.aspectj.lang.annotation.Log;
import com.zkcm.szcp.framework.aspectj.lang.enums.BusinessType;
import com.zkcm.szcp.framework.redis.RedisCache;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.project.cell3d.domain.AccountBindingMachine;
import com.zkcm.szcp.project.cell3d.domain.ClassCode;
import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import com.zkcm.szcp.project.cell3d.domain.vo.AppUserVO;
import com.zkcm.szcp.project.cell3d.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 移动端
 *
 * @author admin
 */
@RestController
@RequestMapping("/mobile/account")
public class AccountMobileController extends BaseController {
    @Autowired
    private IOrgAccountService orgAccountService;
    @Autowired
    private IClassCodeService classCodeService;
    @Autowired
    private IOrgClassService orgClassService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IAccountBindingMachineService accountBindingMachineService;
    @Autowired
    private RedisCache redisCache;
    /*免密登录 hash盐值*/
    public static final String SALT = "ZKCM2019";
    /*redis 验证码*/
    public static final String REDIS_MOBILE_CODE = "mobile_code_";
    /*redis 免密凭证*/
    public static final String REDIS_TOKEN_CODE = "mobile_token_";

    /**
     * 用户注册
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/register")
    public AjaxResult register(AppUserVO appUserVO) {
        //注册校验
        String code = appUserVO.getCode();
        String userId = appUserVO.getUserId();
        String redisKey = REDIS_MOBILE_CODE + userId;
        //校验验证码
        if (!this.verifyCode(redisKey, code)) {
            return AjaxResult.error("无效或错误的验证码");
        }
        if (orgAccountService.checkPhoneUnique(appUserVO.getUserId()) > 0) {
            return AjaxResult.error("注册'" + appUserVO.getUserName() + "'失败，手机号码已存在");
        }
        //完成注册
        OrgAccount orgAccount = new OrgAccount();
        orgAccount.setUserName(appUserVO.getUserName());
        orgAccount.setUserMobile(appUserVO.getUserId());
        orgAccount.setUserPwd(SecurityUtils.encryptPassword(appUserVO.getPassword()));
//        orgAccount.setUserEmail();
        orgAccount.setActivateFlag(Constants.NO);
        orgAccount.setCreateTime(new Date());
        orgAccount.setDelFlag(Constants.NO);
        return toAjax(orgAccountService.insertOrgAccount(orgAccount));
    }

    /**
     * 免密登录
     */
    @GetMapping("/loginWithoutPwd")
    public AjaxResult loginWithoutPwd(String certificateCode) {
        //TODO 考虑加上物理地址的校验，防止免密凭证泄露
        OrgAccount orgAccount;
        String redisKey = REDIS_TOKEN_CODE + certificateCode;
        if (redisCache.getCacheObject(redisKey)) {
            orgAccount = redisCache.getCacheObject(redisKey);
            return AjaxResult.success(orgAccount);
        }
        return AjaxResult.error("凭证过期，请重新登录");
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public AjaxResult login(String userId, String password) {
        JSONObject jsonObject = new JSONObject();
        OrgAccount orgAccount = this.getOrgAccount(userId);
        if (orgAccount != null && SecurityUtils.matchesPassword(password, orgAccount.getUserPwd())) {
            //校验状态,后台不做校验，交给前台实现逻辑
            //是否激活，查看激活状态
            //是否过期

            //生成凭证
            String certificateCode = Md5Utils.hash(userId + SALT);
            String redisKey = REDIS_TOKEN_CODE + certificateCode;
            redisCache.setCacheObject(redisKey, orgAccount, 7, TimeUnit.DAYS);
            jsonObject.put("object", orgAccount);
            jsonObject.put("token", certificateCode);
        } else {
            return AjaxResult.error("账号密码错误");
        }
        return AjaxResult.success(jsonObject);
    }

    /**
     * 修改密码
     */
    @PostMapping("/modifyPwd")
    public AjaxResult modifyPwd(String userMobile, String password, String newPassword) {
        if (StringUtils.isEmpty(userMobile) || StringUtils.isEmpty(password)) {
            return AjaxResult.error("参数不合法");
        }
        if (StringUtils.isEmpty(newPassword)) {
            return AjaxResult.error("新密码不能为空");
        }
        OrgAccount orgAccountDB = this.getOrgAccount(userMobile);
        if (orgAccountDB == null) {
            return AjaxResult.error("账号不存在");
        }
        //比较输入的密码是否正确
        if (!SecurityUtils.matchesPassword(password, orgAccountDB.getUserPwd())) {
            return AjaxResult.error("原密码输入错误");
        }
        orgAccountDB.setUserPwd(SecurityUtils.encryptPassword(newPassword));
        return toAjax(orgAccountService.updateOrgAccount(orgAccountDB));
    }

    /**
     * 忘记密码
     */
    @PostMapping("/resetPwd")
    public AjaxResult resetPwd(String userMobile, String code, String newPassword) {
        //校验验证码
        String redisKey = REDIS_MOBILE_CODE + userMobile;
        if (!this.verifyCode(redisKey, code)) {
            return AjaxResult.error("无效或错误的验证码");
        }

        OrgAccount orgAccountDB = this.getOrgAccount(userMobile);
        if (orgAccountDB == null) {
            return AjaxResult.error("账号不存在");
        }
        orgAccountDB.setUserPwd(SecurityUtils.encryptPassword(newPassword));
        return toAjax(orgAccountService.updateOrgAccount(orgAccountDB));
    }

    /**
     * 用户激活
     */
    @Transactional
    @PostMapping("/activate")
    public AjaxResult activate(HttpServletRequest request,String userMobile, String code) {
        //检查激活码,包括是否正确，以及是否已经被激活
        ClassCode classCode = classCodeService.selectClassCodeByCode(code);
        if (classCode == null || Constants.YES.equals(classCode.getActivateFlag())) {
            return AjaxResult.error("不存在或已被使用的激活码");
        }
        //更新账号信息
        OrgAccount orgAccountDB = this.getOrgAccount(userMobile);
        orgAccountDB.setActivateFlag(Constants.YES);
        orgAccountDB.setCode(code);
        orgAccountDB.setOrgCode(classCode.getOrgCode());
        orgAccountDB.setClassCode(classCode.getClassCode());
        orgAccountDB.setActivateInvalidTime(classCode.getActivateInvalidTime());
        orgAccountService.updateOrgAccount(orgAccountDB);

        //修改激活数据 忽略测试账号
        if(!"admin".equals(classCode.getOrgCode())){
            orgClassService.updateActivateNumber(classCode.getClassCode());
            orgService.updateActivateNumber(classCode.getOrgCode());
        }

        request.setAttribute("user", orgAccountDB);
        //激活信息补充
        classCode.setActivateFlag(Constants.YES);
        classCode.setActivateTime(DateUtil.now());
        classCode.setUserName(orgAccountDB.getUserName());
        classCode.setUserMobile(orgAccountDB.getUserMobile());
        return toAjax(classCodeService.updateClassCode(classCode));
    }

    /**
     * 设备绑定列表
     *
     * @param request
     * @return
     */
    @Transactional
    @GetMapping("/bindingList")
    public AjaxResult bindingList(HttpServletRequest request) {
        OrgAccount user = (OrgAccount) request.getAttribute("user");
        String code = user.getCode();
        List<AccountBindingMachine> bindingMachines = this.getAccountBindingMachineByCode(code);
        return AjaxResult.success(bindingMachines);
    }


    /**
     * 设备绑定
     *
     * @param request
     * @param machineIp 绑定地址
     * @param machineName 绑定地址名称
     * @return
     */
    @PostMapping("/binding")
    public AjaxResult binding(HttpServletRequest request, String machineIp, String machineName) {
        OrgAccount user = (OrgAccount) request.getAttribute("user");
        String code = user.getCode();
        if(StringUtils.isEmpty(code)){
            return AjaxResult.error("请重新登录后，再次绑定设备");
        }
        List<AccountBindingMachine> bindingMachines = this.getAccountBindingMachineByCode(code);
        if (bindingMachines != null && bindingMachines.size() >= 3) {
            return AjaxResult.error("每个激活码最多只能绑定三台设备，如过仍需绑定，请先解绑其他设备");
        }
        String machineIpTemp;
        if(bindingMachines != null && !bindingMachines.isEmpty()){
            for (AccountBindingMachine bindingMachine : bindingMachines) {
                machineIpTemp = bindingMachine.getMachineIp();
                if(machineIp.equals(machineIpTemp)){
                    return AjaxResult.error("已绑定设备，无需再次绑定");
                }
            }
        }

        AccountBindingMachine accountBindingMachine = new AccountBindingMachine();
        accountBindingMachine.setCode(code);
        accountBindingMachine.setMachineIp(machineIp);
        accountBindingMachine.setMachineName(machineName);
        accountBindingMachine.setCreateTime(new Date());
        accountBindingMachine.setDelFlag(Constants.NO);
        return toAjax(accountBindingMachineService.insertAccountBindingMachine(accountBindingMachine));
    }

    /**
     * 设备解绑
     *
     * @param request
     * @return
     */
    @PostMapping("/cancelBinding")
    public AjaxResult cancelBinding(HttpServletRequest request, Long id) {
        AccountBindingMachine accountBindingMachine = accountBindingMachineService.getAccountBindingMachineById(id);
        accountBindingMachine.setDelFlag(Constants.YES);
        return toAjax(accountBindingMachineService.updateAccountBindingMachine(accountBindingMachine));
    }

    private List<AccountBindingMachine> getAccountBindingMachineByCode(String code) {
        AccountBindingMachine query = new AccountBindingMachine();
        query.setDelFlag(Constants.NO);
        query.setCode(code);
        List<AccountBindingMachine> accountBindingMachines = accountBindingMachineService.selectAccountBindingMachineList(query);
        return accountBindingMachines;
    }


    /**
     * 发送手机短信
     *
     * @param userMobile 接受短信的手机号
     * @return
     */
    @Log(title = "手机短信", businessType = BusinessType.UPDATE)
    @PostMapping("/sendMobileInfo")
    public AjaxResult sendMobileInfo(String userMobile) {
        if (StringUtils.isEmpty(userMobile)) {
            return AjaxResult.error("不合法的参数");
        }
        //type目前包括：注册，忘记密码
//        String code = RandomUtil.randomNumbers(4);
        String code = "1234";   //方便测试
        logger.info("发送验证码到手机::" + userMobile + ",验证码为::" + code);
        String redisKey = REDIS_MOBILE_CODE + userMobile;
        redisCache.setCacheObject(redisKey, code, 5, TimeUnit.MINUTES);
        //TODO 发送短信
        return AjaxResult.success("验证码已发送，请注意查收", code);
    }

    /**
     * 校验验证码
     *
     * @param key
     * @param value
     * @return
     */
    private Boolean verifyCode(String key, String value) {
        Object cacheObject = redisCache.getCacheObject(key);
        return cacheObject != null && cacheObject.toString().equals(value);
    }

    private OrgAccount getOrgAccount(String mobile) {
        //获取当前用户的用户信息
        OrgAccount query = new OrgAccount();
        query.setUserMobile(mobile);
        query.setDelFlag(Constants.NO);
        OrgAccount orgAccountDB = orgAccountService.getOrgAccount(query);
        return orgAccountDB;
    }

    /**
     * 校验token
     *
     * @param request
     * @return 有效 返回用户对象； 无效返回null值
     */
    private OrgAccount verifyToken(HttpServletRequest request) {
        //取header 的内容
        String token = request.getHeader("mobile_token");
        OrgAccount orgAccount;
        String redisKey = REDIS_TOKEN_CODE + token;
        if (redisCache.getCacheObject(redisKey) != null) {
            orgAccount = redisCache.getCacheObject(redisKey);
            return orgAccount;
        }
        return null;
    }


}
