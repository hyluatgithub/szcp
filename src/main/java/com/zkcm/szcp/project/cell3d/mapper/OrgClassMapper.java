package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.OrgClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级 数据层
 *
 * @author hylu
 */
public interface OrgClassMapper {
    /**
     * 查询班级列表
     *
     * @param orgClass 班级信息
     * @return 班级集合
     */
    public List<OrgClass> selectOrgClassList(OrgClass orgClass);

    /**
     * 新增班级
     *
     * @param orgClass 班级信息
     * @return 结果
     */
    public int insertOrgClass(OrgClass orgClass);

    /**
     * 修改班级
     *
     * @param orgClass 班级信息
     * @return 结果
     */
    public int updateOrgClass(OrgClass orgClass);

    /**
     * 删除班级
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgClassById(Long id);

    public OrgClass checkOrgClassKeyUnique(String orgCode);

    int countActivateSum(String orgCode);

    OrgClass selectOrgClassById(Long id);

    OrgClass selectOrgClassByCode(String classCode);
}
