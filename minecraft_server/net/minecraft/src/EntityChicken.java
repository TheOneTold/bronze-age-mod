package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityChicken extends EntityAnimal
{

    public EntityChicken(World world)
    {
        super(world);
        tamed = false;
        urged = false;
        birthtime = getBTime();
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

	protected void entityInit()
    {
        dataWatcher.addObject(17, Byte.valueOf((byte)0));
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

        if(--timeUntilNextEgg <= 0)
        {
            worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            dropItem(Item.egg.shiftedIndex, 1);
            timeUntilNextEgg = rand.nextInt(6000) + 6000;
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
                            if(entity1.getClass() != (net.minecraft.src.EntityChicken.class))
                            {
                                continue;
                            }
                            EntityChicken entitychicken = (EntityChicken)entity1;
                            if(!entitychicken.getTamed())
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

    private boolean GuiAnimalFarmingInfo_breed_check()
    {
        return false;
    }

    protected void fall(float f)
    {
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Tamed", getTamed());
        nbttagcompound.setInteger("Timeto", birthtime);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        boolean isTamed = nbttagcompound.getBoolean("Tamed");
        setTamed(isTamed);
        birthtime = nbttagcompound.getInteger("Timeto");
    }
	
	public boolean getTamed()
    {
        return (dataWatcher.getWatchableObjectByte(17) & 1) != 0;
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
            if(itemstack != null && itemstack.itemID == Item.seeds.shiftedIndex)
            {
                if(getTamed())
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
                        setTamed(true);
                        birthtime = getBTime();
                        return true;
                    }
                }
            }
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
            EntityChicken entitychicken = new EntityChicken(worldObj);
            entitychicken.setPosition(posX, posY, posZ);
            entitychicken.setTamed(rand.nextInt(10) != 0);
            worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
            urged = false;
            birthtime = getBTime();
            worldObj.entityJoinedWorld(entitychicken);
            return true;
        } else
        {
            urged = false;
            birthtime = getBTime();
            return false;
        }
    }

    protected boolean canDespawn()
    {
        return !getTamed();
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
    int waiter;
}