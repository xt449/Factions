/*
 * Copyright (c) 2022 Jonathan Talcott (xt449 / BinaryBanana)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.xt449.factions.fabric.mixin;

import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Jonathan Taclott (xt449 / BinaryBanana)
 */

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

	@Shadow
	public ServerPlayerEntity player;

	@Shadow
	private double lastTickX;
	@Shadow
	private double lastTickY;
	@Shadow
	private double lastTickZ;

	@Shadow
	public abstract void requestTeleport(double x, double y, double z, float yaw, float pitch);

	@Inject(method = "onPlayerMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;updatePositionAndAngles(DDDFF)V", shift = At.Shift.AFTER, ordinal = 1), cancellable = true)
	private void interceptMovement(CallbackInfo callbackInfo) {
		player.networkHandler.sendPacket(new EntityPositionS2CPacket(player));
		callbackInfo.cancel();
	}
}
