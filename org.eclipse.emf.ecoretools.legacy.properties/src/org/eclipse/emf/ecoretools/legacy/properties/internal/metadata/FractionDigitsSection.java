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
 * $Id: FractionDigitsSection.java,v 1.4 2008/05/26 12:28:57 jlescot Exp $
 **********************************************************************/

package org.eclipse.emf.ecoretools.legacy.properties.internal.metadata;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecoretools.legacy.properties.internal.Messages;
import org.eclipse.emf.ecoretools.legacy.tabbedproperties.EMFRecordingChangeCommand;
import org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.eclipse.emf.ecoretools.legacy.tabbedproperties.utils.TextChangeListener;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

/**
 * Section to edit fractionDigits extended metadata annotation
 * 
 * @see ExtendedMetaData#setFractionDigitsFacet(org.eclipse.emf.ecore.EDataType,
 *      int)
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class FractionDigitsSection extends AbstractTabbedPropertySection {

	/**
	 * A boolean that store if refreshing is happening and no model
	 * modifications should be performed
	 */
	private boolean isRefreshing = false;

	/**
	 * The text for the section.
	 */
	private Text fractionTxt;

	/**
	 * The section label;
	 */
	private CLabel labelTxt;

	// /**
	// * @see
	// org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	// * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	// */
	// public void createControls(Composite parent, TabbedPropertySheetPage
	// aTabbedPropertySheetPage)
	// {
	// super.createControls(parent, aTabbedPropertySheetPage);
	// Composite composite = getWidgetFactory().createFlatFormComposite(parent);
	// FormData data;
	//
	// String label = getLabelText();
	// CLabel labelTxt = getWidgetFactory().createCLabel(composite, label);
	//
	// fractionTxt = getWidgetFactory().createText(composite, "");
	//
	// data = new FormData();
	// data.left = new FormAttachment(0, 0);
	// data.right = new FormAttachment(fractionTxt,
	// -ITabbedPropertyConstants.HSPACE);
	// data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
	// labelTxt.setLayoutData(data);
	//
	// data = new FormData();
	// data.left = new FormAttachment(0, getStandardLabelWidth(composite, new
	// String[] {label}));
	// data.right = new FormAttachment(100, 0);
	// data.top = new FormAttachment(labelTxt, 0, SWT.CENTER);
	// fractionTxt.setLayoutData(data);
	//
	// hookListeners();
	// }

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgets(Composite composite) {
		labelTxt = getWidgetFactory().createCLabel(composite, getLabelText());

		fractionTxt = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setSectionData(Composite composite) {
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(fractionTxt, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		labelTxt.setLayoutData(data);

		data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[] { getLabelText() }));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(labelTxt, 0, SWT.CENTER);
		fractionTxt.setLayoutData(data);
	}

	/**
	 * Adds the listeners on the widgets
	 */
	@Override
	protected void hookListeners() {
		TextChangeListener listener = new TextChangeListener() {

			@Override
			public void textChanged(Control control) {
				handleTextModified();
			}
		};
		listener.startListeningTo(fractionTxt);
		listener.startListeningForEnter(fractionTxt);
	}

	/**
	 * Handle the text modified event.
	 */
	protected void handleTextModified() {
		if (!isRefreshing) {
			try {
				if (fractionTxt.getText() == null || fractionTxt.getText().length() == 0) {
					fractionTxt.setText("-1"); //$NON-NLS-1$
				}
				final int newFraction = Integer.parseInt(fractionTxt.getText());

				EditingDomain editingDomain = getEditingDomain();
				if (getEObjectList().size() == 1) {
					int oldFraction = ExtendedMetaData.INSTANCE.getFractionDigitsFacet((EDataType) getEObject());
					if (oldFraction != newFraction) {
						editingDomain.getCommandStack().execute(new EMFRecordingChangeCommand(getEObject().eResource()) {

							@Override
							protected void doExecute() {
								ExtendedMetaData.INSTANCE.setFractionDigitsFacet((EDataType) getEObject(), newFraction);
							}
						});
					}
				} else {
					CompoundCommand compoundCommand = new CompoundCommand();
					/* apply the property change to all selected elements */
					for (Iterator<EObject> i = getEObjectList().iterator(); i.hasNext();) {
						final EObject nextObject = i.next();

						int oldFraction = ExtendedMetaData.INSTANCE.getFractionDigitsFacet((EDataType) nextObject);
						if (oldFraction != newFraction) {
							editingDomain.getCommandStack().execute(new EMFRecordingChangeCommand(nextObject.eResource()) {

								@Override
								protected void doExecute() {
									ExtendedMetaData.INSTANCE.setFractionDigitsFacet((EDataType) nextObject, newFraction);
								}
							});
						}
					}
					editingDomain.getCommandStack().execute(compoundCommand);
				}
			} catch (NumberFormatException e) {
				refresh();
			}
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh() {
		isRefreshing = true;

		fractionTxt.setText("" + ExtendedMetaData.INSTANCE.getFractionDigitsFacet((EDataType) getEObject())); //$NON-NLS-1$

		isRefreshing = false;
	}

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 */
	@Override
	protected EStructuralFeature getFeature() {
		return null;
	}

	/**
	 * @see org.eclipse.emf.ecoretools.legacy.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 */
	@Override
	protected String getLabelText() {
		return Messages.FractionDigitsSection_FractionDigits;
	}

}
