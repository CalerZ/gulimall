package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
