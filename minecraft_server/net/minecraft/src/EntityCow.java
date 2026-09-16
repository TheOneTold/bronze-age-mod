package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityCow extends EntityAnimal
{

    public EntityCow(World world)
    {
        super(world);
        rand = new Random();
        tamed = false;
        urged = false;
        birthtime = getBTime();
        texture = "/mob/cow.png";
        setSize(0.9F, 1.3F);
        tamed = false;
    }
	
	protected void entityInit()
    {
        dataWatcher.addObject(17, Byte.valueOf((byte)0));
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
        return "mob.cow";
    }

    protected String getHurtSound()
    {
        return "mob.cowhurt";
    }

    protected String getDeathSound()
    {
        return "mob.cowhurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return Item.leather.shiftedIndex;
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
        if(itemstack != null && itemstack.itemID == Item.bucketEmpty.shiftedIndex)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Item.bucketMilk));
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

    public void onUpdate()
    {
        super.onUpdate();
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
                        List list1 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32D, 16D, 32D));
                        boolean flag = false;
                        for(int j = 0; j < list1.size(); j++)
                        {
                            Entity entity1 = (Entity)list1.get(j);
                            if(entity1.getClass() != (net.minecraft.src.EntityCow.class))
                            {
                                continue;
                            }
                            EntityCow entitycow = (EntityCow)entity1;
                            if(!entitycow.getTamed())
                            {
                                continue;
                            }
                            waiter = 500;
                            other = (EntityCow)entity1;
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
            EntityCow entitycow = new EntityCow(worldObj);
            entitycow.setPosition(posX, posY, posZ);
            entitycow.setTamed(rand.nextInt(10) != 0);
            worldObj.entityJoinedWorld(entitycow);
            worldObj.playSoundAtEntity(this, "random.pop", 0.3F, 0.5F);
            urged = false;
            birthtime = getBTime();
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

    Random rand;
    boolean tamed;
    boolean urged;
    public static boolean pig = true;
    EntityCow other;
    int birthtime;
    int waiter;
}