package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(Block.chest), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Block.hearthIdle), new Object[]{"###", "# #", "#X#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Block.sticks});
		var1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.cobbleBricks});
		var1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Block.mill), new Object[]{"# #", "# #", "###", Character.valueOf('#'), Block.cobbleBricks});
		var1.addRecipe(new ItemStack(Block.sticks), new Object[]{"##", "##", Character.valueOf('#'), Item.stick});
		var1.addRecipe(new ItemStack(Item.stick, 4), new Object[]{"#", Character.valueOf('#'), Block.sticks});
		var1.addRecipe(new ItemStack(Item.coinBronze, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotBronze});
		var1.addRecipe(new ItemStack(Item.coinSilver, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotSilver});
		var1.addRecipe(new ItemStack(Item.coinGold, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.ingotBronze), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinBronze});
		var1.addRecipe(new ItemStack(Item.ingotSilver), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinSilver});
		var1.addRecipe(new ItemStack(Item.ingotGold), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinGold});
		var1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", Character.valueOf('#'), Block.sticks});
		var1.addRecipe(new ItemStack(Block.cobbleBricks, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.bowDrill, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.silk});
		var1.addRecipe(new ItemStack(Item.silk, 4), new Object[]{"#", Character.valueOf('#'), Block.cloth});
		var1.addRecipe(new ItemStack(Item.clay, 4), new Object[]{"#", Character.valueOf('#'), Block.blockClay});
		var1.addRecipe(new ItemStack(Item.wine, 1), new Object[]{"#", Character.valueOf('#'), Item.grapeJuice});
		var1.addRecipe(new ItemStack(Item.helmetRebreather, 1), new Object[]{"# #", "#X#", " # ", Character.valueOf('#'), Item.ingotSilver, Character.valueOf('X'), Block.sponge});
		var1.addRecipe(new ItemStack(Block.sponge), new Object[]{"###", "###", "###", Character.valueOf('#'), Block.sand});
		
		var1.addRecipe(new ItemStack(Item.cupWood, 3), new Object[]{"# #", " # ", " # ", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Item.cupBronze, 3), new Object[]{"# #", " # ", " # ", Character.valueOf('#'), Item.ingotBronze});
		var1.addRecipe(new ItemStack(Item.cupSilver, 3), new Object[]{"# #", " # ", " # ", Character.valueOf('#'), Item.ingotSilver});
		var1.addRecipe(new ItemStack(Item.cupGold, 3), new Object[]{"# #", " # ", " # ", Character.valueOf('#'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.cupWoodGrapeJuice, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.grapeJuice, Character.valueOf('X'), Item.cupWood});
		var1.addRecipe(new ItemStack(Item.cupWoodMilk, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.bucketMilk, Character.valueOf('X'), Item.cupWood});
		var1.addRecipe(new ItemStack(Item.cupWoodWine, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.wine, Character.valueOf('X'), Item.cupWood});
		var1.addRecipe(new ItemStack(Item.cupBronzeGrapeJuice, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.grapeJuice, Character.valueOf('X'), Item.cupBronze});
		var1.addRecipe(new ItemStack(Item.cupBronzeMilk, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.bucketMilk, Character.valueOf('X'), Item.cupBronze});
		var1.addRecipe(new ItemStack(Item.cupBronzeWine, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.wine, Character.valueOf('X'), Item.cupBronze});
		var1.addRecipe(new ItemStack(Item.cupSilverGrapeJuice, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.grapeJuice, Character.valueOf('X'), Item.cupSilver});
		var1.addRecipe(new ItemStack(Item.cupSilverMilk, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.bucketMilk, Character.valueOf('X'), Item.cupSilver});
		var1.addRecipe(new ItemStack(Item.cupSilverWine, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.wine, Character.valueOf('X'), Item.cupSilver});
		var1.addRecipe(new ItemStack(Item.cupGoldGrapeJuice, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.grapeJuice, Character.valueOf('X'), Item.cupGold});
		var1.addRecipe(new ItemStack(Item.cupGoldMilk, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.bucketMilk, Character.valueOf('X'), Item.cupGold});
		var1.addRecipe(new ItemStack(Item.cupGoldWine, 3), new Object[]{"#X", "XX", Character.valueOf('#'), Item.wine, Character.valueOf('X'), Item.cupGold});
		var1.addRecipe(new ItemStack(Item.rawPithos), new Object[]{"# #", "# #", "###", Character.valueOf('#'), Item.clay});
		var1.addRecipe(new ItemStack(Block.flowerWhiteRoseSingle, 3), new Object[]{"#", Character.valueOf('#'), Block.flowerWhiteRose});
		var1.addRecipe(new ItemStack(Block.flowerRedRoseSingle, 3), new Object[]{"#", Character.valueOf('#'), Block.plantRed});
	        var1.addRecipe(new ItemStack(Block.flowerWhiteRoseSingle, 3), new Object[]{"#", Character.valueOf('#'), Block.flowerWhiteRose});
		var1.addRecipe(new ItemStack(Block.flowerRedRoseSingle, 3), new Object[]{"#", Character.valueOf('#'), Block.plantRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaGray, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 8), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaPink, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaGreen, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 2), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaLime, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 10), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaBrown, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 3), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaYellow, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaBlue, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 4), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaCyan, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 6), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaLightBlue, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaOrange, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 14), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaIndigo, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaLightGray, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 7), Character.valueOf('X'), Block.paintedTerracottaRed});
    		var1.addRecipe(new ItemStack(Block.paintedTerracottaViolet, 3), new Object[]{"#X","XX", Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13), Character.valueOf('X'), Block.paintedTerracottaRed});
		var1.addRecipe(new ItemStack(Block.leadedGlass, 9), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.glassPane, Character.valueOf('X'), Item.ingotLead});
		var1.addRecipe(new ItemStack(Block.mud, 8), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.dirt, Character.valueOf('X'), Item.bucketWater});
		var1.addRecipe(new ItemStack(Block.mudBricks, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.mud});
		
		var1.addRecipe(new ItemStack(Block.stainedGlass, 9), new Object[]{"C#D", "#X#", "A#B", Character.valueOf('#'), Item.glassPane, Character.valueOf('X'), Item.ingotLead, Character.valueOf('A'), new ItemStack(Item.dyePowder, 1, 11), Character.valueOf('B'), new ItemStack(Item.dyePowder, 1, 12), Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 10) });
	}
}
