/*
 *  Copyright 2013 Chris Pheby
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jadira.scanner.classpath.filter;

import java.lang.annotation.Annotation;

import org.jadira.scanner.classpath.types.JType;
import org.jadira.scanner.core.filter.AbstractFilter;

public class JTypeAnnotatedWithFilter extends AbstractFilter<JType> {

	private Class<? extends Annotation> annotation;
	
	public JTypeAnnotatedWithFilter() {
	}
	
	public JTypeAnnotatedWithFilter(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	@Override
	public boolean accept(JType type) {
	        
        if (type.getAnnotation(annotation) != null) {
            return true;
        } else {
            return false;
        }
	}
}
