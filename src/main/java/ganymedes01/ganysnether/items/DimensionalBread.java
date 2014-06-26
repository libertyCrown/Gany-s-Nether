package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class DimensionalBread extends ItemFood {

	DimensionalBread() {
		super(5, false);
		setCreativeTab(GanysNether.netherTab);
		setPotionEffect(Potion.invisibility.id, 60, 0, 0.5F);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.DIMENSIONAL_BREAD_NAME));
	}

	public static void setIcon(IIcon icon) {
		((DimensionalBread) ModItems.dimensionalBread).itemIcon = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}
}