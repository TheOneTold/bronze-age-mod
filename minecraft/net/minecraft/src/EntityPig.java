// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, GetMinecraft, DataWatcher, NBTTagCompound, 
//            EntityPlayerSP, EntityPlayer, ItemStack, mod_AnimalFarming, 
//            Item, World, GuiAnimalFarmingInfo, EntityPigZombie, 
//            AchievementList, ItemRoundUp, AxisAlignedBB, Entity, 
//            EntityLightningBolt

public class EntityPig extends EntityAnimal
{

    public EntityPig(World world)
    {
        super(world);
        tamed = false;
        urged = false;
        birthtime = getBTime();
        mc = GetMinecraft.getMC();
        texture = "/mob/pig.png";
        setSize(0.9F, 0.9F);
        tamed = false;
    }

    protected void entityInit()
    {
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddle", getSaddled());
        nbttagcompound.setBoolean("Tamed", tamed);
        nbttagcompound.setInteger("Timeto", birthtime);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
        birthtime = nbttagcompound.getInteger("Timeto");
        tamed = nbttagcompound.getBoolean("Tamed");
    }

    protected String getLivingSound()
    {
        return "mob.pig";
    }

    protected String getHurtSound()
    {
        return "mob.pig";
    }

    protected String getDeathSound()
    {
        return "mob.pigdeath";
    }

    public boolean interact(EntityPlayer entityplayer)
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
        if(getSaddled() && !worldObj.multiplayerWorld && (riddenByEntity == null || riddenByEntity == entityplayer))
        {
            entityplayer.mountEntity(this);
            return true;
        }
        if(itemstack != null && itemstack.itemID == Item.wheat.shiftedIndex)
        {
            if(tamed)
            {
                if(health < 10)
                {
                    itemstack.stackSize--;
                    health++;
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
        {
            mc.displayGuiScreen(new GuiAnimalFarmingInfo(tamed, "Pig", birthtime, this));
        }
        return false;
    }

    protected int getDropItemId()
    {
        if(fire > 0)
        {
            return Item.porkCooked.shiftedIndex;
        } else
        {
            return Item.porkRaw.shiftedIndex;
        }
    }

    public boolean getSaddled()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setSaddled(boolean flag)
    {
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        }
    }

    public void onStruckByLightning(EntityLightningBolt entitylightningbolt)
    {
        if(worldObj.multiplayerWorld)
        {
            return;
        } else
        {
            EntityPigZombie entitypigzombie = new EntityPigZombie(worldObj);
            entitypigzombie.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            worldObj.entityJoinedWorld(entitypigzombie);
            setEntityDead();
            return;
        }
    }

    protected void fall(float f)
    {
        super.fall(f);
        if(f > 5F && (riddenByEntity instanceof EntityPlayer))
        {
            ((EntityPlayer)riddenByEntity).triggerAchievement(AchievementList.flyPig);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(!worldObj.multiplayerWorld)
        {
            if(tamed && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().itemID == mod_AnimalFarming.roundUp.shiftedIndex && ItemRoundUp.pos && (ItemRoundUp.posi == 2 || ItemRoundUp.posi == 5))
            {
                setPathToEntity(worldObj.getPathToEntity(mc.thePlayer, this, 8F));
            }
            if(!GuiAnimalFarmingInfo.breed)
            {
                if(urged)
                {
                    List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(2D, 2D, 2D));
                    if(list != null)
                    {
                        for(int i = 0; i < list.size(); i++)
                        {
                            Entity entity = (Entity)list.get(i);
                            if(entity == other)
                            {
                                GiveBirth(mc);
                            }
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
                            if(entity1.getClass() != (net.minecraft.src.EntityPig.class))
                            {
                                continue;
                            }
                            EntityPig entitypig = (EntityPig)entity1;
                            if(!entitypig.tamed)
                            {
                                continue;
                            }
                            other = (EntityPig)entity1;
                            waiter = 500;
                            urged = true;
                            flag = true;
                            setPathToEntity(worldObj.getPathToEntity(this, other, 16F));
                            break;
                        }

                        if(!flag)
                        {
                            birthtime = getBTime() / 4;
                        }
                    }
                }
            }
        }
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

    private int getBTime()
    {
        return 18000 + rand.nextInt(1000);
    }

    public boolean GiveBirth(Minecraft minecraft)
    {
        if(rand.nextInt(100) > 20)
        {
            for(int i = 0; i < rand.nextInt(3) + 1; i++)
            {
                EntityPig entitypig = new EntityPig(minecraft.theWorld);
                entitypig.setPosition(posX, posY, posZ);
                if(rand.nextInt(10) == 0)
                {
                    entitypig.tamed = false;
                } else
                {
                    entitypig.tamed = true;
                }
                showHeartsOrSmokeFX(false);
                showHeartsOrSmokeFX(true);
                worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
                urged = false;
                birthtime = getBTime();
                minecraft.theWorld.entityJoinedWorld(entitypig);
            }

            return true;
        } else
        {
            showHeartsOrSmokeFX(false);
            urged = false;
            birthtime = getBTime() / 4;
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

    public boolean isMovementBlocked()
    {
        return mc.thePlayer.ridingEntity != null && mc.thePlayer.ridingEntity == this;
    }

    boolean tamed;
    boolean urged;
    EntityPig other;
    int birthtime;
    Minecraft mc;
    int waiter;
}
