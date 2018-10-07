package net.fexcraft.mod.fmt;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = FMT.MODID, name = FMT.NAME, version = FMT.VERSION)
public class FMT {
	
    public static final String MODID = "fmt";
    public static final String NAME = "Fexcraft Modelling Toolbox";
    public static final String VERSION = "1.0";
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	//
    }
    
    public static Logger getLogger(){
    	return logger;
    }
    
}
