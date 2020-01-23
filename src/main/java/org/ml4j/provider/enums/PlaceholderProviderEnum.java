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
 * Represents an Enum from a particular provider that may not exist yet, but for
 * which we wish to create a placeholder.
 * 
 * Aids decoupling.
 * 
 * @author Michael Lavelle
 */
public class PlaceholderProviderEnum extends DefaultProviderEnum<PlaceholderProviderEnum> {

	/**
	 * Default serialization id
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean visible;

	public PlaceholderProviderEnum(Provider provider, String enumTypeClassName, String enumName) {
		super(provider, enumTypeClassName, enumName);
		this.visible = false;
	}
	
	public PlaceholderProviderEnum(Provider provider, String enumName) {
		super(provider, "tbc", enumName);
		this.visible = false;
	}
	
	public PlaceholderProviderEnum(Provider provider, String enumTypeClassName, String enumName, boolean visible) {
		super(provider, enumTypeClassName, enumName);
		this.visible = visible;
	}
	
	public PlaceholderProviderEnum(Provider provider, String enumName, boolean visible) {
		super(provider, "tbc", enumName);
		this.visible = visible;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

}
