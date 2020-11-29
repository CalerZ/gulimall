package io.renren.modules.sys.controller;

import com.atguigu.common.utils.PageForm;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
import io.renren.modules.sys.entity.ExceptionConfigEntity;
import io.renren.modules.sys.entity.ModuleEntity;
import io.renren.modules.sys.service.ExceptionConfigService;
import io.renren.modules.sys.service.ModuleService;
import io.renren.modules.sys.vo.ExceptionConfigEntityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
@Api(value = "ExceptionConfigController",tags = {"系统异常配置接口"})
@RestController
@RequestMapping("sys/exceptionconfig")
public class ExceptionConfigController {
    @Autowired
    private ExceptionConfigService exceptionConfigService;

    @Autowired
    private ModuleService moduleService;

    /**
     * 分页列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "异常配置分页列表")
    //@RequiresPermissions("sys:exceptionconfig:list")
    public R list(@RequestBody PageForm<ExceptionConfigEntityVo> form){
        PageUtils page = exceptionConfigService.queryPage(form);


        return R.ok().putData(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID获取异常配置信息")
    //@RequiresPermissions("sys:exceptionconfig:info")
    public R info(@PathVariable("id") Long id){
		ExceptionConfigEntity exceptionConfig = exceptionConfigService.getById(id);
        return R.ok().putData(exceptionConfig);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存异常配置信息")
    //@RequiresPermissions("sys:exceptionconfig:save")
    public R save(@RequestBody ExceptionConfigEntity exceptionConfig){
        return R.ok().putData(exceptionConfigService.saveDetail(exceptionConfig));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改异常配置信息")
    //@RequiresPermissions("sys:exceptionconfig:update")
    public R update(@RequestBody ExceptionConfigEntity exceptionConfig){
        return R.ok().putData(exceptionConfigService.updateById(exceptionConfig));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("sys:exceptionconfig:delete")
    public R delete(@RequestBody Long[] ids){
        return R.ok().putData(exceptionConfigService.removeByIds(Arrays.asList(ids)));
    }

}
