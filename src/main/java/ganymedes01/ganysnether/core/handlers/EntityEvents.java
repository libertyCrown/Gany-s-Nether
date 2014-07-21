package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class EntityEvents {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void deathEvent(LivingDeathEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (!player.inventory.consumeInventoryItem(Item.getItemFromBlock(ModBlocks.undertaker)))
				return;

			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;
			if (!player.worldObj.isAirBlock(x, y, z))
				y++;
			player.worldObj.setBlock(x, y, z, ModBlocks.undertaker);
			TileEntityUndertaker undertaker = Utils.getTileEntity(player.worldObj, x, y, z, TileEntityUndertaker.class);
			if (undertaker != null) {
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					ItemStack cont = player.inventory.mainInventory[i];
					if (cont != null) {
						undertaker.setInventorySlotContents(i, cont.copy());
						player.inventory.mainInventory[i] = null;
					}
				}
				for (int i = 0; i < player.inventory.armorInventory.length; i++) {
					ItemStack cont = player.inventory.armorInventory[player.inventory.armorInventory.length - 1 - i];
					if (cont != null) {
						undertaker.setInventorySlotContents(i + player.inventory.mainInventory.length, cont);
						player.inventory.armorInventory[player.inventory.armorInventory.length - 1 - i] = null;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;
		Random rand = event.entityLiving.worldObj.rand;
		if (event.entityLiving instanceof EntitySilverfish) {
			addDrop(new ItemStack(ModItems.silverfishScale, rand.nextInt(3)), event.entityLiving, event.drops);
			return;
		} else if (event.entityLiving instanceof EntityBat) {
			addDrop(new ItemStack(ModItems.batWing, 1 + rand.nextInt(1)), event.entityLiving, event.drops);
			return;
		} else if (event.entityLiving instanceof EntityWolf) {
			addDrop(new ItemStack(ModItems.wolfTeeth, rand.nextInt(2)), event.entityLiving, event.drops);
			return;
		}
	}

	private void addDrop(ItemStack stack, EntityLivingBase entity, List<EntityItem> list) {
		if (stack.stackSize <= 0)
			return;

		EntityItem entityItem = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
		entityItem.delayBeforeCanPickup = 10;
		list.add(entityItem);
	}
}