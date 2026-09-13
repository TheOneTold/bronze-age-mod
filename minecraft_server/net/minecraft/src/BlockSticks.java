// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, Item

public class BlockSticks extends Block
{

    public BlockSticks(int i, int j)
    {
        super(i, j, Material.sticks);
    }

    public int idDropped(int i, Random random)
    {
        return Item.stick.shiftedIndex;
    }

    public int quantityDropped(Random random)
    {
        return 4;
    }
}
