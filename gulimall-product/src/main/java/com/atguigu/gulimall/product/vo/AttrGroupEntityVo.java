package com.atguigu.gulimall.product.vo;

import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 11:32
 * @description :
 */
@Data
@ApiModel(value = "属性视图类",description = "前后端交互的属性视图类")
public class AttrGroupEntityVo {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    @NotEmpty(message = "组名不能为空",groups = {SaveGroup.class, UpdateGroup.class})
    private String attrGroupName;
    /**
     * 排序
     */
    @Min(value = 0,message = "排序值不正确",groups = {SaveGroup.class, UpdateGroup.class})
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    @NotEmpty(message = "所属分类id不能为空",groups = {SaveGroup.class, UpdateGroup.class})
    private Long catelogId;

    /**
     * 所属分类路径id集合
     */
    private Long[] catelogPath;


    /**
     * 将vo对象转为po对象
     * @return
     */
    public AttrGroupEntity buildEntity(){
        AttrGroupEntity entity = new AttrGroupEntity();
        BeanUtils.copyProperties(this,entity);
        return entity;
    }

}
