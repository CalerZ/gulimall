#!/bin/sh
## sed -i 's/\r$//' publish-jar.sh & chmod 777 publish-jar.sh & ./publish-jar.sh
BUILD_ID=DONTKILLME #后台执行
arr=("gulimall-product" "gulimall_gateway" "renren-fast")
# shellcheck disable=SC2068
for k in ${arr[@]}; do
  houzhui="-0.0.1-SNAPSHOT.jar"
  jar_name=${k}${houzhui}
  echo "jar_name:${jar_name}"
  # 查询进程id
  pid=$(ps -ef | grep "${jar_name}" | grep -v "grep" | awk '{print $2}')
  # 关闭已经启动的jar进程
  if [ -n "$pid" ]; then
    echo "pid进程 :$pid"
    kill -9 $pid
  fi
  if [ ! -d "/zkl/project/gulimall/log" ]; then
    mkdir -p /zkl/project/gulimall/log
  fi
  #copy 之前先删除
  rm -rf /zkl/project/gulimall/"${jar_name}"
  rm -rf /zkl/project/gulimall/log/"${k}".log

  # 如果文件存在那么进行复制，否则睡五秒之后再复制
  if [  -f /root/.jenkins/workspace/gulimall/"${k}"/target/"${jar_name}" ]; then
     #copy jar 到启动目录
    cp -r /root/.jenkins/workspace/gulimall/"${k}"/target/"${jar_name}" /zkl/project/gulimall/"${jar_name}"
  else
    sleep 4s
    cp -r /root/.jenkins/workspace/gulimall/"${k}"/target/"${jar_name}" /zkl/project/gulimall/"${jar_name}"
  fi

  #如果没有文件等待五秒后再看是否复制完成
  if [ ! -f /zkl/project/gulimall/"${jar_name}" ]; then
    sleep 10s
  fi
  #证明没有复制成功，那么continue
  if [ ! -f /zkl/project/gulimall/"${jar_name}" ]; then
    continue
  fi
  #日志文件不存在，则创建一个日志文件
  if [ ! -f /zkl/project/gulimall/log/"${k}".log ]; then
    sudo touch /zkl/project/gulimall/log/"${k}".log
  fi
  if [ -f /zkl/project/gulimall/"${jar_name}" ]; then
      nohup java -jar /zkl/project/gulimall/"${jar_name}" >/zkl/project/gulimall/log/"${k}".log 2>&1 &
      echo "${k} 已启动！"
  else
     echo "${k} 启动失败！"
  fi

done
