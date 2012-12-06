package com.rs2.model.content.skills.farming;

import java.util.HashMap;
import java.util.Map;

import com.rs2.Server;
import com.rs2.model.content.dialogue.Dialogues;
import com.rs2.model.players.Player;
import com.rs2.model.players.item.Item;
import com.rs2.model.players.item.ItemDefinition;
import com.rs2.util.Misc;


public class Farmers {

	public static final String[][] farmingAdvices = {{"You don't have to buy all your plantpots you know,", "you can make them yourself on a pottery wheel. If", "you are a good enough craftsman, that is."}, {"Don't just throw away your weeds after you've", "raked a patch - put them in a compost bin and", "make some compost."}, {"Tree seeds must be grown in a plantpot of soil", "into a tree sapling, and then transferred to a", "tree patch to continue growing to adulthood."}, {"You can put up to ten potatoes, cabbages, or", "onions in vegetable sacks, although you can't", "have a mix in the same sack."}, {"You can buy all the farming tools from farming", "shops which can be found close to the allotments"}, {"You can fill plantpots with soil from Farming", "patches, if you have a gardening trowel."}, {"If you want to make your own sacks and baskets", "you'll need to use the loom that's near the", "Farming shop in Falador."},
			{"Bittercap mushrooms can only be grown in special", "patches in Morytania, near the Mort Myre swamp."}, {"Applying compost to a patch will not only reduce", "the chance that your crops will get diseased, but", "you will also grow more crops to harvest."}, {"Hops are good for brewing ales. I believe there", "is a brewery up in Keldagrim somewhere."}};

	public enum FarmersData {
		DANTAREA(2324, 41, "allotment", new String[]{"Northern patch", "Southern patch"}), ELSTAN(2323, 54, "allotment", new String[]{"North-west patch", "South-East patch"}), LYRA(2326, 123, "allotment", new String[]{"North-west patch", "South-East patch"}), KRAGEN(2325, 99, "allotment", new String[]{"Northern patch", "Southern patch"}), RHAZIEN(2337, 162, "bushes", null), TARIA(2336, 139, "bushes", null), DREVEN(2335, 47, "bushes", null), TORREL(2338, 144, "bushes", null), RHONEN(2334, 123, "hops", null), SELENA(2332, 134, "hops", null), VASQUEN(2333, 153, "hops", null), FRANCIS(2327, 62, "hops", null), BOLONGO(2343, 32, "fruitTree", null), ELLENA(2331, 53, "fruitTree", null), GILEH(2344, 74, "fruitTree", null), GARTH(2330, 70, "fruitTree", null), HESKEL(2340, 85, "tree", null), ALAIN(2339, 163, "tree", null), TREZNOR(2341, 146, "tree", null), FAYETH(2342, 58, "tree", null);

		private int npcId;
		private int shopId;
		private String fieldProtected;
		private String[] dialogueOptions;

		private static Map<Integer, FarmersData> npcs = new HashMap<Integer, FarmersData>();

		static {
			for (FarmersData data : FarmersData.values()) {
				npcs.put(data.npcId, data);
			}
		}

		FarmersData(int npcId, int shopId, String fieldProtected, String[] dialogueOptions) {
			this.npcId = npcId;
			this.shopId = shopId;
			this.fieldProtected = fieldProtected;
			this.dialogueOptions = dialogueOptions;
		}

		public static FarmersData forId(int npcId) {
			return npcs.get(npcId);
		}

		public int getNpcId() {
			return npcId;
		}

		public String getFieldProtected() {
			return fieldProtected;
		}

		public String[] getDialogueOptions() {
			return dialogueOptions;
		}

		public int getShopId() {
			return shopId;
		}
	}

