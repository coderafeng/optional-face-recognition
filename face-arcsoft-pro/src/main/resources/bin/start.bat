@echo off
chcp 65001
setlocal

set JAVA_HOME="C:\Program Files\Java\jdk-17.0.12"

REM JVM 参数配置
set APPLICATION_JAVA_OPT=-Xms1024m -Xmx1024m -Xmn512m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:-OmitStackTraceInFastThrow -DappName=optional-face-recognition


REM 检查是否传递了JAR包名称参数
if "%~1"=="" (
    REM 查找最新版本的 JAR 文件，按名称降序排列目录中的 JAR 文件，循环读取第一个结果，这个结果就是最新版本的 JAR 文件
    echo 没有传递JAR包参数，查找最新版本的JAR文件...
    for /f "delims=" %%i in ('dir /b /o-n optional-face-recognition-*-exec.jar') do (
        echo 找到JAR文件: %%i
        set LATEST_JAR=%%i
        goto :found_latest
    )

    :found_latest
    if not defined LATEST_JAR (
        echo 没有找到可用的 JAR 文件！
        exit /b 1
    ) else (
        set JAR_FILE=%LATEST_JAR%
        goto :start_jar
    )
) else (
    echo 检查传递的JAR包参数：%~1
    if exist "%~1" (
        set JAR_FILE=%~1
    ) else (
        echo 指定的 JAR 文件不存在：%~1
        exit /b 1
    )
)

:start_jar
echo 启动的 JAR 文件：%JAR_FILE%

REM 启动jar服务
start "" "%JAVA_HOME%\bin\javaw.exe" %APPLICATION_JAVA_OPT% -jar %JAR_FILE% --spring.config.location=file:.\application.yml

endlocal

pause