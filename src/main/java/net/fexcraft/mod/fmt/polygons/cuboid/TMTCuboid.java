package net.fexcraft.mod.fmt.polygons.cuboid;

import org.lwjgl.opengl.GL11;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.fexcraft.mod.fmt.polygons.EditorShape;
import net.fexcraft.mod.lib.tmt.ModelRendererTurbo;
import net.fexcraft.mod.lib.util.math.Vec3f;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TMTCuboid extends EditorShape {
	
	@SideOnly(Side.CLIENT)
	private ModelRendererTurbo turbo;
	private Vec3f size, pos, off;
	private int texX, texY;
	
	public TMTCuboid(){
		size = new Vec3f(4, 4, 4);
		pos = new Vec3f(0, 0, 0);
		off = new Vec3f(0, 0, 0);
		texX = texY = 0;
	}

	@SideOnly(Side.CLIENT) @Override
	public void render(EditorTileEntity tile){
		if(turbo == null) recompile(tile.textureX, tile.textureY);
		turbo.render();
	}

	@Override
	public void apply(String key, float value, Object obj){
		switch(key){
			case "width": size.xCoord = value; break;
			case "height": size.yCoord = value; break;
			case "depth": size.zCoord = value; break;
			case "pos_x": pos.xCoord = value; break;
			case "pos_y": pos.yCoord = value; break;
			case "pos_z": pos.zCoord = value; break;
		}
	}

	@SideOnly(Side.CLIENT) @Override
	public void recompile(int texx, int texy){
		if(turbo != null){ GL11.glDeleteLists(turbo.displaylist(), 0); turbo = null; }
		turbo = new ModelRendererTurbo(null, texx, texy, texX, texY);
		turbo.addBox(off.xCoord, off.yCoord, off.zCoord, size.xCoord, size.yCoord, size.zCoord);
		turbo.setRotationPoint(pos.xCoord, pos.yCoord, pos.zCoord);
	}

	@Override
	public EditorShape readFromNBT(NBTTagCompound compound){
		size = new Vec3f(compound.getFloat("width"), compound.getFloat("height"), compound.getFloat("depth"));
		pos = new Vec3f(compound.getFloat("pos_x"), compound.getFloat("pos_y"), compound.getFloat("pos_z"));
		off = new Vec3f(compound.getFloat("off_x"), compound.getFloat("off_y"), compound.getFloat("off_z"));
		texX = compound.getInteger("texture_x"); texY = compound.getInteger("texture_y"); return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		compound.setFloat("width", size.xCoord); compound.setFloat("height", size.yCoord); compound.setFloat("depth", size.zCoord);
		compound.setFloat("pos_x", pos.xCoord); compound.setFloat("pos_y", pos.yCoord); compound.setFloat("pos_z", pos.zCoord);
		compound.setFloat("off_x", off.xCoord); compound.setFloat("off_y", off.yCoord); compound.setFloat("off_z", off.zCoord);
		compound.setFloat("texture_x", texX); compound.setFloat("texture_y", texY); return compound;
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