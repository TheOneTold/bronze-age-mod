// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB

public class BlockFence extends Block
{

    public BlockFence(int i, int j)
    {
        super(i, j, Material.wood);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j - 1, k) == blockID)
        {
            return true;
        }
        if(!world.getBlockMaterial(i, j - 1, k).isSolid())
        {
            return false;
        } else
        {
            return super.canPlaceBlockAt(world, i, j, k);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        boolean flag = this.canConnectFenceTo(world, i, j, k - 1);
        boolean flag1 = this.canConnectFenceTo(world, i, j, k + 1);
        boolean flag2 = this.canConnectFenceTo(world, i - 1, j, k);
        boolean flag3 = this.canConnectFenceTo(world, i + 1, j, k);
        
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;
        
        if (flag) {
            f2 = 0.0F;
        }
        if (flag1) {
            f3 = 1.0F;
        }
        if (flag2) {
            f = 0.0F;
        }
        if (flag3) {
            f1 = 1.0F;
        }
        return AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f2, (float)i + f1, (float)j + 1.5F, (float)k + f3);
    }

    public boolean canConnectFenceTo(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockId(i, j, k);
        
        
        if (l == blockID)
        {
            return true;
        }

        Block block = Block.blocksList[l];
        if (block != null)
        {
            
            return block.isOpaqueCube();
        }
        
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        boolean flag = this.canConnectFenceTo(iblockaccess, i, j, k - 1);
        boolean flag1 = this.canConnectFenceTo(iblockaccess, i, j, k + 1);
        boolean flag2 = this.canConnectFenceTo(iblockaccess, i - 1, j, k);
        boolean flag3 = this.canConnectFenceTo(iblockaccess, i + 1, j, k);
        
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;
        
        if (flag) {
            f2 = 0.0F;
        }
        if (flag1) {
            f3 = 1.0F;
        }
        if (flag2) {
            f = 0.0F;
        }
        if (flag3) {
            f1 = 1.0F;
        }
        
        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }


    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 11;
    }
}
