package mc.garakrral.gmobsreforged.entity.custom;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;


import mc.garakrral.gmobsreforged.entity.ModEntities;
import mc.garakrral.gmobsreforged.entity.variant.BearVariant;

import org.jetbrains.annotations.Nullable;

public class BearEntity extends Animal {
    public BearEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(BearEntity.class, EntityDataSerializers.INT);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 25d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.FOLLOW_RANGE, 25d)
                .add(Attributes.ATTACK_DAMAGE, 5D);

    }

    @Override
    public void tick() {
        super.tick();

        if (this.getDeltaMovement().horizontalDistanceSqr() > 0.0001) {
            if (!this.walkAnimationState.isStarted()) {
                this.walkAnimationState.start(this.tickCount);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationState.start(this.tickCount);
        return super.doHurtTarget(entity);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant") & 255);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(VARIANT, 0);
        super.defineSynchedData();
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public BearVariant getVariant() {
        return BearVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(BearVariant v) {
        this.entityData.set(VARIANT, v.getId() & 255);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        BearEntity baby = ModEntities.BEAR.get().create(level);

        if (baby != null) {
            BearVariant v1 = this.getVariant();
            BearVariant v2 = ((BearEntity) partner).getVariant();

            if (v1 == v2) {
                BearVariant variant = Util.getRandom(BearVariant.values(), this.random);
                baby.setVariant(variant);
            } else {
                BearVariant selected = this.random.nextBoolean() ? v1 : v2;
                baby.setVariant(selected);
            }
        }

        return baby;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
        BearVariant variant = Util.getRandom(BearVariant.values(), this.getRandom());
        this.setVariant(variant);

        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }
}
