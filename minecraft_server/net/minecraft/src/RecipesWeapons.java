// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Item, ItemStack, CraftingManager

public class RecipesWeapons
{

    public RecipesWeapons()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.planks, Item.flint, Item.ingotIron, Item.diamond, Item.ingotGold, Item.ingotCopper, Item.ingotTin, Item.ingotBronze
            }, new Object[] {
                Item.swordWood, Item.swordStone, Item.swordSteel, Item.swordDiamond, Item.swordGold, Item.swordCopper, Item.swordTin, Item.swordBronze
            }
        });
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        for(int i = 0; i < recipeItems[0].length; i++)
        {
            Object obj = recipeItems[0][i];
            for(int j = 0; j < recipeItems.length - 1; j++)
            {
                Item item = (Item)recipeItems[j + 1][i];
                craftingmanager.addRecipe(new ItemStack(item), new Object[] {
                    recipePatterns[j], Character.valueOf('#'), Item.stick, Character.valueOf('X'), obj
                });
            }

        }

        craftingmanager.addRecipe(new ItemStack(Item.bow, 1), new Object[] {
            " #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick
        });
        craftingmanager.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {
            "X", "#", "Y", Character.valueOf('Y'), Item.feather, Character.valueOf('X'), Item.flint, Character.valueOf('#'), Item.stick
        });
        craftingmanager.addRecipe(new ItemStack(Item.bow, 1), new Object[]{" #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick});
		craftingmanager.addRecipe(new ItemStack(Item.spearWood, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Block.planks});
		craftingmanager.addRecipe(new ItemStack(Item.spearStone, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.flint});
		craftingmanager.addRecipe(new ItemStack(Item.spearIron, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotIron});
		craftingmanager.addRecipe(new ItemStack(Item.spearDiamond, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.diamond});
		craftingmanager.addRecipe(new ItemStack(Item.spearGold, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotGold});
		craftingmanager.addRecipe(new ItemStack(Item.spearCopper, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotCopper});
		craftingmanager.addRecipe(new ItemStack(Item.spearTin, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotTin});
		craftingmanager.addRecipe(new ItemStack(Item.spearBronze, 1), new Object[]{"  Y", " X ", "X  ", Character.valueOf('X'), Item.stick, Character.valueOf('Y'), Item.ingotBronze});
    }

    private String recipePatterns[][] = {
        {
            "X", "X", "#"
        }
    };
    private Object recipeItems[][];
}
