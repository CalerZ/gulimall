package io.renren.modules.sys.controller;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RestController
@RequestMapping("sys/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:module:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = moduleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:module:info")
    public R info(@PathVariable("id") Long id){
		ModuleEntity module = moduleService.getById(id);

        return R.ok().put("module", module);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:module:save")
    public R save(@RequestBody ModuleEntity module){
		moduleService.save(module);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:module:update")
    public R update(@RequestBody ModuleEntity module){
		moduleService.updateById(module);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:module:delete")
    public R delete(@RequestBody Long[] ids){
		moduleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
