package CompactWaterTurbines.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import CompactWaterTurbines.client.container.ContainerEv;
import CompactWaterTurbines.client.container.ContainerHv;
import CompactWaterTurbines.client.container.ContainerLv;
import CompactWaterTurbines.client.container.ContainerMv;
import CompactWaterTurbines.client.gui.GuiEv;
import CompactWaterTurbines.client.gui.GuiHv;
import CompactWaterTurbines.client.gui.GuiLv;
import CompactWaterTurbines.client.gui.GuiMv;
import CompactWaterTurbines.tiles.TileEvTurbine;
import CompactWaterTurbines.tiles.TileHvTurbine;
import CompactWaterTurbines.tiles.TileLvTurbine;
import CompactWaterTurbines.tiles.TileMvTurbine;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int lvID = 0;
	public static final int mvID = 1;
	public static final int hvID = 2;
	public static final int evID = 3;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == lvID)
		{
			return new ContainerLv((TileLvTurbine) world.getTileEntity(x, y, z), player);
		}
		else if (ID == mvID)
		{
			return new ContainerMv((TileMvTurbine) world.getTileEntity(x, y, z), player);
		}
		else if (ID == hvID)
		{
			return new ContainerHv((TileHvTurbine) world.getTileEntity(x, y, z), player);
		}
		else if (ID == evID)
		{
			return new ContainerEv((TileEvTurbine) world.getTileEntity(x, y, z), player);
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == lvID)
		{
			return new GuiLv(player, (TileLvTurbine) world.getTileEntity(x, y, z));
		}
		if (ID == mvID)
		{
			return new GuiMv(player, (TileMvTurbine) world.getTileEntity(x, y, z));
		}
		if (ID == hvID)
		{
			return new GuiHv(player, (TileHvTurbine) world.getTileEntity(x, y, z));
		}
		if (ID == evID)
		{
			return new GuiEv(player, (TileEvTurbine) world.getTileEntity(x, y, z));
		}
		return null;
	}

}
