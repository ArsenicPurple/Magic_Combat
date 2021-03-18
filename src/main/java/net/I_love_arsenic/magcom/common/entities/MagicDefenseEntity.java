package net.I_love_arsenic.magcom.common.entities;

import net.I_love_arsenic.magcom.core.init.EntityTypeInit;
import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagicDefenseEntity extends ProjectileItemEntity {

    BasicParticleType particle;
    int count;
    Double random;

    public MagicDefenseEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MagicDefenseEntity(PlayerEntity shooter, World worldIn) {
        super(EntityTypeInit.MAGIC_DEFENSE_PROJECTILE.get(), shooter, worldIn);
    }

    public MagicDefenseEntity(Vector3d vect, World world) {
        super(EntityTypeInit.MAGIC_DEFENSE_PROJECTILE.get(), vect.x, vect.y, vect.z, world);
    }

    public MagicDefenseEntity setParticleCount(int count) { this.count = count; return this; }
    public MagicDefenseEntity setParticle(BasicParticleType particle) { this.particle = particle; return this; }
    public MagicDefenseEntity setParticleRandom(Double random) { this.random = random; return this; }

    private double getParticleRandom() {
        return random == null ? 0.5D : random;
    }

    private void doStatusEffect(PlayerEntity player) {
        //statusEffect.onEvent();
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public IPacket<?> createSpawnPacket() { return NetworkHooks.getEntitySpawningPacket(this); }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (entity instanceof MagicEntity) {
                entity.remove();
            }
//            if (entity instanceof PlayerEntity) {
//
//                //Give player status effect if it has one
//                //i.e. burning, poison
//
//                if (!this.world.isRemote) {
//                    this.remove();
//                }
//            }
        }

        if (!world.isRemote()) {
            this.remove();
        }
    }

    public void shoot(Vector3d dir, float velocity, float inaccuracy) {
        super.shoot(dir.x, dir.y, dir.z, velocity, inaccuracy);
    }

    public void shoot(Vector3d start, Vector3d to, float velocity, float inaccuracy) {
        shoot(start.subtract(to).inverse(), velocity, inaccuracy);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isRemote) {
            ServerUtils.spawnParticles((ServerWorld) this.world, particle, this.getPosXRandom(getParticleRandom()), this.getPosYRandom(), this.getPosZRandom(getParticleRandom()), count == 0 ? 5 : count);
        }
    }
}
