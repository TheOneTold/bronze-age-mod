// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, InventoryCrafting, InventoryCraftResult, SlotCrafting, 
//            InventoryPlayer, Slot, CraftingManager, IInventory, 
//            World, EntityPlayer, Block, ItemStack

public class ContainerMill extends Container
{
    public ContainerMill(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        craftMatrix = new InventoryCrafting(this, 3, 2);
        craftResult = new InventoryCraftResult();
        field_20133_c = world;
        field_20132_h = i;
        field_20131_i = j;
        field_20130_j = k;
        addSlot(new SlotCrafting(inventoryplayer.player, craftMatrix, craftResult, 0, 124, 35));
        addSlot(new Slot(craftMatrix, 0, 30 + 0 * 18, 17 + 0 * 18));
		addSlot(new Slot(craftMatrix, 1, 30 + 1 * 18, 17 + 0 * 18));
		addSlot(new Slot(craftMatrix, 2, 30 + 2 * 18, 17 + 0 * 18));
		
		
		addSlot(new Slot(craftMatrix, 4, 30 + 1 * 18, 17 + 2 * 18));

        for(int i1 = 0; i1 < 3; i1++)
        {
            for(int l1 = 0; l1 < 9; l1++)
            {
                addSlot(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 84 + i1 * 18));
            }

        }

        for(int j1 = 0; j1 < 9; j1++)
        {
            addSlot(new Slot(inventoryplayer, j1, 8 + j1 * 18, 142));
        }

        onCraftMatrixChanged(craftMatrix);
		
    }

	public void onCraftMatrixChanged(IInventory iinventory)
    {
        craftResult.setInventorySlotContents(0, MillRecipes.getInstance().findMatchingRecipe(craftMatrix));
    }
    

    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
        super.onCraftGuiClosed(entityplayer);
        if(field_20133_c.multiplayerWorld)
        {
            return;
        }
        for(int i = 0; i < 6; i++)
        {
            ItemStack itemstack = craftMatrix.getStackInSlot(i);
            if(itemstack != null)
            {
                entityplayer.dropPlayerItem(itemstack);
            }
        }

    }

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
		if (field_20133_c.multiplayerWorld) {
			
		}
        if(field_20133_c.getBlockId(field_20132_h, field_20131_i, field_20130_j) != Block.mill.blockID)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)field_20132_h + 0.5D, (double)field_20131_i + 0.5D, (double)field_20130_j + 0.5D) <= 64D;
    }

    public ItemStack getStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)slots.get(i);
        
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if(i == 0)
            {
                func_28125_a(itemstack1, 5, 41, true);


                for (int matrixId = 1; matrixId <= 4; matrixId++)
                {
                    Slot matrixSlot = (Slot)slots.get(matrixId);
                    if (matrixSlot != null && matrixSlot.getHasStack())
                    {
                        ItemStack ingredient = matrixSlot.getStack();
                        
                        ingredient.stackSize--;
                        if (ingredient.stackSize <= 0)
                        {
                            matrixSlot.putStack(null);
                        }
                        else
                        {
                            matrixSlot.onSlotChanged();
                        }
                    }
                }

                
                slot.putStack(null);
                
                onCraftMatrixChanged(craftMatrix);
                return itemstack;
            } 
            else if(i >= 5 && i < 32) 
            {
                func_28125_a(itemstack1, 32, 41, false);
            } 
            else if(i >= 32 && i < 41) 
            {
                func_28125_a(itemstack1, 5, 32, false);
            } 
            else 
            {
                func_28125_a(itemstack1, 5, 41, false);
            }
            
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } 
            else 
            {
                slot.onSlotChanged();
            }
            
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(itemstack1);
            } 
            else 
            {
                return null;
            }
        }
        return itemstack;
    }

    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World field_20133_c;
    private int field_20132_h;
    private int field_20131_i;
    private int field_20130_j;
}
