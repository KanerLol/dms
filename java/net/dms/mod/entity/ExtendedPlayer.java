package net.dms.mod.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.dms.mod.dms;
import net.dms.mod.packets.SyncPlayerPropsPacket;
import net.dms.mod.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
/*
Here I create a constant EXT_PROP_NAME for this class of properties. You need a unique name for every instance of IExtendedEntityProperties you make, and doing it at the top of each class as a constant makes
it very easy to organize and avoid typos. It's easiest to keep the same constant name in every class, as it will be distinguished by the class name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME

Note that a single entity can have multiple extended properties, so each property should have a unique name. Try to come up with something more unique than the tutorial example.
*/
public final static String EXT_PROP_NAME = "ExtendedPlayer";

// I always include the entity to which the properties belong for easy access
// It's final because we won't be changing which player it is
private final EntityPlayer player;

// Declare other variables you want to add here

// We're adding mana to the player, so we'll need current and max mana
private int currentSanity, maxSanity,currentHP,maxHP;

/*
The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'

Also, it's best to initialize any other variables you may have added, just like in any constructor.
*/
public ExtendedPlayer(EntityPlayer player)
{
this.player = player;
// Start with max mana. Every player starts with the same amount.
this.currentSanity = this.maxSanity = 150;
this.currentHP=this.maxHP=150;
}

/**
* Used to register these extended properties for the player during EntityConstructing event
* This method is for convenience only; it will make your code look nicer
*/
public static final void register(EntityPlayer player)
{
player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
}

/**
* Returns ExtendedPlayer properties for player
* This method is for convenience only; it will make your code look nicer
*/
public static final ExtendedPlayer get(EntityPlayer player)
{
return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
}

@Override
public void saveNBTData(NBTTagCompound compound)
{
NBTTagCompound properties = new NBTTagCompound();

properties.setInteger("CurrentSanity", this.currentSanity);
properties.setInteger("MaxSanity", this.maxSanity);
properties.setInteger("CurrentHP", this.currentHP);

compound.setTag(EXT_PROP_NAME, properties);
}

@Override
public void loadNBTData(NBTTagCompound compound)
{
NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
this.currentSanity = properties.getInteger("CurrentSanity");
this.maxSanity = properties.getInteger("MaxSanity");
this.currentHP = properties.getInteger("CurrentHP");
System.out.println("[TUT PROPS] Mana from NBT: " + this.currentSanity + "/" + this.maxSanity);
}
private static final String getSaveKey(EntityPlayer player) {
	// no longer a username field, so use the command sender name instead:
	return player.getCommandSenderName() + ":" + EXT_PROP_NAME;
	}

	public static final void loadProxyData(EntityPlayer player) {
	ExtendedPlayer playerData = ExtendedPlayer.get(player);
	NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
	if (savedData != null) { playerData.loadNBTData(savedData); }
	// we are replacing the entire sync() method with a single line; more on packets later
	// data can by synced just by sending the appropriate packet, as everything is handled internally by the packet class
	dms.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}
	public final void sync()
	{
	ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	DataOutputStream outputStream = new DataOutputStream(bos);

	// We'll write max mana first so when we set current mana client
	// side, it doesn't get set to 0 (see methods below)
	try {
	outputStream.writeInt(this.maxSanity);
	outputStream.writeInt(this.currentSanity);
	outputStream.writeInt(this.currentHP);
	} catch (Exception ex) {
	ex.printStackTrace();
	}

	// We only want to send from the server to the client
	if (!player.worldObj.isRemote) {
	dms.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}
	}
	
public int getSanity(){
	return this.currentSanity;
}
public void setSanity(int sanity){
	this.currentSanity=sanity;
	sync();
}
public int getMaxSanity(){
	return maxSanity;
}
public void setMaxSanity(int sanity){
	this.maxSanity=sanity;
	sync();
}
public int getCurrentHP(){
	return currentHP;
}
public void setCurrentHP(int int1){
	this.currentHP=int1;
	player.setHealth(currentHP);
	sync();
}
public int getMaxHP() {
	return maxHP;
}
public void setMaxHP(int int1){
	this.maxHP=int1;
	if (currentHP>maxHP){
		currentHP=maxHP;
		player.setHealth(currentHP);
	}
	sync();
}

public void setStatsFromFood(int h,int he,int s){
	if (he<=0){
		this.currentHP-=(Math.abs(he)>this.currentHP?this.currentHP:Math.abs(he));
	}
	else{
		this.currentHP=((he+this.currentHP)>this.maxHP?this.maxHP:he+this.currentHP);
	}
	if (s<=0){
		this.currentSanity-=(Math.abs(s)>this.currentSanity?this.currentSanity:Math.abs(s));
	}
	else{
		this.currentSanity=((s+this.currentSanity)>this.maxSanity?this.maxSanity:s+this.currentSanity);
	}
	sync();
}

	
public boolean consumeSanity(int amount)
{
// Does the player have enough mana?
boolean sufficient = amount <= this.currentSanity;
// Consume the amount anyway; if it's more than the player's current mana,
// mana will be set to 0
this.currentSanity -= (amount < this.currentSanity ? amount : this.currentSanity);
// Return if the player had enough mana
sync();
return sufficient;
}



@Override
public void init(Entity entity, World world)
{
}


}
