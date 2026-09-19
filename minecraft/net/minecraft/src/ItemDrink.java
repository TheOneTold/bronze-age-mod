// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

public class ItemDrink extends Item
{

    public ItemDrink(int i, int j, int k, int l)
    {
        super(i);
        healAmount = j;
        maxStackSize = k;
		containerType = l;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        entityplayer.heal(healAmount);
        
       
        Item containerItem = Item.bucketEmpty;
        if (containerType == 1) containerItem = Item.cupWood;
        else if (containerType == 2) containerItem = Item.cupBronze;
        else if (containerType == 3) containerItem = Item.cupSilver;
        else if (containerType == 4) containerItem = Item.cupGold;

        
        if (itemstack.stackSize <= 0)
        {
            
            return new ItemStack(containerItem);
        }
        else
        {
            
            if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(containerItem)))
            {
                
                entityplayer.dropPlayerItem(new ItemStack(containerItem));
            }
            
   
            return itemstack;
        }
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    private int healAmount;
	private int containerType;
}
