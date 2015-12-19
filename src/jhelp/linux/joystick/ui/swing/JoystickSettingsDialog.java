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
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.resources.JoystickResources;
import jhelp.util.gui.UtilGUI;

/**
 * Dialog for joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettingsDialog
      extends JDialog
{
   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    */
   public JoystickSettingsDialog(final Joystick joystick)
   {
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Dialog owner
    */
   public JoystickSettingsDialog(final Joystick joystick, final Dialog owner)
   {
      super(owner);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Dialog owner
    * @param modal
    *           Indicates if dialog is modal
    */
   public JoystickSettingsDialog(final Joystick joystick, final Dialog owner, final boolean modal)
   {
      super(owner, modal);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Dialog owner
    * @param title
    *           Dialog title
    */
   public JoystickSettingsDialog(final Joystick joystick, final Dialog owner, final String title)
   {
      super(owner, title);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Dialog owner
    * @param title
    *           Dialog title
    * @param modal
    *           Indicates if dialog is modal
    */
   public JoystickSettingsDialog(final Joystick joystick, final Dialog owner, final String title, final boolean modal)
   {
      super(owner, title, modal);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Dialog owner
    * @param title
    *           Dialog title
    * @param modal
    *           Indicates if dialog is modal
    * @param gc
    *           Graphics configuration
    */
   public JoystickSettingsDialog(final Joystick joystick, final Dialog owner, final String title, final boolean modal, final GraphicsConfiguration gc)
   {
      super(owner, title, modal, gc);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Frame owner
    */
   public JoystickSettingsDialog(final Joystick joystick, final Frame owner)
   {
      super(owner);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Frame owner
    * @param modal
    *           Indicates if dialog is modal
    */
   public JoystickSettingsDialog(final Joystick joystick, final Frame owner, final boolean modal)
   {
      super(owner, modal);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Frame owner
    * @param title
    *           Dialog title
    */
   public JoystickSettingsDialog(final Joystick joystick, final Frame owner, final String title)
   {
      super(owner, title);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Frame owner
    * @param title
    *           Dialog title
    * @param modal
    *           Indicates if dialog is modal
    */
   public JoystickSettingsDialog(final Joystick joystick, final Frame owner, final String title, final boolean modal)
   {
      super(owner, title, modal);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Frame owner
    * @param title
    *           Dialog title
    * @param modal
    *           Indicates if dialog is modal
    * @param gc
    *           Graphics configuration
    */
   public JoystickSettingsDialog(final Joystick joystick, final Frame owner, final String title, final boolean modal, final GraphicsConfiguration gc)
   {
      super(owner, title, modal, gc);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Window owner
    */
   public JoystickSettingsDialog(final Joystick joystick, final Window owner)
   {
      super(owner);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Window owner
    * @param modalityType
    *           Modality type
    */
   public JoystickSettingsDialog(final Joystick joystick, final Window owner, final ModalityType modalityType)
   {
      super(owner, modalityType);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Window owner
    * @param title
    *           Dialog title
    */
   public JoystickSettingsDialog(final Joystick joystick, final Window owner, final String title)
   {
      super(owner, title);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Window owner
    * @param title
    *           Dialog title
    * @param modalityType
    *           Modality type
    */
   public JoystickSettingsDialog(final Joystick joystick, final Window owner, final String title, final ModalityType modalityType)
   {
      super(owner, title, modalityType);
      this.initialize(joystick);
   }

   /**
    * Create a new instance of JoystickSettingsDialog
    * 
    * @param joystick
    *           Joystick to edit settings
    * @param owner
    *           Window owner
    * @param title
    *           Dialog title
    * @param modalityType
    *           Modality type
    * @param gc
    *           Graphics configuration
    */
   public JoystickSettingsDialog(final Joystick joystick, final Window owner, final String title, final ModalityType modalityType,
         final GraphicsConfiguration gc)
   {
      super(owner, title, modalityType, gc);
      this.initialize(joystick);
   }

   /**
    * Initialize Dialog for joystick to edit settings
    * 
    * @param joystick
    *           Joystick to edit settings
    */
   private void initialize(final Joystick joystick)
   {
      this.setTitle(joystick.getName());
      this.setIconImage(JoystickResources.IMAGE_JOYSTICK);
      final JoystickSettingsComponentSwing joystickSettingsComponentSwing = new JoystickSettingsComponentSwing(joystick);
      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(joystickSettingsComponentSwing, BorderLayout.CENTER);
      UtilGUI.packedSize(this);
      UtilGUI.centerOnScreen(this);
   }
}