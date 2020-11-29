package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* import org.apache.shiro.authz.annotation.RequiresPermissions;
*/

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.validgroups.SaveGroup;
import com.atguigu.common.validgroups.UpdateGroup;
import com.atguigu.gulimall.product.exception.ErrorStatusCodeException;
import com.atguigu.gulimall.product.validate.BrandValidate;
import com.atguigu.gulimall.product.vo.BrandEntityVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * 品牌
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-10-20 16:49:05
 */
@Api(value = "BrandController",tags = "品牌接口")
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Resource
    private BrandValidate brandValidate;

    /**
     * 分页列表
     */
    @ApiOperation(value = "获取所有品牌分页列表",tags = {""},notes = "")
    @PostMapping("/list")
    //@RequiresPermissions("product:brand:list")
    @ApiResponse(code = 200,message = "success",response = R.class,reference = "BrandEntityVo数组形式")
    public R list(@ApiParam(name = "form",value = "分页查询表单实体",required = true) @RequestBody PageForm<BrandEntityVo> form){
        PageUtils page = brandService.queryPage(form);
        return R.ok().putData(page);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "获取所有品牌列表",tags = {""},notes = "")
    @GetMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(){
        List<BrandEntity> entities = brandService.list();
        return R.ok().putData(entities);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据品牌ID获取品牌信息",tags = {""},notes = "")
    @GetMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);
        return R.ok().putData(brand);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存品牌信息",tags = {""},notes = "")
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated(value = SaveGroup.class) @RequestBody BrandEntityVo brand){
        return R.ok().putData(brandService.save(brand));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "更新品牌信息",tags = {""},notes = "")
    @PostMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated(value = UpdateGroup.class) @RequestBody BrandEntityVo brand){
		//brandService.updateById(brand);
        return R.ok().putData(brandService.updateByIdDetail(brand));
    }

    /**
     * 修改状态
     */
    @ApiOperation(value = "更新品牌状态",tags = {""},notes = "")
    @GetMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R update(@ApiParam(name = "brandId",value = "品牌ID",required = true)
                        @RequestParam(name ="brandId",required = true) Long brandId,
                    @ApiParam(name = "status",value = "状态码",required = true)
                        @RequestParam(name ="status",required = true)  Integer status){

        brandValidate.validateStatus(status);
        //TODO 需要结合redis  验证品牌id是否存在
        return R.ok().putData(brandService.updateStatus(brandId,status));
    }



    /**
     * 删除
     */
    @ApiOperation(value = "删除品牌信息",tags = {""},notes = "")
    @PostMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
        return R.ok().putData(brandService.removeByIds(Arrays.asList(brandIds)));
    }

}
