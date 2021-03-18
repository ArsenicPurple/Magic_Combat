package net.I_love_arsenic.magcom.common.items.wands;

import net.I_love_arsenic.magcom.common.items.wands.utils.WandCall;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Orientation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import java.util.List;

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

public class EarthWand extends WandUtils implements WandCall {

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
        Vector3d look = player.getLookVec().normalize().mul(4, 1, 4);
        Vector3d pos = player.getPositionVec().add(look.x, 0, look.z);
        BlockPos blockPos = new BlockPos(pos.x, pos.y, pos.z);

        BlockState[] Earth_Blocks = {
                Blocks.DIRT.getDefaultState(),
                Blocks.COARSE_DIRT.getDefaultState()
        };

        //world.setBlockState(blockPos);


    }

    private static void attackL4(World world, PlayerEntity player, Hand hand) {
    }
}
