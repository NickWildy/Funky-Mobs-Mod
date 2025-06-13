package net.nickwildy.fmm.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.nickwildy.fmm.custom.ModDamageTypes;
import net.nickwildy.fmm.entity.goals.AppleAttackGoal;
import net.nickwildy.fmm.entity.goals.GoToAppleGoal;
import net.nickwildy.fmm.item.ModItems;
import net.nickwildy.fmm.sounds.CustomSounds;
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
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public  boolean hasApple = false;

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
        this.goalSelector.add(1, new GoToAppleGoal(this, 1.2D));
        this.goalSelector.add(2, new AppleAttackGoal(this, 1.2D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.75f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        //this.targetSelector.add(5, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
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
        this.setCanPickUpLoot(true);
        this.setLeftHanded(true);
        //this.setStackInHand(Hand.MAIN_HAND, activeItemStack);
        if (spawnReason == SpawnReason.SPAWN_ITEM_USE) {
            this.playSound(CustomSounds.BALDI_OH_HI, 1f, 1f);
        }
        return entityData;
    }

    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
        this.dropStack(world, new ItemStack(ModItems.RULER));
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksSinceSpawn < Integer.MAX_VALUE) {
            ticksSinceSpawn++;
        }
    }

    private int ticksSinceSpawn = 0;

    @Override
    protected SoundEvent getAmbientSound() {
        return ticksSinceSpawn > 45 ? CustomSounds.BALDI_UH_OH : null;
    }



    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (target instanceof LivingEntity && world instanceof ServerWorld serverWorld) {
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(ModDamageTypes.SLAP_DAMAGE.getValue()).get()
            );
            target.damage(serverWorld, damageSource, 2.4f);
        }
        return super.tryAttack(world, target);
    }



    @Override
    protected SoundEvent getDeathSound() {
        return CustomSounds.BALDI_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return CustomSounds.BALDI_HURT;
    }

    /*protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }*/

    @Override
    public boolean canPickupItem(ItemStack stack) {
        //FunkyMobsMod.LOGGER.info("Checking item: " + stack.getItem());
        hasApple = true;
        return stack.isOf(Items.APPLE) && super.canPickupItem(stack);
    }

    public boolean hasCollectedApple() {
        return hasApple;
    }

    public void setHasCollectedApple(boolean value) {
        this.hasApple = value;
    }
}
