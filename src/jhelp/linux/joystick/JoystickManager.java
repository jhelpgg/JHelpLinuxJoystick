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
package jhelp.linux.joystick;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jhelp.util.Utilities;
import jhelp.util.debug.Debug;
import jhelp.util.io.UtilIO;

/**
 * Manager of joysticks currently plugged.<br>
 * Work only on Linux based platform, other operating system will return an empty list of joysticks.<br>
 * WARNING, for now we don't detect the joystick add/remove from computer, so after use the singleton for the first time don't
 * add/remove/change joystick connect to computer. Else may crash or have strange result
 * 
 * @author JHelp
 */
public class JoystickManager
{
   /**
    * Filter of files witch are joystick pipe to read
    * 
    * @author JHelp
    */
   private static class JoystickNameFilter
         implements FileFilter
   {
      /**
       * Create a new instance of JoystickNameFilter
       */
      JoystickNameFilter()
      {
      }

      /**
       * Indicates if file is filtered <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param file
       *           File tested
       * @return {@code true} if file is filtered
       * @see java.io.FileFilter#accept(java.io.File)
       */
      @Override
      public boolean accept(final File file)
      {
         return JoystickManager.PATTERN_JOYSTICK_PIPE_NAME.matcher(file.getName()).matches() == true;
      }
   }

   /** Path where found joysticks pipes */
   private static final String         BASE_PATH                  = "/dev/input/";
   /** Path where found joysticks IDs */
   private static final String         ID_PATH                    = "/dev/input/by-id";
   /** Regular expression that match to joysticks pipes names */
   static final Pattern                PATTERN_JOYSTICK_PIPE_NAME = Pattern.compile("js[0-9]+");
   /** Singleton instance of joystick manager */
   public static final JoystickManager JOYSTICK_MANAGER           = new JoystickManager();
   /** List of joysticks */
   private final List<Joystick>        joysticks;

   /**
    * Create a new instance of JoystickManager
    */
   private JoystickManager()
   {
      this.joysticks = new ArrayList<Joystick>();

      try
      {
         final File directory = new File(JoystickManager.BASE_PATH);
         final JoystickNameFilter filter = new JoystickNameFilter();

         for(final File file : directory.listFiles(filter))
         {
            this.addJoystick(file);
         }
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Can't list joysticks plug to computer");
      }

      Utilities.sleep(1 + (this.joysticks.size() * 128));
   }

   /**
    * Add a joystick based on its pipe
    * 
    * @param file
    *           File path to the joystick pipe
    */
   private void addJoystick(final File file)
   {
      try
      {
         final Joystick joystick = new Joystick(this.joysticks.size(), file.getAbsolutePath(), this.obtainOfficialName(file));
         this.joysticks.add(joystick);
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to create joystick for ", file.getAbsolutePath());
      }
   }

   /**
    * Compute joystick official name
    * 
    * @param file
    *           File path to the joystick pipe
    * @return Joystick official name
    */
   private String obtainOfficialName(final File file)
   {
      final File directory = new File(JoystickManager.ID_PATH);

      for(final File link : directory.listFiles())
      {
         if(UtilIO.isVirtualLink(link) == true)
         {
            try
            {
               if(link.getCanonicalPath().equals(file.getAbsolutePath()) == true)
               {
                  return link.getAbsoluteFile().getName();
               }
            }
            catch(final Exception exception)
            {
            }
         }
      }

      return "JOYSTICK_" + this.joysticks.size();
   }

   /**
    * Destroy properly the manager
    */
   public void destroy()
   {
      for(final Joystick joystick : this.joysticks)
      {
         joystick.destroy();
      }
   }

   /**
    * Number of detected joysticks
    * 
    * @return Number of detected joysticks
    */
   public int numberOfJoysticks()
   {
      return this.joysticks.size();
   }

   /**
    * Obtain a joystick
    * 
    * @param joystickIndex
    *           Joystick index
    * @return The joystick
    */
   public Joystick obtainJoystick(final int joystickIndex)
   {
      return this.joysticks.get(joystickIndex);
   }
}