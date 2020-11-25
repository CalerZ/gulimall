package com.atguigu.gulimall.product.service;

import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.vo.AttrEntityVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean save(AttrEntityVo attrEntityVo);

    Boolean updateById(AttrEntityVo attrEntityVo);

    PageUtils queryPage(PageForm<AttrEntityVo> form);
}

