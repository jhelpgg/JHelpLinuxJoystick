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