# gulimall
谷粒商城
1.前后端数据提交校验(entity 与前端通信的字段)
2.文件上传(本地服务器文件上传)
3.重复提交
4.全局异常
5.错误代码 10  11 

 nohup java -jar /zkl/project/gulimall/renren-fast-0.0.1-SNAPSHOT.jar >/zkl/project/gulimall/log/renren-fast.log 2>&1 &
nohup java -jar /zkl/project/gulimall/gulimall_gateway-0.0.1-SNAPSHOT.jar >/zkl/project/gulimall/log/gulimall_gateway.log  > log-monitor.file  2>&1 &


面临的问题：
1.po与vo之间的转换(大量重复的转换)
2.当分页查询后返回的却是po数据，而从前台接收到的是vo


