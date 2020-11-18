package com.atguigu.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> categoryEntities = this.list();

        return categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid()==0)
                .map(categoryEntity->{
                    categoryEntity.setChildren(getChildren(categoryEntity,categoryEntities));
                    return categoryEntity;
                })
                .sorted((ce1,ce2) -> (ce1.getSort()==null?0:ce1.getSort())-(ce2.getSort()==null?0:ce2.getSort()))
                .collect(Collectors.toList());

    }

    private List<CategoryEntity> getChildren(CategoryEntity menu, List<CategoryEntity> categoryEntities) {

       return categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid()==menu.getCatId())
                .map(categoryEntity->{
                   categoryEntity.setChildren(getChildren(categoryEntity,categoryEntities));
                   return categoryEntity;
                })
                .sorted((ce1,ce2) -> (ce1.getSort()==null?0:ce1.getSort())-(ce2.getSort()==null?0:ce2.getSort()))
                .collect(Collectors.toList());



    }


}
