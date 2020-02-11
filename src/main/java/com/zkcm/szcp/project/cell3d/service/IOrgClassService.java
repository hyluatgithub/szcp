package com.zkcm.szcp.project.cell3d.service;

import com.zkcm.szcp.project.cell3d.domain.OrgClass;

import java.util.List;

/**
 * 班级管理 服务层
 *
 * @author hylu
 */
public interface IOrgClassService {

    /**
     * 查询班级信息
     *
     * @param orgClassId 班级ID
     * @return 班级信息
     */
    public OrgClass selectOrgClassById(Long orgClassId);

    /**
     * 查询班级列表
     *
     * @param orgClass 班级信息
     * @return 班级集合
     */
    public List<OrgClass> selectOrgClassList(OrgClass orgClass);

    /**
     * 校验参数键名是否唯一
     *
     * @param orgClass 参数信息
     * @return 结果
     */
    public String checkOrgClassKeyUnique(OrgClass orgClass);

    /**
     * 查询组织机构已分配的所有激活码额度
     *
     * @param orgCode 班级信息
     * @return 结果
     */
    public int countActivateSum(String orgCode);

    /**
     * 删除班级信息
     *
     * @param orgId 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgClassById(Long orgId);
    /**
     * 新增/编辑班级
     *
     * @param orgClass 班级信息
     * @return 结果
     */
    int saveOrgClass(OrgClass orgClass);

    int updateActivateNumber(String classCode);

}
