package org.activiti.designer.property;

import java.util.List;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.UserTask;
import org.activiti.designer.property.ui.TaskListenerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class PropertyTaskListenerSection extends ActivitiPropertySection implements ITabbedPropertyConstants {

	private TaskListenerEditor listenerEditor;
	
	@Override
  public void createFormControls(TabbedPropertySheetPage aTabbedPropertySheetPage) {
    Composite listenersComposite = getWidgetFactory().createComposite(formComposite, SWT.WRAP);
    FormData data = new FormData();
    data = new FormData();
    data.left = new FormAttachment(0, 140);
    data.right = new FormAttachment(100, 0);
    data.top = new FormAttachment(0, VSPACE);
    listenersComposite.setLayoutData(data);
    GridLayout layout = new GridLayout();
    layout.marginTop = 0;
    layout.numColumns = 1;
    listenersComposite.setLayout(layout);
    listenerEditor = new TaskListenerEditor("taskListenerEditor", listenersComposite);
    listenerEditor.getLabelControl(listenersComposite).setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
    
    CLabel listenersLabel = getWidgetFactory().createCLabel(formComposite, "Task listeners:"); //$NON-NLS-1$
    data = new FormData();
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(listenersComposite, -HSPACE);
    data.top = new FormAttachment(listenersComposite, 0, SWT.TOP);
    listenersLabel.setLayoutData(data);
  }

  @Override
  protected Object getModelValueForControl(Control control, Object businessObject) {
    UserTask userTask = (UserTask) businessObject;
    List<ActivitiListener> taskListenerList = userTask.getTaskListeners();
    
    listenerEditor.pictogramElement = getSelectedPictogramElement();
    listenerEditor.diagramEditor = getDiagramEditor();
    listenerEditor.diagram = getDiagram();
    listenerEditor.initialize(taskListenerList);
    return null;
  }

  @Override
  protected void storeValueInModel(Control control, Object businessObject) {
    // nothing to do
  }
}