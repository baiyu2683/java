package com.zh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 讲一个列表中的对象，按一个属性分组，另外一个属性求和，最终输出位一个列表
 */
public class TestGroupBy {

    public static void main(String[] args) {
        List<Holder> list = new ArrayList<>();
        list.add(new Holder("1", new BigDecimal(10)));
        list.add(new Holder("2", new BigDecimal(11)));
        list.add(new Holder("1", new BigDecimal(12)));
        List<Holder> results = list.stream()
                .collect(Collectors.groupingBy(Holder::getRivalid))
                .entrySet()
                .stream()
                .map(item -> {
                    String key = item.getKey();
                    List<Holder> values = item.getValue();
                    BigDecimal sum = values.stream()
                            .map(Holder::getUsedCreditQuota)
                            .reduce(new BigDecimal(0), (before, after) -> before.add(after));
                    Holder holder = new Holder(key, sum);
                    return holder;
                })
                .collect(Collectors.toList());
        System.out.println(results);
    }
}

class Holder{
    private String rivalid;
    private BigDecimal usedCreditQuota;

    public Holder(String rivalid, BigDecimal usedCreditQuota) {
        this.rivalid = rivalid;
        this.usedCreditQuota = usedCreditQuota;
    }

    public String getRivalid() {
        return rivalid;
    }

    public void setRivalid(String rivalid) {
        this.rivalid = rivalid;
    }

    public BigDecimal getUsedCreditQuota() {
        return usedCreditQuota;
    }

    public void setUsedCreditQuota(BigDecimal usedCreditQuota) {
        this.usedCreditQuota = usedCreditQuota;
    }

    public String toString() {
        return "[ rivalid=" + rivalid + ", " + "usedCreditQuota=" + this.usedCreditQuota + " ]";
    }
}
