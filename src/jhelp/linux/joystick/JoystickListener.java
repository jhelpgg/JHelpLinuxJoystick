package jhelp.linux.joystick;

/**
 * Listener of joystick actions
 * 
 * @author JHelp
 */
public interface JoystickListener
{
   /**
    * Called when a joystick axis is activated
    * 
    * @param joystick
    *           Joystick where is the axis
    * @param axis
    *           Axis moved
    */
   public void joystickAxisActivated(Joystick joystick, Axis axis);

   /**
    * Called when a joystick axis is released
    * 
    * @param joystick
    *           Joystick where is the axis
    * @param axis
    *           Axis released
    */
   public void joystickAxisReleased(Joystick joystick, Axis axis);

   /**
    * Called when a joystick button is pressed
    * 
    * @param joystick
    *           Joystick where is the button
    * @param button
    *           Button pressed
    */
   public void joystickButtonPressed(Joystick joystick, Button button);

   /**
    * Called when a joystick button is released
    * 
    * @param joystick
    *           Joystick where is the button
    * @param button
    *           Button released
    */
   public void joystickButtonReleased(Joystick joystick, Button button);
}