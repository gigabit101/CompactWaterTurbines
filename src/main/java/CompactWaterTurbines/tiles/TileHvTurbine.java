package CompactWaterTurbines.tiles;

import ic2.api.energy.prefab.BasicSource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import CompactWaterTurbines.init.ModBlocks;

public class TileHvTurbine extends TileEntity implements IWrenchable, IEnergyTile{
	
	public BasicSource energy;
	public static int euTick = 512;
    private boolean initialized;
	public boolean hasWater = false;
	
	public TileHvTurbine()
	{
		energy = new BasicSource(this, 10000, 2);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.energy.updateEntity();
			if(!worldObj.isRemote)
			{
				for(ForgeDirection fd : new ForgeDirection[]{ForgeDirection.UP,ForgeDirection.SOUTH,ForgeDirection.EAST})
					if(!worldObj.isAirBlock(xCoord+fd.offsetX, yCoord+fd.offsetY, zCoord+fd.offsetZ) && !worldObj.isAirBlock(xCoord+fd.getOpposite().offsetX, yCoord+fd.getOpposite().offsetY, zCoord+fd.getOpposite().offsetZ))
					{
						boolean isWater = worldObj.getBlock(xCoord+fd.offsetX, yCoord+fd.offsetY, zCoord+fd.offsetZ) == Blocks.water;
						if(isWater)
						{
							energy.addEnergy(euTick);
						}
						hasWater = isWater;

					}
			}		
	}
	
	
	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) 
	{
		return false;
	}

	@Override
	public short getFacing() 
	{
		return 0;
	}

	@Override
	public void setFacing(short facing) {}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) 
	{
		return true;
	}

	@Override
	public float getWrenchDropRate() 
	{
		return 0;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) 
	{
		return new ItemStack(ModBlocks.HvWaterTurbine);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		energy.readFromNBT(tagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		energy.writeToNBT(tagCompound);
	}
	
	@Override
	public void onChunkUnload()
	{
		energy.onChunkUnload();
        super.onChunkUnload();
	}

	@Override
	public void invalidate()
	{
		energy.invalidate();
		super.invalidate();
	}

}
