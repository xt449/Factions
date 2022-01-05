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

/**
 * @author Jonathan Talcott (xt449 / BinaryBanana)
 */
public enum FactionRelation {

	ENEMY(0, "Enemy"),
	NEUTRAL(1, "Neutral"),
	TRUCE(2, "Truce"),
	ALLY(3, "Ally"),
	SELF(4, "Member");

	private final int level;
	public final String displayName;

	// TODO Support other languages
	FactionRelation(int level, String displayName) {
		this.level = level;
		this.displayName = displayName;
	}

	/**
	 * @return negative when {@code other} is of a higher level, zero when {@code other} is of the same level, positive when {@code other} is of a lower level
	 */
	public int compare(FactionRelation other) {
		return this.level - other.level;
	}
}
