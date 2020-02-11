package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.constant.UserConstants;
import com.zkcm.szcp.common.utils.StringUtils;
import com.zkcm.szcp.project.cell3d.domain.Org;
import com.zkcm.szcp.project.cell3d.mapper.OrgMapper;
import com.zkcm.szcp.project.cell3d.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织机构 服务层实现
 *
 * @author hylu
 */
@Service
public class OrgServiceImpl implements IOrgService {
    @Autowired
    private OrgMapper orgMapper;

    /**
     * 查询组织机构信息
     *
     * @param orgId 组织机构ID
     * @return 组织机构信息
     */
    @Override
    public Org selectOrgById(Long orgId) {
        Org org = new Org();
        org.setId(orgId);
        return orgMapper.selectOrg(org);
    }

    /**
     * 根据键名查询组织机构信息
     *
     * @param orgCode 组织机构key
     * @return 组织机构键值
     */
    @Override
    public Org selectOrgByCode(String orgCode) {
        Org org = new Org();
        org.setOrgCode(orgCode);
        Org retOrg = orgMapper.selectOrg(org);
        return StringUtils.isNotNull(retOrg) ? retOrg : null;
    }

    /**
     * 根据负责人手机号码查询组织机构信息
     *
     * @param adminMobile 参数键名
     * @return 参数键值
     */
    @Override
    public Org selectOrgByAdminMobile(String adminMobile) {
        Org org = new Org();
        org.setAdminUserMobile(adminMobile);
        Org retOrg = orgMapper.selectOrg(org);
        return StringUtils.isNotNull(retOrg) ? retOrg : null;
    }

    /**
     * 查询组织机构列表
     *
     * @param org 组织机构信息
     * @return 组织机构集合
     */
    @Override
    public List<Org> selectOrgList(Org org) {
        return orgMapper.selectOrgList(org);
    }

    /**
     * 新增组织机构
     *
     * @param org 组织机构信息
     * @return 结果
     */
    @Override
    public int insertOrg(Org org) {
        return orgMapper.insertOrg(org);
    }

    /**
     * 修改组织机构
     *
     * @param org 组织机构信息
     * @return 结果
     */
    @Override
    public int updateOrg(Org org) {
        return orgMapper.updateOrg(org);
    }

    /**
     * 删除组织机构信息
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrgById(Long orgId) {
        return orgMapper.deleteOrgById(orgId);
    }

    /**
     * 禁用组织机构信息
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int disableOrgById(Long orgId) {
        Org org = new Org();
        org.setId(orgId);
        Org orgDB = orgMapper.selectOrg(org);
        if (orgDB != null) {
            //禁用状态
            orgDB.setStatus(2);
            return orgMapper.updateOrg(orgDB);
        }
        return 0;
    }

    @Override
    public int updateActivateNumber(String orgCode) {
        Org org = new Org();
        org.setOrgCode(orgCode);
        org.setDelFlag(Constants.NO);
        Org orgDB = orgMapper.selectOrg(org);
        if (orgDB != null) {
            int activate = orgDB.getActivateSum();
            int maxActivate = orgDB.getMaxActivate();
            //激活数量是否达到上限
            if (maxActivate - activate < 1) {
                return -1;
            }
            orgDB.setActivateSum(activate + 1);
            return orgMapper.updateOrg(orgDB);
        }
        return 0;
    }

    /**
     * 校验组织机构键名是否唯一
     *
     * @param org 组织机构信息
     * @return 结果
     */
    @Override
    public String checkOrgKeyUnique(Org org) {
        Long orgId = StringUtils.isNull(org.getId()) ? -1L : org.getId();
        Org info = orgMapper.checkOrgKeyUnique(org.getOrgCode());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != orgId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
