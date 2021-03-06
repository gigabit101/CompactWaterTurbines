package CompactWaterTurbines.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import CompactWaterTurbines.CompactWaterTurbines;
import CompactWaterTurbines.client.CreativeTabMod;
import CompactWaterTurbines.client.GuiHandler;
import CompactWaterTurbines.lib.ModInfo;
import CompactWaterTurbines.tiles.TileMvTurbine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMvWaterTurbine extends BlockContainer{

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;

	public BlockMvWaterTurbine(Material material)
	{
		super(material);
		setCreativeTab(CreativeTabMod.instance);
		setHardness(1.0F);
		setBlockName(ModInfo.MOD_ID + ".mvwaterturbine");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileMvTurbine();
	}
	

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
			player.openGui(CompactWaterTurbines.Instance, GuiHandler.mvID, world, x, y,z);
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon("compactwaterturbines:wmvSide");
		this.iconTop = icon.registerIcon("compactwaterturbines:wmvTop");
		this.iconBottom = icon.registerIcon("compactwaterturbines:wmvBottom");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return metadata == 0 && side == 3 ? this.blockIcon
				: side == 1 ? this.iconTop : 
					side == 0 ? this.iconBottom: (side == 0 ? this.iconTop
						: (side == metadata ? this.blockIcon : this.blockIcon));

	}

}
