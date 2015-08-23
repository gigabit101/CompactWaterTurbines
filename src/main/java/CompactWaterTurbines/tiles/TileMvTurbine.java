package CompactWaterTurbines.tiles;

import ic2.api.energy.prefab.BasicSource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import CompactWaterTurbines.init.ModBlocks;
import CompactWaterTurbines.util.Inventory;

public class TileMvTurbine extends TileEntity implements IWrenchable, IEnergyTile, IInventory {

	public BasicSource energy;
	public Inventory inventory = new Inventory(1, "TileMvTurbine", 64);
	public static int euTick = 64;
	public static int neededbuckets = 64;
	private boolean initialized;
	public boolean hasWater = false;
	public static int euPerBucket = 32000;
	int currentBuckets;


	public TileMvTurbine() {
		energy = new BasicSource(this, euPerBucket, 2);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		this.energy.updateEntity();
		if (!worldObj.isRemote) {
			for (ForgeDirection fd : new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.SOUTH,
					ForgeDirection.EAST })
				if (!worldObj.isAirBlock(xCoord + fd.offsetX, yCoord + fd.offsetY, zCoord + fd.offsetZ)
						&& !worldObj.isAirBlock(xCoord + fd.getOpposite().offsetX, yCoord + fd.getOpposite().offsetY,
								zCoord + fd.getOpposite().offsetZ)) {
					boolean isWater = worldObj.getBlock(xCoord + fd.offsetX, yCoord + fd.offsetY,
							zCoord + fd.offsetZ) == Blocks.water;
					if (isWater) {
						energy.addEnergy(euTick);
					}
					hasWater = isWater;

				}
		}

		if (!worldObj.isRemote && inventory.getStackInSlot(0) != null) {
			boolean hasBucket = inventory.getStackInSlot(0).getItem() == Items.water_bucket;
			
			if (hasBucket) 
			{
				currentBuckets = currentBuckets +1;
				inventory.setInventorySlotContents(0, new ItemStack(Items.bucket));
				System.out.print(currentBuckets);
			}
			if (currentBuckets == neededbuckets)
			{
				energy.addEnergy(euPerBucket);
				currentBuckets = 0;
			}
			hasWater = energy.getEnergyStored() != 0;
		}
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
		return false;
	}

	@Override
	public short getFacing() {
		return 0;
	}

	@Override
	public void setFacing(short facing) {
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 0;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.MvWaterTurbine);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		energy.readFromNBT(tagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		energy.writeToNBT(tagCompound);
	}

	@Override
	public void onChunkUnload() {
		energy.onChunkUnload();
		super.onChunkUnload();
	}

	@Override
	public void invalidate() {
		energy.invalidate();
		super.invalidate();
	}

	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inventory.decrStackSize(slot, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inventory.getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory.setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInventoryName() {
		return inventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return inventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return inventory.isUseableByPlayer(player);
	}

	@Override
	public void openInventory() {
		inventory.openInventory();
	}

	@Override
	public void closeInventory() {
		inventory.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if(stack.getItem() == Items.water_bucket)
			return true;
		return false;
	}

}
