package net.fexcraft.mod.fmt.gameobjects;

import net.fexcraft.mod.lib.api.network.IPacketReceiver;
import net.fexcraft.mod.lib.network.packet.PacketTileEntityUpdate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EditorTileEntity extends TileEntity implements IPacketReceiver<PacketTileEntityUpdate> {	

	public EditorTileEntity(){ }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag(){
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager netman, SPacketUpdateTileEntity packet){
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		//TODO
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		//TODO
	}

	@SideOnly(Side.CLIENT)
	@Override
	public double getMaxRenderDistanceSquared(){
		return super.getMaxRenderDistanceSquared() * 8;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox(){
		return INFINITE_EXTENT_AABB;
	}

	@Override
	public boolean canRenderBreaking(){
		return true;
	}

	@Override
	public void processClientPacket(PacketTileEntityUpdate packet){
		if(packet.nbt.hasKey("task")){
			switch(packet.nbt.getString("task")){
				case "test": {
					break; //TODO
				}
			}
		}
		else{
			this.readFromNBT(packet.nbt);
		}
	}

	@Override
	public void processServerPacket(PacketTileEntityUpdate packet){
		if(packet.nbt.hasKey("task")){
			switch(packet.nbt.getString("task")){
				case "test": {
					break; //TODO
				}
			}
		}
	}

}
