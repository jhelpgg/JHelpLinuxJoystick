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

import jhelp.gui.smooth.event.JHelpListSmoothModelListener;
import jhelp.gui.smooth.model.JHelpListSmoothModel;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Joystick;

/**
 * Joystick axis list model smooth
 * 
 * @author JHelp
 */
public class AxisListSmoothModel
      implements JHelpListSmoothModel<Axis>
{
   /** Joystick */
   private final Joystick joystick;

   /**
    * Create a new instance of AxisListSmoothModel
    * 
    * @param joystick
    *           Joystick
    */
   public AxisListSmoothModel(final Joystick joystick)
   {
      if(joystick == null)
      {
         throw new NullPointerException("joystick musn't be null");
      }

      this.joystick = joystick;
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getElement(int)
    */
   @Override
   public Axis getElement(final int index)
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getNumberOfElement()
    */
   @Override
   public int getNumberOfElement()
   {
      return this.joystick.getNumberOfAxis();
   }

   /**
    * Register model listener <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to register
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#registerModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void registerModelListener(final JHelpListSmoothModelListener<Axis> listener)
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#unregisterModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void unregisterModelListener(final JHelpListSmoothModelListener<Axis> listener)
   {
   }
}