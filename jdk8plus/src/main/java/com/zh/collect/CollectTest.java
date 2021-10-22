package com.zh.collect;

import com.zh.entity.Trader;
import com.zh.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 汇总操作
 */
public class CollectTest {

    private List<Transaction> transactions;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void summingInt() {
        int totalValue = transactions.stream().collect(Collectors.summingInt(Transaction::getValue));
        System.out.println(totalValue);
    }

    @Test
    public void average() {
        Double average = transactions.stream().collect(Collectors.averagingInt(Transaction::getValue));
        System.out.println(average);
    }

    @Test
    public void summary() {
        IntSummaryStatistics statistics = transactions.stream().collect(Collectors.summarizingInt(Transaction::getValue));
        System.out.println(statistics);
    }

    @Test
    public void joning() {
        // 连接字符串
        String names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }

    @Test
    public void commonReduce() {
        // 使用reducing实现累加
        int total = transactions.stream().collect(Collectors.reducing(0, Transaction::getValue, (i, j) -> i + j));
        System.out.println(total);
    }
}
