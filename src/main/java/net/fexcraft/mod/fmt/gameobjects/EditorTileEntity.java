package net.fexcraft.mod.fmt.gameobjects;

import java.util.TreeMap;

import net.fexcraft.mod.fmt.polygons.EditorShapeCompound;
import net.fexcraft.mod.lib.api.network.IPacketReceiver;
import net.fexcraft.mod.lib.network.packet.PacketTileEntityUpdate;
import net.fexcraft.mod.lib.util.common.ApiUtil;
import net.fexcraft.mod.lib.util.common.Print;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EditorTileEntity extends TileEntity implements IPacketReceiver<PacketTileEntityUpdate> {
	
	private TreeMap<String, EditorShapeCompound> polygons = new TreeMap<>();
	public int textureX = 256, textureY = 256;

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
		if(getPolygons().size() > 0){
			compound.setInteger("PolygonCompounds", getPolygons().size());
			for(int i = 0; i < getPolygons().size(); i++){
				NBTTagCompound com = getPolygons().values().toArray(new EditorShapeCompound[]{})[i].writeToNBT(new NBTTagCompound());
				if(com != null){
					com.setString("key", getPolygons().keySet().toArray(new String[]{})[i]);
					compound.setTag("PolygonCompound" + i, com);
				}
			}
		}
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound); getPolygons().clear();
		if(!compound.hasKey("PolygonCompounds")) return;
		int amount = compound.getInteger("PolygonCompounds");
		for(int i = 0; i < amount; i++){
			NBTTagCompound com = compound.getCompoundTag("PolygonCompound" + i); if(com == null) continue;
			Class<? extends EditorShapeCompound> clazz = EditorShapeCompound.SHAPECOMPOUND_DICTIONARY.get(com.getString("type"));
			if(clazz == null) continue; try{
				EditorShapeCompound edsh = clazz.newInstance().readFromNBT(com);
				if(edsh != null) getPolygons().put(com.getString("key"), edsh);
			} catch(Exception e){ e.printStackTrace(); continue; }
		}
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
			this.readFromNBT(Print.debugR(packet.nbt));
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

	public TreeMap<String, EditorShapeCompound> getPolygons(){
		return polygons;
	}

	public void update(){
		ApiUtil.sendTileEntityUpdatePacket(this, Print.debugR(getUpdateTag()), 256);
	}

}
