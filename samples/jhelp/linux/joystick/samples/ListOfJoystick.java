package jhelp.linux.joystick.samples;

import jhelp.linux.joystick.JoystickManager;
import jhelp.util.MemorySweeper;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;

/**
 * Sample that show the current list of joystick plugged to computer
 * 
 * @author JHelp
 */
public class ListOfJoystick
{

   /**
    * Launch the sample
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      MemorySweeper.launch();
      final int number = JoystickManager.JOYSTICK_MANAGER.numberOfJoysticks();

      for(int i = 0; i < number; i++)
      {
         Debug.println(DebugLevel.INFORMATION, JoystickManager.JOYSTICK_MANAGER.obtainJoystick(i));
      }

      JoystickManager.JOYSTICK_MANAGER.destroy();
      MemorySweeper.exit(0);
   }
}