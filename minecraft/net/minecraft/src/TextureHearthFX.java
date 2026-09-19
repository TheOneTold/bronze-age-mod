// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            TextureFX, Item, World, WorldProvider

public class TextureHearthFX extends TextureFX
{

    public TextureHearthFX()
    {
        super(Block.hearthActive.blockIndexInTexture+16);
        flame_old = new float[320];
        flame_new = new float[320];
        hearthImageData = new int[256];
        try
        {
            BufferedImage bufferedimage = ImageIO.read((net.minecraft.client.Minecraft.class).getResource("/terrain.png"));
            int i = (iconIndex % 16) * 16;
            int j = (iconIndex / 16) * 16;
            bufferedimage.getRGB(i, j, 16, 16, hearthImageData, 0, 16);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void onTick()
    {
        for(int i = 0; i < 16; i++)
        {
            for(int j=0; j<20;j++)
            {
                int multiplier = 18;
                float newPixel = flame_old[i + ((j + 1) % 20) * 16] * (float)multiplier;
                for(int i1 = i - 1; i1 <= i + 1; i1++)
                {
                    for(int j1 = j; j1 <= j + 1; j1++)
                    {
                        if(i1 >= 0 && j1 >= 0 && i1 < 16 && j1 < 20)
                        {
                            newPixel += flame_old[i1 + j1 * 16];
                        }
                        multiplier ++;
                    }

                }

                flame_new[i + j * 16] = newPixel / ((float)multiplier * 1.06F);
                if(j >= 19)
                {
                    flame_new[i + j * 16] = (float)(Math.random() * Math.random() * Math.random() * 4D + Math.random() * 0.10000000149011612D + 0.20000000298023224D);
                }
                /*int l = 18;
                float f1 = flame_old[i + ((j + 1) % 20) * 16] * (float)l;
                for(int i1 = i - 1; i1 <= i + 1; i1++)
                {
                    for(int k1 = j; k1 <= j + 1; k1++)
                    {
                        int i2 = i1;
                        int k2 = k1;
                        if(i2 >= 0 && k2 >= 0 && i2 < 16 && k2 < 20)
                        {
                            f1 += flame_old[i2 + k2 * 16];
                        }
                        l++;
                    }

                }

                flame_new[i + j * 16] = f1 / ((float)l * 1.06F);
                if(j >= 19)
                {
                    flame_new[i + j * 16] = (float)(Math.random() * Math.random() * Math.random() * 4D + Math.random() * 0.10000000149011612D + 0.20000000298023224D);
                }*/
            }
        }
        float flame_temp[] = flame_new;
        flame_new = flame_old;
        flame_old = flame_temp;
        for(int i = 0; i < 256; i++)
        {
            int b = hearthImageData[i] >> 0 & 0xff;
            int g = hearthImageData[i] >> 8 & 0xff;
            int r = hearthImageData[i] >> 16 & 0xff;
            int a = hearthImageData[i] >> 24 & 0xff;
            float imagePixel = flame_new[i] * 1.6f;
            if(r == b && g == 0 && b > 0)
            {
                if(imagePixel >= 0.5f)
                {
                    if(imagePixel > 1.0f) imagePixel = 1.0f;
                    r = (int)(imagePixel * 155F + 100F);
                    g = (int)(imagePixel * imagePixel * 255F);
                    b = (int)(imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * imagePixel * 255F);
                }else
                {
                     r = 0;
                     g = 0;
                     b = 0;
                 }
                a = 255;
            }
            imageData[i * 4 + 0] = (byte)r;
            imageData[i * 4 + 1] = (byte)g;
            imageData[i * 4 + 2] = (byte)b;
            imageData[i * 4 + 3] = (byte)a;
        }

    }

    private int hearthImageData[];
    private float flame_new[];
    private float flame_old[];
}
