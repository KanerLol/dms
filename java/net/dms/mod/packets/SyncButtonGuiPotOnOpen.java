package net.dms.mod.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dms.mod.dms;
import net.dms.mod.tileentity.TileEntityCrockPot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncButtonGuiPotOnOpen extends AbstractPacket {

	private int x,y,z;
	private boolean i;

	public SyncButtonGuiPotOnOpen(){}

	// The basic, no-argument constructor MUST be included to use the new automated handling
	public SyncButtonGuiPotOnOpen(int x,int y,int z,boolean i){

		this.i=i;
		this.x=x;
		this.y=y;
		this.z=z;
		
		
	}

	// if there are any class fields, be sure to provide a constructor that allows
	// for them to be initialized, and use that constructor when sending the packet


	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// basic Input/Output operations, very much like DataOutputStream
		buffer.writeBoolean(i);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// basic Input/Output operations, very much like DataInputStream
        i =buffer.readBoolean();
		x =buffer.readInt();
		y =buffer.readInt();
		z =buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

			TileEntityCrockPot te = (TileEntityCrockPot) player.worldObj.getTileEntity(x, y, z);
		    if (te!=null){
			te.setButton(i);
		    }
		    else{
		    	System.out.println("TE=NULL"+x+" "+y+" "+z);
		    }
			
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
	
	
	}
	}
	

