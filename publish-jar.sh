#!/bin/sh
## sed -i 's/\r$//' publish-jar.sh & chmod 777 publish-jar.sh & ./publish-jar.sh
BUILD_ID=DONTKILLME #后台执行
arr=("gulimall-product" "gulimall-coupon" "gulimall-member" "gulimall-order" "gulimall-ware" "gulimall_gateway" "renren-fast")
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
  rm -rf /zkl/project/gulimall/
  #copy jar 到启动目录
  cp -r /root/.jenkins/workspace/gulimall/"${k}"/target/"${jar_name}" /zkl/project/gulimall/"${jar_name}"

  if [ ! -f /zkl/project/gulimall/"${jar_name}" ]; then
    sleep 5s
  fi
  if [ ! -f /zkl/project/gulimall/log/"${k}".log ]; then
    touch /zkl/project/gulimall/log/"${k}".log
  fi
  nohup java -jar /zkl/project/gulimall/"${jar_name}" >/zkl/project/gulimall/log/"${k}".log 2>&1 &
  echo "${k} 已启动！"
done
