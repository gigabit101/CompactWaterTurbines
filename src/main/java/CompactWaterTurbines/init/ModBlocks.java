package CompactWaterTurbines.init;

import ic2.api.item.IC2Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import CompactWaterTurbines.blocks.BlockEvWaterTurbine;
import CompactWaterTurbines.blocks.BlockHvWaterTurbine;
import CompactWaterTurbines.blocks.BlockLvWaterTurbine;
import CompactWaterTurbines.blocks.BlockMvWaterTurbine;
import CompactWaterTurbines.tiles.TileEvTurbine;
import CompactWaterTurbines.tiles.TileHvTurbine;
import CompactWaterTurbines.tiles.TileLvTurbine;
import CompactWaterTurbines.tiles.TileMvTurbine;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block LvWaterTurbine;
	public static Block MvWaterTurbine;
	public static Block HvWaterTurbine;
	public static Block EvWaterTurbine;
	
	public static void init()
	{
		LvWaterTurbine = new BlockLvWaterTurbine(Material.iron);
		GameRegistry.registerBlock(LvWaterTurbine, "lvwaterturbine");
		GameRegistry.registerTileEntity(TileLvTurbine.class, "TileLvTurbine");
		
		MvWaterTurbine = new BlockMvWaterTurbine(Material.iron);
		GameRegistry.registerBlock(MvWaterTurbine, "mvwaterturbine");
		GameRegistry.registerTileEntity(TileMvTurbine.class, "TileMvTurbine");
		
		HvWaterTurbine = new BlockHvWaterTurbine(Material.iron);
		GameRegistry.registerBlock(HvWaterTurbine, "hvwaterturbine");
		GameRegistry.registerTileEntity(TileHvTurbine.class, "TileHvTurbine");
		
		EvWaterTurbine = new BlockEvWaterTurbine(Material.iron);
		GameRegistry.registerBlock(EvWaterTurbine, "evwaterturbine");
		GameRegistry.registerTileEntity(TileEvTurbine.class, "TileEvTurbine");
		registerRecipes();
	}
	
	public static void registerRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(LvWaterTurbine), 
				"WWW", "WTW", "WWW",
				'W', IC2Items.getItem("waterMill"),
				'T', IC2Items.getItem("lvTransformer"));
		GameRegistry.addShapedRecipe(new ItemStack(MvWaterTurbine), 
				"WWW", "WTW", "WWW",
				'W', new ItemStack(LvWaterTurbine),
				'T', IC2Items.getItem("mvTransformer"));
		GameRegistry.addShapedRecipe(new ItemStack(HvWaterTurbine), 
				"WWW", "WTW", "WWW",
				'W', new ItemStack(MvWaterTurbine),
				'T', IC2Items.getItem("hvTransformer"));
		GameRegistry.addShapedRecipe(new ItemStack(EvWaterTurbine), 
				"WWW", "WTW", "WWW",
				'W', new ItemStack(HvWaterTurbine),
				'T', IC2Items.getItem("evTransformer"));
	}

}
