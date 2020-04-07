package com.zh.netty.constants;

public interface ServerConstants {

    String SERVER_IP_KEY = "zim.serverIp";

    String DEFAULT_SERVER_IP = "127.0.0.1";

    String ZK_SERVER_ROOT_NODE = "/zim-server";

    // path/serverIp:port
    String SERVER_NODE_TEMPLATE = "%s/%s:%s";

    // zookeeper
    // 重试间隔时间, ms
    int WAIT_TIME_BETWEEN_RETRY = 5000;
    // 最大重试次数
    int COUNT_OF_RETRY = 3;
    // 会话超时时间，ms
    int SESSION_TIMEOUT = 3 * 1000;

}
