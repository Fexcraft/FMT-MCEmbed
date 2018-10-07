package net.fexcraft.mod.fmt.polygons;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** base object to hold data of shapes and temporary render objects */
public abstract class EditorShape {
	
	public static final HashMap<String, Class<? extends EditorShape>> SHAPE_DICTIONARY = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	public abstract void render(EditorTileEntity tile);
	
	/** 
	 * @param key the key of the internal value to be changed
	 * @param value the new value
	 * @param obj alternative value for non-float values
	**/
	public abstract void apply(String key, float value, @Nullable Object obj);
	
	@SideOnly(Side.CLIENT) /** recompile (or compile if new) a new display list for this shape */
	public abstract void recompile();
	
	@Nullable
	public static EditorShape newShape(String type, NBTTagCompound compound){
		Class<? extends EditorShape> clazz = SHAPE_DICTIONARY.get(type);
		if(clazz == null) return null;
		else{
			try{
				return clazz.newInstance().readFromNBT(compound);
			}
			catch(Exception e){ e.printStackTrace(); return null; }
		}
	}

	public abstract EditorShape readFromNBT(NBTTagCompound compound);
	
	public abstract NBTTagCompound writToNBT(NBTTagCompound compound);
	
	public abstract EditorShape parse(String string, String type);
	
	public abstract EditorShape write(StringBuffer buff, String type);
	
}