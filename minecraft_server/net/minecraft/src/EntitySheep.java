package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntitySheep extends EntityAnimal
{

    public EntitySheep(World world)
    {
        super(world);
        tamed = false;
        urged = false;
        birthtime = getBTime();
        reFleece = getFTime();
        texture = "/mob/sheep.png";
        setSize(0.9F, 1.3F);
        reFleece = getFTime();
        tamed = false;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte)0));
		dataWatcher.addObject(17, Byte.valueOf((byte)0));
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

    public boolean attackEntityFrom(Entity entity, int i)
    {
        return super.attackEntityFrom(entity, i);
    }

    protected void dropFewItems()
    {
        if(!getSheared())
        {
            entityDropItem(new ItemStack(Block.cloth.blockID, 1, getFleeceColor()), 0.0F);
        }
    }

    protected int getDropItemId()
    {
        return Block.cloth.blockID;
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
        if(itemstack != null && itemstack.itemID == 359 && !getSheared())
        {
            setSheared(true);
                int i = 2 + rand.nextInt(3);
                for(int k = 0; k < i; k++)
                {
                    EntityItem entityitem = entityDropItem(new ItemStack(Block.cloth.blockID, 1, getFleeceColor()), 1.0F);
                    entityitem.motionY += rand.nextFloat() * 0.05F;
                    entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                }
            itemstack.damageItem(1, entityplayer);
        }
        if(itemstack != null && itemstack.itemID != 359 && itemstack.itemID == Item.wheat.shiftedIndex)
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
                int j = rand.nextInt(5);
                if(j == 2)
                {
                    setTamed(true);
                    birthtime = getBTime();
                    return true;
                }
            }
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Sheared", getSheared());
        nbttagcompound.setByte("Color", (byte)getFleeceColor());
        nbttagcompound.setBoolean("Tamed", getTamed());
        nbttagcompound.setInteger("Timeto", birthtime);
        nbttagcompound.setInteger("FleeceReset", reFleece);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSheared(nbttagcompound.getBoolean("Sheared"));
        setFleeceColor(nbttagcompound.getByte("Color"));
        boolean isTamed = nbttagcompound.getBoolean("Tamed");
        setTamed(isTamed);
        birthtime = nbttagcompound.getInteger("Timeto");
        reFleece = nbttagcompound.getInteger("FleeceReset");
    }

    protected String getLivingSound()
    {
        return "mob.sheep";
    }

    protected String getHurtSound()
    {
        return "mob.sheep";
    }

    protected String getDeathSound()
    {
        return "mob.sheep";
    }

    public int getFleeceColor()
    {
        return dataWatcher.getWatchableObjectByte(16) & 0xf;
    }

    public void setFleeceColor(int i)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & 0xf0 | i & 0xf)));
    }

    public boolean getSheared()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 0x10) != 0;
    }

    public void setSheared(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 0x10)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & 0xffffffef)));
        }
    }

    public static int getRandomFleeceColor(Random random)
    {
        int i = random.nextInt(100);
        if(i < 5)
        {
            return 15;
        }
        if(i < 10)
        {
            return 7;
        }
        if(i < 15)
        {
            return 8;
        }
        if(i < 18)
        {
            return 12;
        } else
        {
            return random.nextInt(500) == 0 ? 6 : 0;
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        if((dataWatcher.getWatchableObjectByte(16) & 0x10) != 0 && reFleece-- <= 0)
            {
                reFleece = getFTime();
                setSheared(false);
                setFleeceColor(getRandomFleeceColor(new Random()));
            }
            if(!GuiAnimalFarmingInfo_breed_check())
            {
                if(urged)
                {
                    List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(2D, 2D, 2D));
                    for(int i = 0; i < list.size(); i++)
                    {
                        Entity entity = (Entity)list.get(i);
                        if(entity == other)
                        {
                            GiveBirth();
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
                            if(entity1.getClass() != (net.minecraft.src.EntitySheep.class))
                            {
                                continue;
                            }
                            EntitySheep entitysheep = (EntitySheep)entity1;
                            if(!entitysheep.getTamed())
                            {
                                continue;
                            }
                            waiter = 500;
                            other = (EntitySheep)entity1;
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

    private int getFTime()
    {
        return 36000 + rand.nextInt(2000);
    }

    public boolean GiveBirth()
    {
        if(rand.nextInt(100) > 20)
        {
            EntitySheep entitysheep = new EntitySheep(worldObj);
            entitysheep.setPosition(posX, posY, posZ);
            entitysheep.setTamed(rand.nextInt(10) != 0);
            worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
            urged = false;
            birthtime = getBTime();
            worldObj.entityJoinedWorld(entitysheep);
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

    public static final float fleeceColorTable[][] = {
        {
            1.0F, 1.0F, 1.0F
        }, {
            0.95F, 0.7F, 0.2F
        }, {
            0.9F, 0.5F, 0.85F
        }, {
            0.6F, 0.7F, 0.95F
        }, {
            0.9F, 0.9F, 0.2F
        }, {
            0.5F, 0.8F, 0.1F
        }, {
            0.95F, 0.7F, 0.8F
        }, {
            0.3F, 0.3F, 0.3F
        }, {
            0.6F, 0.6F, 0.6F
        }, {
            0.3F, 0.6F, 0.7F
        }, {
            0.7F, 0.4F, 0.9F
        }, {
            0.2F, 0.4F, 0.8F
        }, {
            0.5F, 0.4F, 0.3F
        }, {
            0.4F, 0.5F, 0.2F
        }, {
            0.8F, 0.3F, 0.3F
        }, {
            0.1F, 0.1F, 0.1F
        }
    };
    boolean tamed;
    boolean urged;
    EntitySheep other;
    int birthtime;
    int reFleece;
    int waiter;

}