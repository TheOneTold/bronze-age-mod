package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityPig extends EntityAnimal
{

    public EntityPig(World world)
    {
        super(world);
        tamed = false;
        urged = false;
        birthtime = getBTime();
        texture = "/mob/pig.png";
        setSize(0.9F, 0.9F);
        tamed = false;
    }

    protected void entityInit()
    {
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
		dataWatcher.addObject(17, Byte.valueOf((byte)0));
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddle", getSaddled());
        nbttagcompound.setBoolean("Tamed", getTamed());
        nbttagcompound.setInteger("Timeto", birthtime);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
        birthtime = nbttagcompound.getInteger("Timeto");
        boolean isTamed = nbttagcompound.getBoolean("Tamed");
        setTamed(isTamed);
    }
	
	public boolean getTamed()
    {
        return this.tamed;
    }

    public void setTamed(boolean flag)
    {
        this.tamed = flag;
        if(flag)
        {
            dataWatcher.updateObject(17, Byte.valueOf((byte)1));
        } else
        {
            dataWatcher.updateObject(17, Byte.valueOf((byte)0));
        }
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
        if(riddenByEntity != null && riddenByEntity == entityplayer)
        {
            entityplayer.mountEntity(null);
            return true;
        }
        ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        if(getTamed() && itemstack != null && itemstack.itemID == mod_AnimalFarming.mounter.shiftedIndex)
        {
            itemstack.damageItem(1, entityplayer);
            entityplayer.mountEntity(this);
            return true;
        }
        if(getSaddled() && (riddenByEntity == null || riddenByEntity == entityplayer))
        {
            entityplayer.mountEntity(this);
            return true;
        }
        if(itemstack != null && itemstack.itemID == Item.wheat.shiftedIndex)
        {
            if(getTamed())
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
                    setTamed(true);
                    birthtime = getBTime();
                    return true;
                }
            }
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

    public void onStruckByLightning(EntityLightningBolt entitylightningbolt) {
		EntityPigZombie entitypigzombie = new EntityPigZombie(worldObj);
		entitypigzombie.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
		worldObj.entityJoinedWorld(entitypigzombie);
		setEntityDead();
	}

    public void onUpdate()
    {
        super.onUpdate();
        if(!GuiAnimalFarmingInfo_breed_check())
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
                                GiveBirth();
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
                if(getTamed() && !urged)
                {
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
                            if(!entitypig.getTamed())
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

    private boolean GuiAnimalFarmingInfo_breed_check()
    {
        return false;
    }

    private int getBTime()
    {
        return 18000 + rand.nextInt(1000);
    }

    public boolean GiveBirth()
    {
        if(rand.nextInt(100) > 20)
        {
            for(int i = 0; i < rand.nextInt(3) + 1; i++)
            {
                EntityPig entitypig = new EntityPig(worldObj);
                entitypig.setPosition(posX, posY, posZ);
                entitypig.setTamed(rand.nextInt(10) != 0);
                worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
                urged = false;
                birthtime = getBTime();
                worldObj.entityJoinedWorld(entitypig);
            }

            return true;
        } else
        {
            urged = false;
            birthtime = getBTime() / 4;
            return false;
        }
    }

    protected boolean canDespawn()
    {
        return !getTamed();
    }

    boolean tamed;
    boolean urged;
    EntityPig other;
    int birthtime;
    int waiter;
}