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
package org.ml4j.providers.dl4j;

import java.util.Arrays;
import java.util.Optional;

import org.ml4j.provider.Provider;
import org.ml4j.provider.enums.ProviderEnum;
import org.ml4j.provider.enums.DefaultProviderEnum;
import org.ml4j.provider.enums.ProviderEnums;

/**
 * Enum mappings from ProviderEnum values to DL4J enums
 * 
 * @author Michael Lavelle
 *
 */
public enum DL4JEnums implements ProviderEnum{

	RELU(ProviderEnums.RELU_ENUM, getNd4jActivationEnumClassName(), "RELU"),
	SIGMOID(ProviderEnums.SIGMOID_ENUM, getNd4jActivationEnumClassName(), "SIGMOID"),
	SOFTMAX(ProviderEnums.SOFTMAX_ENUM, getNd4jActivationEnumClassName(), "SOFTMAX"),
	IDENTITY(ProviderEnums.LINEAR_ENUM, getNd4jActivationEnumClassName(), "IDENTITY"),
	LEAKYRELU(ProviderEnums.LEAKY_RELU_ENUM,getNd4jActivationEnumClassName(), "LEAKYRELU");
	
	static final String ND4J_ACTIVATION_CLASS_NAME = "org.nd4j.linalg.activations.Activation";
	
	static String getNd4jActivationEnumClassName() {
		return ND4J_ACTIVATION_CLASS_NAME;
	}
	
	DefaultProviderEnum<?> decoratedProviderEnum;
	ProviderEnums providerEnums;
	
	DL4JEnums(ProviderEnums providerEnums, DefaultProviderEnum<?> decoratedProviderEnum) {
		this.decoratedProviderEnum = decoratedProviderEnum;
		this.providerEnums = providerEnums;
	}
	
	DL4JEnums(ProviderEnums providerEnums, String enumTypeClassName, String enumName) {
		this.decoratedProviderEnum = createDecoratedProviderEnum(enumTypeClassName, enumName);
		this.providerEnums = providerEnums;
	}
	
	<E extends Enum<E>> DL4JEnums(ProviderEnums providerEnums, E enumValue) {
		this.decoratedProviderEnum = createDecoratedProviderEnum(enumValue);
		this.providerEnums = providerEnums;
	}
	
	static Provider getProvider() {
		return Provider.DL4J;
	}
	
	static <E extends Enum<E>> DefaultProviderEnum<?> createDecoratedProviderEnum(E enumValue) {
		return new DefaultProviderEnum<>(getProvider(), enumValue);
	}
	
	static <E extends Enum<E>> DefaultProviderEnum<?> createDecoratedProviderEnum(String enumTypeClassName, String enumName) {
		return new DefaultProviderEnum<>(getProvider(), enumTypeClassName, enumName);
	}
	
	public static Optional<? extends Enum<?>> getEnumByType(ProviderEnums providerEnums) {
		return Arrays.asList(values()).stream().filter(e -> e.getProviderEnums().equals(providerEnums)).map(e -> e.getEnum()).findFirst();
	}

	@Override
	public String getProviderName() {
		return getProvider().name();
	}

	@Override
	public Enum<?> getEnum() {
		return decoratedProviderEnum.getEnum();
	}

	@Override
	public <E extends Enum<E>> E getEnumAsType(Class<E> type) {
		return decoratedProviderEnum.getEnumAsType(type);
	}

	public ProviderEnums getProviderEnums() {
		return providerEnums;
	}
}
