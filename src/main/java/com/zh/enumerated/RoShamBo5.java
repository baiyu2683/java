package com.zh.enumerated;

import java.util.EnumMap;
import static com.zh.enumerated.Outcome.*;
/**
 * 使用EnumMap进行多路分发
 * Created by zh on 2017-03-19.
 */
public enum RoShamBo5 implements Competitor<RoShamBo5> {
    PAPER, SCISSORS, ROCK;
    static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<>(RoShamBo5.class);
    static {
        for(RoShamBo5 roShamBo5 : RoShamBo5.values()) {
            table.put(roShamBo5, new EnumMap<>(RoShamBo5.class));
        }
        initRow(PAPER, DRAW, LOSE, WIN);
        initRow(SCISSORS, WIN, DRAW, LOSE);
        initRow(ROCK, LOSE, WIN, DRAW);
    }
    static void initRow(RoShamBo5 roShamBo5, Outcome vPaper, Outcome vSCISSORS, Outcome vROCK) {
        EnumMap<RoShamBo5, Outcome> roShamBo5OutcomeEnumMap = RoShamBo5.table.get(roShamBo5);
        roShamBo5OutcomeEnumMap.put(RoShamBo5.PAPER, vPaper);
        roShamBo5OutcomeEnumMap.put(RoShamBo5.SCISSORS, vSCISSORS);
        roShamBo5OutcomeEnumMap.put(RoShamBo5.ROCK, vROCK);
    }
    @Override
    public Outcome compete(RoShamBo5 competitor) {
        return table.get(this).get(competitor);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class, 20);
    }
}
