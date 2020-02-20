package com.zkcm.szcp.project.animal.service.impl;

import com.alibaba.fastjson.JSON;
import com.zkcm.szcp.project.animal.domain.AnimalKey;
import com.zkcm.szcp.project.animal.mapper.AnimalKeyMapper;
import com.zkcm.szcp.project.animal.service.IAnimalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 个人笔记 服务层实现
 *
 * @author hylu
 */
@Service
public class AnimalKeyServiceImpl implements IAnimalKeyService {
    @Autowired
    private AnimalKeyMapper animalKeyMapper;


    /**
     * 激活码激活
     *
     * @param key
     * @return 结果
     */
    @Override
    public AnimalKey activeKey(String key) {
        return animalKeyMapper.verifyKey(key);
    }

    /**
     * 校验激活码的有效性
     *
     * @param key
     * @return 结果
     */
    @Override
    public int verifyKey(String key) {
        AnimalKey animalKey = animalKeyMapper.verifyKey(key);
        System.out.println("校验激活码：" + key + ",返回数据：\n" + JSON.toJSONString(animalKey));
        if (animalKey != null && animalKey.getLimitDate().after(new Date())) {
            // 有效
            return 1;
        }
        // 无效或过期
        return -1;
    }
}
