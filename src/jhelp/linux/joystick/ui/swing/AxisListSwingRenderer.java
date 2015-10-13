package jhelp.linux.joystick.ui.swing;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import jhelp.linux.joystick.Axis;
import jhelp.util.list.HashInt;

/**
 * Swing renderer of axis
 * 
 * @author JHelp
 */
public class AxisListSwingRenderer
      implements ListCellRenderer<Axis>
{
   /** Created components */
   private final HashInt<AxisComponentSwing> components;

   /**
    * Create a new instance of AxisListSwingRenderer
    */
   public AxisListSwingRenderer()
   {
      this.components = new HashInt<AxisComponentSwing>();
   }

   /**
    * Create component for draw axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param list
    *           List parent
    * @param value
    *           Axis to draw
    * @param index
    *           Index in list
    * @param isSelected
    *           Indicates if selected
    * @param cellHasFocus
    *           Indicates if have focus
    * @return Created component
    * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
    */
   @Override
   public Component getListCellRendererComponent(final JList<? extends Axis> list, final Axis value, final int index, final boolean isSelected,
         final boolean cellHasFocus)
   {
      AxisComponentSwing axisComponentSwing = this.components.get(index);

      if(axisComponentSwing == null)
      {
         axisComponentSwing = new AxisComponentSwing(value);
         this.components.put(index, axisComponentSwing);
      }
      else
      {
         axisComponentSwing.setAxis(value);
      }

      return axisComponentSwing;
   }
}