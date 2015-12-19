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
package jhelp.linux.joystick.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.settings.JoystickSettings;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.io.UtilIO;
import jhelp.util.resources.Resources;

/**
 * Joystick resources management
 * 
 * @author JHelp
 */
public class JoystickResources
{
   /**
    * Common directory where write/copy joystick settings, to be able share those settings between several applications/games.
    * Idea : configure once, used by all
    */
   private static final File         EXTERNAL_DIRECTORY;
   /** Embed resources */
   private static final Resources    RESOURCES_INTERNAL;
   /** Joystick icon image */
   public static final BufferedImage IMAGE_JOYSTICK;

   static
   {
      RESOURCES_INTERNAL = new Resources(JoystickResources.class);
      EXTERNAL_DIRECTORY = new File(UtilIO.obtainHomeDirectory(), "JHelp/Joystick/settings");

      if(UtilIO.createDirectory(JoystickResources.EXTERNAL_DIRECTORY) == false)
      {
         Debug.println(DebugLevel.ERROR, "Can't create external directory : ", JoystickResources.EXTERNAL_DIRECTORY.getAbsolutePath());
         throw new RuntimeException("Can't create external directory : " + JoystickResources.EXTERNAL_DIRECTORY.getAbsolutePath());
      }

      try
      {
         IMAGE_JOYSTICK = JoystickResources.RESOURCES_INTERNAL.obtainBufferedImage("joy4.png");
      }
      catch(final IOException exception)
      {
         Debug.printException(exception, "Failed to create joystick icon");
         throw new RuntimeException("Failed to create joystick icon", exception);
      }
   }

   /**
    * Obtain joystick settings by its settings name
    * 
    * @param settingsName
    *           Settings name
    * @return Joystick settings OR {@code null} if none
    */
   public static JoystickSettings obtainJoystickSettings(final String settingsName)
   {
      final File settings = new File(JoystickResources.EXTERNAL_DIRECTORY, settingsName);

      if(settings.exists() == false)
      {
         final InputStream stream = JoystickResources.RESOURCES_INTERNAL.obtainResourceStream(settingsName);

         if(stream == null)
         {
            return null;
         }

         try
         {
            UtilIO.write(stream, settings);
         }
         catch(final IOException exception)
         {
            Debug.printException(exception, "Failed to copy resource ", settingsName, " to ", settings.getAbsolutePath());
            return null;
         }
         finally
         {
            try
            {
               stream.close();
            }
            catch(final Exception exception)
            {
            }
         }
      }

      InputStream stream = null;

      try
      {
         stream = new FileInputStream(settings);
         return JoystickSettings.parse(stream);
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to parse ", settings.getAbsolutePath());
         return null;
      }
      finally
      {
         if(stream != null)
         {
            try
            {
               stream.close();
            }
            catch(final Exception exception)
            {
            }
         }
      }
   }

   /**
    * Save joystick settings
    * 
    * @param joystick
    *           Joystick to save its settings
    * @throws IOException
    *            On writing issue
    */
   public static void saveJoystickSettings(final Joystick joystick) throws IOException
   {
      final File settings = new File(JoystickResources.EXTERNAL_DIRECTORY, joystick.getOfficialName());

      if(UtilIO.createFile(settings) == false)
      {
         Debug.println(DebugLevel.ERROR, "Can't create file : ", settings.getAbsolutePath());
         throw new IOException("Can't create file : " + settings.getAbsolutePath());
      }

      OutputStream outputStream = null;

      try
      {
         outputStream = new FileOutputStream(settings);
         joystick.obtainJoystickSettings().serialize(outputStream);
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to save ", joystick, " in ", settings.getAbsolutePath());
         throw new IOException("Failed to save " + joystick + " in " + settings.getAbsolutePath(), exception);
      }
      finally
      {
         if(outputStream != null)
         {
            try
            {
               outputStream.flush();
            }
            catch(final Exception exception)
            {
            }

            try
            {
               outputStream.close();
            }
            catch(final Exception exception)
            {
            }
         }
      }
   }
}