package com.rs2.model.content.minigames;

import java.util.ArrayList;

import com.rs2.model.players.Player;

public class LinkedGroupAreas {

    private MinigameAreas.Area waitingArea;

    private MinigameAreas.Area inGameArea;

    private ArrayList<Player> waitingArray = new ArrayList<Player>();

    public LinkedGroupAreas(MinigameAreas.Area waitingArea, MinigameAreas.Area inGameArea) {
        this.waitingArea = waitingArea;
        this.inGameArea = inGameArea;
    }

    public MinigameAreas.Area getWaitingArea() {
        return waitingArea;
    }

    public MinigameAreas.Area getInGameArea() {
        return inGameArea;
    }

    public ArrayList<Player> getWaitingArray() {
        return waitingArray;
    }
}
