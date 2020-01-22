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

import java.util.function.Supplier;

import org.ml4j.provider.enums.ProviderEnums;

public enum StandardActivationFunctionTypeEnum implements Supplier<ActivationFunctionTypeEnum> {

	RELU(new ActivationFunctionTypeEnum(ProviderEnums.RELU_ENUM)),
	SIGMOID(new ActivationFunctionTypeEnum(ProviderEnums.SIGMOID_ENUM)),
	LINEAR(new ActivationFunctionTypeEnum(ProviderEnums.LINEAR_ENUM)),
	IDENTITY(new ActivationFunctionTypeEnum(ProviderEnums.LINEAR_ENUM)),
	LEAKYRELU(new ActivationFunctionTypeEnum(ProviderEnums.LEAKY_RELU_ENUM));

	ActivationFunctionTypeEnum activationFunctionType;

	StandardActivationFunctionTypeEnum(ActivationFunctionTypeEnum activationFunctionType) {
		this.activationFunctionType = activationFunctionType;
	}

	@Override
	public ActivationFunctionTypeEnum get() {
		return activationFunctionType;
	}
}
