package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.AccountBindingMachine;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 激活码绑定设备 数据层
 *
 * @author hylu
 */
public interface AccountBindingMachineMapper {
    /**
     * 查询激活码绑定设备列表
     *
     * @param orgAccount 激活码绑定设备信息
     * @return 激活码绑定设备集合
     */
    public List<AccountBindingMachine> selectAccountBindingMachineList(AccountBindingMachine orgAccount);

    /**
     * 新增激活码绑定设备
     *
     * @param orgAccount 激活码绑定设备信息
     * @return 结果
     */
    public int insertAccountBindingMachine(AccountBindingMachine orgAccount);

    /**
     * 修改激活码绑定设备
     *
     * @param orgAccount 激活码绑定设备信息
     * @return 结果
     */
    public int updateAccountBindingMachine(AccountBindingMachine orgAccount);

    AccountBindingMachine getAccountBindingMachineById(@RequestParam("id") Long id);
}
