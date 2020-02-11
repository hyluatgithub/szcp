package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.Org;

import java.util.List;

/**
 * 组织机构 数据层
 *
 * @author hylu
 */
public interface OrgMapper {
    /**
     * 查询组织机构信息
     *
     * @param org 组织机构信息
     * @return 组织机构信息
     */
    public Org selectOrg(Org org);

    /**
     * 查询组织机构列表
     *
     * @param org 组织机构信息
     * @return 组织机构集合
     */
    public List<Org> selectOrgList(Org org);

    /**
     * 根据键名查询组织机构信息
     *
     * @param orgKey 参数键名
     * @return 组织机构信息
     */
    public Org checkOrgKeyUnique(String orgKey);

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
     * 删除组织机构
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgById(Long orgId);
}
