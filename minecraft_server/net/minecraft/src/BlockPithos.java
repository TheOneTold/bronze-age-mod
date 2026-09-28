// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, World, TileEntityChest, 
//            IInventory, ItemStack, EntityItem, InventoryLargeChest, 
//            EntityPlayer, TileEntity

public class BlockPithos extends BlockContainer
{

    protected BlockPithos(int i)
    {
        super(i, Material.wood);
        random = new Random();
        blockIndexInTexture = 196;
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture + 1;
        }
        if(i == 0)
        {
            return blockIndexInTexture + 2;
        }
        if(i == 3)
        {
            return blockIndexInTexture;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        TileEntityPithos tileentitypithos = (TileEntityPithos)world.getBlockTileEntity(i, j, k);
label0:
        for(int l = 0; l < tileentitypithos.getSizeInventory(); l++)
        {
            ItemStack itemstack = tileentitypithos.getStackInSlot(l);
            if(itemstack == null)
            {
                continue;
            }
            float f = random.nextFloat() * 0.8F + 0.1F;
            float f1 = random.nextFloat() * 0.8F + 0.1F;
            float f2 = random.nextFloat() * 0.8F + 0.1F;
            do
            {
                if(itemstack.stackSize <= 0)
                {
                    continue label0;
                }
                int i1 = random.nextInt(21) + 10;
                if(i1 > itemstack.stackSize)
                {
                    i1 = itemstack.stackSize;
                }
                itemstack.stackSize -= i1;
                EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                float f3 = 0.05F;
                entityitem.motionX = (float)random.nextGaussian() * f3;
                entityitem.motionY = (float)random.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float)random.nextGaussian() * f3;
                world.entityJoinedWorld(entityitem);
            } while(true);
        }

        super.onBlockRemoval(world, i, j, k);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        Object obj = (TileEntityPithos)world.getBlockTileEntity(i, j, k);
        if(world.isBlockNormalCube(i, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i - 1, j, k) == blockID && world.isBlockNormalCube(i - 1, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i + 1, j, k) == blockID && world.isBlockNormalCube(i + 1, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i, j, k - 1) == blockID && world.isBlockNormalCube(i, j + 1, k - 1))
        {
            return true;
        }
        if(world.getBlockId(i, j, k + 1) == blockID && world.isBlockNormalCube(i, j + 1, k + 1))
        {
            return true;
        }
        if(world.singleplayerWorld)
        {
            return true;
        } else
        {
            entityplayer.displayGUIPithos(((IInventory) (obj)));
            return true;
        }
    }

    protected TileEntity getBlockEntity()
    {
        return new TileEntityPithos();
    }

    private Random random;
}
