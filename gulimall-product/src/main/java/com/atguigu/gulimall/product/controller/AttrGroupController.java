package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

/**
 * import org.apache.shiro.authz.annotation.RequiresPermissions;
 */

import com.atguigu.common.utils.PageForm;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.AttrGroupEntityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;


/**
 * 属性分组
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:49:05
 */
@Api(value = "AttrGroupController",tags = "属性分组接口")
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页查询所有属性分组列表",tags = {""},notes = "")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrGroupService.queryPage(params);

        return R.ok().putData(page);
    }

    /**
     * 所属分类的属性分组列表
     */
    @GetMapping("/list/{catId}")
    @ApiOperation(value = "根据分类ID获取属性分组信息",tags = {""},notes = "")
    //@RequiresPermissions("product:attrgroup:list")
    public R listPage(@RequestBody PageForm<AttrGroupEntity> params, @PathVariable Long catId) {
        PageUtils page = attrGroupService.queryPage(params, catId);
        return R.ok().putData(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    @ApiOperation(value = "根据属性分组ID获取属性分组信息",tags = {""},notes = "")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long[] catelogPath = categoryService.findCatelogPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(catelogPath);
        return R.ok().putData(attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存属性分组信息",tags = {""},notes = "")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntityVo attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改属性分组信息",tags = {""},notes = "")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除属性分组信息",tags = {""},notes = "")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
