package com.rs2.model.content.randomevents.InterfaceClicking.impl;

import java.util.ArrayList;

import com.rs2.model.players.Player;
import com.rs2.model.players.item.Item;


public interface InterfaceClickEvent {

	public int npcId();

	public int interfaceSent();

	public String[] stringSent();

	public ArrayList<Integer> buttonsDisplayed();

	public int numberOfStages();

	public String[] cycleMessages();

	public int cycleDuration();

	public Item[] rewards();

	public String[] goodByeMessage();

	public void handleFailure(Player player);

	public void handleSuccess(Player player);

}
