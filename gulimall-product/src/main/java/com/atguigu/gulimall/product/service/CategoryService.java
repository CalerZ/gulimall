package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.vo.CategoryEntityVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntityVo> listWithTree();

    Boolean update(List<CategoryEntity> category);

    Long[] findCatelogPath(Long catelogId);

    Boolean save(CategoryEntityVo category);

    Boolean updateById(CategoryEntityVo categoryEntityVo);
}

