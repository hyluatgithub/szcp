package com.zkcm.szcp.project.cell3d.service;

import com.zkcm.szcp.project.cell3d.domain.RenewLog;

import java.util.List;

/**
 * 续费记录 服务层
 *
 * @author hylu
 */
public interface IRenewLogService {

    /**
     * 查询续费记录列表
     *
     * @param renewLog 续费记录信息
     * @return 续费记录集合
     */
    List<RenewLog> selectRenewLogList(RenewLog renewLog);

    /**
     * 查询续费记录信息
     *
     * @param renewLogId 续费记录ID
     * @return 续费记录信息
     */
    RenewLog selectRenewLogById(Long renewLogId);


    /**
     * 新增续费记录
     *
     * @param renewLog 续费记录信息
     * @return 结果
     */
    int insertRenewLog(RenewLog renewLog);

}
