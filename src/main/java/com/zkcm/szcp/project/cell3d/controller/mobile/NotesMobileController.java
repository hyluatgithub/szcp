package com.zkcm.szcp.project.cell3d.controller.mobile;

import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.utils.StringUtils;
import com.zkcm.szcp.framework.redis.RedisCache;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.Notes;
import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import com.zkcm.szcp.project.cell3d.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 移动端
 *
 * @author admin
 */
@RestController
@RequestMapping("/mobile/notes")
public class NotesMobileController extends BaseController {
    @Autowired
    private INotesService notesService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 获取个人笔记列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HttpServletRequest request, Notes note) {
        OrgAccount user = (OrgAccount) request.getAttribute("user");
        note.setUserMobile(user.getUserMobile());

        startPage();
        List<Notes> list = notesService.selectNotesList(note);
        return getDataTable(list);
    }

    /**
     * 保存个人笔记
     */
    @PostMapping
    public AjaxResult save(HttpServletRequest request, Notes note) {
        OrgAccount user = (OrgAccount) request.getAttribute("user");
        String userMobile = user.getUserMobile();
        //每个知识点的个人笔记 只能存一条
        String points = note.getPoints();
        if (StringUtils.isEmpty(points)) {
            return AjaxResult.error("参数不合法");
        }
        Notes notesDB = this.getNotesByMobile(userMobile, points);
        if (notesDB != null) {
            notesDB.setContent(note.getContent());
            return toAjax(notesService.updateNotes(notesDB));
        } else {
            note.setUserMobile(userMobile);
            note.setDelFlag(Constants.NO);
            note.setCreateTime(new Date());
            return toAjax(notesService.insertNotes(note));
        }
    }

    /**
     * 删除个人笔记
     */
    @DeleteMapping
    public AjaxResult delete(Long noteId) {
        Notes notesDB = notesService.selectNotesById(noteId);
        if (notesDB == null) {
            return AjaxResult.error("不存在的记录");
        }
        notesDB.setDelFlag(Constants.YES);
        return toAjax(notesService.updateNotes(notesDB));
    }


    private Notes getNotesByMobile(String userMobile, String points) {
        Notes notes = new Notes();
        notes.setUserMobile(userMobile);
        notes.setPoints(points);
        notes.setDelFlag(Constants.NO);
        List<Notes> notesDB = notesService.selectNotesList(notes);
        if (notesDB != null && !notesDB.isEmpty()) {
            return notesDB.get(0);
        }
        return null;

    }

}
