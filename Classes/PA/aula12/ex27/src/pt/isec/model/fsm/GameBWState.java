package pt.isec.model.fsm;

import pt.isec.model.data.GameBWData;

public enum GameBWState {
    BEGIN, WAIT_BET, LOST_WAIT_DECISION;

    public IGameBWState createState(GameBWContext context, GameBWData data) {
        return switch (this) {
            case BEGIN -> new BeginState(context, data);
            case WAIT_BET -> new WaitBetState(context, data);
            case LOST_WAIT_DECISION -> new LostWaitDecisionState(context, data);
        };
    }
}
