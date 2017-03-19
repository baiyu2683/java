package com.zh.enumerated;

import static com.zh.enumerated.Outcome.*;

/**
 * 二维数组实现分发
 * Created by zh on 2017-03-19.
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;
    private static Outcome[][] table = {
            {DRAW, LOSE, WIN}, //PAPER
            {WIN, DRAW, LOSE}, //SCISSORS
            {LOSE, WIN, DRAW}, //ROCK
    };
    @Override
    public Outcome compete(RoShamBo6 competitor) {
        return table[this.ordinal()][competitor.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
