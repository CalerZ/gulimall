package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.vo.AttrEntityVo;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrDao;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public Boolean save(AttrEntityVo attrEntityVo) {
        if (attrEntityVo!=null) {
            AttrEntity entity = attrEntityVo.buildEntity();
            this.save(entity);
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateById(AttrEntityVo attrEntityVo) {
        if (attrEntityVo!=null) {
            AttrEntity entity = attrEntityVo.buildEntity();
            this.updateById(entity);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public PageUtils queryPage(PageForm<AttrEntityVo> form) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(PageUtils.convert(form)),
                new QueryWrapper<AttrEntity>()
        );
        return new PageUtils(page);
    }

}
