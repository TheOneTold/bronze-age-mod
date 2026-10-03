// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockSand, Item

public class BlockDirt extends BlockSand
{

    public BlockDirt(int i, int j)
    {
        super(i, j);
    }
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.multiplayerWorld) {
            return;
        }

	tryToFall(world, x, y, z);

        if (isWater(world, x + 1, y, z) || 
            isWater(world, x - 1, y, z) || 
            isWater(world, x, y + 1, z) || 
            isWater(world, x, y - 1, z) || 
            isWater(world, x, y, z + 1) || 
            isWater(world, x, y, z - 1)) {
            
            world.setBlockWithNotify(x, y, z, Block.mud.blockID); 
        }
    }

    private boolean isWater(World world, int x, int y, int z) {
        int blockID = world.getBlockId(x, y, z);
        return blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID;
    }
    private void tryToFall(World world, int i, int j, int k)
    {
        int l = i;
        int i1 = j;
        int j1 = k;
        if(canFallBelow(world, l, i1 - 1, j1) && i1 >= 0)
        {
            byte byte0 = 32;
            if(fallInstantly || !world.checkChunksExist(i - byte0, j - byte0, k - byte0, i + byte0, j + byte0, k + byte0))
            {
                world.setBlockWithNotify(i, j, k, 0);
                for(; canFallBelow(world, i, j - 1, k) && j > 0; j--) { }
                if(j > 0)
                {
                    world.setBlockWithNotify(i, j, k, blockID);
                }
            } else
            {
                EntityFallingSand entityfallingsand = new EntityFallingSand(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, blockID);
                world.entityJoinedWorld(entityfallingsand);
            }
        }
    }
}
