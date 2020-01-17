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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.ml4j.provider.Provider;
import org.ml4j.providers.dl4j.DL4JEnums;
import org.ml4j.providers.ml4j.ML4JEnums;


/**
 * Linking multiple related ProviderEnums together as a single ProviderEnums constant.
 * 
 * @author Michael Lavelle
 */
public enum ProviderEnums  {
	
	RELU_ENUM("Relu Enums", 
			ML4JEnums.RELU, 
			DL4JEnums.RELU),
	
	LINEAR_ENUM("Linear Enums", 
			ML4JEnums.LINEAR, 
			DL4JEnums.IDENTITY),

	SIGMOID_ENUM("Sigmoid Enums", 
			ML4JEnums.SIGMOID,
			DL4JEnums.SIGMOID),
	
	SOFTMAX_ENUM("Softmax Enums", 
			ML4JEnums.SOFTMAX, 
			DL4JEnums.SOFTMAX),
	
	LEAKY_RELU_ENUM("Leaky Relu Enums", 
			new PlaceholderProviderEnum(Provider.ML4J, "LEAKYRELU"), 
			DL4JEnums.LEAKYRELU);
	
	private final String artifactType;
	
	private final Set<ProviderEnum> providerEnums;
	
	ProviderEnums(String artifactType, ProviderEnum...providerEnums) {
		this.artifactType = artifactType;
		this.providerEnums = new HashSet<>();
		this.providerEnums.addAll(Arrays.asList(providerEnums));
	}
	
	public String getArtifactType() {
		return artifactType;
	}
	
	public Set<ProviderEnum> providedByAll() {
		return providerEnums;
	}
	
	public ProviderEnum providedBy(Provider provider) {
		return providedBy(provider.name());
	}
	
	ProviderEnum providedBy(String providerName) {
		return providerEnums.stream().filter(e -> e.getProviderName().equals(providerName) && !e.isPlaceholder()).findFirst().orElseThrow(() -> new ProviderEnumNotFoundException(this, providerName));
	}


	public Set<String> getEnumProviderNames() {
		return providerEnums.stream().filter(e -> !e.isPlaceholder()).map(e -> e.getProviderName()).collect(Collectors.toSet());
	}
	
	public Set<String> getEnumNames() {
		return providerEnums.stream().filter(e -> !e.isPlaceholder()).map(e -> e.getEnumName()).collect(Collectors.toSet());
	}
	
	public Set<String> getQualifiedEnumNames() {
		return providerEnums.stream().filter(e -> !e.isPlaceholder()).map(e -> e.getQualifiedEnumName()).collect(Collectors.toSet());
	}
	
}
