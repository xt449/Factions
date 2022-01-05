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

import com.github.xt449.factions.common.api.FactionRelation;
import com.github.xt449.factions.common.api.FactionRole;
import com.github.xt449.factions.common.api.IFaction;
import com.github.xt449.factions.common.api.RelationContainer;
import com.github.xt449.factions.common.data.ChunkPosition;
import com.github.xt449.factions.common.data.PlayerData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class Faction implements IFaction {

	private final HashMap<UUID, PlayerData> player2Data = new HashMap<>();
	private final HashMap<IFaction, FactionRelation> faction2Relation = new HashMap<>();

	/**
	 * Should be only used internally
	 */
	@Override
	public int getId() {
		// TODO
		return 0;
	}

	/**
	 * @return command friendly name
	 */
	@Override
	public @NotNull String getName() {
		// TODO
		return null;
	}

	/**
	 * @return null if Wilderness
	 */
	@Override
	public @Nullable UUID getLeader() {
		// TODO
		return null;
	}

	/**
	 * @return chunk positions of all current faction claims
	 */
	@Override
	public @NotNull Set<ChunkPosition> getClaims() {
		// TODO
		return null;
	}

	/**
	 * Includes ALL players, including: bans, kicks, invites, etc.
	 */
	@Override
	public @NotNull Map<UUID, FactionRole> getAllPlayers() {
		// TODO
		return null;
	}

	/**
	 * @return null if no relation set or relation not applicatable
	 */
	@Override
	public @Nullable FactionRelation getRelationTo(RelationContainer other) {
		return faction2Relation.get(other.getFaction());
	}
}
