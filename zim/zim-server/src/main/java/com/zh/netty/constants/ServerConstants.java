package com.zh.netty.constants;

public interface ServerConstants {

    String SERVER_IP_KEY = "zim.serverIp";

    String DEFAULT_SERVER_IP = "127.0.0.1";

    String ZK_SERVER_ROOT_NODE = "/zim-server";

    // path/serverIp:port
    String SERVER_NODE_TEMPLATE = "%s/%s:%s";
}
