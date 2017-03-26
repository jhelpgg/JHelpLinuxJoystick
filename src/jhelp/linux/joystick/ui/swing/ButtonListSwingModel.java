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

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickListener;

/**
 * Swing joystick buttons list model
 * 
 * @author JHelp
 */
public class ButtonListSwingModel
      implements ListModel<Button>, JoystickListener
{
   /** Joystick */
   private final Joystick               joystick;
   /** Listeners */
   private final List<ListDataListener> listeners;

   /**
    * Create a new instance of ButtonListSwingModel
    * 
    * @param joystick
    *           Joystick
    */
   public ButtonListSwingModel(final Joystick joystick)
   {
      if(joystick == null)
      {
         throw new NullPointerException("joystick musn't be null");
      }

      this.joystick = joystick;
      this.joystick.registerJoystickListener(this);
      this.listeners = new ArrayList<ListDataListener>();
   }

   /**
    * Register a model change listener <br>
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
      if(listener == null)
      {
         return;
      }

      synchronized(this.listeners)
      {
         if(!this.listeners.contains(listener))
         {
            this.listeners.add(listener);
         }
      }
   }

   /**
    * Obtain joystick button <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Joystick button index
    * @return Joystick button
    * @see javax.swing.ListModel#getElementAt(int)
    */
   @Override
   public Button getElementAt(final int index)
   {
      return this.joystick.getButton(index);
   }

   /**
    * Number of joysticks buttons <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Number of joysticks buttons
    * @see javax.swing.ListModel#getSize()
    */
   @Override
   public int getSize()
   {
      return this.joystick.getNumberOfButtons();
   }

   /**
    * Called when joystick axis activated <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisActivated(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisActivated(final Joystick joystick, final Axis axis)
   {
   }

   /**
    * Called when joystick axis released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisReleased(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisReleased(final Joystick joystick, final Axis axis)
   {
   }

   /**
    * Called when joystick button pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonPressed(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonPressed(final Joystick joystick, final Button button)
   {
      this.update(button.getID());
   }

   /**
    * Called when joystick button released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonReleased(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonReleased(final Joystick joystick, final Button button)
   {
      this.update(button.getID());
   }

   /**
    * Unregister a model change listener <br>
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
      synchronized(this.listeners)
      {
         this.listeners.remove(listener);
      }
   }

   /**
    * Update list model for given index
    * 
    * @param index
    *           Index to update
    */
   public void update(final int index)
   {
      final ListDataEvent listDataEvent = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index);

      synchronized(this.listeners)
      {
         for(final ListDataListener listener : this.listeners)
         {
            listener.contentsChanged(listDataEvent);
         }
      }
   }
}