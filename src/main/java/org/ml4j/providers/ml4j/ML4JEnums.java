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
package org.ml4j.providers.ml4j;

import java.util.Arrays;

import java.util.Optional;

import org.ml4j.nn.activationfunctions.ActivationFunctionBaseType;
import org.ml4j.provider.Provider;
import org.ml4j.provider.enums.ProviderEnum;
import org.ml4j.provider.enums.ProviderEnums;

/**
 * Enum mappings from ProviderEnum values to ML4J enums
 * 
 * @author Michael Lavelle
 *
 */
public enum ML4JEnums implements ProviderEnum{

	RELU(ProviderEnums.RELU_ENUM, ActivationFunctionBaseType.RELU),
	SIGMOID(ProviderEnums.SIGMOID_ENUM, ActivationFunctionBaseType.SIGMOID),
	SOFTMAX(ProviderEnums.SOFTMAX_ENUM, ActivationFunctionBaseType.SOFTMAX),
	LINEAR(ProviderEnums.LINEAR_ENUM, ActivationFunctionBaseType.LINEAR);

	Enum<?> enumValue;
	ProviderEnums providerEnums;
	
	ML4JEnums(ProviderEnums providerEnums, Enum<?> enumValue) {
		this.enumValue = enumValue;
		this.providerEnums = providerEnums;
	}
	
	public static Optional<? extends Enum<?>> getEnumByType(ProviderEnums providerEnums) {
		return Arrays.asList(values()).stream().filter(e -> e.getProviderEnums().equals(providerEnums)).map(e -> e.getEnum()).findFirst();
	}

	@Override
	public String getProviderName() {
		return Provider.ML4J.name();
	}

	@Override
	public Enum<?> getEnum() {
		return enumValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> E getEnumAsType(Class<E> type) {
		return (E) enumValue;
	}

	public ProviderEnums getProviderEnums() {
		return providerEnums;
	}
}
