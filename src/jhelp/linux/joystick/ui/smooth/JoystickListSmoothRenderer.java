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

import jhelp.gui.smooth.JHelpComponentSmooth;
import jhelp.gui.smooth.JHelpLabelTextSmooth;
import jhelp.gui.smooth.renderer.JHelpListSmoothRenderer;
import jhelp.linux.joystick.Joystick;

/**
 * Smooth joystick list renderer
 * 
 * @author JHelp
 */
public class JoystickListSmoothRenderer
      implements JHelpListSmoothRenderer<Joystick>
{
   /**
    * Create a new instance of JoystickListSmoothRenderer
    */
   public JoystickListSmoothRenderer()
   {
   }

   /**
    * Create component for show a joystick <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param element
    *           Joystick to show
    * @return Created component
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#createComponent(java.lang.Object)
    */
   @Override
   public JHelpComponentSmooth createComponent(final Joystick element)
   {
      return new JHelpLabelTextSmooth(element.getName());
   }

   /**
    * Update component to show an joystick <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param component
    *           Component to update
    * @param element
    *           Joystick to show
    * @param indexInList
    *           Index of joystick
    * @param selected
    *           Indicates if selected
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#transformComponent(jhelp.gui.smooth.JHelpComponentSmooth,
    *      java.lang.Object, int, boolean)
    */
   @Override
   public void transformComponent(final JHelpComponentSmooth component, final Joystick element, final int indexInList, final boolean selected)
   {
      ((JHelpLabelTextSmooth) component).setText(element.getName());
   }
}