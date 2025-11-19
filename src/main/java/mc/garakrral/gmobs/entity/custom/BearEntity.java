package mc.garakrral.gmobs.entity.custom;

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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import mc.garakrral.gmobs.entity.ModEntities;
import mc.garakrral.gmobs.entity.variant.BearVariant;

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

        if (this.walkAnimationState.isStarted()) {
        } else if (this.getDeltaMovement().horizontalDistanceSqr() > 0.0001) {
            this.walkAnimationState.start(this.tickCount);
        } else {
            this.walkAnimationState.stop();
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(VARIANT, 0);
        super.defineSynchedData(builder);
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        BearVariant variant = Util.getRandom(BearVariant.values(), this.getRandom());
        this.setVariant(variant);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
