package com.zh.enumerated;

import static com.zh.enumerated.Outcome.*;
/**
 * enum做多路分发例子
 * Created by zh on 2017-03-19.
 */
public enum  RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);
    private Outcome vPaper, vSCISSORS, vROCK;
    RoShamBo2(Outcome vPaper, Outcome vSCISSORS, Outcome vROCK) {
        this.vPaper = vPaper;
        this.vSCISSORS = vSCISSORS;
        this.vROCK = vROCK;
    }
    @Override
    public Outcome compete(RoShamBo2 competitor) {
        switch (competitor){
            default:
            case PAPER:return vPaper;
            case SCISSORS:return vSCISSORS;
            case ROCK:return vROCK;
        }
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
    }
}
