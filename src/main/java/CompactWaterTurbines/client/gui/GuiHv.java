package CompactWaterTurbines.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import CompactWaterTurbines.client.container.ContainerHv;
import CompactWaterTurbines.client.container.ContainerMv;
import CompactWaterTurbines.lib.ModInfo;
import CompactWaterTurbines.tiles.TileHvTurbine;

public class GuiHv extends GuiContainer{
	
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID.toLowerCase(), "textures/gui/GUIWaterGenerator.png");

	TileHvTurbine tilehv;
	ContainerHv container;


	public GuiHv(EntityPlayer player, TileHvTurbine tile) 
	{
		super(new ContainerHv(tile, player));
		this.container = (ContainerHv) inventorySlots;
		this.xSize = 176;
		this.ySize = 167;
		tilehv = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) 
	{
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);	
		
		if(tilehv.hasWater)
		{
			drawTexturedModalRect(k + 80, l + 45, 176, 0, 14, 14);
		}
	}
	
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) 
	{
		String name = StatCollector.translateToLocal("tile.CompactWaterTurbines.hvwaterturbine.name");
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

}