	public static void ProtectPlant(Player player, int indexArray, String fieldType, int npcId, int stage) {
		int index = 0;
		int[] payment = new int[2];
		if (stage == 1) {
			if (fieldType == "allotment") {
				index = Allotments.AllotmentFieldsData.listIndexProtected(npcId).get(indexArray);
				if (Allotments.AllotmentData.forId(player.getAllotment().farmingSeeds[index]) != null)
					payment = Allotments.AllotmentData.forId(player.getAllotment().farmingSeeds[index]).getPaymentToWatch();
				if (player.getAllotment().farmingStages[index] <= 3) {
					player.getDialogue().sendNpcChat("I am sorry but you have no crops growing in this patch.", Dialogues.ANNOYED);
					player.getDialogue().endDialogue();
				} else {
					player.getDialogue().sendNpcChat("If you like, but I want " + payment[1] + " " + getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()) + (getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()).endsWith("s") ? "" : "s") + " for that.", Dialogues.UNINTERESTED);
					Dialogues.sendDialogue(player, player.getDialogue().getLastNpcTalk(), 18, 0);
					player.setTempInteger(indexArray);
				}
			}
			if (fieldType == "bushes") {
				index = Bushes.BushesFieldsData.forId(npcId).getBushesIndex();
				if (Bushes.BushesData.forId(player.getBushes().farmingSeeds[index]) != null)
					payment = Bushes.BushesData.forId(player.getBushes().farmingSeeds[index]).getPaymentToWatch();
				if (player.getBushes().farmingStages[index] <= 3) {
					player.getDialogue().sendNpcChat("I am sorry but you have no crops growing in this patch.", Dialogues.ANNOYED);
					player.getDialogue().endDialogue();
				} else {
					player.getDialogue().sendNpcChat("If you like, but I want " + payment[1] + " " + getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()) + (getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()).endsWith("s") ? "" : "s") + " for that.", Dialogues.UNINTERESTED);
					Dialogues.sendDialogue(player, player.getDialogue().getLastNpcTalk(), 18, 0);
					player.setTempInteger(indexArray);
				}
			}
			if (fieldType == "fruitTree") {
				index = FruitTree.FruitTreeFieldsData.forId(npcId).getFruitTreeIndex();
				if (FruitTree.FruitTreeData.forId(player.getFruitTrees().farmingSeeds[index]) != null)
					payment = FruitTree.FruitTreeData.forId(player.getFruitTrees().farmingSeeds[index]).getPaymentToWatch();
				if (player.getFruitTrees().farmingStages[index] <= 3) {
					player.getDialogue().sendNpcChat("I am sorry but you have no crops growing in this patch.", Dialogues.ANNOYED);
					player.getDialogue().endDialogue();
				} else {
					player.getDialogue().sendNpcChat("If you like, but I want " + payment[1] + " " + getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()) + (getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()).endsWith("s") ? "" : "s") + " for that.", Dialogues.UNINTERESTED);
					Dialogues.sendDialogue(player, player.getDialogue().getLastNpcTalk(), 18, 0);
					player.setTempInteger(indexArray);
				}
			}

			if (fieldType == "hops") {
				index = Hops.HopsFieldsData.forId(npcId).getHopsIndex();
				if (Hops.HopsData.forId(player.getHops().farmingSeeds[index]) != null)
					payment = Hops.HopsData.forId(player.getHops().farmingSeeds[index]).getPaymentToWatch();
				if (player.getHops().farmingStages[index] <= 3) {
					player.getDialogue().sendNpcChat("I am sorry but you have no crops growing in this patch.", Dialogues.ANNOYED);
					player.getDialogue().endDialogue();
				} else {
					player.getDialogue().sendNpcChat("If you like, but I want " + payment[1] + " " + getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()) + (getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()).endsWith("s") ? "" : "s") + " for that.", Dialogues.UNINTERESTED);
					Dialogues.sendDialogue(player, player.getDialogue().getLastNpcTalk(), 18, 0);
					player.setTempInteger(indexArray);
				}
			}

			if (fieldType == "tree") {
				index = WoodTrees.TreeFieldsData.forId(npcId).getTreeIndex();
				if (WoodTrees.TreeData.forId(player.getTrees().farmingSeeds[index]) != null)
					payment = WoodTrees.TreeData.forId(player.getTrees().farmingSeeds[index]).getPaymentToWatch();
				if (player.getTrees().farmingStages[index] <= 3) {
					player.getDialogue().sendNpcChat("I am sorry but you have no crops growing in this patch.", Dialogues.ANNOYED);
					player.getDialogue().endDialogue();
				} else {
					player.getDialogue().sendNpcChat("If you like, but I want " + payment[1] + " " + getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()) + (getFinalPaymentString(ItemDefinition.forId(payment[0]).getName().toLowerCase()).endsWith("s") ? "" : "s") + " for that.", Dialogues.UNINTERESTED);
					Dialogues.sendDialogue(player, player.getDialogue().getLastNpcTalk(), 18, 0);
					player.setTempInteger(indexArray);
				}
			}

		} else if (stage == 2) {
			if (fieldType == "allotment") {
				index = Allotments.AllotmentFieldsData.listIndexProtected(npcId).get(indexArray);
				payment = Allotments.AllotmentData.forId(player.getAllotment().farmingSeeds[index]).getPaymentToWatch();
			}
			if (fieldType == "bushes") {
				index = Bushes.BushesFieldsData.forId(npcId).getBushesIndex();
				payment = Bushes.BushesData.forId(player.getBushes().farmingSeeds[index]).getPaymentToWatch();
			}
			if (fieldType == "fruitTree") {
				index = FruitTree.FruitTreeFieldsData.forId(npcId).getFruitTreeIndex();
				payment = Hops.HopsData.forId(player.getFruitTrees().farmingSeeds[index]).getPaymentToWatch();
			}
			if (fieldType == "hops") {
				index = Hops.HopsFieldsData.forId(npcId).getHopsIndex();
				payment = Hops.HopsData.forId(player.getHops().farmingSeeds[index]).getPaymentToWatch();
			}
			if (fieldType == "tree") {
				index = WoodTrees.TreeFieldsData.forId(npcId).getTreeIndex();
				payment = WoodTrees.TreeData.forId(player.getTrees().farmingSeeds[index]).getPaymentToWatch();
			}
			if (player.getInventory().getItemAmount(payment[0]) < payment[1]) {
				player.getDialogue().sendNpcChat("Sorry, but you do not have the required items", "I need, for letting me take care of this patch", Dialogues.ANNOYED);
				player.getDialogue().endDialogue();
			} else {
				if (fieldType == "allotment") {
					player.getAllotment().farmingWatched[index] = true;
				}
				if (fieldType == "bushes") {
					player.getBushes().bushesWatched[index] = true;
				}
				if (fieldType == "fruitTree") {
					player.getFruitTrees().farmingWatched[index] = true;
				}
				if (fieldType == "hops") {
					player.getHops().farmingWatched[index] = true;
				}
				if (fieldType == "tree") {
					player.getTrees().farmingWatched[index] = true;
				}
				player.getInventory().removeItem(new Item(payment[0], payment[1]));
				player.getDialogue().sendNpcChat("Here you go, I will be taking care of this patch", "as soon as it become diseased, I will cure it", "so you don't have to worry about it.", Dialogues.HAPPY);
				player.getDialogue().endDialogue();
			}
		}

	}
	public static void chopDownTree(Player player, int npcId) {
		int index = WoodTrees.TreeFieldsData.forId(npcId).getTreeIndex();
		if (player.getTrees().farmingStages[index] < 3) {
			Dialogues.sendDialogue(player, npcId, 15, 0);
			return;
		}
		if (player.getInventory().getItemAmount(995) < 200) {
			player.getDialogue().sendNpcChat("I am sorry, but you do not have enough money", "to pay me to chop down this tree.", Dialogues.ANNOYED);
			player.getDialogue().endDialogue();
		} else {
			player.getInventory().removeItem(new Item(995, 200));
			player.getDialogue().sendNpcChat("There you go, I have chopped down your tree but I am ", "keeping the logs and roots as compensation.", Dialogues.ANNOYED);
			player.getTrees().resetTrees(index);
			player.getTrees().farmingStages[index] = 3;
			player.getTrees().farmingTimer[index] = Server.getMinutesCounter();
			player.getTrees().updateTreeStates();
			player.getDialogue().endDialogue();

		}

	}

	public static void sendFarmingAdvice(Player player) {
		player.getDialogue().sendNpcChat(farmingAdvices[Misc.random(farmingAdvices.length - 1)], Dialogues.HAPPY);
		player.getDialogue().endDialogue();
	}

	public static String getFinalPaymentString(String word) {
		if (word.contains("(5)"))
			return "baskets of " + word.replace("(5)", "");
		if (word.contains("(10)"))
			return "sacks of " + word.replace("(10)", "");
		return word;
	}

}
