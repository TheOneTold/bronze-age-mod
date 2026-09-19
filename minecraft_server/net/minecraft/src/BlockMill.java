// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Material, World, EntityPlayer

public class BlockMill extends Block
{

    protected BlockMill(int i)
    {
        super(i, Material.rock);
        blockIndexInTexture = 183;
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture - 16;
        }
        if(i == 0)
        {
            return blockIndexInTexture;
        }
        if(i == 2 || i == 3 || i == 4)
        {
            return blockIndexInTexture + 1;
        } else
        {
            return blockIndexInTexture + 1;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.singleplayerWorld)
        {
            return true;
        } else
        {
            entityplayer.displayMillGUI(i, j, k);
            return true;
        }
    }
}
