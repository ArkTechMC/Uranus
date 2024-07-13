package com.iafenvoy.uranus.mixin;

import com.iafenvoy.uranus.server.entity.ICitadelDataEntity;
import com.iafenvoy.uranus.util.item.ISwingable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ICitadelDataEntity {
    @Unique
    private static final TrackedData<NbtCompound> CITADEL_DATA = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.NBT_COMPOUND);

    protected LivingEntityMixin(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "initDataTracker")
    private void mars_registerData(CallbackInfo ci) {
        this.dataTracker.startTracking(CITADEL_DATA, new NbtCompound());
    }

    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    private void mars_writeAdditional(NbtCompound compoundNBT, CallbackInfo ci) {
        NbtCompound marsDat = this.getCitadelEntityData();
        if (marsDat != null)
            compoundNBT.put("CitadelData", marsDat);
    }

    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    private void mars_readAdditional(NbtCompound compoundNBT, CallbackInfo ci) {
        if (compoundNBT.contains("CitadelData"))
            this.setCitadelEntityData(compoundNBT.getCompound("CitadelData"));
    }

    public NbtCompound getCitadelEntityData() {
        return this.dataTracker.get(CITADEL_DATA);
    }

    public void setCitadelEntityData(NbtCompound nbt) {
        this.dataTracker.set(CITADEL_DATA, nbt);
    }

    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"), cancellable = true)
    private void onSwingHand(Hand hand, boolean fromServerPlayer, CallbackInfo ci) {
        ItemStack stack = this.getStackInHand(hand);
        Item item = stack.getItem();
        if (item instanceof ISwingable iSwingable)
            if (iSwingable.onEntitySwing(stack, this))
                ci.cancel();
    }
}
