package jhelp.linux.joystick.ui.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import jhelp.gui.JHelpLabel;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickListener;
import jhelp.util.debug.Debug;
import jhelp.util.math.UtilMath;

/**
 * Component swing for joystick axis
 * 
 * @author JHelp
 */
public class AxisComponentSwing
      extends JPanel
      implements JoystickListener, InputMethodListener
{
   /** Off color */
   private static final Color OFF = new Color(JHelpConstantsSmooth.COLOR_RED_0400);
   /** On color */
   private static final Color ON  = new Color(JHelpConstantsSmooth.COLOR_GREEN_0400);

   /** Joystick axis */
   private Axis               axis;
   /** Edit text for change axis name */
   private final JTextField   nameEditText;
   /** Negative value text */
   private final JHelpLabel   negativeText;
   /** Positive value text */
   private final JHelpLabel   positiveText;

   /**
    * Create a new instance of AxisComponentSwing
    * 
    * @param axis
    *           Joystick axis
    */
   public AxisComponentSwing(final Axis axis)
   {
      super(new GridBagLayout());

      if(axis == null)
      {
         throw new NullPointerException("axis musn't be null");
      }

      this.axis = axis;
      this.axis.getJoystick().registerJoystickListener(this);

      this.nameEditText = new JTextField(16);
      this.nameEditText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
      this.nameEditText.setForeground(Color.BLACK);
      this.nameEditText.setBackground(Color.WHITE);
      this.nameEditText.setCaretColor(Color.RED);
      this.nameEditText.addInputMethodListener(this);

      this.negativeText = new JHelpLabel("-");
      this.negativeText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
      this.negativeText.setForeground(Color.BLACK);
      this.negativeText.setBackground(AxisComponentSwing.OFF);

      this.positiveText = new JHelpLabel("+");
      this.positiveText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
      this.positiveText.setForeground(Color.BLACK);
      this.positiveText.setBackground(AxisComponentSwing.OFF);

      this.add(this.nameEditText, this.createConstraints(0, 0, 2, 1));
      this.add(this.positiveText, this.createConstraints(0, 1, 1, 1));
      this.add(this.negativeText, this.createConstraints(1, 1, 1, 1));

      this.update();
   }

   /**
    * Create layout constraints
    * 
    * @param x
    *           X location
    * @param y
    *           Y location
    * @param width
    *           Cell width
    * @param height
    *           Cell height
    * @return Constraints
    */
   private GridBagConstraints createConstraints(final int x, final int y, final int width, final int height)
   {
      final GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = x;
      gridBagConstraints.gridy = y;
      gridBagConstraints.gridwidth = width;
      gridBagConstraints.gridheight = height;
      gridBagConstraints.weightx = width;
      gridBagConstraints.weighty = height;
      gridBagConstraints.fill = GridBagConstraints.BOTH;
      return gridBagConstraints;
   }

   /**
    * Called when caret position changed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param event
    *           Event description
    * @see java.awt.event.InputMethodListener#caretPositionChanged(java.awt.event.InputMethodEvent)
    */
   @Override
   public void caretPositionChanged(final InputMethodEvent event)
   {
   }

   /**
    * Called when input method changed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param event
    *           Event description
    * @see java.awt.event.InputMethodListener#inputMethodTextChanged(java.awt.event.InputMethodEvent)
    */
   @Override
   public void inputMethodTextChanged(final InputMethodEvent event)
   {
      // {@todo} TODO Implements inputMethodTextChanged
      Debug.printTodo("Implements inputMethodTextChanged");
      this.axis.setName(this.nameEditText.getText());
   }

   /**
    * Called when joystick axis activated <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Joystick axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisActivated(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisActivated(final Joystick joystick, final Axis axis)
   {
      if(axis == this.axis)
      {
         this.update();
      }
   }

   /**
    * Called when joystick axis released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Joystick axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisReleased(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisReleased(final Joystick joystick, final Axis axis)
   {
      if(axis == this.axis)
      {
         this.update();
      }
   }

   /**
    * Called when joystick button pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Joystick Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonPressed(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonPressed(final Joystick joystick, final Button button)
   {
   }

   /**
    * Called when joystick button released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Joystick Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonReleased(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonReleased(final Joystick joystick, final Button button)
   {
   }

   /**
    * Change managed axis
    * 
    * @param axis
    *           New axis
    */
   public void setAxis(final Axis axis)
   {
      if(axis == null)
      {
         throw new NullPointerException("axis musn't be null");
      }

      this.axis.getJoystick().unregisterJoystickListener(this);
      this.axis = axis;
      this.axis.getJoystick().registerJoystickListener(this);
      this.update();
   }

   /**
    * Update component
    */
   public void update()
   {
      this.nameEditText.setText(this.axis.getName());
      final int sign = UtilMath.sign(this.axis.getValue());

      if(sign < 0)
      {
         this.positiveText.setBackground(AxisComponentSwing.OFF);
         this.negativeText.setBackground(AxisComponentSwing.ON);
      }
      else if(sign > 0)
      {
         this.positiveText.setBackground(AxisComponentSwing.ON);
         this.negativeText.setBackground(AxisComponentSwing.OFF);
      }
      else
      {
         this.positiveText.setBackground(AxisComponentSwing.OFF);
         this.negativeText.setBackground(AxisComponentSwing.OFF);
      }
   }
}