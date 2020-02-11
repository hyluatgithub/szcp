package com.zkcm.szcp.project.cell3d.service;

import com.zkcm.szcp.project.cell3d.domain.ClassCode;

import java.util.List;

/**
 * 激活码管理 服务层
 *
 * @author hylu
 */
public interface IClassCodeService {

    /**
     * 查询激活码信息
     *
     * @param classCodeId 激活码ID
     * @return 激活码信息
     */
    public ClassCode selectClassCodeById(Long classCodeId);

    /**
     * 查询激活码信息
     *
     * @param code      激活码
     * @return 激活码信息
     */
    public ClassCode selectClassCodeByCode( String code);

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
    int insertClassCode(ClassCode classCode);

    /**
     * 修改激活码
     *
     * @param classCode 激活码信息
     * @return 结果
     */
    int updateClassCode(ClassCode classCode);

    /**
     * 删除激活码信息
     *
     * @param classCodeId 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassCodeById(Long classCodeId);

    /**
     * 校验班级下是否有已激活的账号
     *
     * @param orgClassId 激活码信息
     * @return 结果
     */
    boolean checkHasActivateByClassId(Long orgClassId);
}
