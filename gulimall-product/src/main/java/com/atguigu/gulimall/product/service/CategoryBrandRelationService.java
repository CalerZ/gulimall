package com.atguigu.gulimall.product.service;

import com.atguigu.common.utils.PageForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 15:02:25
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(PageForm form, Long brandId);

    Boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation);
}

