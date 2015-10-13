package jhelp.linux.joystick.settings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jhelp.linux.joystick.Joystick;
import jhelp.util.list.HashInt;
import jhelp.util.xml.DynamicWriteXML;

/**
 * Joystick settings
 * 
 * @author JHelp
 */
public class JoystickSettings
{
   /** Joystick axis markup */
   static final String MARKUP_AXIS              = "Axis";
   /** Joystick button markup */
   static final String MARKUP_BUTTON            = "Button";
   /** Main joystick settings markup */
   static final String MARKUP_JOYSTICK_SETTINGS = "JoystickSettings";
   /** Button/axis ID */
   static final String PARAMETER_ID             = "id";
   /** Joystick/button/axis name */
   static final String PARAMETER_NAME           = "name";

   /**
    * Parse stream to have joystick settings
    * 
    * @param inputStream
    *           Stream to parse. Not close by this method
    * @return Joystick settings read
    * @throws IOException
    *            On reading issue
    */
   public static JoystickSettings parse(final InputStream inputStream) throws IOException
   {
      final JoystickSettings joystickSettings = new JoystickSettings();
      final JoystickSettingsParser joystickSettingsParser = new JoystickSettingsParser(joystickSettings);
      joystickSettingsParser.parse(inputStream);
      return joystickSettings;
   }

   /** Axis name */
   private final HashInt<String> axisName;
   /** Buttons name */
   private final HashInt<String> buttonsName;
   /** Joystick name */
   private String                joystickName;

   /**
    * Create a new instance of JoystickSettings
    */
   public JoystickSettings()
   {
      this.buttonsName = new HashInt<String>();
      this.axisName = new HashInt<String>();
   }

   /**
    * Create a new instance of JoystickSettings
    * 
    * @param joystick
    *           Joystick where get settings
    */
   public JoystickSettings(final Joystick joystick)
   {
      this();

      this.joystickName = joystick.getName();
      int number = joystick.getNumberOfButtons();

      for(int index = 0; index < number; index++)
      {
         this.buttonsName.put(index, joystick.getButton(index).getName());
      }

      number = joystick.getNumberOfAxis();

      for(int index = 0; index < number; index++)
      {
         this.axisName.put(index, joystick.getAxis(index).getName());
      }
   }

   /**
    * Obtain axis name
    * 
    * @param id
    *           Axis ID
    * @return Axis name
    */
   public String getAxisName(final int id)
   {
      return this.axisName.get(id);
   }

   /**
    * Obtain button name
    * 
    * @param id
    *           Button ID
    * @return Button name
    */
   public String getButtonName(final int id)
   {
      return this.buttonsName.get(id);
   }

   /**
    * Joystick name
    * 
    * @return Joystick name
    */
   public String getJoystickName()
   {
      return this.joystickName;
   }

   /**
    * Defines an axis name
    * 
    * @param id
    *           Axis ID
    * @param name
    *           New name. Use {@code null} for remove the axis
    */
   public void putAxis(final int id, final String name)
   {
      if(name == null)
      {
         this.axisName.remove(id);
      }
      else
      {
         this.axisName.put(id, name);
      }
   }

   /**
    * Defines a button name
    * 
    * @param id
    *           Button ID
    * @param name
    *           New name. Use {@code null} for remove the button
    */
   public void putButton(final int id, final String name)
   {
      if(name == null)
      {
         this.buttonsName.remove(id);
      }
      else
      {
         this.buttonsName.put(id, name);
      }
   }

   /**
    * Serialize settings in stream
    * 
    * @param outputStream
    *           Stream where write (Closed at end)
    * @throws IOException
    *            On writing issue
    */
   public void serialize(final OutputStream outputStream) throws IOException
   {
      final DynamicWriteXML dynamicWriteXML = new DynamicWriteXML(outputStream);
      dynamicWriteXML.openMarkup(JoystickSettings.MARKUP_JOYSTICK_SETTINGS);

      if(this.joystickName != null)
      {
         dynamicWriteXML.appendParameter(JoystickSettings.PARAMETER_NAME, this.joystickName);
      }

      for(final int key : this.axisName.getKeys())
      {
         dynamicWriteXML.openMarkup(JoystickSettings.MARKUP_AXIS);
         dynamicWriteXML.appendParameter(JoystickSettings.PARAMETER_ID, key);
         dynamicWriteXML.appendParameter(JoystickSettings.PARAMETER_NAME, this.axisName.get(key));
         dynamicWriteXML.closeMarkup();
      }

      for(final int key : this.buttonsName.getKeys())
      {
         dynamicWriteXML.openMarkup(JoystickSettings.MARKUP_BUTTON);
         dynamicWriteXML.appendParameter(JoystickSettings.PARAMETER_ID, key);
         dynamicWriteXML.appendParameter(JoystickSettings.PARAMETER_NAME, this.buttonsName.get(key));
         dynamicWriteXML.closeMarkup();
      }

      dynamicWriteXML.closeMarkup();
   }

   /**
    * Changes joystick name
    * 
    * @param joystickName
    *           New joystick name
    */
   public void setJoystickName(final String joystickName)
   {
      this.joystickName = joystickName;
   }
}