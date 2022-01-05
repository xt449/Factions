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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Faction accessor interface for Factions
 */
public interface IFactionManager {

	/**
	 * @return the Wilderness Faction for unclaimed chunks
	 */
	@NotNull
	IFaction getWildernessFaction();

	/**
	 * @return null if name is invalid
	 */
	@Nullable
	IFaction getFaction(@NotNull String name);

	/**
	 * @return null if id is invalid
	 */
	@Nullable
	IFaction getFaction(int id);

	/**
	 * @return null if name is not available (already used) or if leaderPlayerId does not represent an real, faction-less player
	 */
	@Nullable
	IFaction createFaction(@NotNull String name, @NotNull UUID leaderPlayerId);

	/**
	 * @return true is successful; false if faction is a system Faction (ie. Wilderness) or otherwise permanent
	 */
	boolean deleteFaction(@NotNull IFaction faction);
}
