/*******************************************************************************
 * Copyright (c) 2012 Google, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alex Ruiz (Google) - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core;

/**
 * @since 5.4
 */
public interface IErrorParser3 extends IErrorParser {
	/**
	 * Called to let the parser know that the end of the error stream has been reached.
	 * Can be used by the parser to flush its internal buffers.
	 */
	void shutdown();
}
