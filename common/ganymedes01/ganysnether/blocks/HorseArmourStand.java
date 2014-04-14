package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class HorseArmourStand extends BlockContainer {

	HorseArmourStand() {
		super(ModIDs.HORSE_ARMOUR_STAND_ID, Material.iron);
		setHardness(1.0F);
		setTextureName("stone");
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.HORSE_ARMOUR_STAND_NAME));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			int meta = world.getBlockMetadata(x, y, z);
			switch (meta) {
				case 1:
					y--;
					break;
				case 2:
					z--;
					break;
				case 3:
					x++;
					break;
				case 4:
					z++;
					break;
				case 5:
					x--;
					break;
				case 6:
					y--;
					z--;
					break;
				case 7:
					y--;
					x++;
					break;
				case 8:
					y--;
					z++;
					break;
				case 9:
					y--;
					x--;
					break;
			}

			TileEntityHorseArmourStand tile = (TileEntityHorseArmourStand) world.getBlockTileEntity(x, y, z);
			if (tile != null)
				if (tile.getArmourType() < 0) {
					if (player.getCurrentEquippedItem() != null) {
						ItemStack stack = player.getCurrentEquippedItem();
						boolean added = false;
						if (stack.itemID == Item.horseArmorDiamond.itemID) {
							tile.setArmourType((byte) 2);
							added = true;
						} else if (stack.itemID == Item.horseArmorGold.itemID) {
							tile.setArmourType((byte) 1);
							added = true;
						} else if (stack.itemID == Item.horseArmorIron.itemID) {
							tile.setArmourType((byte) 0);
							added = true;
						} else
							return false;
						if (added && !player.capabilities.isCreativeMode)
							player.setCurrentItemOrArmor(0, null);
					}
				} else {
					ItemStack stack = null;
					switch (tile.getArmourType()) {
						case 0:
							stack = new ItemStack(Item.horseArmorIron);
							break;
						case 1:
							stack = new ItemStack(Item.horseArmorGold);
							break;
						case 2:
							stack = new ItemStack(Item.horseArmorDiamond);
							break;
					}
					if (stack != null) {
						if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(stack))
							Utils.dropStack(world, (int) player.posX, (int) player.posY, (int) player.posZ, stack);
						tile.setArmourType((byte) -1);
					}
				}
			return true;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		switch (meta) {
			case 0:
				TileEntity tile = world.getBlockTileEntity(x, y, z);
				if (tile instanceof TileEntityHorseArmourStand) {
					ItemStack stack = null;
					switch (((TileEntityHorseArmourStand) tile).getArmourType()) {
						case 0:
							stack = new ItemStack(Item.horseArmorIron);
							break;
						case 1:
							stack = new ItemStack(Item.horseArmorGold);
							break;
						case 2:
							stack = new ItemStack(Item.horseArmorDiamond);
							break;
					}
					if (stack != null)
						Utils.dropStack(world, x, y + 1, z, stack);

					byte X = 0;
					byte Z = 0;
					switch (((TileEntityHorseArmourStand) tile).getRotation()) {
						case 0:
							Z = 1;
							break;
						case 1:
							X = -1;
							break;
						case 2:
							Z = -1;
							break;
						case 3:
							X = 1;
							break;
					}
					destroyBlock(world, x + X, y + 1, z + Z);
					destroyBlock(world, x + X, y, z + Z);
					destroyBlock(world, x, y + 1, z);
				}
				Utils.dropStack(world, x, y, z, new ItemStack(blockID, 1, 0));
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				byte X = 0;
				byte Z = 0;
				switch (meta - 2) {
					case 0:
						Z = -1;
						break;
					case 1:
						X = 1;
						break;
					case 2:
						Z = 1;
						break;
					case 3:
						X = -1;
						break;
				}
				destroyBlock(world, x, y + 1, z);
				destroyBlock(world, x + X, y, z + Z);
				destroyBlock(world, x + X, y + 1, z + Z);
				break;
			case 1:
			case 6:
			case 7:
			case 8:
			case 9:
				destroyBlock(world, x, y - 1, z);
				break;
		}
		super.breakBlock(world, x, y, z, id, meta);
	}

	private void destroyBlock(World world, int x, int y, int z) {
		world.playAuxSFXAtEntity(null, 2001, x, y, z, world.getBlockId(x, y, z) + (world.getBlockMetadata(x, y, z) << 12));
		world.setBlockToAir(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHorseArmourStand();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}
}