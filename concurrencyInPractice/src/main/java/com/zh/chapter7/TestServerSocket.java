package com.zh.chapter7;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-09-21.
 */
public class TestServerSocket {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10010);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(new ServerThread(socket));
        }
    }

    private static class ServerThread implements Runnable {
        private Socket socket;
        private OutputStream os;

        public ServerThread(Socket socket) throws IOException {
            this.socket = socket;
            os = socket.getOutputStream();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    os.write("test".getBytes());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
