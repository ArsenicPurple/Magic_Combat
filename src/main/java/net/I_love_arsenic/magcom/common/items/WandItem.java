package net.I_love_arsenic.magcom.common.items;

import net.I_love_arsenic.magcom.common.items.wands.utils.WandUtils;
import net.I_love_arsenic.magcom.core.capabilities.affinity.Affinities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public class WandItem extends SoulboundItem {
    public WandItem(Properties properties) {
        super(properties);
    }

    private int mode = 0;

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote()) {
            if (playerIn.isCrouching()) {
                if (mode == 6) {
                    mode = 0;
                } else {
                    mode++;
                }

                System.out.println("Mode:" + mode);

                return ActionResult.resultFail(playerIn.getHeldItem(handIn));
            }

            playerIn.getCapability(Affinities.ENTITY_AFFINITY_TYPE).ifPresent(a -> {
                int type = a.getAffinity();
                System.out.println(type);
                WandUtils.purchase(type).useSpell(mode, worldIn, playerIn, handIn);
            });
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
