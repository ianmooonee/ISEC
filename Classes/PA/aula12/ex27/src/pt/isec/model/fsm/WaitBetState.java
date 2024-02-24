package pt.isec.model.fsm;

import pt.isec.model.data.BallType;
import pt.isec.model.data.GameBWData;

public class WaitBetState extends GameBWStateAdapter{
    public WaitBetState(GameBWContext context, GameBWData data) {
        super(context, data);
    }

    //redifinir o bet
    @Override
    public BetResult bet(int nr_balls){
        BallType ball=data.getBall();
        switch(ball){
            case WHITE ->{return BetResult.WON;}
            case BLACK -> {return BetResult.LOST;}
            case NONE -> changeState(GameBWState.BEGIN);
        }
        return BetResult.ERROR;
    }

    @Override
    public GameBWState getState() {
        return GameBWState.BEGIN;
    }
}
