package com.zh;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientTest {

    private AtomicInteger count;

    public ClientTest(AtomicInteger count) {
        this.count = count;
    }

    public void client(int portNumber) {
        Socket client = new Socket();
        try {
            client.connect(new InetSocketAddress(portNumber));
            PrintWriter out = new PrintWriter(
                    client.getOutputStream(), true
            );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream())
            );
            String response = null;
            for (int i = 0 ; i < 100 ; i++) {
                String request = generateRequest(String.valueOf(i));
                out.println(request);
                if ((response = in.readLine()) != null) {
                    System.out.println(response);
                }
            }
            out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            count.addAndGet(-1);
        }
    }

    private String generateRequest(String request) {
        return "Request-" + request + "-" + Thread.currentThread().getName() + "\n";
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );
        for (int i = 0 ; i < 10 ; i++) {
            executor.execute(() -> {
                ClientTest client = new ClientTest(count);
                client.client(10010);
            });
        }
        while (true) {
            if (count.get() == 0) {
                executor.shutdownNow();
                break;
            }
        }
    }
}
