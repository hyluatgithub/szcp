package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.ClassCode;

import java.util.List;

/**
 * 激活码 数据层
 *
 * @author hylu
 */
public interface ClassCodeMapper {
    /**
     * 查询激活码列表
     *
     * @param id
     * @return 激活码集合
     */
    ClassCode selectClassCodeById(Long id);

    /**
     * 查询激活码列表
     *
     * @param classCode 激活码信息
     * @return 激活码集合
     */
    public List<ClassCode> selectClassCodeList(ClassCode classCode);

    /**
     * 新增激活码
     *
     * @param classCode 激活码信息
     * @return 结果
     */
    public int insertClassCode(ClassCode classCode);

    /**
     * 修改激活码
     *
     * @param classCode 激活码信息
     * @return 结果
     */
    public int updateClassCode(ClassCode classCode);

    /**
     * 删除激活码
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassCodeById(Long id);

    /**
     * 删除班级下所有的激活码
     */
    int deleteClassCodeByClassCode(String classCode);

    ClassCode selectClassCodeByCode(String code);
}
