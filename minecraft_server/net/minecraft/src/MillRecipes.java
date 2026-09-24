// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            RecipesTools, RecipesWeapons, RecipesIngots, RecipesFood, 
//            RecipesCrafting, RecipesArmor, RecipesDyes, ItemStack, 
//            Item, Block, RecipeSorter, ShapedRecipes, 
//            ShapelessRecipes, IRecipe, InventoryCrafting

public class MillRecipes
{

    public static final MillRecipes getInstance()
    {
        return instance;
    }

    private MillRecipes()
    {
        recipes = new ArrayList();
        
		addRecipe(new ItemStack(Item.grapeJuice, 1), new Object[]{"###", " X ", Character.valueOf('#'), Item.grapes, Character.valueOf('X'), Item.bucketEmpty});
		addRecipe(new ItemStack(Item.flour, 1), new Object[]{"###", " X ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.bucketEmpty});
		addRecipe(new ItemStack(Block.whiteConcrete, 2), new Object[]{"Y#Y", " X ", Character.valueOf('#'), Block.sandStone, Character.valueOf('Y'), Item.gunpowder, Character.valueOf('X'), Item.bucketWater});
		addRecipe(new ItemStack(Block.blackConcrete, 2), new Object[]{"Y#Y", " X ", Character.valueOf('#'), Block.stone, Character.valueOf('Y'), Item.gunpowder, Character.valueOf('X'), Item.bucketWater});
		addRecipe(new ItemStack(Block.gravel, 4), new Object[]{"###", Character.valueOf('#'), Block.cobblestone});
		addRecipe(new ItemStack(Block.gravel, 4), new Object[]{"###", Character.valueOf('#'), Block.sandStone});
		addRecipe(new ItemStack(Block.sand, 4), new Object[]{"###", Character.valueOf('#'), Block.gravel});
		addRecipe(new ItemStack(Block.blockClay, 4), new Object[]{"###", Character.valueOf('#'), Block.sand});
		addRecipe(new ItemStack(Block.cobblestone, 1), new Object[]{"#", Character.valueOf('#'), Block.stone});
		addRecipe(new ItemStack(Block.sandStone, 1), new Object[]{"#", Character.valueOf('#'), Block.smoothSandstone});
		addRecipe(new ItemStack(Block.bloodStone, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstone});
		addRecipe(new ItemStack(Block.netherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstonePillar});
		addRecipe(new ItemStack(Block.netherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.netherstoneBricks});
		addRecipe(new ItemStack(Block.purifiedNetherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.purifiedNetherstonePillar});
		addRecipe(new ItemStack(Block.purifiedNetherstone, 1), new Object[]{"#", Character.valueOf('#'), Block.purifiedNetherstoneBricks});
		addRecipe(new ItemStack(Block.smoothSandstone, 1), new Object[]{"#", Character.valueOf('#'), Block.sandstonePillar});
		addRecipe(new ItemStack(Block.smoothSandstone, 1), new Object[]{"#", Character.valueOf('#'), Block.sandstoneBricks});
		addRecipe(new ItemStack(Block.sandStone, 4), new Object[]{"###", Character.valueOf('#'), Block.smoothSandstone});
		addRecipe(new ItemStack(Item.dyePowder, 2, 11), new Object[] {"#", Character.valueOf('#'), Block.plantYellow});
        addRecipe(new ItemStack(Item.dyePowder, 2, 1), new Object[] {"#", Character.valueOf('#'), Block.plantRed});
		addRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {"#", Character.valueOf('#'), Block.flowerIndigo});
		addRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {"#", Character.valueOf('#'), Block.flowerBluebell});
        addRecipe(new ItemStack(Item.dyePowder, 3, 15), new Object[] {"#", Character.valueOf('#'), Item.bone});
	addRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {"#", Character.valueOf('#'), Block.flowerMarigold});	
        addRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] {"#", Character.valueOf('#'), Block.flowerViolet});
        addRecipe(new ItemStack(Item.dyePowder, 2, 15), new Object[] {"#", Character.valueOf('#'), Block.flowerDaisy});
        addRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {"#", Character.valueOf('#'), Block.flowerTulip});
        addRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] {"#", Character.valueOf('#'), Block.flowerHydrangea});
        addRecipe(new ItemStack(Item.dyePowder, 2, 1), new Object[] {"#", Character.valueOf('#'), Block.flowerPoppy});
		
        Collections.sort(recipes, new MillSorter(this));
        System.out.println((new StringBuilder()).append(recipes.size()).append(" recipes").toString());
    }

    void addRecipe(ItemStack itemstack, Object aobj[])
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
        if(aobj[i] instanceof String[])
        {
            String as[] = (String[])aobj[i++];
            for(int l = 0; l < as.length; l++)
            {
                String s2 = as[l];
                k++;
                j = s2.length();
                s = (new StringBuilder()).append(s).append(s2).toString();
            }

        } else
        {
            while(aobj[i] instanceof String) 
            {
                String s1 = (String)aobj[i++];
                k++;
                j = s1.length();
                s = (new StringBuilder()).append(s).append(s1).toString();
            }
        }
        HashMap hashmap = new HashMap();
        for(; i < aobj.length; i += 2)
        {
            Character character = (Character)aobj[i];
            ItemStack itemstack1 = null;
            if(aobj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)aobj[i + 1]);
            } else
            if(aobj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)aobj[i + 1], 1, -1);
            } else
            if(aobj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)aobj[i + 1];
            }
            hashmap.put(character, itemstack1);
        }

        ItemStack aitemstack[] = new ItemStack[j * k];
        for(int i1 = 0; i1 < j * k; i1++)
        {
            char c = s.charAt(i1);
            if(hashmap.containsKey(Character.valueOf(c)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).copy();
            } else
            {
                aitemstack[i1] = null;
            }
        }

        recipes.add(new ShapedRecipes(j, k, aitemstack, itemstack));
    }



    public ItemStack findMatchingRecipe(InventoryCrafting inventorycrafting)
    {
        for(int i = 0; i < recipes.size(); i++)
        {
            IRecipe irecipe = (IRecipe)recipes.get(i);
            if(irecipe.func_21134_a(inventorycrafting))
            {
                return irecipe.func_21136_b(inventorycrafting);
            }
        }

        return null;
    }

    public List getRecipeList()
    {
        return recipes;
    }

    private static final MillRecipes instance = new MillRecipes();
    private List recipes;

}
