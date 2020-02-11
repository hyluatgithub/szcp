package com.zkcm.szcp.common.exception.user;

import com.zkcm.szcp.common.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author hylu
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
