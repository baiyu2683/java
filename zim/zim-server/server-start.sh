#!/usr/bin/env bash

ip=$(ifconfig | grep -A 1 'eth0' | grep 'inet' | sed 's/^.*inet //g' | cut -d ' ' -f 1 | sed 's/^addr://g')

nohup java -Dzim.serverIp=$ip -jar ./lib/zim-server.jar >/dev/null 2>&1 &