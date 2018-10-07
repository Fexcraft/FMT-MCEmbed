package net.fexcraft.mod.fmt;

import net.fexcraft.mod.fmt.polygons.EditorShape;
import net.fexcraft.mod.fmt.polygons.EditorShapeCompound;
import net.fexcraft.mod.fmt.polygons.TMTCompound;
import net.fexcraft.mod.fmt.polygons.cuboid.TMTCuboid;
import net.fexcraft.mod.lib.util.registry.RegistryUtil.AutoRegisterer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = FMT.MODID, name = FMT.NAME, version = FMT.VERSION, dependencies = "required-after:fcl;")
public class FMT {
	
    public static final String MODID = "fmt";
    public static final String NAME = "Fexcraft Modelling Toolbox";
    public static final String VERSION = "1.0";
    private static AutoRegisterer registerer;
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        registerer = new AutoRegisterer(MODID);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	EditorShapeCompound.SHAPECOMPOUND_DICTIONARY.put("tmt", TMTCompound.class);
    	//
    	EditorShape.SHAPE_DICTIONARY.put("tmt.box", TMTCuboid.class);
    	EditorShape.SHAPE_DICTIONARY.put("tmt.cube", TMTCuboid.class);
    }
    
    public static final AutoRegisterer getAutoRegisterer(){
    	return registerer;
    }
    
    public static final Logger getLogger(){
    	return logger;
    }
    
}
