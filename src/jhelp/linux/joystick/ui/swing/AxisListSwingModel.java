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
 * Joystick axis list model swing
 * 
 * @author JHelp
 */
public class AxisListSwingModel
      implements ListModel<Axis>, JoystickListener
{
   /** Joystick */
   private final Joystick               joystick;
   /** Listeners */
   private final List<ListDataListener> listeners;

   /**
    * Create a new instance of AxisListSwingModel
    * 
    * @param joystick
    *           Joystick
    */
   public AxisListSwingModel(final Joystick joystick)
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
    * Register model listener <br>
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
         if(this.listeners.contains(listener) == false)
         {
            this.listeners.add(listener);
         }
      }
   }

   /**
    * Obtain a joystick axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Index
    * @return Joystick axis
    * @see javax.swing.ListModel#getElementAt(int)
    */
   @Override
   public Axis getElementAt(final int index)
   {
      return this.joystick.getAxis(index);
   }

   /**
    * Number of axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Number of axis
    * @see javax.swing.ListModel#getSize()
    */
   @Override
   public int getSize()
   {
      return this.joystick.getNumberOfAxis();
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
      this.update(axis.getID());
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
      this.update(axis.getID());
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
   }

   /**
    * Unregister model listener <br>
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
    * Update the model for given index
    * 
    * @param index
    *           Modified index
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