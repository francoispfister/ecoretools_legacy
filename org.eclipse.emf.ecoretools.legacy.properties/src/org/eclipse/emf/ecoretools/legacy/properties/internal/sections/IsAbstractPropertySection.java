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
 * $Id: IsAbstractPropertySection.java,v 1.5 2008/05/26 12:28:57 jlescot Exp $
 **********************************************************************/

package org.eclipse.emf.ecoretools.legacy.properties.internal.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecoretools.legacy.properties.internal.Messages;
import org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractBooleanPropertySection;

/**
 * A section for the abstract property of an EClass Object.
 * 
 * @author Jacques LESCOT
 */
public class IsAbstractPropertySection extends AbstractBooleanPropertySection {

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 */
	@Override
	protected EStructuralFeature getFeature() {
		return EcorePackage.Literals.ECLASS__ABSTRACT;
	}

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 */
	@Override
	protected String getLabelText() {
		return Messages.IsAbstractPropertySection_IsAbstract;
	}

}
