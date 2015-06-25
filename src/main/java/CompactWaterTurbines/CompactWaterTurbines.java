package CompactWaterTurbines;

import CompactWaterTurbines.client.GuiHandler;
import CompactWaterTurbines.init.ModBlocks;
import CompactWaterTurbines.lib.ModInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(name = ModInfo.MOD_NAME, modid = ModInfo.MOD_ID, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEP)
public class CompactWaterTurbines 
{
	@Mod.Instance
	public static CompactWaterTurbines Instance;
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModBlocks.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Instance, new GuiHandler());
	}

}
