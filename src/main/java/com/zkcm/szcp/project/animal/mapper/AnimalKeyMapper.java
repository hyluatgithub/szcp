package com.zkcm.szcp.project.animal.mapper;


import com.zkcm.szcp.project.animal.domain.AnimalKey;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 激活码 数据层
 *
 * @author hylu
 */
public interface AnimalKeyMapper {
    /**
     * 校验激活码的有效性
     *
     * @param key
     * @return
     */
    AnimalKey verifyKey(@RequestParam("key") String key);

}
