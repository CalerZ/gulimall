package com.atguigu.gulimall.product.vo;

import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Caler_赵康乐
 * @create 2020-11-23 21:14
 * @description :
 */
@Data
@ApiModel(value = "分类视图类",description = "前后端交互的分类视图类")
public class CategoryEntityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @ApiModelProperty(name = "分类id")
    @NotEmpty(message = "更新时ID不能为空",groups = {UpdateGroup.class})
    private Long catId;
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "品牌名称")
    @NotEmpty(message = "品牌名称不能为空",groups = {UpdateGroup.class, SaveGroup.class})
    private String name;
    /**
     * 父分类id
     */
    @ApiModelProperty(name = "父分类id")
    @NotEmpty(message = "父ID不能为空",groups = {SaveGroup.class})
    private Long parentCid;
    /**
     * 层级
     */
    @ApiModelProperty(name = "层级")
    @Range(min = 0,max = 3,message = "层级数值不正确",groups = {SaveGroup.class})
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    @ApiModelProperty(name = "是否显示")
    @Range(min = 0,max = 1,message = "状态值不正确",groups = {UpdateGroup.class, SaveGroup.class})
    private Integer showStatus;
    /**
     * 排序
     */
    @ApiModelProperty(name = "排序")
    @Min(value = 0,message = "排序数值不正确")
    private Integer sort;
    /**
     * 图标地址
     */
    @ApiModelProperty(name = "图标地址")
    private String icon;
    /**
     * 计量单位
     */
    @ApiModelProperty(name = "计量单位")
    @NotEmpty(message = "计量单位不能为空",groups = {UpdateGroup.class,SaveGroup.class})
    private String productUnit;
    /**
     * 商品数量
     */
    @ApiModelProperty(name = "商品数量")
    @Min(value = 0)
    private Integer productCount;

    /**
     * 是否删除
     */
    @ApiModelProperty(name = "是否删除")
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;

    @ApiModelProperty(name = "子分类")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<CategoryEntityVo> children;

    public CategoryEntity buildEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(this,categoryEntity);
        return categoryEntity;
    }
}
