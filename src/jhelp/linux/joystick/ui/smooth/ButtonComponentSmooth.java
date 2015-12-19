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

/**
 * Smooth component for joystick button
 * 
 * @author JHelp
 */
public class ButtonComponentSmooth
      extends JHelpPanelSmooth
      implements SmoothEditTextListener, JoystickListener
{
   /** Joystick button */
   private Button                     button;
   /** Edit text for change button name */
   private final JHelpEditTextSmooth  nameEditText;
   /** Text to draw button state (down or up) */
   private final JHelpLabelTextSmooth stateText;

   /**
    * Create a new instance of ButtonComponentSmooth
    * 
    * @param button
    *           Button to draw
    */
   public ButtonComponentSmooth(final Button button)
   {
      super(new JHelpTableLayoutSmooth());

      if(button == null)
      {
         throw new NullPointerException("button musn't be null");
      }

      this.button = button;
      this.button.getJoystick().registerJoystickListener(this);
      this.nameEditText = new JHelpEditTextSmooth(JHelpConstantsSmooth.FONT_DISPLAY_1, 16, JHelpConstantsSmooth.COLOR_BLACK,
            JHelpConstantsSmooth.COLOR_RED_0400);
      this.stateText = new JHelpLabelTextSmooth("UP", JHelpConstantsSmooth.FONT_DISPLAY_1);

      this.addComponent(this.nameEditText, new JHelpTableConstraintsSmooth(0, 0, 1, 1));
      this.addComponent(this.stateText, new JHelpTableConstraintsSmooth(0, 1, 1, 1));

      this.nameEditText.registerEditTextListener(this);

      this.update();
   }

   /**
    * Called when enter type inside edit text <br>
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
      this.button.setName(editTextSmooth.getText());
   }

   /**
    * Called when axis activated <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisActivated(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisActivated(final Joystick joystick, final Axis axis)
   {
   }

   /**
    * Called when axis released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param axis
    *           Axis
    * @see jhelp.linux.joystick.JoystickListener#joystickAxisReleased(jhelp.linux.joystick.Joystick, jhelp.linux.joystick.Axis)
    */
   @Override
   public void joystickAxisReleased(final Joystick joystick, final Axis axis)
   {
   }

   /**
    * Called when button pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonPressed(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonPressed(final Joystick joystick, final Button button)
   {
      if(button == this.button)
      {
         this.update();
      }
   }

   /**
    * Called when button released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param joystick
    *           Joystick
    * @param button
    *           Button
    * @see jhelp.linux.joystick.JoystickListener#joystickButtonReleased(jhelp.linux.joystick.Joystick,
    *      jhelp.linux.joystick.Button)
    */
   @Override
   public void joystickButtonReleased(final Joystick joystick, final Button button)
   {
      if(button == this.button)
      {
         this.update();
      }
   }

   /**
    * Change the button
    * 
    * @param button
    *           New button
    */
   public void setButton(final Button button)
   {
      if(button == null)
      {
         throw new NullPointerException("button musn't be null");
      }

      this.button.getJoystick().unregisterJoystickListener(this);
      this.button = button;
      this.button.getJoystick().registerJoystickListener(this);
      this.update();
   }

   /**
    * Update the component
    */
   public void update()
   {
      this.nameEditText.setText(this.button.getName());

      if(this.button.isDown() == true)
      {
         this.stateText.setText("DOWN");
      }
      else
      {
         this.stateText.setText("UP");
      }
   }
}