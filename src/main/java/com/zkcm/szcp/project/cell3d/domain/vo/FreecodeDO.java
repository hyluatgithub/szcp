package com.zkcm.szcp.project.cell3d.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 免费激活码
 *
 * @author hylu
 */
public class FreecodeDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String activateInvalidTime;
    /**
     * 最大激活数量
     */
    private int maxActivate;

    public String getActivateInvalidTime() {
        return activateInvalidTime;
    }

    public void setActivateInvalidTime(String activateInvalidTime) {
        this.activateInvalidTime = activateInvalidTime;
    }

    public int getMaxActivate() {
        return maxActivate;
    }

    public void setMaxActivate(int maxActivate) {
        this.maxActivate = maxActivate;
    }
}
