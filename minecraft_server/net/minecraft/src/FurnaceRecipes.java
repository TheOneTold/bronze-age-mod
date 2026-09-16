// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item

public class FurnaceRecipes
{

    public static final FurnaceRecipes smelting()
    {
        return smeltingBase;
    }

    private FurnaceRecipes()
    {
        smeltingList = new HashMap();
        //addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
		addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
		addSmelting(Block.oreCopper.blockID, new ItemStack(Item.ingotCopper));
		addSmelting(Block.oreTin.blockID, new ItemStack(Item.ingotTin));
		addSmelting(Block.oreSilver.blockID, new ItemStack(Item.ingotSilver));
		//addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
		addSmelting(Item.rawBronze.shiftedIndex, new ItemStack(Item.ingotBronze));
		//addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
		addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
		addSmelting(Block.sand.blockID, new ItemStack(Block.sandStone));
		addSmelting(Block.bloodStone.blockID, new ItemStack(Block.netherstone));
		addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick));
		addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
		addSmelting(Block.netherstone.blockID, new ItemStack(Block.purifiedNetherstone));
		addSmelting(Block.netherstonePillar.blockID, new ItemStack(Block.purifiedNetherstonePillar));
		addSmelting(Block.netherstoneBricks.blockID, new ItemStack(Block.purifiedNetherstoneBricks));
		addSmelting(Block.clayBricks.blockID, new ItemStack(Block.tileBricks));
    }

    public void addSmelting(int i, ItemStack itemstack)
    {
        smeltingList.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack getSmeltingResult(int i)
    {
        return (ItemStack)smeltingList.get(Integer.valueOf(i));
    }

    public Map getSmeltingList()
    {
        return smeltingList;
    }

    private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
    private Map smeltingList;

}
