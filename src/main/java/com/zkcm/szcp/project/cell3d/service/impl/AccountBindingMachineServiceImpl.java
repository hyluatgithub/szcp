package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.project.cell3d.domain.AccountBindingMachine;
import com.zkcm.szcp.project.cell3d.mapper.AccountBindingMachineMapper;
import com.zkcm.szcp.project.cell3d.service.IAccountBindingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author admin
 */
@Service
public class AccountBindingMachineServiceImpl implements IAccountBindingMachineService {
    @Autowired
    private AccountBindingMachineMapper accountBindingMachineMapper;


    /**
     * 查询激活码绑定设备列表
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 激活码绑定设备集合
     */
    @Override
    public List<AccountBindingMachine> selectAccountBindingMachineList(AccountBindingMachine accountBindingMachine) {
        return accountBindingMachineMapper.selectAccountBindingMachineList(accountBindingMachine);
    }

    /**
     * 新增激活码绑定设备
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 结果
     */
    @Override
    public int insertAccountBindingMachine(AccountBindingMachine accountBindingMachine) {
        return accountBindingMachineMapper.insertAccountBindingMachine(accountBindingMachine);
    }

    /**
     * 修改激活码绑定设备
     *
     * @param accountBindingMachine 激活码绑定设备信息
     * @return 结果
     */
    @Override
    public int updateAccountBindingMachine(AccountBindingMachine accountBindingMachine) {
        return accountBindingMachineMapper.updateAccountBindingMachine(accountBindingMachine);
    }

    @Override
    public AccountBindingMachine getAccountBindingMachineById(Long id) {
        return accountBindingMachineMapper.getAccountBindingMachineById(id);
    }
}
