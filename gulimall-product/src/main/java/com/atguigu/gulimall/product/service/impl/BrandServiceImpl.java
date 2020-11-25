package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.vo.BrandEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.BrandDao;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateStatus(Long brandId, Integer status) {
        if(brandId==null||status==null){
            return false;
        }else{
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setBrandId(brandId);
            brandEntity.setShowStatus(status);
            this.updateById(brandEntity);
            return true;
        }

    }

    @Override
    public PageUtils queryPage(PageForm form) {
        Map<String,String> params = form.getParams();
        String key = params.get("key");
        QueryWrapper<BrandEntity> queryWrapper =null;
        if(!StringUtils.isEmpty(key)){
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("brand_id",key).and((obj)->{
                obj.like("NAME",key).or().like("descript",key);
            });
        }else{
            queryWrapper = new QueryWrapper<>();
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(PageUtils.convert(form)),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean save(BrandEntityVo brand) {
        if (brand!=null) {
            this.save(brand.buildEntity());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateById(BrandEntityVo brand) {
        if (brand!=null) {
            BrandEntity brandEntity = new BrandEntity();
            BeanUtils.copyProperties(brand,brandEntity);
            this.updateById(brandEntity);
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateByIdDetail(BrandEntityVo brand) {
        if (brand!=null) {
            this.updateById(brand.buildEntity());
            //TODO 更新关联表品牌名称 规定到底使用service 还是 dao
            categoryBrandRelationDao.updateBrandNameByBrandID(brand.getBrandId(),brand.getName());
//            //TODO 更新关联表分类名称
//            categoryBrandRelationDao.updateCategoryNameByCategoryID();
            //TODO 更新其他关联设置
            return true;
        }else {
            return false;
        }
    }

}
