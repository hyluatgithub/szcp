package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.Notes;

import java.util.List;

/**
 * 个人笔记 数据层
 *
 * @author hylu
 */
public interface NotesMapper {
    /**
     * 查询个人笔记信息
     *
     * @param notes 个人笔记信息
     * @return 个人笔记信息
     */
    public Notes selectNotes(Notes notes);

    /**
     * 查询个人笔记列表
     *
     * @param notes 个人笔记信息
     * @return 个人笔记集合
     */
    public List<Notes> selectNotesList(Notes notes);

    /**
     * 新增个人笔记
     *
     * @param notes 个人笔记信息
     * @return 结果
     */
    public int insertNotes(Notes notes);

    /**
     * 修改个人笔记
     *
     * @param notes 个人笔记信息
     * @return 结果
     */
    public int updateNotes(Notes notes);

}
