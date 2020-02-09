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
package org.ml4j.provider.enums.activationfunctions;

import java.util.Arrays;
import java.util.Optional;

import org.ml4j.provider.enums.DefaultProviderEnum;
import org.ml4j.provider.enums.ProviderEnums;
import org.ml4j.provider.enums.TypeEnum;

/**
 * Represents an Enum for an ActivationFunctionType in a provider-agnostic
 * manner. Allows for decoupling of the representation of an
 * ActivationFunctionType with the actual provider-specific Enum class.
 * 
 * @author Michael Lavelle
 */
public class ActivationFunctionTypeEnum extends TypeEnum {

	public ActivationFunctionTypeEnum(ProviderEnums providerEnums) {
		super(providerEnums);
	}

	public static Optional<ActivationFunctionTypeEnum> findByProviderEnum(DefaultProviderEnum<?> providerEnum) {
		return findByQualifiedEnumName(providerEnum.getQualifiedEnumName());
	}

	public static Optional<ActivationFunctionTypeEnum> findByEnumValue(Enum<?> enumValue) {
		
		return findByQualifiedEnumName(enumValue.getDeclaringClass().getName() + "." + enumValue);
	}

	public static Optional<ActivationFunctionTypeEnum> findByQualifiedEnumName(String qualifiedEnumName) {	
		return Arrays.asList(ProviderEnums.values()).stream()
				.filter(e -> e.getQualifiedEnumNames().contains(qualifiedEnumName))
				.map(ActivationFunctionTypeEnum::new).findFirst();
	}
}
