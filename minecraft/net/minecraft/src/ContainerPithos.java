// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, IInventory, Slot, ItemStack, 
//            EntityPlayer

public class ContainerPithos extends Container
{

    public ContainerPithos(IInventory iinventory, IInventory iinventory1)
    {
        field_20125_a = iinventory1;
        field_27282_b = iinventory1.getSizeInventory() / 9;
        int i = (field_27282_b - 4) * 18;
	for (int col = 0; col < 9; ++col) {
    		addSlot(new Slot(iinventory1, col, 8 + col * 18, 18));
	}

	for (int row = 0; row < 3; ++row) {
	    for (int col = 0; col < 9; ++col) {
        	addSlot(new Slot(iinventory, col + row * 9 + 9, 8 + col * 18, 50 + row * 18));
    		}
	}

	for (int col = 0; col < 9; ++col) {
    		addSlot(new Slot(iinventory, col, 8 + col * 18, 108));
	}
    }
    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return field_20125_a.canInteractWith(entityplayer);
    }

    public ItemStack getStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)slots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i < field_27282_b * 9)
            {
                func_28125_a(itemstack1, field_27282_b * 9, slots.size(), true);
            } else
            {
                func_28125_a(itemstack1, 0, field_27282_b * 9, false);
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    private IInventory field_20125_a;
    private int field_27282_b;
}
