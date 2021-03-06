/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ponson.actuator;

import io.opentelemetry.extension.annotations.WithSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackendService {

	@Autowired
	private ServiceProperties configuration;

	@WithSpan
	public String getProcessOutput() {

		try{
			Thread.sleep((long)Math.random()*1000);
		}catch(Exception e){
			e.printStackTrace();
		}

		return "Hello " + this.configuration.getName() + ". Random Value: " + Math.random();
	}

}
