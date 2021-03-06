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
@TableName("sys_module")
public class ModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 模块名称
	 */
	private String name;
	/**
	 * 模块标题
	 */
	private String title;
	/**
	 * 模块标识
	 */
	private String tag;
	/**
	 * 描述
	 */
	private String descriptions;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 是否删除
	 */
	@TableLogic(value = "0",delval = "1")
	private Integer isDelete;

}
