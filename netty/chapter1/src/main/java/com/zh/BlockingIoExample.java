package com.zh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingIoExample {

    public void server(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(portNumber));
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true
                );
                String request, response;
                while ((request = in.readLine()) != null) {
                    if ("Done".equals(request)) {
                        break;
                    }
//                    response = processRequest(request);
                    out.println(request);
                }
            } catch (IOException e) {
//                e.printStackTrace();
            } finally {
                if (clientSocket != null)
                    clientSocket.close();
            }
        }
    }

    /**
     * 处理请求并产生响应
     * @param request
     * @return
     */
    private String processRequest(String request) {
        return "Processed";
    }

    public static void main(String[] args) throws IOException {
        BlockingIoExample server = new BlockingIoExample();
        server.server(10010);
    }
}
