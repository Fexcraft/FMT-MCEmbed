package net.fexcraft.mod.fmt.polygons.cuboid;

import org.lwjgl.opengl.GL11;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.fexcraft.mod.fmt.polygons.EditorShape;
import net.fexcraft.mod.lib.tmt.ModelRendererTurbo;
import net.fexcraft.mod.lib.util.math.Vec3f;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TMTShapebox extends TMTCuboid {
	
	private Vec3f[] corners = new Vec3f[8];
	
	public TMTShapebox(){
		for(int i = 0; i < corners.length; i++){
			corners[i] = new Vec3f(0, 0, 0);
		}
	}

	@SideOnly(Side.CLIENT) @Override
	public void render(EditorTileEntity tile){
		if(turbo == null) recompile(tile.textureX, tile.textureY);
		turbo.render();
	}

	@Override
	public void apply(String key, float value, Object obj){
		super.apply(key, value, obj);
		if(key.startsWith("cor")){
			try{
				int cor = Integer.parseInt(key.replace("cor", "").substring(0, 1));
				switch(key.substring(key.indexOf("_") + 1)){
					case "x": corners[cor].xCoord = value; break;
					case "y": corners[cor].yCoord = value; break;
					case "z": corners[cor].zCoord = value; break;
				}
			}
			catch(Exception e){ e.printStackTrace(); }
		}
	}

	@SideOnly(Side.CLIENT) @Override
	public void recompile(int texx, int texy){
		if(turbo != null){ GL11.glDeleteLists(turbo.displaylist(), 0); turbo = null; }
		turbo = new ModelRendererTurbo(null, texx == -1 ? 256 : texx, texy == -1 ? 256 : texy, texX, texY);
		turbo.addShapeBox(off.xCoord, off.yCoord, off.zCoord, size.xCoord, size.yCoord, size.zCoord, 1,
			corners[0].xCoord, corners[0].yCoord, corners[0].zCoord,
			corners[1].xCoord, corners[1].yCoord, corners[1].zCoord,
			corners[2].xCoord, corners[2].yCoord, corners[2].zCoord,
			corners[3].xCoord, corners[3].yCoord, corners[3].zCoord,
			corners[4].xCoord, corners[4].yCoord, corners[4].zCoord,
			corners[5].xCoord, corners[5].yCoord, corners[5].zCoord,
			corners[6].xCoord, corners[6].yCoord, corners[6].zCoord,
			corners[7].xCoord, corners[7].yCoord, corners[7].zCoord);
		turbo.setRotationPoint(pos.xCoord, pos.yCoord, pos.zCoord);
		turbo.rotateAngleX = rot.xCoord; turbo.rotateAngleY = rot.yCoord; turbo.rotateAngleZ = rot.zCoord;
	}

	@Override
	public EditorShape readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		corners[0] = new Vec3f(compound.getFloat("cor0_x"), compound.getFloat("cor0_y"), compound.getFloat("cor0_z"));
		corners[1] = new Vec3f(compound.getFloat("cor1_x"), compound.getFloat("cor1_y"), compound.getFloat("cor1_z"));
		corners[2] = new Vec3f(compound.getFloat("cor2_x"), compound.getFloat("cor2_y"), compound.getFloat("cor2_z"));
		corners[3] = new Vec3f(compound.getFloat("cor3_x"), compound.getFloat("cor3_y"), compound.getFloat("cor3_z"));
		corners[4] = new Vec3f(compound.getFloat("cor4_x"), compound.getFloat("cor4_y"), compound.getFloat("cor4_z"));
		corners[5] = new Vec3f(compound.getFloat("cor5_x"), compound.getFloat("cor5_y"), compound.getFloat("cor5_z"));
		corners[6] = new Vec3f(compound.getFloat("cor6_x"), compound.getFloat("cor6_y"), compound.getFloat("cor6_z"));
		corners[7] = new Vec3f(compound.getFloat("cor7_x"), compound.getFloat("cor7_y"), compound.getFloat("cor7_z"));
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		compound = super.writeToNBT(compound);
		for(int i = 0; i < corners.length; i++){
			compound.setFloat("cor" + i + "_x", corners[i].xCoord);
			compound.setFloat("cor" + i + "_y", corners[i].yCoord);
			compound.setFloat("cor" + i + "_z", corners[i].zCoord);
		}
		compound.setString("type", "tmt.shapebox");
		return compound;
	}
	
}