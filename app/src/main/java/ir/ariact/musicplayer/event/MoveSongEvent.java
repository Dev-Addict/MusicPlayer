package ir.ariact.musicplayer.event;

import ir.ariact.musicplayer.model.MoveSongState;

public class MoveSongEvent {
    private MoveSongState moveSongState;

    public MoveSongEvent(MoveSongState moveSongState) {
        this.moveSongState = moveSongState;
    }

    public MoveSongState getMoveSongState() {
        return moveSongState;
    }

    public void setMoveSongState(MoveSongState moveSongState) {
        this.moveSongState = moveSongState;
    }
}
