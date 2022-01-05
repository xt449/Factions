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

import com.github.xt449.factions.common.api.FactionRelation;
import com.github.xt449.factions.common.api.IFaction;
import com.github.xt449.factions.common.api.IPlayerData;
import com.github.xt449.factions.common.api.IRegistrar;
import com.github.xt449.factions.common.data.ChunkPosition;
import com.github.xt449.factions.fabric.data.Faction;
import com.github.xt449.factions.fabric.data.PlayerData;
import com.github.xt449.factions.fabric.data.WildernessFaction;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public class FabricFactions implements ModInitializer, IRegistrar {

	public FabricFactions() {
		// init
	}

	// ModInitializer implementation

	@Override
	public void onInitialize() {
		lowercaseName2Faction.put("wilderness", WILDERNESS);
		id2Faction.add(WILDERNESS);
	}

	//
	// IFactionManager implementation
	//

	private final HashMap<String, IFaction> lowercaseName2Faction = new HashMap<>();
	private final ArrayList<IFaction> id2Faction = new ArrayList<>();

	/**
	 * @return the Wilderness Faction for unclaimed chunks
	 */
	@Override
	public @NotNull IFaction getWildernessFaction() {
		return WILDERNESS;
	}

	/**
	 * @return null if name is invalid
	 */
	@Override
	public @Nullable IFaction getFaction(@NotNull String name) {
		return lowercaseName2Faction.get(name.toLowerCase());
	}

	/**
	 * @return null if id is invalid
	 */
	@Override
	public @Nullable IFaction getFaction(int id) {
		return id2Faction.get(id);
	}

	/**
	 * @return null if name is not available (already used)
	 */
	@Override
	public @Nullable IFaction createFaction(@NotNull String name, @NotNull UUID leaderPlayerId) {
		final String lowercaseName = name.toLowerCase();
		if(lowercaseName2Faction.get(lowercaseName) != null) {
			return null;
		}

		final PlayerData playerData = player2Data.get(leaderPlayerId);
		if(playerData == null || playerData.faction != null) {
			return null;
		}

		final Faction faction = new Faction(id2Faction.size(), name, playerData);
		lowercaseName2Faction.put(lowercaseName, faction);
		id2Faction.add(faction);

		return faction;
	}

	/**
	 * @return true is successful; false if faction is a system Faction (ie. Wilderness) or otherwise permanent
	 */
	@Override
	public boolean deleteFaction(@NotNull IFaction faction) {
		if(faction instanceof Faction fabricFaction) {
			// Remove faction info from all "members"
			for(HashMap.Entry<UUID, PlayerData> kvp : fabricFaction.player2Data.entrySet()) {
				kvp.getValue().leave();
			}
			fabricFaction.player2Data.clear();

			// Remove faction relation from all "relatives"
			for(HashMap.Entry<Faction, FactionRelation> kvp : fabricFaction.faction2Relation.entrySet()) {
				kvp.getKey().faction2Relation.remove(fabricFaction);
			}
			fabricFaction.faction2Relation.clear();

			// Remove faction ownership from claims
			for(ChunkPosition chunkPosition : fabricFaction.claims) {
				chunkClaims.remove(chunkPosition);
			}
			fabricFaction.claims.clear();

			lowercaseName2Faction.remove(faction.getName().toLowerCase());
			id2Faction.remove(fabricFaction.getId());
			// TODO Should be done now
			return true;
		}

		return false;
	}

	//
	// IPlayerManager implementation
	//

	private final HashMap<UUID, PlayerData> player2Data = new HashMap<>();

	@Override
	public @NotNull IPlayerData initPlayerData(@NotNull UUID playerId) {
		final PlayerData playerData = new PlayerData(playerId);
		player2Data.put(playerId, playerData);

		return playerData;
	}

	/**
	 * @return null if player has not joined the server
	 */
	@Override
	public @Nullable IPlayerData getPlayerData(@NotNull UUID playerId) {
		return player2Data.get(playerId);
	}

	//
	// IClaimManager implementation
	//

	private final HashMap<ChunkPosition, IFaction> chunkClaims = new HashMap<>();

	/**
	 * @return null if no faction has claimed this chunk (wilderness)
	 */
	@Override
	public @Nullable IFaction getFactionAt(@NotNull ChunkPosition chunkPosition) {
		return chunkClaims.get(chunkPosition);
	}

	/**
	 * @return true is successful; false if chunkPosition is already NOT claimed
	 */
	@Override
	public boolean unclaimAt(@NotNull ChunkPosition chunkPosition) {
		return chunkClaims.remove(chunkPosition) != null;
	}

	/**
	 * !!! DANGER !!!
	 */
	@Override
	public void unclaimAll(@NotNull IFaction faction) {
		for(Map.Entry<ChunkPosition, IFaction> kvp : chunkClaims.entrySet()) {
			if(kvp.getValue().equals(faction)) {
				chunkClaims.remove(kvp.getKey());
			}
		}
	}

	/**
	 * @return true is successful; false if chunkPosition already has another, different faction owner
	 */
	@Override
	public boolean setFactionAt(@NotNull ChunkPosition chunkPosition, @NotNull IFaction faction) {
		return chunkClaims.putIfAbsent(chunkPosition, faction) == null;
	}

	/**
	 * Forcebly set the claim at chunkPosition to be owned by faction, even if it means unclaiming it from another Faction
	 */
	@Override
	public void forceSetFactionAt(@NotNull ChunkPosition chunkPosition, @NotNull IFaction faction) {
		chunkClaims.put(chunkPosition, faction);
	}

	//
	// Static Initializers
	//

	private static final WildernessFaction WILDERNESS = new WildernessFaction();
}
