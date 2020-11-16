package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Caler_赵康乐
 * @create 2020-10-21 16:34
 * @description :
 */
@FeignClient("gulimall-coupon")
public interface MemberCouponService {
    @GetMapping("/coupon/coupon/getcoupon")
    public R getcoupon();
}
