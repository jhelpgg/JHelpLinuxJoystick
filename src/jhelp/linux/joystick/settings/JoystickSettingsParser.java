package jhelp.linux.joystick.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import jhelp.util.debug.Debug;
import jhelp.xml.ExceptionParseXML;
import jhelp.xml.ExceptionXML;
import jhelp.xml.ParseXMLlistener;
import jhelp.xml.ParserXML;

/**
 * Parser of joystick settings
 * 
 * @author JHelp
 */
class JoystickSettingsParser
      implements ParseXMLlistener
{
   /** Joystick settings where write parsing result */
   private final JoystickSettings joystickSettings;

   /**
    * Create a new instance of JoystickSettingsParser
    * 
    * @param joystickSettings
    *           Joystick settings where write parsing result
    */
   public JoystickSettingsParser(final JoystickSettings joystickSettings)
   {
      this.joystickSettings = joystickSettings;
   }

   /**
    * Claeed when comment find in XML <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param comment
    *           Found comment
    * @see jhelp.xml.ParseXMLlistener#commentFind(java.lang.String)
    */
   @Override
   public void commentFind(final String comment)
   {
   }

   /**
    * Called when read a close markup <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param markupName
    *           Closed markup
    * @throws ExceptionXML
    *            If close the markup now is illegal
    * @see jhelp.xml.ParseXMLlistener#endMarkup(java.lang.String)
    */
   @Override
   public void endMarkup(final String markupName) throws ExceptionXML
   {
   }

   /**
    * Called when parsing finished <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @throws ExceptionXML
    *            If finish parsing now is illegal
    * @see jhelp.xml.ParseXMLlistener#endParse()
    */
   @Override
   public void endParse() throws ExceptionXML
   {
   }

   /**
    * Called on exception that forced to stop brutally the parsing <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param exceptionParseXML
    *           Exception happen
    * @see jhelp.xml.ParseXMLlistener#exceptionForceEndParse(jhelp.xml.ExceptionParseXML)
    */
   @Override
   public void exceptionForceEndParse(final ExceptionParseXML exceptionParseXML)
   {
      Debug.printException(exceptionParseXML, "Parse failed !");
   }

   /**
    * Parse a stream with joystick settings inside
    * 
    * @param inputStream
    *           Stream to parse. Not cloase at end
    * @throws IOException
    *            On reading issue
    */
   public void parse(final InputStream inputStream) throws IOException
   {
      final ParserXML parserXML = new ParserXML();

      try
      {
         parserXML.parse(this, inputStream);
      }
      catch(final Exception exception)
      {
         throw new IOException("Failed to parse joystick settings", exception);
      }
   }

   /**
    * Called when read an open markup <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param markupName
    *           Opened markup
    * @param parameters
    *           Markup parameters
    * @throws ExceptionXML
    *            If markup or its parameters are invalid
    * @see jhelp.xml.ParseXMLlistener#startMakup(java.lang.String, java.util.Hashtable)
    */
   @Override
   public void startMakup(final String markupName, final Hashtable<String, String> parameters) throws ExceptionXML
   {
      if(JoystickSettings.MARKUP_JOYSTICK_SETTINGS.equals(markupName) == true)
      {
         this.joystickSettings.setJoystickName(ParserXML.obtainParameter(markupName, parameters, JoystickSettings.PARAMETER_NAME, false));
         return;
      }

      if(JoystickSettings.MARKUP_BUTTON.equals(markupName) == true)
      {
         final int id = ParserXML.obtainInteger(markupName, parameters, JoystickSettings.PARAMETER_ID, true, 0);
         final String name = ParserXML.obtainParameter(markupName, parameters, JoystickSettings.PARAMETER_NAME, true);
         this.joystickSettings.putButton(id, name);
         return;
      }

      if(JoystickSettings.MARKUP_AXIS.equals(markupName) == true)
      {
         final int id = ParserXML.obtainInteger(markupName, parameters, JoystickSettings.PARAMETER_ID, true, 0);
         final String name = ParserXML.obtainParameter(markupName, parameters, JoystickSettings.PARAMETER_NAME, true);
         this.joystickSettings.putAxis(id, name);
         return;
      }
   }

   /**
    * Called when parsing starts <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.xml.ParseXMLlistener#startParse()
    */
   @Override
   public void startParse()
   {
   }

   /**
    * called when text find <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param text
    *           Text read
    * @throws ExceptionXML
    *            On invalid text
    * @see jhelp.xml.ParseXMLlistener#textFind(java.lang.String)
    */
   @Override
   public void textFind(final String text) throws ExceptionXML
   {
   }
}