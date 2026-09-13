// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, GetMinecraft, World, Item, 
//            EntityPlayerSP, ItemStack, mod_AnimalFarming, ItemRoundUp, 
//            GuiAnimalFarmingInfo, AxisAlignedBB, Entity, NBTTagCompound, 
//            EntityPlayer

public class EntityChicken extends EntityAnimal
{

    public EntityChicken(World world)
    {
        super(world);
        tamed = false;
        urged = false;
        birthtime = getBTime();
        mc = GetMinecraft.getMC();
        waiter = 500;
        field_753_a = false;
        field_752_b = 0.0F;
        destPos = 0.0F;
        field_755_h = 1.0F;
        texture = "/mob/chicken.png";
        setSize(0.3F, 0.4F);
        health = 4;
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
        tamed = false;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        field_756_e = field_752_b;
        field_757_d = destPos;
        destPos += (double)(onGround ? -1 : 4) * 0.29999999999999999D;
        if(destPos < 0.0F)
        {
            destPos = 0.0F;
        }
        if(destPos > 1.0F)
        {
            destPos = 1.0F;
        }
        if(!onGround && field_755_h < 1.0F)
        {
            field_755_h = 1.0F;
        }
        field_755_h *= 0.90000000000000002D;
        if(!onGround && motionY < 0.0D)
        {
            motionY *= 0.59999999999999998D;
        }
        field_752_b += field_755_h * 2.0F;
        if(!worldObj.multiplayerWorld && --timeUntilNextEgg <= 0)
        {
            worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            dropItem(Item.egg.shiftedIndex, 1);
            timeUntilNextEgg = rand.nextInt(6000) + 6000;
        }
        if(!worldObj.multiplayerWorld)
        {
            if(tamed && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().itemID == mod_AnimalFarming.roundUp.shiftedIndex && ItemRoundUp.pos && (ItemRoundUp.posi == 4 || ItemRoundUp.posi == 5))
            {
                setPathToEntity(worldObj.getPathToEntity(mc.thePlayer, this, 8F));
            }
            if(!GuiAnimalFarmingInfo.breed)
            {
                if(urged)
                {
                    List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(2D, 2D, 2D));
                    for(int i = 0; i < list.size(); i++)
                    {
                        Entity entity = (Entity)list.get(i);
                        if(entity == other)
                        {
                            GiveBirth(mc);
                        }
                    }

                    setPathToEntity(worldObj.getPathToEntity(this, other, 16F));
                    if(waiter-- <= 0 || other == null)
                    {
                        urged = false;
                        birthtime = getBTime() / 2;
                    }
                }
                if(tamed && !urged)
                {
                    if(mc.thePlayer.ridingEntity != null && mc.thePlayer.ridingEntity == this)
                    {
                        updateKeys();
                    }
                    if(birthtime-- == 0 || birthtime < 0)
                    {
                        List list1 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32D, 32D, 32D));
                        boolean flag = false;
                        for(int j = 0; j < list1.size(); j++)
                        {
                            Entity entity1 = (Entity)list1.get(j);
                            if(entity1.getClass() != (net.minecraft.src.EntityChicken.class))
                            {
                                continue;
                            }
                            EntityChicken entitychicken = (EntityChicken)entity1;
                            if(!entitychicken.tamed)
                            {
                                continue;
                            }
                            other = (EntityChicken)entity1;
                            waiter = 500;
                            urged = true;
                            flag = true;
                            setPathToEntity(worldObj.getPathToEntity(this, other, 16F));
                            break;
                        }

                        if(!flag)
                        {
                            birthtime = getBTime() / 5;
                        }
                    }
                }
            }
        }
    }

    protected void fall(float f)
    {
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Tamed", tamed);
        nbttagcompound.setInteger("Timeto", birthtime);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        tamed = nbttagcompound.getBoolean("Tamed");
        birthtime = nbttagcompound.getInteger("Timeto");
        if(!tamed && tamed)
        {
            tamed = false;
        }
    }

    protected String getLivingSound()
    {
        return "mob.chicken";
    }

    protected String getHurtSound()
    {
        return "mob.chickenhurt";
    }

    protected String getDeathSound()
    {
        return "mob.chickenhurt";
    }

    protected int getDropItemId()
    {
        return Item.feather.shiftedIndex;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if(!worldObj.multiplayerWorld)
        {
            if(mc.thePlayer.ridingEntity != null && mc.thePlayer.ridingEntity == this)
            {
                entityplayer.mountEntity(null);
                return true;
            }
            ItemStack itemstack = entityplayer.getCurrentEquippedItem();
            if(tamed && itemstack != null && itemstack.itemID == mod_AnimalFarming.mounter.shiftedIndex)
            {
                itemstack.damageItem(1, entityplayer);
                entityplayer.mountEntity(this);
                return true;
            }
            if(itemstack != null && itemstack.itemID == Item.seeds.shiftedIndex)
            {
                if(tamed)
                {
                    if(health < 4)
                    {
                        health++;
                        itemstack.stackSize--;
                    }
                } else
                {
                    itemstack.stackSize--;
                    int i = rand.nextInt(5);
                    if(i == 2)
                    {
                        tamed = true;
                        showHeartsOrSmokeFX(false);
                        showHeartsOrSmokeFX(true);
                        birthtime = getBTime();
                        return true;
                    }
                    showHeartsOrSmokeFX(false);
                }
            } else
            if(itemstack == null)
            {
                mc.displayGuiScreen(new GuiAnimalFarmingInfo(tamed, "Chicken", birthtime, this));
            } else
            if(itemstack.itemID != Item.seeds.shiftedIndex)
            {
                mc.displayGuiScreen(new GuiAnimalFarmingInfo(tamed, "Chicken", birthtime, this));
            }
        }
        return false;
    }

    private int getBTime()
    {
        return 18000 + rand.nextInt(1000);
    }

    public boolean isMovementBlocked()
    {
        return mc.thePlayer.ridingEntity != null && mc.thePlayer.ridingEntity == this;
    }

    private void updateKeys()
    {
        motionX = 0.0D;
        motionZ = 0.0D;
        rotationYaw = mc.thePlayer.rotationYaw;
        prevRotationYaw = mc.thePlayer.rotationYaw;
        isJumping = mc.thePlayer.isJumping;
        if(isJumping)
        {
            boolean flag = isInWater();
            boolean flag1 = handleLavaMovement();
            if(flag)
            {
                motionY += 0.039999999105930328D;
            } else
            if(flag1)
            {
                motionY += 0.039999999105930328D;
            } else
            if(onGround)
            {
                jump();
                mc.thePlayer.jump();
            }
        }
        if(!isCollidedVertically)
        {
            addVelocity(mc.thePlayer.motionX * 5D, 0.035000000000000003D, mc.thePlayer.motionZ * 5D);
        } else
        {
            addVelocity(mc.thePlayer.motionX * 3D, 0.0D, mc.thePlayer.motionZ * 3D);
        }
        moveEntityWithHeading(mc.thePlayer.moveStrafing, mc.thePlayer.moveForward);
    }

    public boolean GiveBirth(Minecraft minecraft)
    {
        if(rand.nextInt(100) > 20)
        {
            EntityChicken entitychicken = new EntityChicken(minecraft.theWorld);
            entitychicken.setPosition(posX, posY, posZ);
            if(rand.nextInt(10) == 0)
            {
                entitychicken.tamed = false;
            } else
            {
                entitychicken.tamed = true;
            }
            showHeartsOrSmokeFX(false);
            showHeartsOrSmokeFX(true);
            worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
            urged = false;
            birthtime = getBTime();
            minecraft.theWorld.entityJoinedWorld(entitychicken);
            return true;
        } else
        {
            showHeartsOrSmokeFX(false);
            urged = false;
            birthtime = getBTime();
            return false;
        }
    }

    protected boolean canDespawn()
    {
        return !tamed;
    }

    void showHeartsOrSmokeFX(boolean flag)
    {
        String s = "heart";
        if(!flag)
        {
            s = "smoke";
        }
        for(int i = 0; i < 7; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
        }

    }

    public boolean field_753_a;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
    boolean tamed;
    boolean urged;
    EntityChicken other;
    int birthtime;
    Minecraft mc;
    int waiter;
}
