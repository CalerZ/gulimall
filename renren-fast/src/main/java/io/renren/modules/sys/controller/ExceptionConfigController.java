package io.renren.modules.sys.controller;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
import io.renren.modules.sys.entity.ExceptionConfigEntity;
import io.renren.modules.sys.service.ExceptionConfigService;
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
@RequestMapping("sys/exceptionconfig")
public class ExceptionConfigController {
    @Autowired
    private ExceptionConfigService exceptionConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:exceptionconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = exceptionConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:exceptionconfig:info")
    public R info(@PathVariable("id") Long id){
		ExceptionConfigEntity exceptionConfig = exceptionConfigService.getById(id);

        return R.ok().put("exceptionConfig", exceptionConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:exceptionconfig:save")
    public R save(@RequestBody ExceptionConfigEntity exceptionConfig){
		exceptionConfigService.save(exceptionConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:exceptionconfig:update")
    public R update(@RequestBody ExceptionConfigEntity exceptionConfig){
		exceptionConfigService.updateById(exceptionConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:exceptionconfig:delete")
    public R delete(@RequestBody Long[] ids){
		exceptionConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
