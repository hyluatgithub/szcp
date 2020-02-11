package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.project.cell3d.domain.RenewLog;
import com.zkcm.szcp.project.cell3d.mapper.RenewLogMapper;
import com.zkcm.szcp.project.cell3d.service.IRenewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 续费记录 服务层实现
 *
 * @author hylu
 */
@Service
public class RenewLogServiceImpl implements IRenewLogService {
    @Autowired
    private RenewLogMapper renewLogMapper;

    /**
     * 查询续费记录信息
     *
     * @param renewLogId 续费记录ID
     * @return 续费记录信息
     */
    @Override
    public RenewLog selectRenewLogById(Long renewLogId) {
        RenewLog renewLog = new RenewLog();
        renewLog.setId(renewLogId);
        return renewLogMapper.selectRenewLog(renewLog);
    }

    /**
     * 查询续费记录列表
     *
     * @param renewLog 续费记录信息
     * @return 续费记录集合
     */
    @Override
    public List<RenewLog> selectRenewLogList(RenewLog renewLog) {
        return renewLogMapper.selectRenewLogList(renewLog);
    }

    /**
     * 新增续费记录
     *
     * @param renewLog 续费记录信息
     * @return 结果
     */
    @Override
    public int insertRenewLog(RenewLog renewLog) {
        return renewLogMapper.insertRenewLog(renewLog);
    }

}
