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
package jhelp.linux.joystick.ui.smooth;

import jhelp.gui.smooth.JHelpListSmooth;
import jhelp.gui.smooth.JHelpScrollModeSmooth;
import jhelp.linux.joystick.Joystick;

/**
 * Smooth list of current plugged joysticks
 * 
 * @author JHelp
 */
public class JoystickListSmooth
      extends JHelpListSmooth<Joystick>
{
   /**
    * Create a new instance of JoystickListSmooth
    */
   public JoystickListSmooth()
   {
      this(JHelpScrollModeSmooth.SCROLL_VERTICAL);
   }

   /**
    * Create a new instance of JoystickListSmooth
    * 
    * @param scrollModeSmooth
    *           Way of scroll
    */
   public JoystickListSmooth(final JHelpScrollModeSmooth scrollModeSmooth)
   {
      super(new JoystickListSmoothModel(), new JoystickListSmoothRenderer(), scrollModeSmooth);
   }
}