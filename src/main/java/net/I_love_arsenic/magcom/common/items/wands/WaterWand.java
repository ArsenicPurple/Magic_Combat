package net.I_love_arsenic.magcom.common.items.wands;

import net.I_love_arsenic.magcom.common.entities.MagicDefenseEntity;
import net.I_love_arsenic.magcom.common.entities.MagicEntity;
import net.I_love_arsenic.magcom.common.items.wands.utils.BlockUtils;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandType;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandCall;
import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;

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

    Spells have 4 types inside each affinity
    1. Attack
    2. Utility
    3. Movement
    4. Defense
 */

public class WaterWand extends WandUtils implements WandCall {

    @Override
    public void useSpell(int index, World worldIn, PlayerEntity playerIn, Hand handIn) {
        switch (index) {
            case 0: movementL1(worldIn, playerIn, handIn); break;
            case 1: attackL1(worldIn, playerIn, handIn); break;
            case 2: utilityL3(worldIn, playerIn, handIn); break;
            case 3: defenseL3(worldIn, playerIn, handIn); break;
            case 4: utilityL4(worldIn, playerIn, handIn); break;
            case 5: attackL1(worldIn, playerIn, handIn); break;
            case 6: attackL1(worldIn, playerIn, handIn); break;
        }
    }

    private static void movementL1(World world, PlayerEntity player, Hand hand) {
    }

    private static void utilityL4(World world, PlayerEntity player, Hand hand) {
        Vector3d pos = player.getPositionVec();
        ArrayList<BlockPos> oldBlocks = new ArrayList<>();
        BlockState Frosted_Ice = Blocks.FROSTED_ICE.getDefaultState();

        for (Vector3d vector : BlockUtils.WaterUtilityL4) {
            BlockPos blockPos = new BlockPos(pos.add(vector));
            if (world.getBlockState(blockPos).getBlock() instanceof AirBlock) {
                oldBlocks.add(blockPos);
                world.setBlockState(blockPos, Frosted_Ice);
            }
        }

        int range = 5;
        int rangeY = 3;
        List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(player.getPositionVec().add(-0.5 - range, -0.5 - rangeY, -0.5 - range), player.getPositionVec().add(0.5 + range, 0.5 + rangeY, 0.5 + range)));
        final UUID SLOW_EFFECT_ID = UUID.randomUUID();
        final AttributeModifier SLOW_EFFECT = new AttributeModifier(SLOW_EFFECT_ID, "Water spell slow effect", -0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        for (LivingEntity entity : entities) {
            ModifiableAttributeInstance modifiableattributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            assert modifiableattributeinstance != null;
            modifiableattributeinstance.applyNonPersistentModifier(SLOW_EFFECT);
        }


        ServerUtils.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BlockState Air = Blocks.AIR.getDefaultState();
                for (BlockPos pos : oldBlocks) {
                    world.setBlockState(pos, Air);
                }
                for (LivingEntity entity : entities) {
                    ModifiableAttributeInstance modifiableattributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
                    if (modifiableattributeinstance.getModifier(SLOW_EFFECT_ID) != null) {
                        modifiableattributeinstance.removeModifier(SLOW_EFFECT);
                    }
                }
            }
        }, 7000);
    }

    private static void defenseL3(World world, PlayerEntity player, Hand hand) {
        Vector3d pos = player.getLookVec().mul(4, 1, 4);
        pos = player.getPositionVec().add(pos);
        MagicDefenseEntity entity = new MagicDefenseEntity(player, world)
                .setParticle(ParticleTypes.DRIPPING_WATER)
                .setParticleCount(8);
        entity.setNoGravity(true);
        entity.setPosition(pos.x, pos.y, pos.z);
        world.addEntity(entity);

        ServerUtils.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                entity.remove();
            }
        }, 1000);
    }

    private static void attackL1(World world, PlayerEntity player, Hand hand) {

    }

    private static void utilityL3(World world, PlayerEntity player, Hand hand) {

        ServerUtils.spawnParticles((ServerWorld) world, ParticleTypes.BUBBLE, player.getPosXRandom(0.5D), player.getPosYRandom() + 1, player.getPosZRandom(0.5D), 6, 0.1);

        ServerUtils.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.heal(7);
            }
        }, 3000);


    }

    private static void attackL4(World world, PlayerEntity player, Hand hand) {
        
    }
}
