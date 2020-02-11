package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.common.constant.UserConstants;
import com.zkcm.szcp.common.utils.StringUtils;
import com.zkcm.szcp.project.cell3d.domain.OrgClass;
import com.zkcm.szcp.project.cell3d.mapper.ClassCodeMapper;
import com.zkcm.szcp.project.cell3d.mapper.OrgClassMapper;
import com.zkcm.szcp.project.cell3d.service.IOrgClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级 服务层实现
 *
 * @author hylu
 */
@Service
public class OrgClassServiceImpl implements IOrgClassService {
    @Autowired
    private OrgClassMapper orgClassMapper;
    @Autowired
    private ClassCodeMapper classCodeMapper;

    /**
     * 查询班级信息
     *
     * @param orgClassId 班级ID
     * @return 班级信息
     */
    @Override
    public OrgClass selectOrgClassById(Long orgClassId) {
        return orgClassMapper.selectOrgClassById(orgClassId);
    }

    /**
     * 查询班级列表
     *
     * @param orgClass 班级信息
     * @return 班级集合
     */
    @Override
    public List<OrgClass> selectOrgClassList(OrgClass orgClass) {
        return orgClassMapper.selectOrgClassList(orgClass);
    }

    /**
     * 新增班级
     *
     * @param orgClass 班级信息
     * @return 结果
     */
    @Override
    public int saveOrgClass(OrgClass orgClass) {
        if (orgClass == null) {
            return -1;
        }
        if (orgClass.getId() == null) {
            return orgClassMapper.insertOrgClass(orgClass);
        } else {
            return orgClassMapper.updateOrgClass(orgClass);
        }
    }

    @Override
    public int updateActivateNumber(String classCode) {
        OrgClass orgClass = orgClassMapper.selectOrgClassByCode(classCode);
        if (orgClass != null) {
            int activate = orgClass.getActivate();
            int maxActivate = orgClass.getMaxActivate();
            //激活数量是否达到上限
            if (maxActivate - activate < 1) {
                return -1;
            }
            orgClass.setActivate(activate + 1);
            return orgClassMapper.updateOrgClass(orgClass);
        }
        return 0;
    }

    /**
     * 删除班级信息
     *
     * @param orgClassId 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrgClassById(Long orgClassId) {
        //删除班级下的所有激活码
        OrgClass orgClass = orgClassMapper.selectOrgClassById(orgClassId);
        if (orgClass != null) {
            classCodeMapper.deleteClassCodeByClassCode(orgClass.getClassCode());
        }
        return orgClassMapper.deleteOrgClassById(orgClassId);
    }

    /**
     * 校验班级键名是否唯一
     *
     * @param orgClass 班级信息
     * @return 结果
     */
    @Override
    public String checkOrgClassKeyUnique(OrgClass orgClass) {
        Long orgId = StringUtils.isNull(orgClass.getId()) ? -1L : orgClass.getId();
        OrgClass info = orgClassMapper.checkOrgClassKeyUnique(orgClass.getOrgCode());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != orgId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询组织机构已分配的所有激活码额度
     *
     * @param orgCode 班级信息
     * @return 结果
     */
    @Override
    public int countActivateSum(String orgCode) {
        return orgClassMapper.countActivateSum(orgCode);
    }

}
