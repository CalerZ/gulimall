package io.renren.modules.sys.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.ModuleDao;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ModuleService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("moduleService")
public class ModuleServiceImpl extends ServiceImpl<ModuleDao, ModuleEntity> implements ModuleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModuleEntity> page = this.page(
                new Query<ModuleEntity>().getPage(params),
                new QueryWrapper<ModuleEntity>()
        );

        return new PageUtils(page);
    }

}
