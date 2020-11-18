package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;
import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 商品三级分类
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotBlank
	private String name;
	/**
	 * 父分类id
	 */
	@NotNull
	private Long parentCid;
	/**
	 * 层级
	 */
	@Min(value = 1L,message = "不能小于1")
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@TableLogic(value = "0",delval = "1")
	@Pattern(regexp = "/^[0-1]$/",message = "状态码不正确")
	private Integer showStatus;
	/**
	 * 排序
	 */
	@Min(value = 0,message = "排序数值不正确")
	private Integer sort;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 计量单位
	 */
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

	@TableField(exist = false)
	private List<CategoryEntity> children;

}
