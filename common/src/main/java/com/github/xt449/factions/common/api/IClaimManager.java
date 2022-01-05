package com.github.xt449.factions.common.api;

import com.github.xt449.factions.common.data.ChunkPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Claim accessor interface for Factions
 */
public interface IClaimManager {

	// TODO - Support multiple worlds
	//@NotNull
	//UUID getWorldId();

	/**
	 * @return null if no faction has claimed this chunk (wilderness)
	 */
	@Nullable
	IFaction getFactionAt(@NotNull ChunkPosition chunkPosition);

	/**
	 * @return true is successful; false if chunkPosition is already NOT claimed
	 */
	boolean unclaimAt(@NotNull ChunkPosition chunkPosition);

	/**
	 * !!! DANGER !!!
	 */
	void unclaimAll(@NotNull IFaction faction);

	/**
	 * @return true is successful; false if chunkPosition already has another, different faction owner
	 */
	boolean setFactionAt(@NotNull ChunkPosition chunkPosition, @NotNull IFaction faction);

	/**
	 * Forcebly set the claim at chunkPosition to be owned by faction, even if it means unclaiming it from another Faction
	 */
	void forceSetFactionAt(@NotNull ChunkPosition chunkPosition, @NotNull IFaction faction);
}
