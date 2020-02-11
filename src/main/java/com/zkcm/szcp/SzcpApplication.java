package com.zkcm.szcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author hylu
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SzcpApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SzcpApplication.class, args);
    }
}
