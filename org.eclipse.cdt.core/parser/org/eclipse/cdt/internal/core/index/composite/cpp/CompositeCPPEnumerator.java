/*******************************************************************************
 * Copyright (c) 2007, 2014 Symbian Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrew Ferguson (Symbian) - Initial implementation
 *     Sergey Prigogin (Google)
 *******************************************************************************/
package org.eclipse.cdt.internal.core.index.composite.cpp;

import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.IValue;
import org.eclipse.cdt.internal.core.dom.parser.cpp.ICPPInternalEnumerator;
import org.eclipse.cdt.internal.core.index.composite.ICompositesFactory;

class CompositeCPPEnumerator extends CompositeCPPBinding implements ICPPInternalEnumerator {
	public CompositeCPPEnumerator(ICompositesFactory cf, ICPPInternalEnumerator rbinding) {
		super(cf, rbinding);
	}

	@Override
	public IType getType() {
		IType type = ((ICPPInternalEnumerator) rbinding).getType();
		return cf.getCompositeType(type);
	}
	
	@Override
	public IValue getValue() {
		return ((ICPPInternalEnumerator) rbinding).getValue();
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public IType getInternalType() {
		return ((ICPPInternalEnumerator) rbinding).getInternalType();
	}
}
