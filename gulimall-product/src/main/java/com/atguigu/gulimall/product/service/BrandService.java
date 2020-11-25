package com.atguigu.gulimall.product.service;

import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.vo.BrandEntityVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateStatus(Long brandId, Integer status);

    PageUtils queryPage(PageForm form);

    boolean save(BrandEntityVo brand);

    boolean updateById(BrandEntityVo brand);

    boolean updateByIdDetail(BrandEntityVo brand);
}

