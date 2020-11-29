package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 20:44:11
 */
@Data
@TableName("sys_exception_config")
public class ExceptionConfigEntity implements Serializable {
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
	/**
	 * 是否删除
	 */
	@TableLogic(value = "0",delval = "1")
	private Integer isDelete;

}
