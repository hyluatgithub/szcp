package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.project.cell3d.domain.ClassCode;
import com.zkcm.szcp.project.cell3d.domain.OrgClass;
import com.zkcm.szcp.project.cell3d.mapper.ClassCodeMapper;
import com.zkcm.szcp.project.cell3d.mapper.OrgClassMapper;
import com.zkcm.szcp.project.cell3d.service.IClassCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 */
@Service
public class ClassCodeServiceImpl implements IClassCodeService {
    @Autowired
    private ClassCodeMapper classCodeMapper;
    @Autowired
    private OrgClassMapper orgClassMapper;

    /**
     * 查询激活码信息
     *
     * @param classCodeId 激活码ID
     * @return 激活码信息
     */
    @Override
    public ClassCode selectClassCodeById(Long classCodeId) {
        return classCodeMapper.selectClassCodeById(classCodeId);
    }

    /**
     * 查询激活码信息
     *
     * @param code 激活码
     * @return 激活码信息
     */
    @Override
    public ClassCode selectClassCodeByCode(String code) {
        return classCodeMapper.selectClassCodeByCode(code);
    }

    /**
     * 查询激活码列表
     *
     * @param classCode 激活码信息
     * @return 激活码集合
     */
    @Override
    public List<ClassCode> selectClassCodeList(ClassCode classCode) {
        return classCodeMapper.selectClassCodeList(classCode);
    }

    /**
     * 新增激活码
     *
     * @param classCode 激活码信息
     * @return 结果
     */
    @Override
    public int insertClassCode(ClassCode classCode) {
        return classCodeMapper.insertClassCode(classCode);
    }

    /**
     * 修改激活码
     *
     * @param classCode 激活码信息
     * @return 结果
     */
    @Override
    public int updateClassCode(ClassCode classCode) {
        return classCodeMapper.updateClassCode(classCode);
    }

    /**
     * 删除激活码信息
     *
     * @param classCodeId 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassCodeById(Long classCodeId) {
        return classCodeMapper.deleteClassCodeById(classCodeId);
    }

    @Override
    public boolean checkHasActivateByClassId(Long orgClassId) {
        OrgClass orgClass = orgClassMapper.selectOrgClassById(orgClassId);
        if (orgClass != null) {
            ClassCode classCode = new ClassCode();
            classCode.setClassCode(orgClass.getClassCode());
            classCode.setDelFlag(Constants.NO);
            classCode.setActivateFlag(Constants.YES);
            List<ClassCode> classCodes = classCodeMapper.selectClassCodeList(classCode);
            return classCodes != null && !classCodes.isEmpty();
        }
        return false;
    }
}
