package net.I_love_arsenic.magcom.common.items.wands;

import net.I_love_arsenic.magcom.common.entities.MagicEntity;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandType;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandCall;
import net.I_love_arsenic.magcom.core.packets.PlayerVelocityPKT;
import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.TimerTask;

/*
Notes
Spell Levels denote strength and overall damage not combat effectiveness
    Levels from lowest to highest
    1. Basic
    2. Intermediate
    3. Advanced
    4. Saint
    5. King
    6. Emperor
    7. Divine

    Spells have 2 types inside each affinity
    1. Attack
    2. Utility
 */

public class FireWand extends WandUtils implements WandCall {

    @Override
    public void useSpell(int index, World worldIn, PlayerEntity playerIn, Hand handIn) {
        switch (index) {
            case 0: movementL1(worldIn, playerIn, handIn); break;
            case 1: attackL1(worldIn, playerIn, handIn); break;
            case 2: attackL1(worldIn, playerIn, handIn); break;
            case 3: attackL1(worldIn, playerIn, handIn); break;
            case 4: attackL1(worldIn, playerIn, handIn); break;
            case 5: attackL5(worldIn, playerIn, handIn); break;
            case 6: attackL6(worldIn, playerIn, handIn); break;
        }
    }

    private static void movementL1(World world, PlayerEntity player, Hand hand) {
        ServerUtils.spawnParticles((ServerWorld) world, ParticleTypes.SMOKE, player.getPosX(), player.getPosY(), player.getPosZ(), 5);
        Vector3d motion = player.getMotion().mul(1.25, 2, 1.25);
        ServerUtils.sendTo((ServerPlayerEntity) player, new PlayerVelocityPKT(motion));
    }

    private static void attackL1(World world, PlayerEntity player, Hand hand) {
        MagicEntity projectile = new MagicEntity(player, world)
                .setDamage(2)
                .setParticle(ParticleTypes.FLAME)
                .setMagicType(WandType.FIRE);
        projectile.shoot(player.getLookVec(), 2, 1);
        world.addEntity(projectile);
    }

    private static void attackL6(World world, PlayerEntity player, Hand hand) {
        MagicEntity[] projectiles = new MagicEntity[10];
        Vector3d shootDir = player.getLookVec();
        for (int i = 0; i < 10; i++) {
            projectiles[i] = new MagicEntity(player, world)
                    .setDamage(10)
                    .setParticle(ParticleTypes.SOUL_FIRE_FLAME)
                    .setParticleCount(3)
                    .setMagicType(WandType.FIRE)
                    .setParticleSpeed(0);
            projectiles[i].setNoGravity(true);
            world.addEntity(projectiles[i]);
        }

        ServerUtils.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (MagicEntity projectile : projectiles) {
                    ServerUtils.timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            projectile.shoot(shootDir, 5, 1);
                        }
                    }, 50);
                }
            }
        }, 1000);
    }

    private static void attackL5(World world, PlayerEntity player, Hand hand) {
        BlockRayTraceResult result = WandUtils.doRayTrace(world, player, RayTraceContext.FluidMode.NONE, 20);
        Vector3d hitCenter = result.getHitVec();

        Vector3d[] vectors = new Vector3d[8];
        vectors[0] = hitCenter.add(new Vector3d(0, 20, 7));
        vectors[1] = hitCenter.add(new Vector3d(0, 20, -7));
        vectors[2] = hitCenter.add(new Vector3d(7, 20, 0));
        vectors[3] = hitCenter.add(new Vector3d(-7, 20, 0));
        vectors[4] = hitCenter.add(new Vector3d(7, 20, 7));
        vectors[5] = hitCenter.add(new Vector3d(-7, 20, -7));
        vectors[6] = hitCenter.add(new Vector3d(7, 20, 7));
        vectors[7] = hitCenter.add(new Vector3d(-7, 20, -7));

        MagicEntity[] projectiles = new MagicEntity[8];

        for (int i = 0; i < vectors.length; i++) {
            MagicEntity projectile = new MagicEntity(vectors[i], world)
                    .setDamage(3)
                    .setParticle(ParticleTypes.FLAME)
                    .setMagicType(WandType.FIRE);
            projectile.setNoGravity(true);
            projectiles[i] = projectile;
            world.addEntity(projectiles[i]);
        }

        ServerUtils.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < projectiles.length; i++) {
                    projectiles[i].shoot(vectors[i], hitCenter, 4, 1);
                }
            }
        }, 1000);
    }
}
