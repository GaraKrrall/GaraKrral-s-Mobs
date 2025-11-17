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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import mc.garakrral.gmobs.entity.ModEntities;
import mc.garakrral.gmobs.entity.variant.GeckoVariant;
import mc.garakrral.gmobs.item.ModItems;

import org.jetbrains.annotations.Nullable;

public class GeckoEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState sleepAnimationState = new AnimationState();

    private int idleAnimationTimeOut = 0;


    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(GeckoEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> SLEEPING =
            SynchedEntityData.defineId(GeckoEntity.class, EntityDataSerializers.BOOLEAN);

    public GeckoEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.TEST_ITEM), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.FOLLOW_RANGE, 24d);

    }

    @Override
    public boolean isFood(ItemStack food) {
        return food.is(ModItems.TEST_ITEM);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        GeckoEntity baby = ModEntities.GECKO.get().create(level);

        if (baby != null) {
            GeckoVariant v1 = this.getVariant();
            GeckoVariant v2 = ((GeckoEntity) partner).getVariant();

            if (v1 == v2) {
                GeckoVariant variant = Util.getRandom(GeckoVariant.values(), this.random);
                baby.setVariant(variant);
            } else {
                GeckoVariant selected = this.random.nextBoolean() ? v1 : v2;
                baby.setVariant(selected);
            }
        }

        return baby;
    }

    private void setupAnimationStates() {
        boolean sleeping = this.isSleepingGecko();
        boolean inWater = this.isInWaterOrBubble();
        boolean isNight = this.level().isNight();
        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;

        if (sleeping && !inWater) {
            if (!this.sleepAnimationState.isStarted()) {
                this.sleepAnimationState.start(this.tickCount);
            }
            this.idleAnimationState.stop();
            this.swimAnimationState.stop();
            return;
        }

        if (!sleeping && this.sleepAnimationState.isStarted()) {
            this.sleepAnimationState.stop();
        }

        if (inWater) {
            this.swimAnimationState.startIfStopped(this.tickCount);
            this.idleAnimationState.stop();
            return;
        } else {
            this.swimAnimationState.stop();
        }

        if (!isMoving && !inWater) {
            if (this.idleAnimationTimeOut <= 0) {
                this.idleAnimationTimeOut = 80;
                this.idleAnimationState.start(this.tickCount);
            } else {
                this.idleAnimationTimeOut--;
            }
        } else {
            this.idleAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        boolean inWater = this.isInWaterOrBubble();
        boolean isNight = this.level().isNight();

        if (!this.level().isClientSide()) {
            if (isNight && !inWater) {
                if (!this.isSleepingGecko()) {
                    this.setSleeping(true);
                }
                this.setNoAi(true);
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
                this.navigation.stop();
            } else {
                // Wake up
                if (this.isSleepingGecko()) {
                    this.setSleeping(false);
                }
                this.setNoAi(false);
            }
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
        builder.define(SLEEPING, false);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public GeckoVariant getVariant() {
        return GeckoVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(GeckoVariant v) {
        this.entityData.set(VARIANT, v.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag c) {
        super.addAdditionalSaveData(c);
        c.putInt("Variant", this.getTypeVariant());
        c.putBoolean("Sleeping", this.isSleepingGecko());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag c) {
        super.readAdditionalSaveData(c);
        this.entityData.set(VARIANT, c.getInt("Variant") & 255);

        if (c.contains("Sleeping")) {
            this.entityData.set(SLEEPING, c.getBoolean("Sleeping"));
        } else {
            this.entityData.set(SLEEPING, false);
        }
    }

    public boolean isSleepingGecko() {
        return this.entityData.get(SLEEPING);
    }

    public void setSleeping(boolean s) {
        this.entityData.set(SLEEPING, s);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType,
                                        @Nullable SpawnGroupData spawnGroupData) {
        GeckoVariant variant = Util.getRandom(GeckoVariant.values(), this.random);
        this.setVariant(variant);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
