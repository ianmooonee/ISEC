package pt.isec.model.fsm;

import pt.isec.model.data.GameBWData;

abstract class GameBWStateAdapter implements IGameBWState {
    GameBWContext context;
    GameBWData data;

    protected GameBWStateAdapter(GameBWContext context, GameBWData data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public void start(){}

    @Override
    public void end(){}

    @Override
    public BetResult bet(int nr_balls){
        return BetResult.ERROR;
    }

    @Override
    public boolean looseWhiteBall(){
        return false;
    }

    @Override
    public boolean removeTwoBalls(){
        return false;
    }

    protected void changeState(GameBWState newState){
        context.changeState(newState.createState(context,data));
    }

}
