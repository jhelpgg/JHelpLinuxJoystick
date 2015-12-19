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
 * Axis types.<br>
 * For work properly, remember to give axis name, one of this names. The names <b>"AXIS_?"</b> and <b>"UNKNOWN"</b> are here
 * when axis not named, prefer use an other name.
 * 
 * @author JHelp
 */
public enum AxisType
{
   /** Generic axis 0 */
   AXIS_0,
   /** Generic axis 1 */
   AXIS_1,
   /** Generic axis 10 */
   AXIS_10,
   /** Generic axis 11 */
   AXIS_11,
   /** Generic axis 12 */
   AXIS_12,
   /** Generic axis 13 */
   AXIS_13,
   /** Generic axis 14 */
   AXIS_14,
   /** Generic axis 15 */
   AXIS_15,
   /** Generic axis 16 */
   AXIS_16,
   /** Generic axis 17 */
   AXIS_17,
   /** Generic axis 18 */
   AXIS_18,
   /** Generic axis 19 */
   AXIS_19,
   /** Generic axis 2 */
   AXIS_2,
   /** Generic axis 20 */
   AXIS_20,
   /** Generic axis 21 */
   AXIS_21,
   /** Generic axis 22 */
   AXIS_22,
   /** Generic axis 23 */
   AXIS_23,
   /** Generic axis 24 */
   AXIS_24,
   /** Generic axis 25 */
   AXIS_25,
   /** Generic axis 26 */
   AXIS_26,
   /** Generic axis 27 */
   AXIS_27,
   /** Generic axis 28 */
   AXIS_28,
   /** Generic axis 29 */
   AXIS_29,
   /** Generic axis 3 */
   AXIS_3,
   /** Generic axis 30 */
   AXIS_30,
   /** Generic axis 31 */
   AXIS_31,
   /** Generic axis 32 */
   AXIS_32,
   /** Generic axis 4 */
   AXIS_4,
   /** Generic axis 5 */
   AXIS_5,
   /** Generic axis 6 */
   AXIS_6,
   /** Generic axis 7 */
   AXIS_7,
   /** Generic axis 8 */
   AXIS_8,
   /** Generic axis 9 */
   AXIS_9,
   /** Unknown axis */
   UNKNOWN,
   /** Axis of X direction */
   X_AXIS,
   /** Axis of "second" X direction */
   X_AXIS_SECOND,
   /** Axis of X roll */
   X_ROLL,
   /** Axis of Y direction */
   Y_AXIS,
   /** Axis of "second" Y direction */
   Y_AXIS_SECOND,
   /** Axis of Y roll */
   Y_ROLL;

   /**
    * Obtain axis type of an axis
    * 
    * @param axis
    *           Axis to get its type
    * @return Axis type
    */
   public static AxisType obtainType(final Axis axis)
   {
      AxisType type = null;

      try
      {
         type = AxisType.valueOf(axis.getName());

         if(type == null)
         {
            return UNKNOWN;
         }

         return type;
      }
      catch(final Exception exception)
      {
         return UNKNOWN;
      }
   }
}