// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class BlockHay extends Block
{

    public BlockHay(int i, int j)
    {
        super(i, j, Material.ground);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i <= 1)
        {
            return blockIndexInTexture+1;
        } else
        {
            return blockIndexInTexture;
        }
    }
}
