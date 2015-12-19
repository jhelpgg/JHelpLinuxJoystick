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
import jhelp.linux.joystick.Axis;

/**
 * Smooth renderer of axis
 * 
 * @author JHelp
 */
public class AxisListSmoothRenderer
      implements JHelpListSmoothRenderer<Axis>
{
   /**
    * Create a new instance of AxisListSmoothRenderer
    */
   public AxisListSmoothRenderer()
   {
   }

   /**
    * Create component for draw axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param element
    *           Axis to draw
    * @return Created component
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#createComponent(java.lang.Object)
    */
   @Override
   public JHelpComponentSmooth createComponent(final Axis element)
   {
      return new AxisComponentSmooth(element);
   }

   /**
    * Update component to draw axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param component
    *           component to update
    * @param element
    *           Axis to draw
    * @param indexInList
    *           Axis index
    * @param selected
    *           Indicates if selected
    * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#transformComponent(jhelp.gui.smooth.JHelpComponentSmooth,
    *      java.lang.Object, int, boolean)
    */
   @Override
   public void transformComponent(final JHelpComponentSmooth component, final Axis element, final int indexInList, final boolean selected)
   {
      ((AxisComponentSmooth) component).setAxis(element);
   }
}