// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            TileEntity, IInventory, ItemStack, NBTTagCompound, 
//            NBTTagList, World, Blockhearth, hearthRecipes, 
//            Item, Block, Material, EntityPlayer

public class TileEntityHearth extends TileEntity
    implements IInventory
{

    public TileEntityHearth()
    {
        hearthItemStacks = new ItemStack[3];
        hearthBurnTime = 0;
        currentItemBurnTime = 0;
        hearthCookTime = 0;
    }

    public int getSizeInventory()
    {
        return hearthItemStacks.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        return hearthItemStacks[i];
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(hearthItemStacks[i] != null)
        {
            if(hearthItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = hearthItemStacks[i];
                hearthItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = hearthItemStacks[i].splitStack(j);
            if(hearthItemStacks[i].stackSize == 0)
            {
                hearthItemStacks[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        hearthItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "Hearth";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        hearthItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if(byte0 >= 0 && byte0 < hearthItemStacks.length)
            {
                hearthItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }

        hearthBurnTime = nbttagcompound.getShort("BurnTime");
        hearthCookTime = nbttagcompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(hearthItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)hearthBurnTime);
        nbttagcompound.setShort("CookTime", (short)hearthCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < hearthItemStacks.length; i++)
        {
            if(hearthItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                hearthItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.setTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int i)
    {
        return (hearthCookTime * i) / 200;
    }

    public int getBurnTimeRemainingScaled(int i)
    {
        if(currentItemBurnTime == 0)
        {
            currentItemBurnTime = 200;
        }
        return (hearthBurnTime * i) / currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return hearthBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = hearthBurnTime > 0;
        boolean flag1 = false;
        if(hearthBurnTime > 0)
        {
            hearthBurnTime--;
        }
        if(!worldObj.multiplayerWorld)
        {
            if(hearthBurnTime == 0 && canSmelt())
            {
                currentItemBurnTime = hearthBurnTime = getItemBurnTime(hearthItemStacks[1]);
                if(hearthBurnTime > 0)
                {
                    flag1 = true;
                    if(hearthItemStacks[1] != null)
                    {
                        hearthItemStacks[1].stackSize--;
                        if(hearthItemStacks[1].stackSize == 0)
                        {
                            hearthItemStacks[1] = null;
                        }
                    }
                }
            }
            if(isBurning() && canSmelt())
            {
                hearthCookTime++;
                if(hearthCookTime == 200)
                {
                    hearthCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else
            {
                hearthCookTime = 0;
            }
            if(flag != (hearthBurnTime > 0))
            {
                flag1 = true;
                BlockHearth.updateHearthBlockState(hearthBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if(hearthItemStacks[0] == null)
        {
            return false;
        }
        ItemStack itemstack = HearthRecipes.smelting().getSmeltingResult(hearthItemStacks[0].getItem().shiftedIndex);
        if(itemstack == null)
        {
            return false;
        }
        if(hearthItemStacks[2] == null)
        {
            return true;
        }
        if(!hearthItemStacks[2].isItemEqual(itemstack))
        {
            return false;
        }
        if(hearthItemStacks[2].stackSize < getInventoryStackLimit() && hearthItemStacks[2].stackSize < hearthItemStacks[2].getMaxStackSize())
        {
            return true;
        }
        return hearthItemStacks[2].stackSize < itemstack.getMaxStackSize();
    }

    public void smeltItem()
    {
        if(!canSmelt())
        {
            return;
        }
        ItemStack itemstack = HearthRecipes.smelting().getSmeltingResult(hearthItemStacks[0].getItem().shiftedIndex);
        if(hearthItemStacks[2] == null)
        {
            hearthItemStacks[2] = itemstack.copy();
        } else
        if(hearthItemStacks[2].itemID == itemstack.itemID)
        {
            hearthItemStacks[2].stackSize++;
        }
        hearthItemStacks[0].stackSize--;
        if(hearthItemStacks[0].stackSize <= 0)
        {
            hearthItemStacks[0] = null;
        }
    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().shiftedIndex;
        if(i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
        {
            return 300;
        }
        if(i == Item.stick.shiftedIndex)
        {
            return 100;
        }
        if(i == Item.coal.shiftedIndex)
        {
            return 1600;
        }
        if(i == Item.bucketLava.shiftedIndex)
        {
            return 20000;
        }
        return i != Block.sapling.blockID ? 0 : 100;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }

    private ItemStack hearthItemStacks[];
    public int hearthBurnTime;
    public int currentItemBurnTime;
    public int hearthCookTime;
}
