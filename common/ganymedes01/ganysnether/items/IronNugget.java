package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class IronNugget extends Item {

	public IronNugget() {
		super(ModIDs.IRON_NUGGET_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.IRON_NUGGET_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.IRON_NUGGET_NAME));
	}
}