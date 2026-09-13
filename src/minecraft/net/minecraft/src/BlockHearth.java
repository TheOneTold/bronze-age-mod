// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, Block, World, 
//            IBlockAccess, TileEntityFurnace, EntityPlayer, TileEntity, 
//            EntityLiving, MathHelper, IInventory, ItemStack, 
//            EntityItem

public class BlockHearth extends BlockContainer
{

    protected BlockHearth(int i, boolean flag)
    {
        super(i, Material.wood);
        furnaceRand = new Random();
        isActive = flag;
        blockIndexInTexture = 125;
    }

    public int idDropped(int i, Random random)
    {
        return Block.hearthIdle.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		// Top Face
		if(l == 1)
		{
			if(isActive) {
				return blockIndexInTexture + 17;
			} else {
				return blockIndexInTexture + 1;
			}
		}
		
		// Bottom Face
		if(l == 0)
		{
			return blockIndexInTexture - 16;
		}
		
		// Side Faces
		int i1 = iblockaccess.getBlockMetadata(i, j, k);
		if(l == i1)
		{
			if(isActive) {
				return blockIndexInTexture + 16; 
			} else {
				return blockIndexInTexture;
			}
		}
		else
		{
			if(isActive) {
				return blockIndexInTexture + 16;
			} else {
				return blockIndexInTexture;
			}
		}
	}

    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if(!isActive)
        {
            return;
        }
        int l = world.getBlockMetadata(x, y, z);

		float centerX = (float)x + 0.5F;
		float centerY = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
		float centerZ = (float)z + 0.5F;
		
		float offsetOutward = 0.52F; 
		float randomSpread = random.nextFloat() * 0.6F - 0.3F;

		// --- WEST FACE ---
		world.spawnParticle("smoke", centerX - offsetOutward, centerY, centerZ + randomSpread, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", centerX - offsetOutward, centerY, centerZ + randomSpread, 0.0D, 0.0D, 0.0D);

		// --- EAST FACE ---
		world.spawnParticle("smoke", centerX + offsetOutward, centerY, centerZ + randomSpread, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", centerX + offsetOutward, centerY, centerZ + randomSpread, 0.0D, 0.0D, 0.0D);

		// --- NORTH FACE ---
		world.spawnParticle("smoke", centerX + randomSpread, centerY, centerZ - offsetOutward, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", centerX + randomSpread, centerY, centerZ - offsetOutward, 0.0D, 0.0D, 0.0D);

		// --- SOUTH FACE ---
		world.spawnParticle("smoke", centerX + randomSpread, centerY, centerZ + offsetOutward, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", centerX + randomSpread, centerY, centerZ + offsetOutward, 0.0D, 0.0D, 0.0D);
		
		// --- TOP FACE ---
		float topY = (float)y + 1.02F;
        

        for (int count = 0; count < 5; count++) {

            float randomSpreadX = random.nextFloat() * 0.4F - 0.2F;
            float randomSpreadZ = random.nextFloat() * 0.4F - 0.2F;
            


            world.spawnParticle("smoke", centerX + randomSpreadX, topY, centerZ + randomSpreadZ, 0.0D, 0.1D, 0.0D);
        }
        

        if (random.nextInt(4) == 0) {
            float randomSpreadX = random.nextFloat() * 0.4F - 0.2F;
            float randomSpreadZ = random.nextFloat() * 0.4F - 0.2F;
            world.spawnParticle("flame", centerX + randomSpreadX, topY, centerZ + randomSpreadZ, 0.0D, 0.0D, 0.0D);
        }
		
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture + 17;
        }
        if(i == 0)
        {
            return blockIndexInTexture - 16;
        }
        if(i == 3)
        {
            return blockIndexInTexture - 1;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.multiplayerWorld)
        {
            return true;
        } else
        {
            TileEntityHearth tileentityhearth = (TileEntityHearth)world.getBlockTileEntity(i, j, k);
            entityplayer.displayGUIHearth(tileentityhearth);
            return true;
        }
    }

    public static void updateHearthBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepHearthInventory = true;
        if(flag)
        {
            world.setBlockWithNotify(i, j, k, Block.hearthActive.blockID);
        } else
        {
            world.setBlockWithNotify(i, j, k, Block.hearthIdle.blockID);
        }
        keepHearthInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l);
        tileentity.func_31004_j();
        world.setBlockTileEntity(i, j, k, tileentity);
    }

    protected TileEntity getBlockEntity()
    {
        return new TileEntityHearth();
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if(!keepHearthInventory)
        {
            TileEntityHearth tileentityhearth = (TileEntityHearth)world.getBlockTileEntity(i, j, k);
label0:
            for(int l = 0; l < tileentityhearth.getSizeInventory(); l++)
            {
                ItemStack itemstack = tileentityhearth.getStackInSlot(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
                float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
                float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.stackSize <= 0)
                    {
                        continue label0;
                    }
                    int i1 = furnaceRand.nextInt(21) + 10;
                    if(i1 > itemstack.stackSize)
                    {
                        i1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float)furnaceRand.nextGaussian() * f3;
                    entityitem.motionY = (float)furnaceRand.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float)furnaceRand.nextGaussian() * f3;
                    world.entityJoinedWorld(entityitem);
                } while(true);
            }

        }
        super.onBlockRemoval(world, i, j, k);
    }

    private Random furnaceRand;
    private final boolean isActive;
    private static boolean keepHearthInventory = false;

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		// Top Face
		if (side == 1) {
			if (isActive) {
				return blockIndexInTexture + 17;
			} else {
				return blockIndexInTexture + 1;
			}
		}
		
		// Bottom Face
		if (side == 0) {
			return blockIndexInTexture - 16;
		}
		
		// Side Faces
		if (side == 3) {
			if (isActive) {
				return blockIndexInTexture + 16;
			} else {
				return blockIndexInTexture;      
			}
		}
		
	
		if (isActive) {
			return blockIndexInTexture + 16;     
		} else {
			return blockIndexInTexture;          
		}
	}

}
