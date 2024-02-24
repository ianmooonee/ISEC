package pt.isec.model.fsm;

import pt.isec.model.data.GameBWData;

public class LostWaitDecisionState extends GameBWStateAdapter{
    public LostWaitDecisionState(GameBWContext context, GameBWData data) {
        super(context, data);
    }

    //redifinir lostWhiteBall e remove2Balls

    @Override
    public GameBWState getState() {
        return GameBWState.BEGIN;
    }
}
