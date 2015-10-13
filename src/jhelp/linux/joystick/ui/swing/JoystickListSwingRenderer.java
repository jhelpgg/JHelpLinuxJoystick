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