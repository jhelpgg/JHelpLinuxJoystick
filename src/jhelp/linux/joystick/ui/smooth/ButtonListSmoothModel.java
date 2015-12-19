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
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;

/**
 * Smooth joystick buttons list model
 * 
 * @author JHelp
 */
public class ButtonListSmoothModel
      implements JHelpListSmoothModel<Button>
{
   /** Joystick */
   private final Joystick joystick;

   /**
    * Create a new instance of ButtonListSmoothModel
    * 
    * @param joystick
    *           Joystick
    */
   public ButtonListSmoothModel(final Joystick joystick)
   {
      if(joystick == null)
      {
         throw new NullPointerException("joystick musn't be null");
      }

      this.joystick = joystick;
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getElement(int)
    */
   @Override
   public Button getElement(final int index)
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getNumberOfElement()
    */
   @Override
   public int getNumberOfElement()
   {
      return this.joystick.getNumberOfButtons();
   }

   /**
    * Register a model change listener <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to register
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#registerModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void registerModelListener(final JHelpListSmoothModelListener<Button> listener)
   {
   }

   /**
    * Unregister a model change listener <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to unregister
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#unregisterModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void unregisterModelListener(final JHelpListSmoothModelListener<Button> listener)
   {
   }
}