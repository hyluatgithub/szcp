package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.RenewLog;

import java.util.List;

/**
 * 续费记录 数据层
 *
 * @author hylu
 */
public interface RenewLogMapper {
    /**
     * 查询续费记录信息
     *
     * @param renewLog 续费信息
     * @return 续费信息
     */
    public RenewLog selectRenewLog(RenewLog renewLog);

    /**
     * 查询续费记录列表
     *
     * @param renewLog 续费信息
     * @return 续费集合
     */
    public List<RenewLog> selectRenewLogList(RenewLog renewLog);

    /**
     * 新增续费记录
     *
     * @param renewLog 续费信息
     * @return 结果
     */
    public int insertRenewLog(RenewLog renewLog);

}
