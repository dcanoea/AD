/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SAX_XML;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author David Cano Escario
 */
public class SAXLectura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(); 
            SAXParser parser = factory.newSAXParser();
            
            SAXLibroHandler handler = new SAXLibroHandler();
            parser.parse("C:\\AccesoDatos\\XML\\libros.xml", handler);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SAXLectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SAXLectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SAXLectura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
