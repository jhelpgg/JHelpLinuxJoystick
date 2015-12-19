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