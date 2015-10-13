package jhelp.linux.joystick;

/**
 * Represents a joystick button
 * 
 * @author JHelp
 */
public class Button
{
   /** Indicates if button is down (true) or up (false) */
   private boolean        down;
   /** Button ID */
   private final int      id;
   /** Joystick where lies the button */
   private final Joystick joystick;
   /** Button name */
   private String         name;

   /**
    * Create a new instance of Button
    * 
    * @param id
    *           Button ID
    * @param joystick
    *           Joystick where lies the button
    */
   Button(final int id, final Joystick joystick)
   {
      this.id = id;
      this.joystick = joystick;
      this.name = "BUTTON_" + id;
   }

   /**
    * Button ID
    * 
    * @return Button ID
    */
   public int getID()
   {
      return this.id;
   }

   /**
    * Joystick where the button is
    * 
    * @return Joystick where the button is
    */
   public Joystick getJoystick()
   {
      return this.joystick;
   }

   /**
    * Button name
    * 
    * @return Button name
    */
   public String getName()
   {
      return this.name;
   }

   /**
    * Button type
    * 
    * @return Button type
    */
   public ButtonType getType()
   {
      return ButtonType.obtainType(this);
   }

   /**
    * Indicates if button is down
    * 
    * @return {@code true} if button is down
    */
   public boolean isDown()
   {
      return this.down;
   }

   /**
    * Change button down status
    * 
    * @param down
    *           New down status
    */
   public void setDown(final boolean down)
   {
      this.down = down;
   }

   /**
    * Change button name
    * 
    * @param name
    *           New button name
    */
   public void setName(final String name)
   {
      if(name == null)
      {
         this.name = "BUTTON_" + this.id;
      }
      else
      {
         this.name = name;
      }
   }
}