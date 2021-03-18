package net.I_love_arsenic.magcom.common.items.wands.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface WandCall {
    void useSpell(int mode, World worldIn, PlayerEntity playerIn, Hand handIn);
}
