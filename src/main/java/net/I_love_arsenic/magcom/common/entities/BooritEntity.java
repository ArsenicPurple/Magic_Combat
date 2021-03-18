package net.I_love_arsenic.magcom.common.entities;

import net.I_love_arsenic.magcom.core.init.EntityTypeInit;
import net.I_love_arsenic.magcom.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;

public class BooritEntity extends MonsterEntity {

    public BooritEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BooritEntity(World worldIn, PlayerEntity target) {
        super(EntityTypeInit.BOORIT_ENTITY.get(), worldIn);
        setRevengeTarget(target);
        setAttackTarget(target);
        setAggroed(true);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        // func_233666_p_() -> registerAttributes()
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.1D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.9D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1D);
    }

    @Override
    public boolean canPickUpItem(ItemStack itemstackIn) {
        if (itemstackIn.isItemEqual(new ItemStack(ItemInit.RED_CRYSTAL.get()))) {
            return true;
        }
        return false;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return this.world.rand.nextInt(50) + 300;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        super.playStepSound(pos, blockIn);
    }
}
