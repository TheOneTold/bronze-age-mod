package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MillRecipes {
	private static final MillRecipes instance = new MillRecipes();
	private List recipes = new ArrayList();

	public static final MillRecipes getInstance() {
		return instance;
	}

	private MillRecipes() {

		this.addRecipe(new ItemStack(Item.grapeJuice, 1), new Object[]{"###", " X ", Character.valueOf('#'), Item.grapes, Character.valueOf('X'), Item.bucketEmpty});
		this.addRecipe(new ItemStack(Item.flour, 1), new Object[]{"###", " X ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.bucketEmpty});
		this.addRecipe(new ItemStack(Block.whiteConcrete, 2), new Object[]{"Y#Y", " X ", Character.valueOf('#'), Block.sandStone, Character.valueOf('Y'), Item.gunpowder, Character.valueOf('X'), Item.bucketWater});
		this.addRecipe(new ItemStack(Block.blackConcrete, 2), new Object[]{"Y#Y", " X ", Character.valueOf('#'), Block.stone, Character.valueOf('Y'), Item.gunpowder, Character.valueOf('X'), Item.bucketWater});
		this.addRecipe(new ItemStack(Block.gravel, 4), new Object[]{"###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.gravel, 4), new Object[]{"###", Character.valueOf('#'), Block.sandStone});
		this.addRecipe(new ItemStack(Block.sand, 4), new Object[]{"###", Character.valueOf('#'), Block.gravel});
		this.addRecipe(new ItemStack(Block.blockClay, 4), new Object[]{"###", Character.valueOf('#'), Block.sand});
		this.addRecipe(new ItemStack(Block.cobblestone, 1), new Object[]{"#", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.sandStone, 1), new Object[]{"#", Character.valueOf('#'), Block.smoothSandstone});
		this.addRecipe(new ItemStack(Block.netherrack, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstone});
		this.addRecipe(new ItemStack(Block.netherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstonePillar});
		this.addRecipe(new ItemStack(Block.netherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstoneBricks});
		this.addRecipe(new ItemStack(Block.purifiedNetherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.purifiedNetherstonePillar});
		this.addRecipe(new ItemStack(Block.purifiedNetherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.purifiedNetherstoneBricks});
		this.addRecipe(new ItemStack(Block.smoothSandstone, 1), new Object[]{"#", Character.valueOf('#'), Block.sandstonePillar});
		this.addRecipe(new ItemStack(Block.smoothSandstone, 1), new Object[]{"#", Character.valueOf('#'), Block.sandstoneBricks});
		this.addRecipe(new ItemStack(Block.sandStone, 4), new Object[]{"###", Character.valueOf('#'), Block.smoothSandstone});
		this.addRecipe(new ItemStack(Item.dyePowder, 2, 11), new Object[] {"#", Character.valueOf('#'), Block.plantYellow});
        this.addRecipe(new ItemStack(Item.dyePowder, 2, 1), new Object[] {"#", Character.valueOf('#'), Block.plantRed});
		this.addRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {"#", Character.valueOf('#'), Block.flowerIndigo});
		this.addRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {"#", Character.valueOf('#'), Block.flowerBluebell});
        this.addRecipe(new ItemStack(Item.dyePowder, 3, 15), new Object[] {"#", Character.valueOf('#'), Item.bone});
		
		Collections.sort(this.recipes, new MillSorter(this));
		System.out.println(this.recipes.size() + " recipes");
	}

	void addRecipe(ItemStack var1, Object... var2) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if(var2[var4] instanceof String[]) {
			String[] var11 = (String[])((String[])var2[var4++]);

			for(int var8 = 0; var8 < var11.length; ++var8) {
				String var9 = var11[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while(var2[var4] instanceof String) {
				String var7 = (String)var2[var4++];
				++var6;
				var5 = var7.length();
				var3 = var3 + var7;
			}
		}

		HashMap var12;
		for(var12 = new HashMap(); var4 < var2.length; var4 += 2) {
			Character var13 = (Character)var2[var4];
			ItemStack var15 = null;
			if(var2[var4 + 1] instanceof Item) {
				var15 = new ItemStack((Item)var2[var4 + 1]);
			} else if(var2[var4 + 1] instanceof Block) {
				var15 = new ItemStack((Block)var2[var4 + 1], 1, -1);
			} else if(var2[var4 + 1] instanceof ItemStack) {
				var15 = (ItemStack)var2[var4 + 1];
			}

			var12.put(var13, var15);
		}

		ItemStack[] var14 = new ItemStack[var5 * var6];

		for(int var16 = 0; var16 < var5 * var6; ++var16) {
			char var10 = var3.charAt(var16);
			if(var12.containsKey(Character.valueOf(var10))) {
				var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
			} else {
				var14[var16] = null;
			}
		}

		this.recipes.add(new ShapedRecipes(var5, var6, var14, var1));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting var1) {
		for(int var2 = 0; var2 < this.recipes.size(); ++var2) {
			IRecipe var3 = (IRecipe)this.recipes.get(var2);
			if(var3.matches(var1)) {
				return var3.getCraftingResult(var1);
			}
		}

		return null;
	}

	public List getRecipeList() {
		return this.recipes;
	}
}
