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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jhelp.linux.joystick.resources.JoystickResources;
import jhelp.linux.joystick.settings.JoystickSettings;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.io.UtilIO;
import jhelp.util.list.HashInt;
import jhelp.util.text.UtilText;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * Represents a joystick
 * 
 * @author JHelp
 */
public final class Joystick
{
   /**
    * Task for manage joystick events
    * 
    * @author JHelp
    */
   private class EventManager
         extends ThreadedVerySimpleTask
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {

      }

      /**
       * Do the task : manage joystick events <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
       */
      @Override
      protected void doVerySimpleAction()
      {
         Joystick.this.readNextEvent();
      }
   }

   /** Nature of joystick axis */
   private static final int             NATURE_AXIS   = 0x0200;
   /** Nature of joystick button */
   private static final int             NATURE_BUTTON = 0x0100;
   /** Indicates if management still alive */
   private boolean                      alive;
   /** Joystick axis */
   private final HashInt<Axis>          axis;
   /** Buffer for read one joystick event in pipe */
   private final byte[]                 buffer;
   /** Joystick buttons */
   private final HashInt<Button>        buttons;
   /** Manager of joystick events */
   private final EventManager           eventManager;
   /** Joystick ID */
   private final int                    id;
   /** Listeners of joystick events */
   private final List<JoystickListener> joystickListeners;
   /** Joystick settings */
   private JoystickSettings             joystickSettings;
   /** Joystick name */
   private String                       name;
   /** Joystick "official" name */
   private final String                 officalName;
   /** Stream to read the joystick pipe */
   private InputStream                  streamToRead;

   /**
    * Create a new instance of Joystick
    * 
    * @param id
    *           Joystick ID
    * @param pathToRead
    *           Pipe path
    * @param officalName
    *           Official name
    * @throws IOException
    *            On reading issue
    */
   Joystick(final int id, final String pathToRead, final String officalName)
         throws IOException
   {
      this.eventManager = new EventManager();
      this.buffer = new byte[8];
      this.axis = new HashInt<Axis>();
      this.buttons = new HashInt<Button>();
      this.joystickListeners = new ArrayList<JoystickListener>();
      this.id = id;
      this.officalName = officalName;
      this.streamToRead = (new URL("file://" + pathToRead)).openStream();
      this.alive = true;
      this.joystickSettings = JoystickResources.obtainJoystickSettings(this.officalName);

      if(this.joystickSettings != null)
      {
         this.name = this.joystickSettings.getJoystickName();
      }

      ThreadManager.THREAD_MANAGER.doThread(this.eventManager, null);
   }

   /**
    * Parse a joystick event form joystick pipe
    * 
    * @param value
    *           Event value
    * @param code
    *           Event code
    * @param source
    *           Event source (button or axis)
    */
   private void messageRead(final byte value, final byte code, final int source)
   {
      final int nature = source & 0x0F00;
      final int id = source & 0xFF;

      // If its event for declare joystick button or axis
      if(source >= 0x8000)
      {
         switch(nature)
         {
            case NATURE_BUTTON:
               final Button button = new Button(id, this);
               button.setDown(value > 0);

               if(this.joystickSettings != null)
               {
                  button.setName(this.joystickSettings.getButtonName(id));
               }

               this.buttons.put(id, button);
            break;
            case NATURE_AXIS:
               final Axis axis = new Axis(id, this);
               axis.setValue(value);

               if(this.joystickSettings != null)
               {
                  axis.setName(this.joystickSettings.getAxisName(id));
               }

               this.axis.put(id, axis);
            break;
         }

         return;
      }

      // Just a change status
      switch(nature)
      {
         case NATURE_BUTTON:
            final Button button = this.buttons.get(id);

            if(button != null)
            {
               button.setDown(value > 0);

               if(value == 0)
               {
                  synchronized(this.joystickListeners)
                  {
                     for(final JoystickListener joystickListener : this.joystickListeners)
                     {
                        joystickListener.joystickButtonReleased(this, button);
                     }
                  }
               }
               else
               {
                  synchronized(this.joystickListeners)
                  {
                     for(final JoystickListener joystickListener : this.joystickListeners)
                     {
                        joystickListener.joystickButtonPressed(this, button);
                     }
                  }
               }
            }

         break;
         case NATURE_AXIS:
            final Axis axis = this.axis.get(id);

            if(axis != null)
            {
               axis.setValue(value);

               if(value == 0)
               {
                  synchronized(this.joystickListeners)
                  {
                     for(final JoystickListener joystickListener : this.joystickListeners)
                     {
                        joystickListener.joystickAxisReleased(this, axis);
                     }
                  }
               }
               else
               {
                  synchronized(this.joystickListeners)
                  {
                     for(final JoystickListener joystickListener : this.joystickListeners)
                     {
                        joystickListener.joystickAxisActivated(this, axis);
                     }
                  }
               }
            }

         break;
      }
   }

   /**
    * Destroy properly the joystick
    */
   void destroy()
   {
      this.alive = false;

      if(this.streamToRead != null)
      {
         try
         {
            this.streamToRead.close();
         }
         catch(final IOException exception)
         {
            Debug.printException(exception, "Failed to close the stream");
         }

         this.streamToRead = null;
      }
   }

   /**
    * Read next joystick event on pipe
    */
   void readNextEvent()
   {
      try
      {
         final int read = UtilIO.readStream(this.streamToRead, this.buffer);

         if(read != 8)
         {
            Debug.println(DebugLevel.WARNING, "Not read 8 bytes, but ", read);
            this.destroy();
            return;
         }

         this.messageRead(this.buffer[4], this.buffer[5], ((this.buffer[6] & 0xFF) << 8) | (this.buffer[7] & 0xFF));

         if(this.alive)
         {
            ThreadManager.THREAD_MANAGER.doThread(this.eventManager, null);
         }
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to read next event in joystick : ", this.officalName);
         this.destroy();
      }
   }

   /**
    * Obtain a joystick axis
    * 
    * @param axis
    *           Axis index
    * @return Axis
    */
   public Axis getAxis(final int axis)
   {
      return this.axis.get(axis);
   }

   /**
    * Obtain a joystick button
    * 
    * @param button
    *           Button index
    * @return Button
    */
   public Button getButton(final int button)
   {
      return this.buttons.get(button);
   }

   /**
    * Joystick ID
    * 
    * @return Joystick ID
    */
   public int getID()
   {
      return this.id;
   }

   /**
    * Joystick name
    * 
    * @return Joystick name
    */
   public String getName()
   {
      if(this.name == null)
      {
         return this.officalName;
      }

      return this.name;
   }

   /**
    * Number of joystick axis
    * 
    * @return Number of joystick axis
    */
   public int getNumberOfAxis()
   {
      return this.axis.getSize();
   }

   /**
    * Number of joystick buttons
    * 
    * @return Number of joystick buttons
    */
   public int getNumberOfButtons()
   {
      return this.buttons.getSize();
   }

   /**
    * Official name
    * 
    * @return Official name
    */
   public String getOfficialName()
   {
      return this.officalName;
   }

   /**
    * Search axis by its name
    * 
    * @param name
    *           Axis name
    * @return Axis found OR {@code null} if not found
    */
   public Axis obtainAxisByName(final String name)
   {
      for(final Axis axis : this.axis)
      {
         if(axis.getName()
                .equals(name))
         {
            return axis;
         }
      }

      return null;
   }

   /**
    * Search button by its name
    * 
    * @param name
    *           Button name
    * @return Button found OR {@code null} if not found
    */
   public Button obtainButtonByName(final String name)
   {
      for(final Button button : this.buttons)
      {
         if(button.getName()
                  .equals(name))
         {
            return button;
         }
      }

      return null;
   }

   /**
    * Current joystick settings
    * 
    * @return Current joystick settings
    */
   public JoystickSettings obtainJoystickSettings()
   {
      this.joystickSettings = new JoystickSettings(this);
      return this.joystickSettings;
   }

   /**
    * Register listener of joystick events
    * 
    * @param joystickListener
    *           Listener to register
    */
   public void registerJoystickListener(final JoystickListener joystickListener)
   {
      if(joystickListener == null)
      {
         throw new NullPointerException("joystickListener musn't be null");
      }

      synchronized(this.joystickListeners)
      {
         if(!this.joystickListeners.contains(joystickListener))
         {
            this.joystickListeners.add(joystickListener);
         }
      }
   }

   /**
    * Save joystick current settings
    * 
    * @throws IOException
    *            On writing issue
    */
   public void saveSettings() throws IOException
   {
      JoystickResources.saveJoystickSettings(this);
   }

   /**
    * change joystick name
    * 
    * @param name
    *           New joystick name. Use {@code null} to return to official name
    */
   public void setName(final String name)
   {
      this.name = name;
   }

   /**
    * String description <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return String description
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return UtilText.concatenate("Joystick ", this.getName(), " : ", this.getNumberOfButtons(), " buttons, ", this.getNumberOfAxis(), " axis");
   }

   /**
    * Unregister a listener of joystick events
    * 
    * @param joystickListener
    *           Listener to unregister
    */
   public void unregisterJoystickListener(final JoystickListener joystickListener)
   {
      synchronized(this.joystickListeners)
      {
         this.joystickListeners.remove(joystickListener);
      }
   }
}