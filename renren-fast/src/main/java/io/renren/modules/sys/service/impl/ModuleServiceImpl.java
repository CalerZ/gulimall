package io.renren.modules.sys.service.impl;

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.ModuleDao;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ModuleService;
import io.renren.modules.sys.vo.ModuleEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("moduleService")
public class ModuleServiceImpl extends ServiceImpl<ModuleDao, ModuleEntity> implements ModuleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModuleEntity> page = this.page(
                new Query<ModuleEntity>().getPage(params),
                new QueryWrapper<ModuleEntity>().orderByAsc("sort")
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(PageForm<ModuleEntity> form) {
        IPage<ModuleEntity> page = this.page(
                new Query<ModuleEntity>().getPage(PageUtils.convert(form)),
                new QueryWrapper<ModuleEntity>().orderByAsc("sort")
        );

        List<ModuleEntityVo> moduleEntityVos = page.getRecords().stream().map(item -> {
            ModuleEntityVo moduleEntityVo = new ModuleEntityVo();
            BeanUtils.copyProperties(item, moduleEntityVo);
            return moduleEntityVo;
        }).collect(Collectors.toList());
        Page<ModuleEntityVo> entityVoPage = new Page<>();
        BeanUtils.copyProperties(page,entityVoPage);
        entityVoPage.setRecords(moduleEntityVos);
        return new PageUtils(entityVoPage);
    }

}
