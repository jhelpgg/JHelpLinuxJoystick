/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 * 
 * @author JHelp
 */
package jhelp.linux.joystick.ui.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.util.debug.Debug;
import jhelp.util.text.UtilText;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * Swing component for joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettingsComponentSwing
      extends JPanel
      implements ListSelectionListener
{
   /**
    * Action for save settings
    * 
    * @author JHelp
    */
   private class SaveAction
         extends GenericAction
   {
      /**
       * Create a new instance of SaveAction
       */
      SaveAction()
      {
         super("Save settings");
      }

      /**
       * Called when save action choose <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param actionEvent
       *           Action event description
       * @see jhelp.gui.action.GenericAction#doActionPerformed(java.awt.event.ActionEvent)
       */
      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         JoystickSettingsComponentSwing.this.saveSettings();
      }
   }

   /**
    * Task for edit axis name
    * 
    * @author JHelp
    */
   private class TaskEditAxisName
         extends ThreadedSimpleTask<Axis>
   {
      /**
       * Create a new instance of TaskEditAxisName
       */
      TaskEditAxisName()
      {
      }

      /**
       * Edit axis name <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param axis
       *           Axis to edit its name
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final Axis axis)
      {
         JoystickSettingsComponentSwing.this.editAxisName(axis);
      }
   }

   /**
    * Task for edit button name
    * 
    * @author JHelp
    */
   private class TaskEditButtonName
         extends ThreadedSimpleTask<Button>
   {
      /**
       * Create a new instance of TaskEditButtonName
       */
      TaskEditButtonName()
      {
      }

      /**
       * Edit button name <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param button
       *           Button to edit its name
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final Button button)
      {
         JoystickSettingsComponentSwing.this.editButtonName(button);
      }
   }

   /** List of joystick axis */
   private final JList<Axis>        axisList;
   /** List of joystick buttons */
   private final JList<Button>      buttonsList;
   /** Joystick to edit settings */
   private final Joystick           joystick;
   /** Edit text for change joystick name */
   private final JTextField         joystickNameEditText;
   /** Task for edit axis name */
   private final TaskEditAxisName   taskEditAxisName;
   /** Task for edit button name */
   private final TaskEditButtonName taskEditButtonName;

   /**
    * Create a new instance of JoystickSettingsComponentSwing
    * 
    * @param joystick
    *           Joystick to edit settings
    */
   public JoystickSettingsComponentSwing(final Joystick joystick)
   {
      super(new GridBagLayout());

      if(joystick == null)
      {
         throw new NullPointerException("joystick musn't be null");
      }

      this.taskEditAxisName = new TaskEditAxisName();
      this.taskEditButtonName = new TaskEditButtonName();

      this.joystick = joystick;

      this.joystickNameEditText = new JTextField(32);
      this.joystickNameEditText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
      this.joystickNameEditText.setForeground(Color.BLACK);
      this.joystickNameEditText.setBackground(Color.WHITE);
      this.joystickNameEditText.setCaretColor(Color.RED);

      this.axisList = new JList<Axis>(new AxisListSwingModel(joystick));
      this.axisList.setCellRenderer(new AxisListSwingRenderer());
      this.axisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.axisList.addListSelectionListener(this);

      this.buttonsList = new JList<Button>(new ButtonListSwingModel(joystick));
      this.buttonsList.setCellRenderer(new ButtonListSwingRenderer());
      this.buttonsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.buttonsList.addListSelectionListener(this);

      // Name
      this.add(this.joystickNameEditText, this.createConstraints(0, 0, 2, 1));

      // Axis
      this.add(this.createLabel("AXIS"), this.createConstraints(0, 1, 1, 1));
      this.add(new JScrollPane(this.axisList), this.createConstraints(0, 2, 1, 10));

      // Buttons
      this.add(this.createLabel("BUTTONS"), this.createConstraints(1, 1, 1, 1));
      this.add(new JScrollPane(this.buttonsList), this.createConstraints(1, 2, 1, 10));

      // Save
      this.add(this.createButton(new SaveAction()), this.createConstraints(0, 12, 2, 1));

      this.updateValues();
   }

   /**
    * Create a button
    * 
    * @param action
    *           Action on button
    * @return Created button
    */
   private JButton createButton(final GenericAction action)
   {
      final JButton button = new JButton(action);
      button.setFont(JHelpConstantsSmooth.FONT_BUTTON.getFont());
      return button;
   }

   /**
    * Create a constraints
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param width
    *           Width
    * @param height
    *           Height
    * @return Created constraints
    */
   private GridBagConstraints createConstraints(final int x, final int y, final int width, final int height)
   {
      final GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = x;
      gridBagConstraints.gridy = y;
      gridBagConstraints.gridwidth = width;
      gridBagConstraints.gridheight = height;
      return gridBagConstraints;
   }

   /**
    * Create a label
    * 
    * @param text
    *           Label text
    * @return Created label
    */
   private JLabel createLabel(final String text)
   {
      final JLabel label = new JLabel(text, SwingConstants.CENTER);
      label.setFont(JHelpConstantsSmooth.FONT_BODY_2.getFont());
      return label;
   }

   /**
    * Edit axis name
    * 
    * @param axis
    *           Axis to edit its name
    */
   void editAxisName(final Axis axis)
   {
      this.axisList.clearSelection();
      String name = JOptionPane.showInputDialog(this, "Change axis name", axis.getName());

      if(name == null)
      {
         return;
      }

      name = UtilText.removeWhiteCharacters(name);

      if(name.length() == 0)
      {
         return;
      }

      axis.setName(name);
      ((AxisListSwingModel) this.axisList.getModel()).update(axis.getID());
   }

   /**
    * Edit button name
    * 
    * @param button
    *           Button to edit its name
    */
   void editButtonName(final Button button)
   {
      this.buttonsList.clearSelection();
      String name = JOptionPane.showInputDialog(this, "Change button name", button.getName());

      if(name == null)
      {
         return;
      }

      name = UtilText.removeWhiteCharacters(name);

      if(name.length() == 0)
      {
         return;
      }

      button.setName(name);
      ((ButtonListSwingModel) this.buttonsList.getModel()).update(button.getID());
   }

   /**
    * Save joystick settings
    */
   void saveSettings()
   {
      try
      {
         final String name = this.joystickNameEditText.getText().trim();

         if(name.length() > 0)
         {
            this.joystick.setName(name);
         }

         this.joystick.saveSettings();
      }
      catch(final IOException exception)
      {
         Debug.printException(exception, "Failed to save settings of ", this.joystick);
      }
   }

   /**
    * Update settings values
    */
   public void updateValues()
   {
      this.joystickNameEditText.setText(this.joystick.getName());
   }

   /**
    * Called when a selection changed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listSelectionEvent
    *           Selection event description
    * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
    */
   @Override
   public void valueChanged(final ListSelectionEvent listSelectionEvent)
   {
      if((listSelectionEvent.getValueIsAdjusting()) || (listSelectionEvent.getFirstIndex() < 0))
      {
         return;
      }

      final Object source = listSelectionEvent.getSource();

      if(this.axisList == source)
      {
         ThreadManager.THREAD_MANAGER.doThread(this.taskEditAxisName, this.axisList.getSelectedValue());
         return;
      }

      if(this.buttonsList == source)
      {
         ThreadManager.THREAD_MANAGER.doThread(this.taskEditButtonName, this.buttonsList.getSelectedValue());
      }
   }
}