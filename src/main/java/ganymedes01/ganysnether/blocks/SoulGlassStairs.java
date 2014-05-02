package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SoulGlassStairs extends BlockStairs {

	SoulGlassStairs() {
		super(ModBlocks.soulGlass, 1);
		setHardness(0.3F);
		setLightOpacity(3);
		setStepSound(soundTypeGlass);
		setCreativeTab(GanysNether.netherTab);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.SOUL_GLASS_STAIRS_NAME));
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		int quantity = rand.nextInt(10);
		if (quantity == 5)
			return 1;
		return 0;
	}
}