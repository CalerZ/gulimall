package com.atguigu.gulimall.product.vo;

import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Caler_赵康乐
 * @create 2020-11-23 21:50
 * @description :
 */
@Data
@ApiModel(value = "品牌视图类",description = "前后端交互的品牌视图类")
public class BrandEntityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id",name = "brandId")
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名称不为空",groups = {UpdateGroup.class, SaveGroup.class})
    @ApiModelProperty(value = "品牌名称",name = "name")
    private String name;
    /**
     * 品牌logo地址
     */
    @ApiModelProperty(value = "品牌logo地址",name = "logo")
    private String logo;
    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍",name = "descript")
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @Range(min = 0,max = 1,message = "状态码不正确")
    @ApiModelProperty(value = "显示状态",name = "showStatus")
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @Pattern(regexp = "^[a-zA-Z]$",message = "首字符错误")
    @ApiModelProperty(value = "检索首字母",name = "firstLetter")
    private String firstLetter;
    /**
     * 排序
     */
    @Min(value = 0,message = "排序不正确")
    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "是否删除",name = "isDelete")
    private Integer isDelete;

    /**
     * 将vo对象转为po对象
     * @return
     */
    public BrandEntity buildEntity(){
        BrandEntity entity = new BrandEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

}
