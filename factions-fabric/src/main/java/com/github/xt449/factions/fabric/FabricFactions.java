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

package com.github.xt449.factions.fabric;

import com.github.xt449.factions.common.api.IFaction;
import com.github.xt449.factions.common.api.IRegistrar;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class FabricFactions implements ModInitializer, IRegistrar {

	// ModInitializer implementation

	@Override
	public void onInitialize() {

	}

	// IRegistrar implementation

	private final HashMap<UUID, IFaction> player2Faction = new HashMap<>();
	private final Int2ObjectOpenHashMap<IFaction> id2Faction = new Int2ObjectOpenHashMap<>();

	/**
	 * @return the Wilderness Faction for unclaimed chunks
	 */
	@Override
	public @NotNull IFaction getWildernessFaction() {
		// TODO
		return null;
	}

	/**
	 * @return null if player is not part of a Faction
	 */
	@Override
	public @Nullable IFaction getPlayerFaction(UUID playerId) {
		return player2Faction.get(playerId);
	}

	/**
	 * @return null if id is invalid
	 */
	@Override
	public @Nullable IFaction getFaction(int id) {
		return id2Faction.get(id);
	}
}
