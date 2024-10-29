package net.digitalpear.aqua_creepers.common.entities;

import net.digitalpear.aqua_creepers.common.entities.entity_goals.AquaCreeperIgniteGoal;
import net.digitalpear.aqua_creepers.common.entities.entity_goals.WaterGoals;
import net.digitalpear.aqua_creepers.init.AquaCreeperSounds;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;


@SuppressWarnings("unused")
public class AquaCreeperEntity extends FishEntity implements SkinOverlayOwner, Monster {

    private static final TrackedData<Integer> FUSE_SPEED = DataTracker.registerData(AquaCreeperEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> CHARGED = DataTracker.registerData(AquaCreeperEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IGNITED = DataTracker.registerData(AquaCreeperEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(AquaCreeperEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private int lastFuseTime;
    private int currentFuseTime;
    private int fuseTime = 30;
    private int explosionRadius = 3;
    private int headsDropped;
    public final AnimationState swimmingAnimationState = new AnimationState();


    public AquaCreeperEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    @Override
    public int getXpToDrop() {
        return STRONG_MONSTER_XP;
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.goalSelector.add(3, new AquaCreeperIgniteGoal(this));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, OcelotEntity.class, 6.0f, 1.0, 2.0));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, CatEntity.class, 6.0f, 1.0, 2.0));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, AxolotlEntity.class, 6.0f, 1.0, 2.0));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 4.5, false));
        this.goalSelector.add(4, new WaterGoals(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public int getFuseTime() {
        return fuseTime;
    }

    public static DefaultAttributeContainer.Builder createAquaCreeperAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16);
    }

    @Override
    public int getSafeFallDistance() {
        if (this.getTarget() == null) {
            return 3;
        }
        return 3 + (int)(this.getHealth() - 1.0f);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        boolean bl = super.handleFallDamage(fallDistance, damageMultiplier, damageSource);
        this.currentFuseTime += (int)(fallDistance * 1.5f);
        if (this.currentFuseTime > this.fuseTime - 5) {
            this.currentFuseTime = this.fuseTime - 5;
        }
        return bl;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE_SPEED, -1);
        this.dataTracker.startTracking(CHARGED, false);
        this.dataTracker.startTracking(IGNITED, false);
        this.dataTracker.startTracking(FROM_BUCKET, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("Fuse", (short)this.fuseTime);
        nbt.putByte("ExplosionRadius", (byte)this.explosionRadius);
        nbt.putBoolean("ignited", this.dataTracker.get(IGNITED));
        nbt.putBoolean("Charged", this.dataTracker.get(CHARGED));
        nbt.putBoolean("FromBucket", this.dataTracker.get(FROM_BUCKET));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Fuse", 99)) {
            this.fuseTime = nbt.getShort("Fuse");
        }
        this.setCharged(nbt.getBoolean("Charged"));

        if (nbt.contains("ExplosionRadius", 99)) {
            this.explosionRadius = nbt.getByte("ExplosionRadius");
        }
        if (nbt.getBoolean("ignited")) {
            this.ignite();
        }
        this.setFromBucket(nbt.getBoolean("FromBucket"));
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setFromBucket(spawnReason == SpawnReason.BUCKET);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    //Every tick
    @Override
    public void tick() {
        if (this.isAlive()) {
            this.lastFuseTime = this.currentFuseTime;
            if (this.isIgnited()) {
                this.setFuseSpeed(1);
            }

            int i = this.getFuseSpeed();
            if (i > 0 && this.currentFuseTime == 0) {
                this.playSound(AquaCreeperSounds.UNDERWATER_FUSE, 1.0F, 0.5F);
                this.emitGameEvent(GameEvent.PRIME_FUSE);
            }

            this.currentFuseTime += i;
            if (this.currentFuseTime < 0) {
                this.currentFuseTime = 0;
            }

            if (this.currentFuseTime >= this.fuseTime) {
                this.currentFuseTime = this.fuseTime;
                this.explode();
            }
        }

        super.tick();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        this.swimmingAnimationState.stop();
        super.onDeath(damageSource);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (target instanceof GoatEntity) {
            return;
        }
        super.setTarget(target);
    }

    public boolean shouldDropHead() {
        return this.isCharged() && this.headsDropped < 1;
    }
    public void onHeadDropped() {
        ++this.headsDropped;
    }

    @Override
    public boolean tryAttack(Entity target) {
        return true;
    }

    public boolean isCharged() {
        return this.dataTracker.get(CHARGED);
    }
    public void setCharged(boolean value){
        this.dataTracker.set(CHARGED, value);
    }

    public float getClientFuseTime(float timeDelta) {
        return MathHelper.lerp(timeDelta, this.lastFuseTime, this.currentFuseTime) / (float)(this.fuseTime - 2);
    }

    public boolean isFromBucket(){
        return this.dataTracker.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    public int getFuseSpeed() {
        return this.dataTracker.get(FUSE_SPEED);
    }

    public void setFuseSpeed(int fuseSpeed) {
        this.dataTracker.set(FUSE_SPEED, fuseSpeed);
    }

    //Make charged
    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        super.onStruckByLightning(world, lightning);
        this.dataTracker.set(CHARGED, true);
    }

    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isIn(ItemTags.CREEPER_IGNITERS)) {
            SoundEvent soundEvent = itemStack.isOf(Items.FIRE_CHARGE) ? SoundEvents.ITEM_FIRECHARGE_USE : SoundEvents.ITEM_FLINTANDSTEEL_USE;
            this.getWorld().playSound(player, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.getWorld().isClient) {
                this.ignite();
                if (!itemStack.isDamageable()) {
                    itemStack.decrement(1);
                } else {
                    itemStack.damage(1, player, (playerx) -> {
                        playerx.sendToolBreakStatus(hand);
                    });
                }
            }

            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    //BOOM
    private void explode() {
        if (!this.getWorld().isClient) {
            float f = this.isCharged() ? 2.0f : 1.0f;
            this.dead = true;
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.MOB);
            this.discard();
            this.spawnEffectsCloud();
        }
    }

    //If it has a status effect when exploding, it will spawn an effect cloud
    private void spawnEffectsCloud() {
        Collection<StatusEffectInstance> collection = this.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = createAreaEffectCloudEntity();
            for (StatusEffectInstance statusEffectInstance : collection) {
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }
            this.getWorld().spawnEntity(areaEffectCloudEntity);
        }
    }

    @NotNull
    private AreaEffectCloudEntity createAreaEffectCloudEntity() {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
        areaEffectCloudEntity.setRadius(2f);
        areaEffectCloudEntity.setRadiusOnUse(-0.5f);
        areaEffectCloudEntity.setWaitTime(20);
        areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        return areaEffectCloudEntity;
    }

    public boolean isIgnited() {
        return this.dataTracker.get(IGNITED);
    }

    public void ignite() {
        this.dataTracker.set(IGNITED, true);
    }

    //Cannot spawn in peaceful
    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }

    //Set sound category to hostile
    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    //All sounds
    @Override
    protected SoundEvent getSwimSound() {
        return AquaCreeperSounds.ENTITY_AQUA_CREEPER_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AquaCreeperSounds.ENTITY_AQUA_CREEPER_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    public boolean shouldRenderOverlay() {
        return this.isCharged();
    }

    /*
        Bucketed item
    */
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(AquaItems.AQUA_CREEPER_BUCKET);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("FromBucket", true);
        nbtCompound.putBoolean("Charged", this.isCharged());
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
        this.setFromBucket(nbt.getBoolean("FromBucket"));
        this.setCharged(nbt.getBoolean("Charged"));
    }
}


