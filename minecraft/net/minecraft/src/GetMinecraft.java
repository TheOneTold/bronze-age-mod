// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            mod_AnimalFarming

public class GetMinecraft
{

    public GetMinecraft()
    {
    }

    public static Minecraft getMC()
    {
        if(mc == null)
        {
            try
            {
                ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
                int i = threadgroup.activeCount();
                Thread athread[] = new Thread[i];
                threadgroup.enumerate(athread);
                int j = 0;
                do
                {
                    if(j >= athread.length)
                    {
                        break;
                    }
                    if(athread[j].getName().equals("Minecraft main thread"))
                    {
                        mc = (Minecraft)getPrivateValue(java.lang.Thread.class, athread[j], "target");
                        break;
                    }
                    j++;
                } while(true);
            }
            catch(Exception exception)
            {
                System.out.println((new StringBuilder()).append("ERROR: ").append(exception.getMessage()).toString());
                try
                {
                    PrintStream printstream = new PrintStream("ANOTHERERRORLOG.txt");
                    exception.printStackTrace(printstream);
                    printstream.close();
                    BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter("ERRORLOG.txt", false));
                    bufferedwriter.write("ERROR Grabbing Minecraft Instance!:");
                    bufferedwriter.newLine();
                    bufferedwriter.write(exception.getMessage());
                    bufferedwriter.close();
                }
                catch(Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }
            mod_AnimalFarming mod_animalfarming = new mod_AnimalFarming();
        }
        return mc;
    }

    public static Object getPrivateValue(Class class1, Object obj, String s)
        throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            return null;
        }
    }

    public static Minecraft mc;
}
