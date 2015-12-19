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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.DialogDecsription;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.gui.smooth.JHelpFrameSmooth;
import jhelp.gui.smooth.JHelpListSmooth;
import jhelp.gui.smooth.ShortCut;
import jhelp.gui.smooth.event.JHelpListSmoothSelectListener;
import jhelp.gui.smooth.layout.JHelpBorderConstraintsSmooth;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickManager;
import jhelp.linux.joystick.resources.JoystickResources;

/**
 * Smooth frame for choose a joystick
 * 
 * @author JHelp
 */
public class JoystickChooserFrame
      extends JHelpFrameSmooth
      implements JHelpListSmoothSelectListener<Joystick>
{
   /**
    * Action for close current dialog
    * 
    * @author JHelp
    */
   private class ActionCloseCurrentdialog
         extends GenericAction
   {
      /**
       * Create a new instance of ActionCloseCurrentdialog
       */
      ActionCloseCurrentdialog()
      {
         super("Close");
      }

      /**
       * Called when action triggered <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param actionEvent
       *           Event description
       * @see jhelp.gui.action.GenericAction#doActionPerformed(java.awt.event.ActionEvent)
       */
      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         JoystickChooserFrame.this.closeCurrentDialog();
      }
   }

   /**
    * Create a new instance of JoystickChooserFrame
    */
   public JoystickChooserFrame()
   {
      super("Joystick chooser");

      this.setIconImage(JoystickResources.IMAGE_JOYSTICK);
      final JoystickListSmooth joystickListSmooth = new JoystickListSmooth();
      joystickListSmooth.registerSelectListener(this);
      this.addComponent(joystickListSmooth, JHelpBorderConstraintsSmooth.CENTER);
      this.defineShortCut(new ShortCut(KeyEvent.VK_ESCAPE, false, false, false), new ActionCloseCurrentdialog());
   }

   /**
    * Create a dialog <br>
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
      return new DialogDecsription(DialogDecsription.CENTER_IN_PARENT, DialogDecsription.CENTER_IN_PARENT, new JoystickSettingsComponentSmooth(
            JoystickManager.JOYSTICK_MANAGER.obtainJoystick(dialogID)), 0xCAFEFACE, JHelpConstantsSmooth.COLOR_GREY_0400, ShadowLevel.FAR);
   }

   /**
    * Called when element selected in joystick list <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param list
    *           Joystick list
    * @param element
    *           Chosen joystick
    * @param index
    *           Joystick index
    * @see jhelp.gui.smooth.event.JHelpListSmoothSelectListener#elementSelected(jhelp.gui.smooth.JHelpListSmooth,
    *      java.lang.Object, int)
    */
   @Override
   public void elementSelected(final JHelpListSmooth<Joystick> list, final Joystick element, final int index)
   {
      this.showDialog(index);
   }
}