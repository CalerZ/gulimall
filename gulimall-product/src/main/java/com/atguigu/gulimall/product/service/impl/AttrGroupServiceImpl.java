package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.Constant;
import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.vo.AttrGroupEntityVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(PageForm form, Long catId) {

        QueryWrapper<AttrGroupEntity> queryWrapper = null;
        if (catId.equals(0L)) {//查询全部
            queryWrapper = new QueryWrapper<AttrGroupEntity>();
        } else {
            Map<String, String> params = form.getParams();
            String key = params.get("key");

            queryWrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catId);
            if (!StringUtils.isEmpty(key)) {
                queryWrapper.and(obj -> {
                    //like 双百分号的like
                    obj.eq("attr_group_id", key).or().like("attr_group_name", key).or().like("descript", key);
                });
            }

        }

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(PageUtils.convert(form)),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public Boolean save(AttrGroupEntityVo attrGroupVo) {
        if (attrGroupVo != null) {
            AttrGroupEntity attrGroupEntity = attrGroupVo.buildEntity();
            this.save(attrGroupEntity);
            return true;
        } else {
            return false;
        }
    }

}
