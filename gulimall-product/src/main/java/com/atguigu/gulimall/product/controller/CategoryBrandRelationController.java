package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

/**
* import org.apache.shiro.authz.annotation.RequiresPermissions;
*/

import com.atguigu.common.utils.PageForm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 品牌分类关联
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 15:02:25
 */
@Api(value = "CategoryBrandRelationController",tags = "品牌分类关联")
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 分页列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页查询品牌分类关联信息",tags = {""},notes = "")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 分页列表
     */
    @ApiOperation(value = "根据品牌ID获取分类信息",tags = {""},notes = "")
    @PostMapping("/catelog/list/{brandId}")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R cateloglist(@RequestBody PageForm form, @PathVariable(value = "brandId") Long brandId){
        PageUtils page = categoryBrandRelationService.queryPage(form,brandId);
        return R.ok().putData(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID获取品牌分类关联信息",tags = {""},notes = "")
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);
        return R.ok().putData(categoryBrandRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存品牌分类关联信息",tags = {""},notes = "")
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        return R.ok().putData(categoryBrandRelationService.saveDetail(categoryBrandRelation));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改品牌分类关联信息",tags = {""},notes = "")
    //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        return R.ok().putData(categoryBrandRelationService.updateById(categoryBrandRelation));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除品牌分类关联信息",tags = {""},notes = "")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
        return R.ok().putData(categoryBrandRelationService.removeByIds(Arrays.asList(ids)));
    }

}
