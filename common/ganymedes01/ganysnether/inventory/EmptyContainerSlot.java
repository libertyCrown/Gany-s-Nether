package ganymedes01.ganysnether.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class EmptyContainerSlot extends Slot {

	public EmptyContainerSlot(IInventory inventory, int slot, int posX, int posY) {
		super(inventory, slot, posX, posY);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return FluidContainerRegistry.isEmptyContainer(stack);
	}
}