/*******************************************************************************
 * Copyright 2011 Krzysztof Otrebski
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package pl.otros.logview.gui.actions;

import org.jdesktop.swingx.JXTable;
import pl.otros.logview.gui.LogDataTableModel;
import pl.otros.logview.gui.OtrosApplication;
import pl.otros.logview.gui.StatusObserver;

import java.awt.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkRowAction extends  OtrosAction {

	private  static final Logger LOGGER = LoggerFactory.getLogger(MarkRowAction.class.getName());

  public MarkRowAction(OtrosApplication otrosApplication) {
		super(otrosApplication);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
		LogDataTableModel model = getOtrosApplication().getSelectedPaneLogDataTableModel();
		StatusObserver observer = getOtrosApplication().getStatusObserver();
		JXTable table = getOtrosApplication().getSelectPaneJXTable();
		if (model == null || table == null){
			return;
		}
		int[] selected = table.getSelectedRows();
    for (int i = 0; i < selected.length; i++) {
      selected[i] = table.convertRowIndexToModel(selected[i]);
    }
    model.markRows(getOtrosApplication().getSelectedMarkColors(), selected);
    if (observer != null) {
      observer.updateStatus(selected.length + " rows marked");
    }

  }

}
