package io.renren.modules.sys.controller;

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ModuleService;
import io.renren.modules.sys.vo.ExceptionConfigEntityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * import org.apache.shiro.authz.annotation.RequiresPermissions;
 */


/**
 *
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 20:44:11
 */
@Api(value = "ModuleController",tags = {"系统模块"})
@RestController
@RequestMapping("sys/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页列表")
    //@RequiresPermissions("sys:module:list")
    public R list(@RequestBody PageForm<ModuleEntity> form){
        PageUtils page = moduleService.queryPage(form);
        return R.ok().putData(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有系统模块")
    //@RequiresPermissions("sys:module:list")
    public R list(){
        List<ModuleEntity> list = moduleService.list();
        return R.ok().putData(list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据id获取模块信息")
    //@RequiresPermissions("sys:module:info")
    public R info(@PathVariable("id") Long id){
		ModuleEntity module = moduleService.getById(id);
        return R.ok().putData(module);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存模块信息")
    //@RequiresPermissions("sys:module:save")
    public R save(@RequestBody ModuleEntity module){
        return R.ok().putData(moduleService.save(module));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改模块信息")
    //@RequiresPermissions("sys:module:update")
    public R update(@RequestBody ModuleEntity module){
        return R.ok().putData(moduleService.updateById(module));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("sys:module:delete")
    public R delete(@RequestBody Long[] ids){
        return R.ok().putData(moduleService.removeByIds(Arrays.asList(ids)));
    }

}
