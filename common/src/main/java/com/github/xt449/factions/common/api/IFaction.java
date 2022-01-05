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

package com.github.xt449.factions.common.api;

import com.github.xt449.factions.common.data.ChunkPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public interface IFaction extends RelationContainer {

	/**
	 * Should be only used internally
	 */
	int getId();

	/**
	 * @return command friendly name
	 */
	@NotNull
	String getName();

	/**
	 * @return null if Wilderness
	 */
	@Nullable
	UUID getLeader();

	/**
	 * @return chunk positions of all current faction claims
	 */
	@NotNull
	Set<ChunkPosition> getClaims();

	/**
	 * Includes ALL players, including: bans, kicks, invites, etc.
	 */
	@NotNull
	Map<UUID, FactionRole> getAllPlayers();

	/**
	 * Utility method for filtering {@link IFaction#getAllPlayers()}
	 */
	@NotNull
	default Set<UUID> getPlayers(FactionRole role) {
		return getAllPlayers().entrySet().stream().filter(kvp -> kvp.getValue().equals(role)).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	/**
	 * Utility method for filtering {@link IFaction#getAllPlayers()}
	 */
	@NotNull
	default Set<UUID> getPlayersGreaterThan(FactionRole role) {
		return getAllPlayers().entrySet().stream().filter(kvp -> kvp.getValue().compare(role) > 0).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	/**
	 * Utility method for filtering {@link IFaction#getAllPlayers()}
	 */
	@NotNull
	default Set<UUID> getPlayersGreaterThanOrEqualTo(FactionRole role) {
		return getAllPlayers().entrySet().stream().filter(kvp -> kvp.getValue().compare(role) >= 0).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	/**
	 * Utility method for filtering {@link IFaction#getAllPlayers()}
	 */
	@NotNull
	default Set<UUID> getPlayersLessThan(FactionRole role) {
		return getAllPlayers().entrySet().stream().filter(kvp -> kvp.getValue().compare(role) < 0).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	/**
	 * Utility method for filtering {@link IFaction#getAllPlayers()}
	 */
	@NotNull
	default Set<UUID> getPlayersLessThanOrEqualTo(FactionRole role) {
		return getAllPlayers().entrySet().stream().filter(kvp -> kvp.getValue().compare(role) <= 0).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	/**
	 * @return this
	 */
	@Override
	default @Nullable IFaction getFaction() {
		return this;
	}
}
