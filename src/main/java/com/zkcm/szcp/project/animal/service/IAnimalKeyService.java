package com.zkcm.szcp.project.animal.service;

import com.zkcm.szcp.project.animal.domain.AnimalKey;

/**
 * 动物激活码 服务层
 *
 * @author hylu
 */
public interface IAnimalKeyService {

    /**
     * 激活码激活
     *
     * @param key
     * @return 结果
     */
    AnimalKey activeKey(String key);

    /**
     * 校验激活码的有效性
     *
     * @param key
     * @return 结果
     */
    int verifyKey(String key);

}
