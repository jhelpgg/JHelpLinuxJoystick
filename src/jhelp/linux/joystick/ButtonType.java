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
package jhelp.linux.joystick;

/**
 * Button types.<br>
 * For work properly, remember to give button name, one of this names. The names <b>"BUTTON_?"</b> and <b>"UNKNOWN"</b> are here
 * when button not named, prefer use an other name.
 * 
 * @author JHelp
 */
public enum ButtonType
{
   /** Button on "first" axis */
   AXIS_BUTTON,
   /** Button on "second" axis */
   AXIS_BUTTON_SECOND,
   /** Automatic button name 0 */
   BUTTON_0,
   /** Automatic button name 1 */
   BUTTON_1,
   /** Automatic button name 10 */
   BUTTON_10,
   /** Automatic button name 11 */
   BUTTON_11,
   /** Automatic button name 12 */
   BUTTON_12,
   /** Automatic button name 13 */
   BUTTON_13,
   /** Automatic button name 14 */
   BUTTON_14,
   /** Automatic button name 15 */
   BUTTON_15,
   /** Automatic button name 16 */
   BUTTON_16,
   /** Automatic button name 17 */
   BUTTON_17,
   /** Automatic button name 18 */
   BUTTON_18,
   /** Automatic button name 19 */
   BUTTON_19,
   /** Automatic button name 2 */
   BUTTON_2,
   /** Automatic button name 20 */
   BUTTON_20,
   /** Automatic button name 21 */
   BUTTON_21,
   /** Automatic button name 22 */
   BUTTON_22,
   /** Automatic button name 23 */
   BUTTON_23,
   /** Automatic button name 24 */
   BUTTON_24,
   /** Automatic button name 25 */
   BUTTON_25,
   /** Automatic button name 26 */
   BUTTON_26,
   /** Automatic button name 27 */
   BUTTON_27,
   /** Automatic button name 28 */
   BUTTON_28,
   /** Automatic button name 29 */
   BUTTON_29,
   /** Automatic button name 3 */
   BUTTON_3,
   /** Automatic button name 30 */
   BUTTON_30,
   /** Automatic button name 31 */
   BUTTON_31,
   /** Automatic button name 32 */
   BUTTON_32,
   /** Automatic button name 4 */
   BUTTON_4,
   /** Automatic button name 5 */
   BUTTON_5,
   /** Automatic button name 6 */
   BUTTON_6,
   /** Automatic button name 7 */
   BUTTON_7,
   /** Automatic button name 8 */
   BUTTON_8,
   /** Automatic button name 9 */
   BUTTON_9,
   /** Not directional button in "down" place */
   BUTTON_DOWN,
   /** Not directional button in "left" place */
   BUTTON_LEFT,
   /** Not directional button in "right" place */
   BUTTON_RIGHT,
   /** Not directional button in "up" place */
   BUTTON_UP,
   /** Directional button down */
   DOWN,
   /** Directional button left */
   LEFT,
   /** Directional button right */
   RIGHT,
   /** <b>"Select"</b> button */
   SELECT,
   /** Button on side at left and down */
   SIDE_LEFT_DOWN,
   /** Button on side at left and up */
   SIDE_LEFT_UP,
   /** Button on side at right and down */
   SIDE_RIGHT_DOWN,
   /** Button on side at right and up */
   SIDE_RIGHT_UP,
   /** "Special" button */
   SPECIAL,
   /** Start button */
   START,
   /** Unknown button */
   UNKNOWN,
   /** Directional button up */
   UP;

   /**
    * Obtain button type of a button
    * 
    * @param button
    *           Button to have its type
    * @return Button type
    */
   public static ButtonType obtainType(final Button button)
   {
      ButtonType type = null;

      try
      {
         type = ButtonType.valueOf(button.getName());

         return type;
      }
      catch(final Exception exception)
      {
         return UNKNOWN;
      }
   }
}