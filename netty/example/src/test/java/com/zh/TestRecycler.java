package com.zh;

import io.netty.util.Recycler;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;

public class TestRecycler {

    static class RecyclerTestClass {
        private Recycler.Handle<RecyclerTestClass> handle;

        public RecyclerTestClass(Recycler.Handle<RecyclerTestClass> handle) {
            this.handle = handle;
        }

        public Recycler.Handle<RecyclerTestClass> getHandle() {
            return handle;
        }

        public void setHandle(Recycler.Handle<RecyclerTestClass> handle) {
            this.handle = handle;
        }

        public void recycle() {
            handle.recycle(this);
        }
    }

    private static Recycler<RecyclerTestClass> recycler = new Recycler<RecyclerTestClass>() {
        @Override
        protected RecyclerTestClass newObject(Handle<RecyclerTestClass> handle) {
            return new RecyclerTestClass(handle);
        }
    };

    public static void main(String[] args) {
        RecyclerTestClass recyclerTestClass = recycler.get();
        recyclerTestClass.recycle();
        recycler.get();
    }
}
