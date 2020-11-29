package io.renren.modules.sys.service;

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.ExceptionConfigEntity;
import io.renren.modules.sys.vo.ExceptionConfigEntityVo;

import java.util.Map;

/**
 *
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 20:44:11
 */
public interface ExceptionConfigService extends IService<ExceptionConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(PageForm<ExceptionConfigEntityVo> form);

    Boolean saveDetail(ExceptionConfigEntity exceptionConfig);
}

