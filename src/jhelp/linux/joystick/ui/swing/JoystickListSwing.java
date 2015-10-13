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