package jhelp.linux.joystick;

/**
 * Represents a dialog axis
 * 
 * @author JHelp
 */
public class Axis
      implements Comparable<Axis>
{
   /** Axis ID */
   private final int      id;
   /** Joystick where axis is */
   private final Joystick joystick;
   /** Axis name */
   private String         name;
   /** Axis value */
   private double         value;

   /**
    * Create a new instance of Axis
    * 
    * @param id
    *           Axis ID
    * @param joystick
    *           Joystick where axis is
    */
   Axis(final int id, final Joystick joystick)
   {
      this.id = id;
      this.joystick = joystick;
      this.name = "AXIS_" + id;
   }

   /**
    * Compare to an other axis.<br>
    * It returns :
    * <table border=0>
    * <tr>
    * <th>&lt; 0</th>
    * <td>:</td>
    * <td>If this axis before given one</td>
    * </tr>
    * <tr>
    * <th>0</th>
    * <td>:</td>
    * <td>If this axis equals given one</td>
    * </tr>
    * <tr>
    * <th>&gt; 0</th>
    * <td>:</td>
    * <td>If this axis after given one</td>
    * </tr>
    * </table>
    * <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param axis
    *           Axis to compare with
    * @return Comparison result
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(final Axis axis)
   {
      return this.name.compareTo(axis.name);
   }

   /**
    * Indicates if object equals to this Axis <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param object
    *           Object to compare
    * @return {@code true} if object equals to this Axis
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object object)
   {
      if(object == this)
      {
         return true;
      }

      if(object == null)
      {
         return false;
      }

      if((object instanceof Axis) == false)
      {
         return false;
      }

      return this.name.equals(((Axis) object).name);
   }

   /**
    * Axis ID
    * 
    * @return Axis ID
    */
   public int getID()
   {
      return this.id;
   }

   /**
    * Joystick where axis is
    * 
    * @return Joystick where axis is
    */
   public Joystick getJoystick()
   {
      return this.joystick;
   }

   /**
    * Axis name
    * 
    * @return Axis name
    */
   public String getName()
   {
      return this.name;
   }

   /**
    * Axis type
    * 
    * @return Axis type
    */
   public AxisType getType()
   {
      return AxisType.obtainType(this);
   }

   /**
    * Axis value
    * 
    * @return Axis value
    */
   public double getValue()
   {
      return this.value;
   }

   /**
    * Hash code <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Hash code
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      return this.name.hashCode();
   }

   /**
    * Change axis name
    * 
    * @param name
    *           New name. Use {@code null} for default name
    */
   public void setName(final String name)
   {
      if(name == null)
      {
         this.name = "AXIS_" + this.id;
      }
      else
      {
         this.name = name;
      }
   }

   /**
    * change value
    * 
    * @param value
    *           New value
    */
   public void setValue(final double value)
   {
      this.value = value;
   }
}