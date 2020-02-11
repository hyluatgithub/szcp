package com.zkcm.szcp.project.tool.codegen.service;

import com.zkcm.szcp.project.tool.codegen.domain.GenConfig;
import com.zkcm.szcp.project.tool.codegen.domain.TableEntity;

import java.util.List;
import java.util.Map;

/**
 * @author hylu
 */
public interface IGeneratorCodeService {
	/**
	 * 生成代码
	 *
	 * @param genConfig 表配置
	 * @return
	 */
	byte[] generatorCode(GenConfig genConfig);

	/**
	 * 分页查询表
	 *
	 * @param tableEntity 表查询对象
	 * @return
	 */
	List<Map<String, Object>> queryPage(TableEntity tableEntity);
}
