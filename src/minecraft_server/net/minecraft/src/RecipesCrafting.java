// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemStack, Block, CraftingManager

public class RecipesCrafting
{

    public RecipesCrafting()
    {
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        craftingmanager.addRecipe(new ItemStack(Block.chest), new Object[] {
            "###", "# #", "###", Character.valueOf('#'), Block.planks
        });
        craftingmanager.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.stoneBricks});
        craftingmanager.addRecipe(new ItemStack(Block.workbench), new Object[] {
            "##", "##", Character.valueOf('#'), Block.planks
        });
        craftingmanager.addRecipe(new ItemStack(Block.sandStone), new Object[] {
            "##", "##", Character.valueOf('#'), Block.sand
        });
        craftingmanager.addRecipe(new ItemStack(Block.hearthIdle), new Object[]{"###", "# #", "#X#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Block.sticks});
		craftingmanager.addRecipe(new ItemStack(Block.sticks), new Object[]{"##", "##", Character.valueOf('#'), Item.stick});
		craftingmanager.addRecipe(new ItemStack(Item.coinCopper, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotCopper});
		craftingmanager.addRecipe(new ItemStack(Item.coinSilver, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotSilver});
		craftingmanager.addRecipe(new ItemStack(Item.coinGold, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotGold});
		craftingmanager.addRecipe(new ItemStack(Item.ingotCopper), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinCopper});
		craftingmanager.addRecipe(new ItemStack(Item.ingotSilver), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinSilver});
		craftingmanager.addRecipe(new ItemStack(Item.ingotGold), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.coinGold});
		craftingmanager.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", Character.valueOf('#'), Block.sticks});
		craftingmanager.addRecipe(new ItemStack(Item.stick, 4), new Object[]{"#", Character.valueOf('#'), Block.sticks});
		craftingmanager.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", Character.valueOf('#'), Block.sand});
    }
}
