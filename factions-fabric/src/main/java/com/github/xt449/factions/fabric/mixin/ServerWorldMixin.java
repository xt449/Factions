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

import net.minecraft.entity.EntityPose;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Jonathan Taclott (xt449 / BinaryBanana)
 */
@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

	@Inject(method = "onPlayerConnected", at = @At("TAIL"))
	private void onPlayerConnected(ServerPlayerEntity player, CallbackInfo callbackInfo) {
		player.sendMessage(Text.of("p0"), false);

		player.sleep(player.getBlockPos());

		player.sendMessage(Text.of("p1"), false);

		player.setPose(EntityPose.SLEEPING);
		player.setSleepingPosition(player.getBlockPos());
		player.setVelocity(Vec3d.ZERO);
		player.velocityDirty = true;

		player.sendMessage(Text.of("p2"), false);

		player.sleep(player.getBlockPos());

		player.sendMessage(Text.of("p3"), false);
	}
}
