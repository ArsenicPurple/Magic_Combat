package net.I_love_arsenic.magcom.common.items.wands;

import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.common.items.wands.utils.WandCall;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

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

public class AirWand extends WandUtils implements WandCall {

    @Override
    public void useSpell(int index, World worldIn, PlayerEntity playerIn, Hand handIn) {
        switch (index) {
            case 0: movementL1(worldIn, playerIn, handIn); break;
            case 1: attackL1(worldIn, playerIn, handIn); break;
            case 2: attackL1(worldIn, playerIn, handIn); break;
            case 3: attackL1(worldIn, playerIn, handIn); break;
            case 4: attackL1(worldIn, playerIn, handIn); break;
            case 5: attackL1(worldIn, playerIn, handIn); break;
            case 6: attackL1(worldIn, playerIn, handIn); break;
        }
    }

    private static void movementL1(World world, PlayerEntity player, Hand hand) {
    }

    private static void attackL1(World world, PlayerEntity player, Hand hand) {
        ArrowEntity arrow = new ArrowEntity(world, player.getPosX(), player.getPosY() + 1, player.getPosZ());
        Vector3d f = player.getLookVec().mul(2, 2, 2);
        arrow.setMotion(f);
        arrow.setNoGravity(true);
        world.addEntity(arrow);
    }

    private static void attackL2(World world, PlayerEntity player, Hand hand) {

    }
}
