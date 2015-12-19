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

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import jhelp.linux.joystick.Button;
import jhelp.util.list.HashInt;

/**
 * Swing renderer of button
 * 
 * @author JHelp
 */
public class ButtonListSwingRenderer
      implements ListCellRenderer<Button>
{
   /** Created components */
   private final HashInt<ButtonComponentSwing> components;

   /**
    * Create a new instance of ButtonListSwingRenderer
    */
   public ButtonListSwingRenderer()
   {
      this.components = new HashInt<ButtonComponentSwing>();
   }

   /**
    * Create component for draw button <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param list
    *           List parent
    * @param value
    *           Button to draw
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
   public Component getListCellRendererComponent(final JList<? extends Button> list, final Button value, final int index, final boolean isSelected,
         final boolean cellHasFocus)
   {
      ButtonComponentSwing buttonComponentSwing = this.components.get(index);

      if(buttonComponentSwing == null)
      {
         buttonComponentSwing = new ButtonComponentSwing(value);
         this.components.put(index, buttonComponentSwing);
      }
      else
      {
         buttonComponentSwing.setButton(value);
      }

      return buttonComponentSwing;
   }
}