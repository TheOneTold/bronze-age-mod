// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockSand, Item

public class BlockGravel extends BlockSand
{

    public BlockGravel(int i, int j)
    {
        super(i, j);
    }

    public int idDropped(int i, Random random)
    {
        if (random.nextInt(8) < 3)
        {
            return Item.flint.shiftedIndex;
        } else
        {
            return blockID;
        }
    }
	public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f) {
        if (world.multiplayerWorld) {
            return;
        }
        
        // 30% chance for 2 Flint
        if (world.rand.nextInt(16) < 3) {
            this.dropBlockAsItem_do(world, i, j, k, new ItemStack(Item.flint.shiftedIndex, 2, 0));
        } else {
            // 70% chance for 1 Gravel Block
            this.dropBlockAsItem_do(world, i, j, k, new ItemStack(Block.gravel.blockID, 1, 0));
        }
	}
}
