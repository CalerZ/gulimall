package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.renren.modules.sys.entity.ModuleEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Caler_赵康乐
 * @create 2020-11-26 10:16
 * @description :
 */
@Data
public class ExceptionConfigEntityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 短编码
     */
    private String shortCode;
    /**
     * 完整异常编码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;
    /**
     * 归属模块
     */
    private Long moduleId;

    private String moduleName;
}
