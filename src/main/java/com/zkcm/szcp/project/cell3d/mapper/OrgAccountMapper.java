package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.OrgAccount;

import java.util.List;

/**
 * 机构账号 数据层
 *
 * @author hylu
 */
public interface OrgAccountMapper {
    /**
     * 查询机构账号信息
     *
     * @param id 机构账号主键
     * @return 机构账号信息
     */
    public OrgAccount getOrgAccountById(Long id);

    /**
     * 查询机构账号信息
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号信息
     */
    public OrgAccount getOrgAccount(OrgAccount orgAccount);

    /**
     * 查询机构账号列表
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号集合
     */
    public List<OrgAccount> selectOrgAccountList(OrgAccount orgAccount);

    /**
     * 新增机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    public int insertOrgAccount(OrgAccount orgAccount);

    /**
     * 修改机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    public int updateOrgAccount(OrgAccount orgAccount);

    /**
     * 删除机构账号
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgAccountById(Long id);

    /**
     * 批量删除机构账号
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteOrgAccount(Long[] ids);

    int checkPhoneUnique(String userMobile);
}
