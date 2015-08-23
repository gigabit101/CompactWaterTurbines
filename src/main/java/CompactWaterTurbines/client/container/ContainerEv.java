package CompactWaterTurbines.client.container;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import CompactWaterTurbines.tiles.TileEvTurbine;

public class ContainerEv extends ContainerWaterTurbine{
	public TileEvTurbine tile;
	public EntityPlayer player;
	private boolean hasWater;
	private boolean initialized;
	
	public ContainerEv(TileEvTurbine tile, EntityPlayer player)
	{
		super();
		this.tile = tile;
		this.player = player;
		
        this.addSlotToContainer(new Slot(tile.inventory, 0, 80, 26));
		
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9
						+ 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18,
					142));
		}
	}
	
    @Override
    public void detectAndSendChanges() 
    {
        super.detectAndSendChanges();
        @SuppressWarnings("unchecked")
        List<ICrafting> crafters = this.crafters;
        for (ICrafting crafter : crafters) 
        {
            if (hasWater != tile.hasWater || !initialized) 
            {
                crafter.sendProgressBarUpdate(this, 0, tile.hasWater ? 1 : 0);
            }
        }
        initialized = true;
        hasWater = tile.hasWater;
    }

    @Override
    public void updateProgressBar(int i, int j)
    {
        if (i == 0) 
        {
            tile.hasWater = (j == 1);
        }
    }
	

	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}

}
