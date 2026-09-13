// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, GuiButton, EntityCow, EntityPig, 
//            EntitySheep, EntityChicken

public class GuiAnimalFarmingInfo extends GuiScreen
{

    public GuiAnimalFarmingInfo(boolean flag, String s, int i, EntityCow entitycow)
    {
        tame = flag;
        name = s;
        timeLeft = i;
        cow = entitycow;
    }

    public GuiAnimalFarmingInfo(boolean flag, String s, int i, EntityPig entitypig)
    {
        tame = flag;
        name = s;
        timeLeft = i;
        pig = entitypig;
    }

    public GuiAnimalFarmingInfo(boolean flag, String s, int i, EntitySheep entitysheep)
    {
        tame = flag;
        name = s;
        timeLeft = i;
        sheep = entitysheep;
    }

    public GuiAnimalFarmingInfo(boolean flag, String s, int i, EntityChicken entitychicken)
    {
        tame = flag;
        name = s;
        timeLeft = i;
        chicken = entitychicken;
    }

    public void initGui()
    {
        controlList.clear();
        Keyboard.enableRepeatEvents(true);
        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120, "Exit"));
        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 60, "Untame"));
        controlList.add(new GuiButton(2, width / 2 - 100, height / 4 + 90, (new StringBuilder()).append("Pause All Breeding(").append((new StringBuilder()).append(breed).append("").toString().toUpperCase()).append(")").toString()));
        GuiButton guibutton = (GuiButton)controlList.get(1);
        guibutton.enabled = false;
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, name, width / 2, 20, 0xffffff);
        if(tame)
        {
            drawCenteredString(fontRenderer, "This Animal Is Tamed", width / 2, 40, 0xffffff);
            drawCenteredString(fontRenderer, (new StringBuilder()).append("It has approximately ").append(Math.round(timeLeft / 25)).append(" seconds left, untill it attempts to reproduce").toString(), width / 2, 70, 0xffffff);
            drawCenteredString(fontRenderer, "(TIP: If the time is not going down, make sure the animal cannot see you!)", width / 2, 100, 0xffffff);
            GuiButton guibutton = (GuiButton)controlList.get(1);
            guibutton.enabled = true;
        } else
        {
            drawCenteredString(fontRenderer, "This Animal Is Not Tamed", width / 2, 70, 0xffffff);
        }
        super.drawScreen(i, j, f);
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 0)
        {
            mc.displayGuiScreen(null);
        }
        if(guibutton.id == 1)
        {
            if(name.toLowerCase().equals("cow"))
            {
                cow.tamed = false;
                tame = false;
            }
            if(name.toLowerCase().equals("pig"))
            {
                pig.tamed = false;
                tame = false;
            }
            if(name.toLowerCase().equals("sheep"))
            {
                sheep.tamed = false;
                tame = false;
            }
            if(name.toLowerCase().equals("chicken"))
            {
                chicken.tamed = false;
                tame = false;
            }
            GuiButton guibutton1 = (GuiButton)controlList.get(1);
            guibutton1.enabled = false;
        }
        if(guibutton.id == 2)
        {
            breed = !breed;
            guibutton.displayString = (new StringBuilder()).append("Pause All Breeding(").append((new StringBuilder()).append(breed).append("").toString().toUpperCase()).append(")").toString();
        }
    }

    boolean tame;
    public static boolean breed = false;
    String name;
    int timeLeft;
    EntityCow cow;
    EntityPig pig;
    EntitySheep sheep;
    EntityChicken chicken;

}
