package net.minecraft.src;

public class RecipesWeapons {
	private String[][] recipePatterns = new String[][]{{"X", "X", "#"}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Item.flint, Item.ingotIron, Item.diamond, Item.ingotGold, Item.ingotCopper, Item.ingotTin, Item.ingotBronze}, {Item.swordWood, Item.swordStone, Item.swordSteel, Item.swordDiamond, Item.swordGold, Item.swordCopper, Item.swordTin, Item.swordBronze}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.bow, 1), new Object[]{" #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick});
		var1.addRecipe(new ItemStack(Item.spearWood, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Block.planks});
		var1.addRecipe(new ItemStack(Item.spearStone, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.flint});
		var1.addRecipe(new ItemStack(Item.spearIron, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.spearDiamond, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.diamond});
		var1.addRecipe(new ItemStack(Item.spearGold, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.spearCopper, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotCopper});
		var1.addRecipe(new ItemStack(Item.spearTin, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotTin});
		var1.addRecipe(new ItemStack(Item.spearBronze, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotBronze});
		var1.addRecipe(new ItemStack(Item.arrow, 4), new Object[]{"X", "#", "Y", Character.valueOf('Y'), Item.feather, Character.valueOf('X'), Item.flint, Character.valueOf('#'), Item.stick});
	}
}
