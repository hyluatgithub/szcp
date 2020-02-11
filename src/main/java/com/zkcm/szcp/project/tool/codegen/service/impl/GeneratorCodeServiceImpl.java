package com.zkcm.szcp.project.tool.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import com.zkcm.szcp.project.tool.codegen.domain.GenConfig;
import com.zkcm.szcp.project.tool.codegen.domain.TableEntity;
import com.zkcm.szcp.project.tool.codegen.mapper.GeneratorCodeMapper;
import com.zkcm.szcp.project.tool.codegen.service.IGeneratorCodeService;
import com.zkcm.szcp.project.tool.codegen.util.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author hylu
 */
@Service
public class GeneratorCodeServiceImpl implements IGeneratorCodeService {
    @Autowired
    private GeneratorCodeMapper generatorCodeMapper;


    /**
     * 分页查询表
     *
     * @param tableEntity 查询条件
     * @return
     */
    @Override
    public List<Map<String, Object>> queryPage(TableEntity tableEntity) {
        return generatorCodeMapper.queryList(tableEntity);
    }

    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return
     */
    @Override
    public byte[] generatorCode(GenConfig genConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        //查询表信息
        Map<String, String> table = queryTable(genConfig.getTableName());
        //查询列信息
        List<Map<String, String>> columns = queryColumns(genConfig.getTableName());
        //生成代码
        new GenUtils().generatorCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    private Map<String, String> queryTable(String tableName) {
        return generatorCodeMapper.queryTable(tableName);
    }

    private List<Map<String, String>> queryColumns(String tableName) {
        return generatorCodeMapper.queryColumns(tableName);
    }
}
