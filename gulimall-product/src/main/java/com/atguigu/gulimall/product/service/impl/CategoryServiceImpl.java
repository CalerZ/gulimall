package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.vo.CategoryEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;

import javax.validation.constraints.NotNull;


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
    public List<CategoryEntityVo> listWithTree() {

        List<CategoryEntity> categoryEntities = this.list();
        List<CategoryEntityVo> categoryEntityVos = categoryEntities.stream().map(categoryEntity -> {
            CategoryEntityVo categoryEntityVo = new CategoryEntityVo();
            BeanUtils.copyProperties(categoryEntity, categoryEntityVo);
            return categoryEntityVo;
        }).collect(Collectors.toList());

        return categoryEntityVos.stream()
                .filter(categoryEntityVo -> categoryEntityVo.getParentCid().equals(0L))
                .map(categoryEntityVo -> {
                    categoryEntityVo.setChildren(getChildren(categoryEntityVo, categoryEntityVos));
                    return categoryEntityVo;
                })
                .sorted((ce1, ce2) -> (ce1.getSort() == null ? 0 : ce1.getSort()) - (ce2.getSort() == null ? 0 : ce2.getSort()))
                .collect(Collectors.toList());

    }

    @Override
    public Boolean update(List<CategoryEntity> categorys) {
        if (categorys.size() == 0) {
            return false;
        } else {
            categorys.forEach(category -> {
                this.updateById(category);
            });
            return true;
        }

    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        findPath(catelogId, path);
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }

    @Override
    public Boolean save(CategoryEntityVo categoryEntityVo) {
        if (categoryEntityVo != null) {
            this.save(categoryEntityVo.buildEntity());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateById(CategoryEntityVo categoryEntityVo) {
        if (categoryEntityVo != null) {
            this.updateById(categoryEntityVo.buildEntity());
            return true;
        } else {
            return false;
        }
    }

    private void findPath(Long catelogId, List<Long> path) {
        path.add(catelogId);
        CategoryEntity categoryEntity = this.getById(catelogId);
        @NotNull Long parentCid = categoryEntity.getParentCid();
        if (parentCid != 0) {
            findPath(parentCid, path);
        }
    }

    private List<CategoryEntityVo> getChildren(CategoryEntityVo menu, List<CategoryEntityVo> categoryEntityVos) {

        List<CategoryEntityVo> list = categoryEntityVos.stream()
                .filter(categoryEntityVo -> categoryEntityVo.getParentCid().equals(menu.getCatId()))
                .map(categoryEntityVo -> {
                    categoryEntityVo.setChildren(getChildren(categoryEntityVo, categoryEntityVos));
                    return categoryEntityVo;
                })
                .sorted((ce1, ce2) -> (ce1.getSort() == null ? 0 : ce1.getSort()) - (ce2.getSort() == null ? 0 : ce2.getSort()))
                .collect(Collectors.toList());
        return list;


    }


}
