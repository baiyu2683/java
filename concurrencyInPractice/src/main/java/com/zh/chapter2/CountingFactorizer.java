package com.zh.chapter2;

import com.zh.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zh on 2017-07-06.
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet {
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        count.incrementAndGet();
    }
}
