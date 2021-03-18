package net.I_love_arsenic.magcom.common.entities;

import net.I_love_arsenic.magcom.common.items.wands.utils.WandType;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.core.init.EntityTypeInit;
import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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

public class MagicEntity extends ProjectileItemEntity {

    //Particle Customization Variables
    private BasicParticleType particle;
    private int count;
    private double speed;
    private Double random;

    private int damage;

    public WandType type;

    public MagicEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MagicEntity(PlayerEntity shooter, World worldIn) {
        super(EntityTypeInit.MAGIC_PROJECTILE.get(), shooter, worldIn);
    }

    public MagicEntity(Vector3d vect, World world) {
        super(EntityTypeInit.MAGIC_PROJECTILE.get(), vect.x, vect.y, vect.z, world);
    }

    public MagicEntity setParticleCount(int count) { this.count = count; return this; }
    public MagicEntity setParticle(BasicParticleType particle) { this.particle = particle; return this; }
    public MagicEntity setDamage(int damage) { this.damage = damage; return this; }
    public MagicEntity setParticleRandom(Double random) { this.random = random; return this; }
    public MagicEntity setMagicType(WandType type) { this.type = type; return this; }
    public MagicEntity setParticleSpeed(double speed) { this.speed = speed; return this; }

    private void doStatusEffect(PlayerEntity player) {
        //statusEffect.onEvent();
    }

    private boolean isOpposing(MagicEntity entity) {
        return WandUtils.getOpposing(type) == entity.type;
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
                if (isOpposing((MagicEntity) entity)) {
                    if (!this.world.isRemote) {
                        this.remove();
                    }
                }
            }
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this), damage);
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
            if (random == null) {
                ServerUtils.spawnParticles(
                        (ServerWorld) this.world,
                        particle,
                        this.getPosX(),
                        this.getPosY(),
                        this.getPosZ(),
                        count == 0 ? 1 : count,
                        speed
                );
            } else {
                ServerUtils.spawnParticles(
                        (ServerWorld) this.world,
                        particle,
                        this.getPosXRandom(random),
                        this.getPosYRandom(),
                        this.getPosZRandom(random),
                        count == 0 ? 1 : count,
                        speed
                );
            }

        }
    }
}
