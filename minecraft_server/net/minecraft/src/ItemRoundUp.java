package net.minecraft.src;

public class ItemRoundUp extends Item
{

    protected ItemRoundUp(int i)
    {
        super(i);
    }

    public void cycleMode(EntityPlayer entityplayer)
    {
        String s = "";
        switch(posi)
        {
        case 1: // '\001'
            posi = 2;
            s = "Pigs only";
            break;

        case 2: // '\002'
            posi = 3;
            s = "Sheep only";
            break;

        case 3: // '\003'
            posi = 4;
            s = "Chickens only";
            break;

        case 4: // '\004'
            posi = 5;
            s = "All Tamed Animals";
            break;

        case 5: // '\005'
            posi = 1;
            s = "Cows only";
            break;
        }
        
        if (entityplayer != null)
        {
			if (entityplayer instanceof EntityPlayerMP) {
				((EntityPlayerMP)entityplayer).playerNetServerHandler.sendPacket(new Packet3Chat(s));
			}
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(pos)
        {
            pos = false;
            world.playSoundAtEntity(entityplayer, "random.click", 0.3F, 0.3F);
            if (entityplayer instanceof EntityPlayerMP) {
				((EntityPlayerMP)entityplayer).playerNetServerHandler.sendPacket(new Packet3Chat("Animals Will Stop Following You"));
			}
        } else
        {
            pos = true;
            world.playSoundAtEntity(entityplayer, "random.click", 0.3F, 0.7F);
            if (entityplayer instanceof EntityPlayerMP) {
				((EntityPlayerMP)entityplayer).playerNetServerHandler.sendPacket(new Packet3Chat("Animals Will Now Follow You"));
			}
        }
        return itemstack;
    }

    // WARNING: In a multiplayer environment, static variables apply globally across the server.
    // If Player A toggles these variables, the changes will immediately affect Player B. 
    // To allow individual player toggles in a future update, these states must be moved to player NBT data or an item's damage value.
    public static boolean pos = false;
    public static boolean on = false;
    public static int posi = 5;

}