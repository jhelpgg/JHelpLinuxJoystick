package jhelp.linux.joystick.ui.swing;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jhelp.gui.JHelpFrame;
import jhelp.linux.joystick.Joystick;
import jhelp.linux.joystick.JoystickManager;
import jhelp.linux.joystick.resources.JoystickResources;

/**
 * Swing frame for choose joystick
 * 
 * @author JHelp
 */
public class JoystickChooserFrame
      extends JHelpFrame
      implements ListSelectionListener
{
   /** Joystick list */
   private JoystickListSwing joystickListSwing;

   /**
    * Create a new instance of JoystickChooserFrame
    */
   public JoystickChooserFrame()
   {
      super("Joystick chooser", true, true);
      this.setIconImage(JoystickResources.IMAGE_JOYSTICK);
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
      this.joystickListSwing.addListSelectionListener(this);
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
      this.joystickListSwing = new JoystickListSwing();
      this.joystickListSwing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
      this.setLayout(new BorderLayout());
      this.add(new JScrollPane(this.joystickListSwing), BorderLayout.CENTER);
   }

   /**
    * Called when list selection changed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param event
    *           Event description
    * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
    */
   @Override
   public void valueChanged(final ListSelectionEvent event)
   {
      if(event.getValueIsAdjusting() == true)
      {
         return;
      }

      final Joystick joystick = JoystickManager.JOYSTICK_MANAGER.obtainJoystick(event.getFirstIndex());
      final JoystickSettingsDialog joystickSettingsDialog = new JoystickSettingsDialog(joystick, this, true);
      joystickSettingsDialog.setVisible(true);

      this.joystickListSwing.removeListSelectionListener(this);
      this.joystickListSwing.clearSelection();
      this.joystickListSwing.addListSelectionListener(this);
   }
}