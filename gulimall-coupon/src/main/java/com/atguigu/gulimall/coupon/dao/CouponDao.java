package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-21 10:32:02
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
