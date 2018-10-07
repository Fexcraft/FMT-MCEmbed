package net.fexcraft.mod.fmt.polygons;

import java.util.ArrayList;
import java.util.List;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.minecraft.nbt.NBTTagCompound;

public class TMTCompound extends EditorShapeCompound {
	
	private ArrayList<EditorShape> shapes = new ArrayList<EditorShape>();

	@Override
	public void render(EditorTileEntity tile){
		for(EditorShape shape : shapes) shape.render(tile);
	}

	@Override
	public void recompile(int member, int texX, int texY){
		if(valid(member)) shapes.get(member).recompile(texX, texY);
	}

	@Override
	public EditorShapeCompound readFromNBT(NBTTagCompound compound){
		int amount = compound.getInteger("Shapes");
		for(int i = 0; i < amount; i++){
			NBTTagCompound com = compound.getCompoundTag("Shape" + i);
			if(com == null) continue;
			Class<? extends EditorShape> clazz = EditorShape.SHAPE_DICTIONARY.get(com.getString("type"));
			if(clazz == null) continue; try{
				addShape(-1, clazz.newInstance().readFromNBT(com));
			} catch(Exception e){ e.printStackTrace(); continue; }
		}
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		if(shapes.size() > 0){
			compound.setInteger("Shapes", shapes.size());
			for(int i = 0; i < shapes.size(); i++){
				NBTTagCompound com = shapes.get(i).writeToNBT(new NBTTagCompound());
				if(com != null) compound.setTag("Shape" + i, com); 
			}
		} compound.setString("type", "tmt");
		return compound;
	}

	@Override
	public EditorShapeCompound parse(String string, String type){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditorShapeCompound write(StringBuffer buff, String type){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addShape(int id, EditorShape object){
		if(valid(id)){ shapes.add(id, object); return id; }
		else{ shapes.add(object); return shapes.size() - 1; }
	}

	@Override
	public int delShape(int id, EditorShape object){
		if(valid(id)){ shapes.remove(id); return 0; } else { return shapes.isEmpty() ? 0 : shapes.size() - 1; }
	}

	@Override
	public void apply(int id, String key, float value, Object obj){
		if(valid(id)) shapes.get(id).apply(key, value, obj); else return;
	}

	@Override
	public List<EditorShape> getEditorShapes(){
		return shapes;
	}
	
}