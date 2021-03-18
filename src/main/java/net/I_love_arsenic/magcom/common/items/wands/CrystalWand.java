package net.I_love_arsenic.magcom.common.items.wands;

import net.I_love_arsenic.magcom.common.entities.MagicDefenseEntity;
import net.I_love_arsenic.magcom.common.items.wands.utils.BlockUtils;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandCall;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.core.init.BlockInit;
import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPredicate;
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
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.lwjgl.system.CallbackI;

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

public class CrystalWand extends WandUtils implements WandCall {

    @Override
    public void useSpell(int index, World worldIn, PlayerEntity playerIn, Hand handIn) {
        switch (index) {
            case 0: movementL1(worldIn, playerIn, handIn); break;
            case 1: attackL4(worldIn, playerIn, handIn); break;
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

    }

    private static void defenseL3(World world, PlayerEntity player, Hand hand) {

    }

    private static void attackL1(World world, PlayerEntity player, Hand hand) {

    }

    private static void utilityL3(World world, PlayerEntity player, Hand hand) {

    }

    private static void attackL4(World world, PlayerEntity player, Hand hand) {
        Vector3d pos = player.getLookVec().normalize().mul(4, 1, 4);
        pos = player.getPositionVec().add(pos);

        BlockPos blockPos = new BlockPos(pos);

        int range = 20;
        int rangeY = 3;
        List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(player.getPositionVec().add(-0.5 - range, -0.5 - rangeY, -0.5 - range), player.getPositionVec().add(0.5 + range, 0.5 + rangeY, 0.5 + range)));
        LivingEntity target = WandUtils.getClosestEntity(entities, player, blockPos.getX(), blockPos.getY(), blockPos.getZ());

        Block Crystal_Block = BlockInit.CRYSTAL_BLOCK.get()
                .setTarget(target);

        world.setBlockState(blockPos, Crystal_Block.getDefaultState());
        world.getPendingBlockTicks().scheduleTick(blockPos, Crystal_Block, 1);
    }
}
