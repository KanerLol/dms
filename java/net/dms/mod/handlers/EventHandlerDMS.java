package net.dms.mod.handlers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dms.mod.entity.ExtendedPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class EventHandlerDMS {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
	ExtendedPlayer.register((EntityPlayer) event.entity);
	if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
	event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
	if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
	ExtendedPlayer.get((EntityPlayer) event.entity).sync();
	
	if (event.entity instanceof EntityPlayer)
	{
		EntityPlayer ep = (EntityPlayer) event.entity;
		 if (ep.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue()!=150.0D){
			 
		 
		System.out.println("[Event]Player Join");
	double valueToSet = 130.0D;

	try {
        valueToSet = ep.getEntityAttribute(SharedMonsterAttributes.maxHealth).getModifier(ep.getPersistentID()).getAmount() + 130.0D;
     } catch (Exception var8) {
        ;
     }

     AttributeModifier moreHealth = new AttributeModifier(ep.getPersistentID(), "Heart Crystal Health Boost", valueToSet, 0);
     IAttributeInstance attributeinstance = ep.getEntityAttribute(SharedMonsterAttributes.maxHealth);
     attributeinstance.removeModifier(moreHealth);
     attributeinstance.applyModifier(moreHealth);
	
	ExtendedPlayer props = ExtendedPlayer.get(ep);
     ep.setHealth(props.getCurrentHP());
	 }
	}
     
	

    	 
   
	}		
	
	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event)
	{
		EntityPlayer ep =event.player;
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		props.setCurrentHP((int)ep.getHealth());
	}
		

	
	
	
	
	@SubscribeEvent
	public void render(RenderGameOverlayEvent.Pre event){
	    if(event.type == ElementType.HEALTH){
	        event.setCanceled(true);
	    }
	}

	@SubscribeEvent
	public void render(RenderGameOverlayEvent.Post event){
	    if(event.type == ElementType.HEALTH){
	    	event.setCanceled(true);
	    }
	}

	
}

