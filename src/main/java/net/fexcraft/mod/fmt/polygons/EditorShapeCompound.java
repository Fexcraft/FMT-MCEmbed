package net.fexcraft.mod.fmt.polygons;

import java.util.HashMap;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EditorShapeCompound {

	public static final HashMap<String, Class<? extends EditorShapeCompound>> SHAPECOMPOUND_DICTIONARY = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	public abstract void render(EditorTileEntity tile);
	
	@SideOnly(Side.CLIENT) /** recompile (or compile if new) a new display list for the specific shape*/
	public abstract void recompile(int member);

	public abstract EditorShapeCompound readFromNBT(NBTTagCompound compound);

	public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);
	
	public abstract EditorShape parse(String string, String type);
	
	public abstract EditorShape write(StringBuffer buff, String type);
	
}