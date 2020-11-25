package com.atguigu.gulimall.product.vo;

import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 10:21
 * @description :
 */
@Data
@ApiModel(value = "属性视图类",description = "前后端交互的属性视图类")
public class AttrEntityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id",name = "attrId")
    private Long attrId;
    /**
     * 属性名
     */
    @NotEmpty(message = "属性名不能为空",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "属性名",name = "attrName")
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @Range(min = 0,max = 1,message = "检索码不正确",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "是否需要检索",name = "searchType")
    private Integer searchType;
    /**
     * 属性图标
     */
    @ApiModelProperty(value = "属性图标",name = "icon")
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    @ApiModelProperty(value = "可选值列表",name = "valueSelect")
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @Range(min = 0,max = 2,message = "属性类型码不正确",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "属性类型",name = "attrType")
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @Range(min = 0,max = 1,message = "状态码不正确",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "启用状态",name = "enable")
    private Long enable;
    /**
     * 所属分类
     */
    @NotEmpty(message = "所属分类不能为空",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "所属分类",name = "catelogId")
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @Range(min = 0,max = 1,message = "快速展示码不正确",groups = {SaveGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "快速展示",name = "showDesc")
    private Integer showDesc;

    /**
     * 属性分组
     */
    @NotEmpty(message = "属性分组不能为空",groups = {SaveGroup.class})
    @ApiModelProperty(value = "属性分组",name = "attrGroupId")
    private Long attrGroupId;

    /**
     * 将vo对象转为po对象
     * @return
     */
    public AttrEntity buildEntity(){
        AttrEntity entity = new AttrEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

}
