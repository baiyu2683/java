package com.zh.stream;

import com.zh.entity.Trader;
import com.zh.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流练习
 */
public class ExerciseTest {

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
    public void traderAt2011() {
        // 2011年的交易，按交易额从低到高排序
        List<Transaction> list = transactions.stream()
                .filter(t -> 2011 == t.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void allWorkerCity() {
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .map(trader -> trader.getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);
    }

    @Test
    public void cambridgeTrader() {
        List<Trader> names = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(trader -> trader.getName()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void tranderName() {
        List<String> names = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .map(trader -> trader.getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void existMilanTrader() {
        boolean milan = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(milan);
    }

    @Test
    public void sumInCambridgeTrader() {
        Integer sum = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getValue())
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    @Test
    public void maxValue() {
        Integer max = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::max);
        System.out.println(max);
    }

    @Test
    public void minTransaction() {
        Transaction transaction = transactions.stream()
                .sorted(Comparator.comparing(transaction1 -> transaction1.getValue()))
                .limit(1)
                .findFirst()
                .orElseThrow();
        System.out.println(transaction);

        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(min.get());
    }

}
