/***********************************************************************
 * Copyright (c) 2007 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 *
 * $Id: DiagramPrintingPreferencePage.java,v 1.2 2008/04/28 08:41:33 jlescot Exp $
 **********************************************************************/

package org.eclipse.emf.ecoretools.legacy.diagram.preferences;

import org.eclipse.emf.ecoretools.legacy.diagram.part.EcoreDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage;

/**
 * @generated
 */
public class DiagramPrintingPreferencePage extends PrintingPreferencePage {

	/**
	 * @generated
	 */
	public DiagramPrintingPreferencePage() {
		setPreferenceStore(EcoreDiagramEditorPlugin.getInstance().getPreferenceStore());
	}
}
