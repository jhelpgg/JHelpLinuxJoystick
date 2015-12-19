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

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickManager;

/**
 * Swing list model of current joysticks plugged
 * 
 * @author JHelp
 */
public class JoystickListSwingModel
      implements ListModel<Joystick>
{
   /**
    * Create a new instance of JoystickListSwingModel
    */
   public JoystickListSwingModel()
   {
   }

   /**
    * Register listener of model changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to register
    * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
    */
   @Override
   public void addListDataListener(final ListDataListener listener)
   {
   }

   /**
    * Obtain a joystick <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Joystick index
    * @return Joystick
    * @see javax.swing.ListModel#getElementAt(int)
    */
   @Override
   public Joystick getElementAt(final int index)
   {
      return JoystickManager.JOYSTICK_MANAGER.obtainJoystick(index);
   }

   /**
    * Number of joysticks plugged <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Number of joysticks plugged * @see javax.swing.ListModel#getSize()
    */
   @Override
   public int getSize()
   {
      return JoystickManager.JOYSTICK_MANAGER.numberOfJoysticks();
   }

   /**
    * Unregister listener of model changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to unregister
    * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
    */
   @Override
   public void removeListDataListener(final ListDataListener listener)
   {
   }
}