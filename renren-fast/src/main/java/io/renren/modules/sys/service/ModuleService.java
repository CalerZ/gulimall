package io.renren.modules.sys.service;

import com.atguigu.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.ModuleEntity;

import java.util.Map;

/**
 *
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 20:44:11
 */
public interface ModuleService extends IService<ModuleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

