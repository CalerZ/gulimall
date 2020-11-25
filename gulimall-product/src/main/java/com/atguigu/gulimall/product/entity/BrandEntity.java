package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 品牌
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:28:01
 */
@Data
@TableName("pms_brand")
@ApiModel(value = "品牌实体类",description = "品牌数据库表对应实体类")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@ApiModelProperty(value = "品牌id",name = "brandId")
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank
	private String name;
	/**
	 * 品牌logo地址
	 */
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@Range(min = 0,max = 1,message = "状态码不正确")
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@Pattern(regexp = "^[a-zA-Z]$",message = "首字符错误")
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(value = 0,message = "排序不正确")
	private Integer sort;

	/**
	 * 是否删除
	 */
	@TableLogic(value = "0",delval = "1")
	private Integer isDelete;

}
