/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ml4j.provider.enums;

import java.util.function.Supplier;

import org.ml4j.provider.Provider;

/**
 * Represents an Enum for an specific type in a provider-agnostic manner. Allows
 * for decoupling of the representation of the type with the actual
 * provider-specific Enum class.
 * 
 * @author Michael Lavelle
 */
public class TypeEnum {

	private ProviderEnums providerEnums;

	protected TypeEnum(ProviderEnums providerEnums) {
		this.providerEnums = providerEnums;
	}

	public ProviderEnum providedBy(Provider provider) {
		return providerEnums.providedBy(provider);
	}

	public ProviderEnum providedBy(String provider) {
		return providerEnums.providedBy(provider);
	}

	public static <T extends TypeEnum> T get(Supplier<T> type) {
		return type.get();
	}

	@Override
	public String toString() {
		return providerEnums.getDeclaringClass().getName() + "." + providerEnums.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((providerEnums == null) ? 0 : providerEnums.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeEnum other = (TypeEnum) obj;
		if (providerEnums != other.providerEnums)
			return false;
		return true;
	}

}
