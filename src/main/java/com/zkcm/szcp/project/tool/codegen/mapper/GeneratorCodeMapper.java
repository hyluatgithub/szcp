package com.zkcm.szcp.project.tool.codegen.mapper;

import com.zkcm.szcp.project.tool.codegen.domain.TableEntity;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author hylu
 */
public interface GeneratorCodeMapper {

    /**
     * 分页查询表格
     *
     * @return
     */
    List<Map<String, Object>> queryList(TableEntity tableEntity);

    /**
     * 查询表信息
     *
     * @param tableName 表名称
     * @return
     */
    Map<String, String> queryTable(String tableName);

    /**
     * 查询表列信息
     *
     * @param tableName 表名称
     * @return
     */
    List<Map<String, String>> queryColumns(String tableName);
}
