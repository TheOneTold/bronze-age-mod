package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class HearthRecipes {
	private static final HearthRecipes smeltingBase = new HearthRecipes();
	private Map smeltingList = new HashMap();

	public static final HearthRecipes smelting() {
		return smeltingBase;
	}

	private HearthRecipes() {
		this.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
		this.addSmelting(Block.oreCopper.blockID, new ItemStack(Item.ingotCopper));
		this.addSmelting(Block.oreTin.blockID, new ItemStack(Item.ingotTin));
		this.addSmelting(Block.oreSilver.blockID, new ItemStack(Item.ingotSilver));
		this.addSmelting(Item.rawBronze.shiftedIndex, new ItemStack(Item.ingotBronze));
		this.addSmelting(Item.porkRaw.shiftedIndex, new ItemStack(Item.porkCooked));
		this.addSmelting(Item.fishRaw.shiftedIndex, new ItemStack(Item.fishCooked));
		this.addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick));
		this.addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2));
		this.addSmelting(Block.sticks.blockID, new ItemStack(Item.gunpowder));
	}

	public void addSmelting(int var1, ItemStack var2) {
		this.smeltingList.put(Integer.valueOf(var1), var2);
	}

	public ItemStack getSmeltingResult(int var1) {
		return (ItemStack)this.smeltingList.get(Integer.valueOf(var1));
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}
}
