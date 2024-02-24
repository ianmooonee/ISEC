package pt.isec.model.fsm;

public interface IGameBWState {
    void start();
    void end();
    BetResult bet(int nr_balls);
    boolean looseWhiteBall();
    boolean removeTwoBalls();


    GameBWState getState();
}
