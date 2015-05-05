package net.dms.mod.packets;



import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dms.mod.entity.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsPacket extends AbstractPacket
{
// Previously, we've been writing each field in our properties one at a time,
// but that is really annoying, and we've already done it in the save and load
// NBT methods anyway, so here's a slick way to efficiently send all of your
// extended data, and no matter how much you add or remove, you'll never have
// to change the packet / synchronization of your data.

// this will store our ExtendedPlayer data, allowing us to easily read and write
private NBTTagCompound data;

// The basic, no-argument constructor MUST be included to use the new automated handling
public SyncPlayerPropsPacket() {}

// We need to initialize our data, so provide a suitable constructor:
public SyncPlayerPropsPacket(EntityPlayer player) {
// create a new tag compound
data = new NBTTagCompound();
// and save our player's data into it
ExtendedPlayer.get(player).saveNBTData(data);
}



@Override
public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// TODO Auto-generated method stub
	ByteBufUtils.writeTag(buffer, data);
}

@Override
public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// TODO Auto-generated method stub
	data = ByteBufUtils.readTag(buffer);
}

@Override
public void handleClientSide(EntityPlayer player) {
	// TODO Auto-generated method stub
	ExtendedPlayer.get(player).loadNBTData(data);
}

@Override
public void handleServerSide(EntityPlayer player) {
	
	// TODO Auto-generated method stub
	
}
}