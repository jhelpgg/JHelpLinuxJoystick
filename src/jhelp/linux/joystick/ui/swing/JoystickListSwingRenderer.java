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

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.linux.joystick.Joystick;

/**
 * Renderer for list of joysticks
 * 
 * @author JHelp
 */
public class JoystickListSwingRenderer
      extends JLabel
      implements ListCellRenderer<Joystick>
{
   /**
    * Create a new instance of JoystickListSwingRenderer
    */
   public JoystickListSwingRenderer()
   {
      super("Joystick", SwingConstants.CENTER);
      this.setFont(JHelpConstantsSmooth.FONT_BODY_2.getFont());
   }

   /**
    * Obtain component to use for draw a joystick <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param list
    *           List parent
    * @param value
    *           Joystick to draw
    * @param index
    *           Joystick index
    * @param isSelected
    *           Indicates if selected
    * @param cellHasFocus
    *           Indicates if have the focus
    * @return Component to use
    * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
    */
   @Override
   public Component getListCellRendererComponent(final JList<? extends Joystick> list, final Joystick value, final int index, final boolean isSelected,
         final boolean cellHasFocus)
   {
      this.setText(value.getName());
      return this;
   }
}