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

import javax.swing.JList;

import jhelp.linux.joystick.Joystick;

/**
 * Joystick list swing
 * 
 * @author JHelp
 */
public class JoystickListSwing
      extends JList<Joystick>
{
   /**
    * Create a new instance of JoystickListSwing
    */
   public JoystickListSwing()
   {
      super(new JoystickListSwingModel());
      this.setCellRenderer(new JoystickListSwingRenderer());
   }
}