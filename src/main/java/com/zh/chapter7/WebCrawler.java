package com.zh.chapter7;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-09-24.
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    private final Set<URL> urlsToCrawl = new HashSet<>();
    private final Integer TIMEOUT = 1000;
    private final TimeUnit UNIT = TimeUnit.SECONDS;

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for(URL url : urlsToCrawl)
            submitCrawlTask(url);
        urlsToCrawl.clear();
    }

    private void submitCrawlTask(URL u) {
        exec.execute(new CrawlTask(u));
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(exec.shutdownNow());
            if(exec.awaitTermination(TIMEOUT, UNIT))
                saveUncrawled(exec.getCancelledTasks());
        } finally {
            exec = null;
        }
    }

    /**
     * 获得翻页等所有需要的链接
     * @param url
     * @return
     */
    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
        for(Runnable task : uncrawled)
            urlsToCrawl.add(((CrawlTask)task).getPage());
    }

    private class CrawlTask implements Runnable {
        private final URL url;

        public CrawlTask(URL u) {
            this.url = u;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if(Thread.currentThread().isInterrupted())
                    return;
                submitCrawlTask(link);
            }
        }
        public URL getPage() {
            return url;
        }
    }
}
