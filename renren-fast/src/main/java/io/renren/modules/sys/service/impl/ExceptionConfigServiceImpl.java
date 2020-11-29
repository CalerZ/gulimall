package io.renren.modules.sys.service.impl;

import cn.hutool.core.util.PageUtil;
import com.atguigu.common.utils.PageForm;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.ExceptionConfigDao;
import io.renren.modules.sys.dao.ModuleDao;
import io.renren.modules.sys.entity.ExceptionConfigEntity;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ExceptionConfigService;
import io.renren.modules.sys.vo.ExceptionConfigEntityVo;
import io.renren.modules.sys.vo.ModuleEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("exceptionConfigService")
public class ExceptionConfigServiceImpl extends ServiceImpl<ExceptionConfigDao, ExceptionConfigEntity> implements ExceptionConfigService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExceptionConfigEntity> page = this.page(
                new Query<ExceptionConfigEntity>().getPage(params),
                new QueryWrapper<ExceptionConfigEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(PageForm<ExceptionConfigEntityVo> form) {
        Map<String, String> params = form.getParams();
        String key = params.get("key");
        QueryWrapper<ExceptionConfigEntity> query=new QueryWrapper<ExceptionConfigEntity>().orderByAsc("sort");
        if(!StringUtils.isEmpty(key) ){
            query.eq("id",key).and(obj->{
                obj.or().like("code",key).or().like("message",key);
            });
        }

        IPage<ExceptionConfigEntity> page = this.page(
                new Query<ExceptionConfigEntity>().getPage(PageUtils.convert(form)),
                query
        );

        //将module_id转成module的包装类
        List<ModuleEntity> moduleEntities = moduleDao.selectList(null);
        Map<Long, ModuleEntity> moduleEntityMap = moduleEntities.stream().collect(Collectors.toMap(ModuleEntity::getId, Function.identity(), (key1, key2) -> key2));
        List<ExceptionConfigEntityVo> list = page.getRecords().stream().map(exceptionConfigEntity -> {
            ExceptionConfigEntityVo exceptionConfigEntityVo = new ExceptionConfigEntityVo();
            BeanUtils.copyProperties(exceptionConfigEntity, exceptionConfigEntityVo);
            exceptionConfigEntityVo.setModuleName(moduleEntityMap.get(exceptionConfigEntityVo.getModuleId()).getName());
            return exceptionConfigEntityVo;
        }).collect(Collectors.toList());


        Page<ExceptionConfigEntityVo> entityVoPage = new Page<>();
        BeanUtils.copyProperties(page,entityVoPage);
        entityVoPage.setRecords(list);
        return new PageUtils(entityVoPage);
    }

    @Override
    public Boolean saveDetail(ExceptionConfigEntity exceptionConfig) {
        if(exceptionConfig!=null&&!StringUtils.isEmpty(exceptionConfig.getShortCode()) ){
            Long moduleId = exceptionConfig.getModuleId();
            ModuleEntity moduleEntity = moduleDao.selectById(moduleId);
            if(moduleEntity!=null){
                String code = moduleEntity.getTag()+exceptionConfig.getShortCode();
                if(StringUtils.isEmpty(exceptionConfig.getCode())||!code.equals(exceptionConfig.getCode())){
                    throw new RuntimeException("code计算不正确");
                }
                exceptionConfig.setCode(code);
                this.save(exceptionConfig);
            }
        }
        return false;
    }


}
