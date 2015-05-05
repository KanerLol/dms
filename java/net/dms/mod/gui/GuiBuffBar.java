package net.dms.mod.gui;

import java.util.Collection;
import java.util.Iterator;

import net.dms.mod.dms;
import net.dms.mod.entity.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiBuffBar extends Gui
{
  private Minecraft mc;

  public GuiBuffBar(Minecraft mc)
  {
    super();
    
    // We need this to invoke the render engine.
    this.mc = mc;
  }
  
  
  //
  // This event is called by GuiIngameForge during each frame by
  // GuiIngameForge.pre() and GuiIngameForce.post().
  //
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRenderExperienceBar(RenderGameOverlayEvent event)
  {
    // 
    // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
    // will return true from isCancelable.  If you call event.setCanceled(true) in
    // that case, the portion of rendering which this event represents will be canceled.
    // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
    // false and that the eventType represents the ExperienceBar event.
    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
    {      
      return;
    }
    
    //Sanity
    ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
    if (props == null || props.getMaxSanity() == 0)
    {
    return;
    }

    if (props.getSanity()>150){
    	props.setSanity(150);
    }
    //
  
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(GL11.GL_LIGHTING);
    int pos = (int)(((float) props.getSanity() / props.getMaxSanity()) * 32);
 
    drawString(mc.fontRenderer,Double.toString(mc.thePlayer.getHealth()),0,0, 0xffffff);
    this.mc.renderEngine.bindTexture(new ResourceLocation(dms.modID+":/textures/gui/sanity.png"));
    if (mc.gameSettings.guiScale==0){
    this.drawTexturedModalRect(mc.displayWidth/2-39,3,41,5,33,32);
    this.drawTexturedModalRect(mc.displayWidth/2-39,3,76,4,33,32-pos);
    this.drawTexturedModalRect(mc.displayWidth/2-33,8,8,42,23,25);
    this.drawTexturedModalRect(mc.displayWidth/2-40,0,2,2,36,36);
    
    drawCenteredString(mc.fontRenderer,Integer.toString(props.getSanity()),mc.displayWidth/2-30,18, 0xffffff);
    }
    if (mc.gameSettings.guiScale!=0){
    	this.drawTexturedModalRect(mc.displayWidth/mc.gameSettings.guiScale-39,3,41,5,33,32);
    	this.drawTexturedModalRect(mc.displayWidth/mc.gameSettings.guiScale-39,3,76,4,33,32-pos);
    	this.drawTexturedModalRect(mc.displayWidth/mc.gameSettings.guiScale-33,8,8,42,23,25);
    	this.drawTexturedModalRect(mc.displayWidth/mc.gameSettings.guiScale-40,0,2,2,36,36);
    drawCenteredString(mc.fontRenderer,Integer.toString(props.getSanity()),mc.displayWidth/mc.gameSettings.guiScale-30,18, 0xffffff);
    }
   
    
  }
}
