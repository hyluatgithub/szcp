package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 激活码绑定设备表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-12-2
 */
public class AccountBindingMachine extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 激活码
     */
    private String code;
    /**
     * 绑定设备名称
     */
    private String machineName;
    /**
     * 绑定设备ip
     */
    private String machineIp;
    /**
     * 逻辑删除：N未删除  Y已删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
