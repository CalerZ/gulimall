package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 *
 * @author caleb
 * @email caleb@gmail.com
 * @date 2020-11-22 20:44:11
 */
@Data
public class ModuleEntityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Long id;
	/**
	 * 模块名称
	 */
	@NotEmpty(message = "模块名称不能为空")
	private String name;
	/**
	 * 模块标题
	 */
	private String title;
	/**
	 * 模块标识
	 */
	@NotEmpty(message = "模块标识不能为空")
	private String tag;
	/**
	 * 描述
	 */
	private String descriptions;
	/**
	 * 排序
	 */
	@Min(value = 0,message = "排序值不能小于0")
	private Integer sort;
	/**
	 * 是否删除
	 */
	@TableLogic(value = "0",delval = "1")
	private Integer isDelete;

}
