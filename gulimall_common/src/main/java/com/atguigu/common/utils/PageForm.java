package com.atguigu.common.utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Caler_赵康乐
 * @create 2020-11-22 11:50
 * @description :分页查询 前端---参数传递的包装类--后端
 */
@Data
@ApiModel(value = "分页表单实体类",description = "分页表单包装实体类")
public class PageForm<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数",name = "pageSize")
    private Integer pageSize=10;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数",name = "currPage")
    private Integer currPage=1;


    //TODO  封装查询包装类

    /**
     * 分页查询对应实体类
     */
    @ApiModelProperty(value = "分页查询对应实体类",name = "entityVo",notes = "")
    private T entityVo ;

    /**
     * 参数列表
     */
    @ApiModelProperty(value = "参数列表",name = "params",notes = "params为键值对形式")
    private Map<String, String> params = new HashMap<String, String>();



}
