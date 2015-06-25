package CompactWaterTurbines.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import CompactWaterTurbines.init.ModBlocks;
import CompactWaterTurbines.lib.ModInfo;

public class CreativeTabMod extends CreativeTabs{
	
	public static CreativeTabMod instance = new CreativeTabMod();


	public CreativeTabMod() 
	{
		super(ModInfo.MOD_ID);
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(ModBlocks.EvWaterTurbine);
	}

}
