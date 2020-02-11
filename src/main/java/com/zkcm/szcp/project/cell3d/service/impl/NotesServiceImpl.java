package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.project.cell3d.domain.Notes;
import com.zkcm.szcp.project.cell3d.mapper.NotesMapper;
import com.zkcm.szcp.project.cell3d.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人笔记 服务层实现
 *
 * @author hylu
 */
@Service
public class NotesServiceImpl implements INotesService {
    @Autowired
    private NotesMapper notesMapper;

    /**
     * 查询个人笔记信息
     *
     * @param notesId 个人笔记ID
     * @return 个人笔记信息
     */
    @Override
    public Notes selectNotesById(Long notesId) {
        Notes notes = new Notes();
        notes.setId(notesId);
        return notesMapper.selectNotes(notes);
    }

    /**
     * 查询个人笔记列表
     *
     * @param notes 个人笔记信息
     * @return 个人笔记集合
     */
    @Override
    public List<Notes> selectNotesList(Notes notes) {
        return notesMapper.selectNotesList(notes);
    }

    /**
     * 新增个人笔记
     *
     * @param notes 个人笔记信息
     * @return 结果
     */
    @Override
    public int insertNotes(Notes notes) {
        return notesMapper.insertNotes(notes);
    }

    /**
     * 修改个人笔记
     *
     * @param notes 个人笔记信息
     * @return 结果
     */
    @Override
    public int updateNotes(Notes notes) {
        return notesMapper.updateNotes(notes);
    }

}
