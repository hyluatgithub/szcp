package com.zkcm.szcp.project.cell3d.service;

import com.zkcm.szcp.project.cell3d.domain.Org;

import java.util.List;

/**
 * 组织机构 服务层
 *
 * @author hylu
 */
public interface IOrgService {

    /**
     * 查询组织机构列表
     *
     * @param org 组织机构信息
     * @return 组织机构集合
     */
    public List<Org> selectOrgList(Org org);

    /**
     * 查询组织机构信息
     *
     * @param orgId 组织机构ID
     * @return 组织机构信息
     */
    public Org selectOrgById(Long orgId);

    /**
     * 根据键名查询组织机构信息
     *
     * @param orgCode 参数键名
     * @return 参数键值
     */
    public Org selectOrgByCode(String orgCode);

    /**
     * 根据负责人手机号码查询组织机构信息
     *
     * @param adminMobile 参数键名
     * @return 参数键值
     */
    public Org selectOrgByAdminMobile(String adminMobile);

    /**
     * 校验参数键名是否唯一
     *
     * @param org 参数信息
     * @return 结果
     */
    public String checkOrgKeyUnique(Org org);

    /**
     * 新增组织机构
     *
     * @param org 组织机构信息
     * @return 结果
     */
    public int insertOrg(Org org);

    /**
     * 修改组织机构
     *
     * @param org 组织机构信息
     * @return 结果
     */
    public int updateOrg(Org org);

    /**
     * 删除组织机构信息
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgById(Long orgId);

    /**
     * 禁用组织机构信息
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    int disableOrgById(Long orgId);

    int updateActivateNumber(String orgCode);
}
