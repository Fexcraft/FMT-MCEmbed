package net.fexcraft.mod.fmt.polygons.round;

import org.lwjgl.opengl.GL11;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.fexcraft.mod.fmt.polygons.EditorShape;
import net.fexcraft.mod.lib.tmt.ModelRendererTurbo;
import net.fexcraft.mod.lib.util.math.Vec3f;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TMTSphere extends EditorShape {
	
	@SideOnly(Side.CLIENT)
	protected ModelRendererTurbo turbo;
	protected Vec3f pos, off, rot;
	protected int texX, texY;
	//
	protected float radius;
	protected int segments, rings;
	protected boolean mirror, flip;
	
	public TMTSphere(){
		pos = new Vec3f(0, 0, 0); off = new Vec3f(0, 0, 0); rot = new Vec3f(0, 0, 0);
		texX = texY = 0; radius = 1; segments = 8; rings = 8;
	}

	@SideOnly(Side.CLIENT) @Override
	public void render(EditorTileEntity tile){
		if(turbo == null) recompile(tile.textureX, tile.textureY);
		turbo.render();
	}

	@Override
	public void apply(String key, float value, Object obj){
		switch(key){
			case "pos_x": pos.xCoord = value; break;
			case "pos_y": pos.yCoord = value; break;
			case "pos_z": pos.zCoord = value; break;
			case "rot_x": rot.xCoord = value; break;
			case "rot_y": rot.yCoord = value; break;
			case "rot_z": rot.zCoord = value; break;
			case "radius": radius = value; break;
			case "segments": case "seg": segments = (int)value; break;
			case "rings": case "rin": rings = (int)value; break;
			case "mirror": mirror = obj instanceof Boolean ? (boolean)obj : value == 1f; break;
			case "flip": flip = obj instanceof Boolean ? (boolean)obj : value == 1f; break;
		}
	}

	@SideOnly(Side.CLIENT) @Override
	public void recompile(int texx, int texy){
		if(turbo != null){ GL11.glDeleteLists(turbo.displaylist(), 0); turbo = null; }
		turbo = new ModelRendererTurbo(null, texx, texy, texX, texY);
		turbo.addSphere(off.xCoord, off.yCoord, off.zCoord, radius, segments, rings, texx, texy);
		turbo.setRotationPoint(pos.xCoord, pos.yCoord, pos.zCoord);
		turbo.rotateAngleX = rot.xCoord; turbo.rotateAngleY = rot.yCoord; turbo.rotateAngleZ = rot.zCoord;
	}

	@Override
	public EditorShape readFromNBT(NBTTagCompound compound){
		pos = new Vec3f(compound.getFloat("pos_x"), compound.getFloat("pos_y"), compound.getFloat("pos_z"));
		rot = new Vec3f(compound.getFloat("rot_x"), compound.getFloat("rot_y"), compound.getFloat("rot_z"));
		off = new Vec3f(compound.getFloat("off_x"), compound.getFloat("off_y"), compound.getFloat("off_z"));
		texX = compound.getInteger("texture_x"); texY = compound.getInteger("texture_y");
		//
		compound.setFloat("radius", radius);
		compound.setInteger("segments", segments); compound.setInteger("rings", rings);
		compound.setBoolean("flip", flip); compound.getBoolean("mirror");
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		compound.setFloat("pos_x", pos.xCoord); compound.setFloat("pos_y", pos.yCoord); compound.setFloat("pos_z", pos.zCoord);
		compound.setFloat("rot_x", rot.xCoord); compound.setFloat("rot_y", rot.yCoord); compound.setFloat("rot_z", rot.zCoord);
		compound.setFloat("off_x", off.xCoord); compound.setFloat("off_y", off.yCoord); compound.setFloat("off_z", off.zCoord);
		compound.setFloat("texture_x", texX); compound.setFloat("texture_y", texY);
		//
		radius = compound.getFloat("radius");
		segments = compound.getInteger("segments"); rings = compound.getInteger("rings");
		flip = compound.getBoolean("skip"); mirror = compound.getBoolean("mirror");
		compound.setString("type", "tmt.cylinder"); return compound;
	}

	@Override
	public EditorShape parse(String string, String type){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditorShape write(StringBuffer buff, String type){
		// TODO Auto-generated method stub
		return null;
	}
	
}