package ganymedes01.ganysnether.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SlowExplosion extends Explosion {

	private final ArrayList<EntityLivingBase> entitiesInRange = new ArrayList<EntityLivingBase>();
	private final World worldObj;

	public SlowExplosion(World world, Entity entity, double x, double y, double z, float power) {
		super(world, entity, x, y, z, power);
		worldObj = world;
	}

	@Override
	public void doExplosionA() {
		super.doExplosionA();

		List list = worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getAABBPool().getAABB(explosionX - explosionSize, explosionY - explosionSize, explosionZ - explosionSize, explosionX + explosionSize, explosionY + explosionSize, explosionZ + explosionSize),
		IEntitySelector.selectAnything);
		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				EntityLivingBase entity = (EntityLivingBase) iterator.next();
				if (entity.worldObj == worldObj) {
					int duration = 3000;
					PotionEffect slowness = entity.getActivePotionEffect(Potion.moveSlowdown);
					if (slowness != null && slowness.getAmplifier() == 2)
						duration += slowness.duration;
					entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration, 2));
				}
			}
		}
	}
}