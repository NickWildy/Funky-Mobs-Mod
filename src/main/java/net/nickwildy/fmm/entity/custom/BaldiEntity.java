package net.nickwildy.fmm.entity.custom;

import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.nickwildy.fmm.FunkyMobsMod;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BaldiEntity extends HostileEntity implements GeoEntity {
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("move.walk");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public BaldiEntity(EntityType<? extends BaldiEntity> type, World level)
    {
        super(type, level);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 16.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.ARMOR, 2.0)
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 0.5f)
                .add(EntityAttributes.SPAWN_REINFORCEMENTS);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f));

        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
    }


    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("Baldi", 2, this::baldiAnimController));
    }

    protected <E extends BaldiEntity> PlayState baldiAnimController(final AnimationTest<GeoAnimatable> animTest) {
        if (animTest.isMoving()){
            if(isAttacking()){
                animTest.controller().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
            }
            else {
                animTest.controller().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            }
            return PlayState.CONTINUE;
        }

        if(isDead()){
            animTest.controller().setAnimation(RawAnimation.begin().then("die", Animation.LoopType.PLAY_ONCE));
        }

        if(isAttacking()){
            animTest.controller().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
        }
        else {
            animTest.controller().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData);
        float f = difficulty.getClampedLocalDifficulty();
        if (spawnReason != SpawnReason.SPAWN_ITEM_USE) {
            this.setCanPickUpLoot(random.nextFloat() < 0.55F * f);
        }
        return entityData;
    }
    //MAKE THE HEAD MOVE
}
