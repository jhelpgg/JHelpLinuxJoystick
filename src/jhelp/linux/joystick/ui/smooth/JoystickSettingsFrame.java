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

import jhelp.gui.smooth.DialogDecsription;
import jhelp.gui.smooth.JHelpFrameSmooth;
import jhelp.gui.smooth.layout.JHelpBorderConstraintsSmooth;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.resources.JoystickResources;

/**
 * Smooth frame for edit joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettingsFrame
      extends JHelpFrameSmooth
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
    *           Indicates if its a stand alone frame (true) or launched by an other one (false)
    */
   public JoystickSettingsFrame(final Joystick joystick, final boolean standAlone)
   {
      super(joystick.getName());

      this.setIconImage(JoystickResources.IMAGE_JOYSTICK);
      this.setDisposeOnClose(standAlone);
      this.setExitAllOnClose(standAlone);

      this.addComponent(new JoystickSettingsComponentSmooth(joystick), JHelpBorderConstraintsSmooth.CENTER);
   }

   /**
    * Created a dialog <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param dialogID
    *           Dialog ID
    * @return Created dialog OR {@code null} if no dialog for given ID
    * @see jhelp.gui.smooth.JHelpFrameSmooth#createDialog(int)
    */
   @Override
   protected DialogDecsription createDialog(final int dialogID)
   {
      return null;
   }
}