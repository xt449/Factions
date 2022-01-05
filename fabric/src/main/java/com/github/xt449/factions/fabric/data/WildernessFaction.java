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

package com.github.xt449.factions.fabric.data;

import com.github.xt449.factions.common.api.*;
import com.github.xt449.factions.common.data.ChunkPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class WildernessFaction implements IFaction {

	/**
	 * Should be only used internally
	 */
	@Override
	public int getId() {
		return 0;
	}

	/**
	 * @return command friendly name
	 */
	@Override
	public @NotNull String getName() {
		return "Wilderness";
	}

	/**
	 * @return null if Wilderness
	 */
	@Override
	public @Nullable IPlayerData getLeader() {
		return null;
	}

	/**
	 * @return chunk positions of all current faction claims
	 */
	@Override
	public @NotNull Set<ChunkPosition> getClaims() {
		return Collections.emptySet();
	}

	/**
	 * Includes ALL players, including: bans, kicks, invites, etc.
	 */
	@Override
	public @NotNull Map<UUID, IPlayerData> getAllPlayers() {
		return Collections.emptyMap();
	}

	/**
	 * @return null if no relation set or relation not applicatable
	 */
	@Override
	public @Nullable FactionRelation getRelationTo(RelationContainer other) {
		return FactionRelation.NEUTRAL;
	}
}
