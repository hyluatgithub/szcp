package com.zkcm.szcp.project.cell3d.service;


import com.zkcm.szcp.project.cell3d.domain.AccountBindingMachine;

import java.util.List;

/**
 * 激活码绑定设备表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-16 13:45:50
 */
public interface IAccountBindingMachineService {

    /**
     * 查询激活码绑定设备列表
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 激活码绑定设备集合
     */
    List<AccountBindingMachine> selectAccountBindingMachineList(AccountBindingMachine accountBindingMachine);

    /**
     * 新增激活码绑定设备
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 结果
     */
    int insertAccountBindingMachine(AccountBindingMachine accountBindingMachine);

    /**
     * 修改激活码绑定设备
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 结果
     */
    int updateAccountBindingMachine(AccountBindingMachine accountBindingMachine);

    AccountBindingMachine getAccountBindingMachineById(Long id);
}
