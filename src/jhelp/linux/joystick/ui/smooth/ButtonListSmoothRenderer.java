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
import jhelp.gui.smooth.renderer.JHelpListSmoothRenderer;
import jhelp.linux.joystick.Button;

/**
 * Smooth button list renderer
 * 
 * @author JHelp
 */
public class ButtonListSmoothRenderer
      implements JHelpListSmoothRenderer<Button>
{
   /**
    * Create a new instance of ButtonListSmoothRenderer
    */
   public ButtonListSmoothRenderer()
   {
   }

   /**
    * Create component smooth for a joystick button <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param element
    *           Joystick button
    * @return Created component
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#createComponent(java.lang.Object)
    */
   @Override
   public JHelpComponentSmooth createComponent(final Button element)
   {
      return new ButtonComponentSmooth(element);
   }

   /**
    * Update component for a joystick button <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param component
    *           Component to update
    * @param element
    *           Joystick button
    * @param indexInList
    *           Joystick button index
    * @param selected
    *           Indicates if selected
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#transformComponent(jhelp.gui.smooth.JHelpComponentSmooth,
    *      java.lang.Object, int, boolean)
    */
   @Override
   public void transformComponent(final JHelpComponentSmooth component, final Button element, final int indexInList, final boolean selected)
   {
      ((ButtonComponentSmooth) component).setButton(element);
   }
}