@echo off
REM UTF-8编码
chcp 65001

REM 设置Java运行环境变量，如果没有设置JAVA_HOME，请替换为你的Java安装路径
set JAVA_HOME="C:\Program Files\Java\jdk-17.0.12"

set char=optional-face-recognition

echo 程序匹配名称 : %char%

for /f "usebackq tokens=1-2" %%a in (`"%JAVA_HOME%\bin\jps" -l ^| findstr %char%`) do (
		echo 找到进程 %%a %%b
		echo 将终止程序 : 进程号 %%a, 程序名称 %%b
		pause
        taskkill /f /pid %%a
        echo 已终止!
)

pause