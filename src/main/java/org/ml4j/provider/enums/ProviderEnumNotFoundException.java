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

import org.ml4j.provider.Provider;

/**
 * @author Michael Lavelle
 */
public class ProviderEnumNotFoundException extends RuntimeException {

	/**
	 * Default serialization id.
	 */
	private static final long serialVersionUID = 1L;
	

	public ProviderEnumNotFoundException(ProviderEnums providerEnums, Provider provider) {
		this(providerEnums, provider.name());
	}
	
	public ProviderEnumNotFoundException(ProviderEnums providerEnums, String providerName) {
		super(providerEnums.getClass().getName() + "." + providerEnums + " does not include an enum from provider:" + providerName);
	}
	
	public ProviderEnumNotFoundException(DefaultProviderEnum<?> providerEnum) {
		super("Provider enum " + providerEnum.getProviderName() + ":" + providerEnum.getEnumTypeClassName() 
			+ "." + providerEnum.getEnumName() + " cannot be found on the classpath");
	}

}
