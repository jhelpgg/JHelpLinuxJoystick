package jhelp.linux.joystick.ui.smooth;

import jhelp.gui.smooth.event.JHelpListSmoothModelListener;
import jhelp.gui.smooth.model.JHelpListSmoothModel;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickManager;

/**
 * Smooth list model of current joysticks plugged
 * 
 * @author JHelp
 */
public class JoystickListSmoothModel
      implements JHelpListSmoothModel<Joystick>
{
   /**
    * Create a new instance of JoystickListSmoothModel
    */
   public JoystickListSmoothModel()
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
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getElement(int)
    */
   @Override
   public Joystick getElement(final int index)
   {
      return JoystickManager.JOYSTICK_MANAGER.obtainJoystick(index);
   }

   /**
    * Number of joysticks plugged <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Number of joysticks plugged
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getNumberOfElement()
    */
   @Override
   public int getNumberOfElement()
   {
      return JoystickManager.JOYSTICK_MANAGER.numberOfJoysticks();
   }

   /**
    * Register listener of model changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to register
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#registerModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void registerModelListener(final JHelpListSmoothModelListener<Joystick> listener)
   {
   }

   /**
    * Unregister listener of model changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to unregister
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#unregisterModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public void unregisterModelListener(final JHelpListSmoothModelListener<Joystick> listener)
   {
   }
}