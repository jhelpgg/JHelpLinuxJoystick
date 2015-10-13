package jhelp.linux.joystick.ui.swing;

import jhelp.util.gui.UtilGUI;

/**
 * Launch swing joystick chooser
 * 
 * @author JHelp
 */
public class JoystickChooser
{

   /**
    * Launch swing joystick chooser
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      UtilGUI.initializeGUI();
      final JoystickChooserFrame joystickChooserFrame = new JoystickChooserFrame();
      joystickChooserFrame.setVisible(true);
   }
}