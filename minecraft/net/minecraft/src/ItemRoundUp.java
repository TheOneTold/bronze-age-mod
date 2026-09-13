// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

// Referenced classes of package net.minecraft.src:
//            Item, GetMinecraft, EntityPlayerSP, World, 
//            EntityPlayer, ItemStack, Entity

public class ItemRoundUp extends Item
{

    protected ItemRoundUp(int i)
    {
        super(i);
    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
        if(flag)
        {
            if(Mouse.getEventButtonState() && !on)
            {
                if(Mouse.getEventButton() == 0 && GetMinecraft.getMC().inGameHasFocus)
                {
                    on = true;
                    onClick();
                }
            } else
            if(!Mouse.getEventButtonState())
            {
                on = false;
            }
        }
    }

    private void onClick()
    {
        String s = "";
        switch(posi)
        {
        case 1: // '\001'
            posi = 2;
            s = "Pigs only";
            break;

        case 2: // '\002'
            posi = 3;
            s = "Sheep only";
            break;

        case 3: // '\003'
            posi = 4;
            s = "Chickens only";
            break;

        case 4: // '\004'
            posi = 5;
            s = "All Tamed Animals";
            break;

        case 5: // '\005'
            posi = 1;
            s = "Cows only";
            break;
        }
        GetMinecraft.getMC().thePlayer.addChatMessage(s);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(pos)
        {
            pos = false;
            world.playSoundAtEntity(entityplayer, "random.click", 0.3F, 0.3F);
            entityplayer.addChatMessage("Animals Will Stop Following You");
        } else
        {
            pos = true;
            world.playSoundAtEntity(entityplayer, "random.click", 0.3F, 0.7F);
            entityplayer.addChatMessage("Animals Will Now Follow You");
        }
        return itemstack;
    }

    public static boolean pos = false;
    public static boolean on = false;
    public static int posi = 5;

}
