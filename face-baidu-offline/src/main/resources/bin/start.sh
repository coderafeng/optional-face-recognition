#!/bin/bash

# 设置 JAVA_HOME 环境变量指向 JDK 安装目录
JAVA_HOME="/opt/java/jdk-17.0.12"

# JVM 参数配置
APPLICATION_JAVA_OPT="-Xms1024m -Xmx1024m -Xmn512m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:-OmitStackTraceInFastThrow -DappName=optional-face-recognition"

# 检查是否传递了 JAR 包名称参数
if [ -z "$1" ]; then
    echo "没有传递JAR包参数，查找最新版本的JAR文件..."
    # 查找最新版本的 JAR 文件，按名称降序排列目录中的 JAR 文件，取第一个结果，这个结果就是最新版本的 JAR 文件
    LATEST_JAR=$(ls -t optional-face-recognition-*-exec.jar 2>/dev/null | head -n 1)

    if [ -z "$LATEST_JAR" ]; then
        echo "没有找到可用的 JAR 文件！"
        exit 1
    fi
    JAR_FILE="$LATEST_JAR"
else
    echo "检查传递的JAR包参数：$1"
    if [ -f "$1" ]; then
        JAR_FILE="$1"
    else
        echo "指定的 JAR 文件不存在：$1"
        exit 1
    fi
fi

echo "启动的 JAR 文件：$JAR_FILE"

# 启动 jar 服务
nohup $JAVA_HOME/bin/java $APPLICATION_JAVA_OPT -jar "$JAR_FILE" --spring.config.location=file:./application.yml >/dev/null 2>face-open-visual.log &
