package com.zkcm.szcp.framework.security.filter;

import com.zkcm.szcp.framework.redis.RedisCache;
import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileAuthenticationFilter extends HandlerInterceptorAdapter {
    /*redis 免密凭证*/
    public static final String REDIS_TOKEN_CODE = "mobile_token_";
    @Autowired
    private RedisCache redisCache;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getServletPath();
        if (path.matches("/mobile/account/register")
                || path.matches("/mobile/account/loginWithoutPwd")
                || path.matches("/mobile/account/login")
                || path.matches("/mobile/account/resetPwd")
                || path.matches("/mobile/account/sendMobileInfo")) {
            //app的注册 免密登录 登录 忘记免密 发送验证码 免权限校验
            return true;
        } else {
            //不需要的拦截直接过
            OrgAccount orgAccount = this.verifyToken(request);
            if (orgAccount != null) {
                request.setAttribute("user", orgAccount);
                return true;
            }
            return false;
        }
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
        if (token == null) {
            return null;
        }
        OrgAccount orgAccount;
        String redisKey = REDIS_TOKEN_CODE + token;
        if (redisCache.getCacheObject(redisKey) != null) {
            orgAccount = redisCache.getCacheObject(redisKey);
            return orgAccount;
        }
        return null;
    }


}
