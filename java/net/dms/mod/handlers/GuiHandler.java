package net.dms.mod.handlers;

import net.dms.mod.dms;
import net.dms.mod.container.ContainerCrockPot;
import net.dms.mod.gui.GuiCrockPot;
import net.dms.mod.tileentity.TileEntityCrockPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case dms.guiIdCrockPot:
				if (entity instanceof TileEntityCrockPot) {
					return new ContainerCrockPot(player.inventory, (TileEntityCrockPot) entity);
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case dms.guiIdCrockPot:
				if (entity instanceof TileEntityCrockPot) {
					return new GuiCrockPot(player.inventory, (TileEntityCrockPot) entity);
				}
				return null;
			}
		}
		return null;
	}

}
