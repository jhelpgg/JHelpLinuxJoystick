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

import java.awt.BorderLayout;

import jhelp.gui.JHelpFrame;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.resources.JoystickResources;
import jhelp.util.gui.UtilGUI;

/**
 * Frame for show joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettingsFrame
      extends JHelpFrame
{
   /**
    * Create a new instance of JoystickSettingsFrame
    * 
    * @param joystick
    *           Joystick to edit
    */
   public JoystickSettingsFrame(final Joystick joystick)
   {
      this(joystick, true);
   }

   /**
    * Create a new instance of JoystickSettingsFrame
    * 
    * @param joystick
    *           Joystick to edit
    * @param standAlone
    *           Indicates if frame is stand alone
    */
   public JoystickSettingsFrame(final Joystick joystick, final boolean standAlone)
   {
      super(joystick.getName(), false, true);

      final JoystickSettingsComponentSwing joystickSettingsComponentSwing = new JoystickSettingsComponentSwing(joystick);
      this.setLayout(new BorderLayout());
      this.add(joystickSettingsComponentSwing, BorderLayout.CENTER);

      this.setIconImage(JoystickResources.IMAGE_JOYSTICK);
      this.setExitAllOnClose(standAlone);

      UtilGUI.packedSize(this);
      UtilGUI.centerOnScreen(this);
   }

   /**
    * Add listeners <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#addListeners()
    */
   @Override
   protected void addListeners()
   {
   }

   /**
    * Create components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#createComponents()
    */
   @Override
   protected void createComponents()
   {
   }

   /**
    * Layout components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#layoutComponents()
    */
   @Override
   protected void layoutComponents()
   {
   }
}