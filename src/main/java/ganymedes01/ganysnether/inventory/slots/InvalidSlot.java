package ganymedes01.ganysnether.inventory.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class InvalidSlot extends Slot {

	public InvalidSlot(IInventory inventory, int slot, int posX, int posY) {
		super(inventory, slot, posX, posY);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}