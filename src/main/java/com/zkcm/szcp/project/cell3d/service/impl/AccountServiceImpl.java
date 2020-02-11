package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import com.zkcm.szcp.project.cell3d.mapper.OrgAccountMapper;
import com.zkcm.szcp.project.cell3d.service.IOrgAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author admin
 */
@Service
public class AccountServiceImpl implements IOrgAccountService {
    @Autowired
    private OrgAccountMapper orgAccountMapper;


    /**
     * 查询机构账号信息
     *
     * @param orgAccountId 机构账号主键
     * @return 机构账号信息
     */
    @Override
    public OrgAccount getOrgAccountById(Long orgAccountId) {
        return orgAccountMapper.getOrgAccountById(orgAccountId);
    }

    /**
     * 查询机构账号信息
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号信息
     */
    @Override
    public OrgAccount getOrgAccount(OrgAccount orgAccount) {
        return orgAccountMapper.getOrgAccount(orgAccount);
    }

    /**
     * 查询机构账号列表
     *
     * @param orgAccount 机构账号信息
     * @return 机构账号集合
     */
    @Override
    public List<OrgAccount> selectOrgAccountList(OrgAccount orgAccount) {
        return orgAccountMapper.selectOrgAccountList(orgAccount);
    }

    /**
     * 新增机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    @Override
    public int insertOrgAccount(OrgAccount orgAccount) {
        return orgAccountMapper.insertOrgAccount(orgAccount);
    }

    /**
     * 修改机构账号
     *
     * @param orgAccount 机构账号信息
     * @return 结果
     */
    @Override
    public int updateOrgAccount(OrgAccount orgAccount) {
        return orgAccountMapper.updateOrgAccount(orgAccount);
    }

    /**
     * 删除机构账号
     *
     * @param orgAccountId 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrgAccountById(Long orgAccountId) {
        return orgAccountMapper.deleteOrgAccountById(orgAccountId);
    }

    /**
     * 批量删除机构账号
     *
     * @param orgAccountIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteOrgAccount(Long[] orgAccountIds) {
        return orgAccountMapper.batchDeleteOrgAccount(orgAccountIds);
    }

    /**
     * 校验账号唯一性
     *
     * @param userMobile
     * @return
     */
    @Override
    public int checkPhoneUnique(String userMobile) {
        return orgAccountMapper.checkPhoneUnique(userMobile);
    }
}
