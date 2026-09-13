// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Properties;

// Referenced classes of package net.minecraft.src:
//            CraftingManager, ItemStack, Item, Block, 
//            StringTranslate, ItemRoundUp

public class mod_AnimalFarming
{

    public mod_AnimalFarming()
    {
        init();
        AddName(roundUp, "RoundUp");
        AddName(mounter, "Mounter");
        CraftingManager.getInstance().addRecipe(new ItemStack(roundUp), new Object[] {
            "#  ", " # ", "  #", Character.valueOf('#'), Item.stick
        });
        CraftingManager.getInstance().addRecipe(new ItemStack(mounter), new Object[] {
            "###", "XYX", Character.valueOf('#'), Item.leather, Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), Item.diamond
        });
    }

    private void init()
    {
    }

    public static void AddName(Item item, String s)
    {
        String s1 = null;
        if(item.getItemName() != null)
        {
            s1 = (new StringBuilder(String.valueOf(item.getItemName()))).append(".name").toString();
        }
        AddTheName(s1, s);
    }

    public static void AddName(Block block, String s)
    {
        String s1 = null;
        if(block.getBlockName() != null)
        {
            s1 = (new StringBuilder(String.valueOf(block.getBlockName()))).append(".name").toString();
        }
        AddTheName(s1, s);
    }

    private static void AddTheName(String s, String s1)
    {
        if(s != null)
        {
            try
            {
                Field field = (net.minecraft.src.StringTranslate.class).getDeclaredFields()[1];
                field.setAccessible(true);
                Properties properties = (Properties)field.get(StringTranslate.getInstance());
                if(properties != null)
                {
                    properties.put(s, s1);
                }
            }
            catch(Exception exception)
            {
                System.out.println("Error Adding ItemName:\n");
                exception.printStackTrace();
            }
        }
    }

    public static Item roundUp = (new ItemRoundUp(9922)).setIconCoord(5, 3).setItemName("roundup");
    public static Item mounter = (new Item(9923)).setIconCoord(8, 6).setMaxStackSize(1).setMaxDamage(100).setItemName("mounter");

}
