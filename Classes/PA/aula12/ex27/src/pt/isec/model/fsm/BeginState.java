package pt.isec.model.fsm;

import pt.isec.model.data.GameBWData;

public class BeginState extends GameBWStateAdapter{
    public BeginState(GameBWContext context, GameBWData data) {
        super(context, data);
    }

    @Override
    public void start(){
        data.initGame();
        changeState(GameBWState.WAIT_BET);
    }

    @Override
    public GameBWState getState() {
        return GameBWState.BEGIN;
    }
}
