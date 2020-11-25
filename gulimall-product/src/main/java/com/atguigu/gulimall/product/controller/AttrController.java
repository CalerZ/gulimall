package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* import org.apache.shiro.authz.annotation.RequiresPermissions;
*/

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.vo.AttrEntityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品属性
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:49:05
 */
@Api(value = "AttrController",tags = {"商品属性"})
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestBody PageForm<AttrEntityVo> form){
        PageUtils page = attrService.queryPage(form);
        return R.ok().putData(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取所有属性")
    //@RequiresPermissions("product:attr:list")
    public R list(){
        List<AttrEntity> entities = attrService.list();
        return R.ok().putData(entities);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    @ApiOperation(value = "根据属性ID获取属性信息")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);
        return R.ok().putData(attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存属性信息")
    //@RequiresPermissions("product:attr:save")
    public R save(@Validated(value = SaveGroup.class) @RequestBody AttrEntityVo attrEntityVo){
        return R.ok().putData(attrService.save(attrEntityVo));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "保存属性信息")
    //@RequiresPermissions("product:attr:update")
    public R update(@Validated(value = UpdateGroup.class) @RequestBody AttrEntityVo attrEntityVo){
        return R.ok().putData(attrService.updateById(attrEntityVo));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
        return R.ok().putData(attrService.removeByIds(Arrays.asList(attrIds)));
    }

}
