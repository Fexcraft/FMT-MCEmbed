package net.fexcraft.mod.fmt.polygons;

import java.util.Collection;
import java.util.HashMap;

import javax.annotation.Nullable;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EditorShapeCompound {

	public static final HashMap<String, Class<? extends EditorShapeCompound>> SHAPECOMPOUND_DICTIONARY = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	public abstract void render(EditorTileEntity tile);
	
	@SideOnly(Side.CLIENT) /** recompile (or compile if new) a new display list for the specific shape*/
	public abstract void recompile(int member, int texX, int texY);

	public abstract EditorShapeCompound readFromNBT(NBTTagCompound compound);

	public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);
	
	public abstract EditorShapeCompound parse(String string, String type);
	
	public abstract EditorShapeCompound write(StringBuffer buff, String type);

	public abstract int addShape(int id, EditorShape object);
	
	public abstract int delShape(int id, EditorShape object);
	
	public abstract void apply(int id, String key, float value, @Nullable Object obj);
	
	public abstract Collection<EditorShape> getEditorShapes();
	
	protected boolean valid(int id){
		return getEditorShapes().isEmpty() ? false : id > 0 && id < getEditorShapes().size();
	}
	
}