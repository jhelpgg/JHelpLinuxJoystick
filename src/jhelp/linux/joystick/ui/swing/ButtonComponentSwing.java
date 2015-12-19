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

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.linux.joystick.Axis;
import jhelp.linux.joystick.Button;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickListener;
import jhelp.util.debug.Debug;

/**
 * Component swing for edit joystick button
 * 
 * @author JHelp
 */
public class ButtonComponentSwing
      extends JPanel
      implements JoystickListener, InputMethodListener
{
   /** Joystick button */
   private Button           button;
   /** Edit text for change axis name */
   private final JTextField nameEditText;
   /** Text to draw button state (down or up) */
   private final JLabel     stateText;

   /**
    * Create a new instance of ButtonComponentSwing
    * 
    * @param button
    *           Button to draw
    */
   public ButtonComponentSwing(final Button button)
   {
      super(new GridLayout(2, 1));

      this.button = button;
      this.button.getJoystick().registerJoystickListener(this);

      this.nameEditText = new JTextField(16);
      this.nameEditText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
      this.nameEditText.setForeground(Color.BLACK);
      this.nameEditText.setBackground(Color.WHITE);
      this.nameEditText.setCaretColor(Color.RED);
      this.nameEditText.addInputMethodListener(this);

      this.stateText = new JLabel("UP", SwingConstants.CENTER);
      this.stateText.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());

      this.add(this.nameEditText);
      this.add(this.stateText);

      this.update();
   }

   /**
    * Called when caret changed <br>
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
      this.button.setName(this.nameEditText.getText());
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