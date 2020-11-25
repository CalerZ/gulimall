package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* import org.apache.shiro.authz.annotation.RequiresPermissions;
*/

import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.vo.CategoryEntityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;

import javax.validation.Valid;


/**
 * 商品三级分类
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:49:05
 */
@Api(value = "CategoryController",tags = "商品三级分类")
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list/tree")
    @ApiOperation(value = "获取树形分类",tags = {""},notes = "")
    //@RequiresPermissions("product:category:list")
    public R listTree(){
        List<CategoryEntityVo> categoryEntities = categoryService.listWithTree();
        return R.ok().putData(categoryEntities);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取所有分类",tags = {""},notes = "")
    //@RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);
        return R.ok().putData(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    @ApiOperation(value = "根据分类ID获取分类信息",tags = {""},notes = "")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().putData(category);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存分类信息",tags = {""},notes = "")
    @PostMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@Validated(value = SaveGroup.class) @RequestBody CategoryEntityVo category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */

    @PostMapping("/update")
    @ApiOperation(value = "修改分类信息",tags = {""},notes = "")
    //@RequiresPermissions("product:category:update")
    public R update(@Validated(value = UpdateGroup.class) @RequestBody CategoryEntityVo category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update/muti")
    @ApiOperation(value = "批量修改分类信息",tags = {""},notes = "")
    //@RequiresPermissions("product:category:update")
    public R update(@Valid @RequestBody List<CategoryEntity>  category){
        categoryService.update(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除分类信息",tags = {""},notes = "")
    @PostMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
