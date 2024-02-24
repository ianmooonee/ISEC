package pt.isec.model.fsm;

import pt.isec.model.data.GameBWData;

public class GameBWContext {
    IGameBWState state;
    GameBWData data;

    public GameBWContext() {
        data = new GameBWData();
        state = GameBWState.BEGIN.createState(this,data);
    }

    void changeState(IGameBWState newState) {
        state = newState;
    }

    // getters
    public GameBWState getState() {
        if (state == null)
            return null;
        return state.getState();
    }

    public void start(){
        state.start();
    }

    public void end(){
        state.end();
    }

    BetResult bet(int nr_balls){
        return state.bet(nr_balls);
    }

    boolean loseWhiteBall(){
        return state.looseWhiteBall();
    }

    boolean removeTwoBalls(){
        return state.removeTwoBalls();
    }


    public int getNrWhiteBallsWon() {
        return data.getNrWhiteBallsWon();
    }

    public int getNrWhiteBallsOut() {
        return data.getNrWhiteBallsOut();
    }

    public int getNrBlackBallsOut() {
        return data.getNrBlackBallsOut();
    }

    public boolean bagIsEmpty() {
        return data.bagIsEmpty();
    }

}
