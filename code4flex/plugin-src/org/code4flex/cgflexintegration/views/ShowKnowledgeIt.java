 /*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by KnowledgeIt.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 * 
 * Visit our site: http://knowledgeit.com.ar/code4flex/
 *
 */

package org.code4flex.cgflexintegration.views;

import org.code4flex.cgflexintegration.model.PluginModel;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class ShowKnowledgeIt extends PopupDialog implements Runnable{

	private PluginModel model = PluginModel.getInstance();
	
	public ShowKnowledgeIt(Shell parent, int shellStyle,
			boolean takeFocusOnOpen, boolean persistSize,
			boolean persistLocation, boolean showDialogMenu,
			boolean showPersistActions, String titleText, String infoText) {
		super(parent, shellStyle, takeFocusOnOpen, persistSize, persistLocation,
				showDialogMenu, showPersistActions, titleText, infoText);
		
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		container.setLayout(null);

		final Label httpknowledgeitcomarLabel = new Label(container, SWT.CENTER);
		httpknowledgeitcomarLabel.setText(model.getProperty("com.c4f.showknowledgeit.url"));
		httpknowledgeitcomarLabel.setBounds(10, 18, 629, 13);
		
		
		final Browser browser = new Browser(container, SWT.NONE);
		browser.setUrl(httpknowledgeitcomarLabel.getText());
		browser.setBounds(10, 37, 629, 361);
		
		final Button btnclose = new Button(container, SWT.NONE);
		btnclose.setText("close");
		btnclose.setBounds(595, 415, 44, 23);
		btnclose.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent arg0) {
				close();
			}
			public void widgetSelected(SelectionEvent arg0) {
				close();
			}
			
		});
		
		return container;
		
	}

	public void run() {
		this.open();
		
	}

}
