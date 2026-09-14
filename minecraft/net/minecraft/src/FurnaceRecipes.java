package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class FurnaceRecipes {
	private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
	private Map smeltingList = new HashMap();

	public static final FurnaceRecipes smelting() {
		return smeltingBase;
	}

	private FurnaceRecipes() {
		//this.addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
		this.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
		this.addSmelting(Block.oreCopper.blockID, new ItemStack(Item.ingotCopper));
		this.addSmelting(Block.oreTin.blockID, new ItemStack(Item.ingotTin));
		this.addSmelting(Block.oreSilver.blockID, new ItemStack(Item.ingotSilver));
		//this.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
		this.addSmelting(Item.rawBronze.shiftedIndex, new ItemStack(Item.ingotBronze));
		//this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
		this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
		this.addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick));
		this.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
		this.addSmelting(Block.netherstone.blockID, new ItemStack(Block.purifiedNetherstone));
		this.addSmelting(Block.netherstonePillar.blockID, new ItemStack(Block.purifiedNetherstonePillar));
		this.addSmelting(Block.netherstoneBricks.blockID, new ItemStack(Block.purifiedNetherstoneBricks));
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
