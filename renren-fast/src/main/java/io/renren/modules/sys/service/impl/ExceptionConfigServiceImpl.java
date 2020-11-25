package io.renren.modules.sys.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.ExceptionConfigDao;
import io.renren.modules.sys.entity.ExceptionConfigEntity;
import io.renren.modules.sys.service.ExceptionConfigService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("exceptionConfigService")
public class ExceptionConfigServiceImpl extends ServiceImpl<ExceptionConfigDao, ExceptionConfigEntity> implements ExceptionConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExceptionConfigEntity> page = this.page(
                new Query<ExceptionConfigEntity>().getPage(params),
                new QueryWrapper<ExceptionConfigEntity>()
        );

        return new PageUtils(page);
    }

}
