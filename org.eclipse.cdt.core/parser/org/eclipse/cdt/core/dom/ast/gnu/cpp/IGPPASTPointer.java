/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    John Camelon (IBM) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.ast.gnu.cpp;

import org.eclipse.cdt.core.dom.ast.IASTPointer;

/**
 * @deprecated Use {@link IASTPointer}, instead.
 * @noreference This interface is not intended to be referenced by clients.
 */
@Deprecated
public interface IGPPASTPointer extends IASTPointer {
	/**
	 * @since 5.1
	 */
	@Override
	public IGPPASTPointer copy();
}
