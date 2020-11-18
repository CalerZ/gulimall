package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-21 10:36:49
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
