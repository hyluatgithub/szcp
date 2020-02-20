package com.zkcm.szcp.project.animal.controller;

import cn.hutool.core.date.DateUnit;
import com.zkcm.szcp.common.utils.DateUtils;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.project.animal.domain.AnimalKey;
import com.zkcm.szcp.project.animal.service.IAnimalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 移动端
 *
 * @author admin
 */
@RestController
@RequestMapping("/mobile/animal")
public class AnimalKeyController extends BaseController {
    @Autowired
    private IAnimalKeyService animalKeyService;

    /**
     * 获取当前时间
     */
    @GetMapping("/now")
    public AjaxResult now() {
        return AjaxResult.success("current dateTime", DateUtils.dateTimeNow("yyyy-MM-dd HH:mm"));
    }

    /**
     * 激活码激活
     */
    @GetMapping("/activationKey")
    public AjaxResult activationKey(String key) {
        AnimalKey animalKey = animalKeyService.activeKey(key);
        // 未失效，且激活次数为0的激活码可以激活（可激活次数后期可能会变更）
        Boolean canActivation = animalKey != null && animalKey.getLimitDate().after(new Date()) && animalKey.getActivationTimes() < 1;
        if (canActivation) {
            //TODO 激活次数加一
            return AjaxResult.success("Activation success", animalKey);
        } else {
            return AjaxResult.error("Invalid or expired activation code");
        }
    }

    /**
     * 激活码校验
     */
    @GetMapping("/verifyKey")
    public AjaxResult activate(String key) {
        int result = animalKeyService.verifyKey(key);
        if (result > 0) {
            return AjaxResult.success("Activation code available");
        } else {
            return AjaxResult.error("Invalid or expired activation code");
        }
    }


}
