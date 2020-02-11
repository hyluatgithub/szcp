package com.zkcm.szcp.project.cell3d.service;


import com.zkcm.szcp.project.cell3d.domain.OrgAccount;

import java.util.List;

/**
 * 机构用户账号表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-16 13:45:50
 */
public interface IOrgAccountService {
    /**
     * 查询机构账号信息
     *
     * @param orgAccountId 机构账号主键
     * @return 机构账号信息
     */
    OrgAccount getOrgAccountById(Long orgAccountId);

    /**
     * 查询机构账号信息
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号信息
     */
    OrgAccount getOrgAccount(OrgAccount orgAccount);

    /**
     * 查询机构账号列表
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号集合
     */
    List<OrgAccount> selectOrgAccountList(OrgAccount orgAccount);

    /**
     * 新增机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    int insertOrgAccount(OrgAccount orgAccount);

    /**
     * 修改机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    int updateOrgAccount(OrgAccount orgAccount);

    /**
     * 删除机构账号
     *
     * @param orgAccountId 需要删除的数据ID
     * @return 结果
     */
    int deleteOrgAccountById(Long orgAccountId);

    /**
     * 批量删除机构账号
     *
     * @param orgAccountIds 需要删除的数据ID
     * @return 结果
     */
    int batchDeleteOrgAccount(Long[] orgAccountIds);

    /**
     * 校验账号唯一性
     *
     * @param userMobile
     * @return
     */
    int checkPhoneUnique(String userMobile);
}
