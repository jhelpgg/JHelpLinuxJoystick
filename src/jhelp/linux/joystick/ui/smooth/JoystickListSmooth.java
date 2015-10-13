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