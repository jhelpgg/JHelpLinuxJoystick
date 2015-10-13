package jhelp.linux.joystick.ui.smooth;

import java.awt.event.ActionEvent;
import java.io.IOException;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.JHelpButtonSmooth;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.gui.smooth.JHelpEditTextSmooth;
import jhelp.gui.smooth.JHelpLabelTextSmooth;
import jhelp.gui.smooth.JHelpListSmooth;
import jhelp.gui.smooth.JHelpPanelSmooth;
import jhelp.gui.smooth.JHelpScrollModeSmooth;
import jhelp.gui.smooth.layout.JHelpTableConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpTableLayoutSmooth;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.util.debug.Debug;

/**
 * Smooth component for joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettingsComponentSmooth
      extends JHelpPanelSmooth
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
         JoystickSettingsComponentSmooth.this.saveSettings();
      }
   }

   /** List of joystick axis */
   private final JHelpListSmooth<Axis>   axisList;
   /** List of joystick buttons */
   private final JHelpListSmooth<Button> buttonsList;
   /** Joystick to edit settings */
   private final Joystick                joystick;
   /** Edit text for change joystick name */
   private final JHelpEditTextSmooth     joystickNameEditText;

   /**
    * Create a new instance of JoystickSettingsComponentSmooth
    * 
    * @param joystick
    *           Joystick to edit settings
    */
   public JoystickSettingsComponentSmooth(final Joystick joystick)
   {
      super(new JHelpTableLayoutSmooth());

      if(joystick == null)
      {
         throw new NullPointerException("joystick musn't be null");
      }

      this.joystick = joystick;
      this.joystickNameEditText = new JHelpEditTextSmooth(JHelpConstantsSmooth.FONT_DISPLAY_2, 32, JHelpConstantsSmooth.COLOR_BLACK,
            JHelpConstantsSmooth.COLOR_RED_0400);
      this.axisList = new JHelpListSmooth<Axis>(new AxisListSmoothModel(joystick), new AxisListSmoothRenderer(), JHelpScrollModeSmooth.SCROLL_VERTICAL);
      this.axisList.setScroolWithRightButtonOnly(true);
      this.axisList.setSelectBySimpleClick(true);
      this.buttonsList = new JHelpListSmooth<Button>(new ButtonListSmoothModel(joystick), new ButtonListSmoothRenderer(), JHelpScrollModeSmooth.SCROLL_VERTICAL);
      this.buttonsList.setScroolWithRightButtonOnly(true);
      this.buttonsList.setSelectBySimpleClick(true);

      // Name
      this.addComponent(this.joystickNameEditText, new JHelpTableConstraintsSmooth(0, 0, 2, 1));

      // Axis
      this.addComponent(new JHelpLabelTextSmooth("AXIS"), new JHelpTableConstraintsSmooth(0, 1, 1, 1));
      this.addComponent(this.axisList, new JHelpTableConstraintsSmooth(0, 2, 1, 10));

      // Buttons
      this.addComponent(new JHelpLabelTextSmooth("BUTTONS"), new JHelpTableConstraintsSmooth(1, 1, 1, 1));
      this.addComponent(this.buttonsList, new JHelpTableConstraintsSmooth(1, 2, 1, 10));

      // Save
      this.addComponent(new JHelpButtonSmooth(new SaveAction()), new JHelpTableConstraintsSmooth(0, 12, 2, 1));

      this.updateValues();
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
}