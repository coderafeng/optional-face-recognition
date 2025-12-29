package com.loongdot.face.common.config;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.core.basic.api.database.CodeFirstCommand;
import com.easy.query.core.basic.api.database.DatabaseCodeFirst;
import com.loongdot.face.common.entity.po.UserFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// @formatter:off
/**
 * @author ：coderafeng
 * @email  ：
 *     - Gmail：<a href="k1994583917@gmail.com">Gmail Email</a>
 *     - QQ：<a href="1994583917@qq.com">QQ Email</a>
 * @home   ：
 *     - <a href="http://loongdot.com/community">龙点科技有限责任公司</a>
 *     - <a href="https://github.com/coderafeng">GitHub</a>
 *     - <a href="https://gitee.com/coderafeng">GitHub</a>
 *     - <a href="https://space.bilibili.com/481342296">哔哩哔哩</a>
 * @company：<a href="http://loongdot.com">龙点科技有限责任公司</a>
 * @date   ：2025-04-12 23:15
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Slf4j
@Configuration
public class DbConfig {

    public DbConfig(EasyEntityQuery easyEntityQuery) {
        DatabaseCodeFirst databaseCodeFirst = easyEntityQuery.getDatabaseCodeFirst();
        // 自动同步表结构 如果数据库不存在则创建数据库(oracle不支持)
        // 如果表不存在则创建表
        // 如果表存在且class内的属性比数据库列多则自动生成添加列
        // 如果列或者表添加oldName则自动生成rename命令
        CodeFirstCommand codeFirstCommand = databaseCodeFirst.syncTableCommand(getAllPoClass());
        // 执行命令
        codeFirstCommand.executeWithTransaction(t -> {
            log.info("sql: {}", t.getSQL());
            t.commit();
        });
    }

    private List<Class<?>> getAllPoClass() {
        return List.of(UserFace.class);
    }

}