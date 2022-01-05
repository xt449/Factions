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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class PlayerData implements IPlayerData {

	private final UUID uuid;
	public Faction faction;
	public FactionRole role;

	public PlayerData(UUID uuid, Faction faction, FactionRole role) {
		this.uuid = uuid;
		this.faction = faction;
		this.role = role;
	}

	public PlayerData(UUID uuid) {
		this.uuid = uuid;
		this.faction = null;
	}

	/**
	 * Should be only used internally
	 */
	@Override
	public @NotNull UUID getId() {
		return uuid;
	}

	/**
	 * @return null if not part of a Faction
	 */
	@Override
	public @Nullable IFaction getFaction() {
		return faction;
	}

	/**
	 * @return null if not part of a Faction
	 */
	@Override
	public @Nullable FactionRole getRole() {
		return role;
	}

	/**
	 * @return null if no relation set or relation not applicatable
	 */
	@Override
	public @Nullable FactionRelation getRelationTo(RelationContainer other) {
		if(getFaction() == null) {
			return null;
		}
		return getFaction().getRelationTo(other);
	}

	public void join(Faction faction, FactionRole role) {
		this.faction = faction;
		this.role = role;
	}

	public void leave() {
		this.faction = null;
		this.role = null;
	}
}
