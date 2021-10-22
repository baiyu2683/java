package com.zh.collect;

import com.zh.entity.Trader;
import com.zh.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分组
 */
public class GroupByTest {

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
    public void groupby() {
        Map<String, List<Trader>> traderByCity = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .collect(Collectors.groupingBy(Trader::getCity));
        System.out.println(traderByCity);
    }

    @Test
    public void groupbyCustom() {
       Map<Integer, List<Transaction>> valueGroup = transactions.stream()
               .collect(Collectors.groupingBy(transaction -> {
                   if (transaction.getValue() > 500) {
                       return 1;
                   } else {
                       return 0;
                   }
               }));
        System.out.println(valueGroup);
    }
}
