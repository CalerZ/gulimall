#!/bin/sh
## sed -i 's/\r$//' close_jar.sh & chmod 777 close_jar.sh
BUILD_ID=DONTKILLME #后台执行
arr=("gulimall-product" "gulimall_gateway" "renren-fast" "gulimall-coupon" "gulimall-member" "gulimall-order" "gulimall-ware")
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
done
