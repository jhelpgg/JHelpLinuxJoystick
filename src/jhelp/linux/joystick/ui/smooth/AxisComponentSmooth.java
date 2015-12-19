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

import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.gui.smooth.JHelpEditTextSmooth;
import jhelp.gui.smooth.JHelpLabelTextSmooth;
import jhelp.gui.smooth.JHelpPanelSmooth;
import jhelp.gui.smooth.event.SmoothEditTextListener;
import jhelp.gui.smooth.layout.JHelpTableConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpTableLayoutSmooth;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickListener;
import jhelp.util.math.UtilMath;

/**
 * Component smooth for joystick axis
 * 
 * @author JHelp
 */
public class AxisComponentSmooth
      extends JHelpPanelSmooth
      implements SmoothEditTextListener, JoystickListener
{
   /** Joystick axis */
   private Axis                       axis;
   /** Edit text for change axis name */
   private final JHelpEditTextSmooth  nameEditText;
   /** Negative value text */
   private final JHelpLabelTextSmooth negativeText;
   /** Positive value text */
   private final JHelpLabelTextSmooth positiveText;

   /**
    * Create a new instance of AxisComponentSmooth
    * 
    * @param axis
    *           Joystick axis
    */
   public AxisComponentSmooth(final Axis axis)
   {
      super(new JHelpTableLayoutSmooth());

      if(axis == null)
      {
         throw new NullPointerException("axis musn't be null");
      }

      this.axis = axis;
      this.axis.getJoystick().registerJoystickListener(this);
      this.nameEditText = new JHelpEditTextSmooth(JHelpConstantsSmooth.FONT_DISPLAY_1, 16, JHelpConstantsSmooth.COLOR_BLACK,
            JHelpConstantsSmooth.COLOR_RED_0400);
      this.positiveText = new JHelpLabelTextSmooth("+", JHelpConstantsSmooth.FONT_DISPLAY_1);
      this.negativeText = new JHelpLabelTextSmooth("-", JHelpConstantsSmooth.FONT_DISPLAY_1);

      this.addComponent(this.nameEditText, new JHelpTableConstraintsSmooth(0, 0, 2, 1));
      this.addComponent(this.positiveText, new JHelpTableConstraintsSmooth(0, 1, 1, 1));
      this.addComponent(this.negativeText, new JHelpTableConstraintsSmooth(1, 1, 1, 1));

      this.nameEditText.registerEditTextListener(this);

      this.update();
   }

   /**
    * Called when press enter in edit text <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param editTextSmooth
    *           Edit text source
    * @see jhelp.gui.smooth.event.SmoothEditTextListener#editTextEnterTyped(jhelp.gui.smooth.JHelpEditTextSmooth)
    */
   @Override
   public void editTextEnterTyped(final JHelpEditTextSmooth editTextSmooth)
   {
      this.axis.setName(editTextSmooth.getText());
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
         this.positiveText.setBackground(JHelpConstantsSmooth.COLOR_RED_0400);
         this.negativeText.setBackground(JHelpConstantsSmooth.COLOR_GREEN_0400);
      }
      else if(sign > 0)
      {
         this.positiveText.setBackground(JHelpConstantsSmooth.COLOR_GREEN_0400);
         this.negativeText.setBackground(JHelpConstantsSmooth.COLOR_RED_0400);
      }
      else
      {
         this.positiveText.setBackground(JHelpConstantsSmooth.COLOR_RED_0400);
         this.negativeText.setBackground(JHelpConstantsSmooth.COLOR_RED_0400);
      }
   }
}