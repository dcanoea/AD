/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package StAX_XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author David Cano Escario
 */
public class Cursor_API {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Archivo XML a procesar
        String archivoXML = "C:\\AccesoDatos\\XML\\libros.xml";

        //Crear instancia de XMLInputFactory, que es el punto de entrada de la API StAX
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            //FileInputStream para leer el archivo XML
            FileInputStream fis = new FileInputStream(archivoXML);

            //Crear XMLStreamReader a partir del archivo utilizando la fábrica
            XMLStreamReader reader = factory.createXMLStreamReader(fis);

            //Variable para almacenar el nombre del elemento actual
            String elementoActual = "";

            //Bucle que recorre el archivo XML mientras haya más elementos
            while (reader.hasNext()) {
                //Obtener el siguiente evento en el documento XML
                int evento = reader.next();

                //Revisar el tipo de evento para procesarlo de acuerdo a su tipo
                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:
                        //Cuando empieza un nuevo elemento
                        elementoActual = reader.getLocalName();

                        //Si el elemento actual es un libro, obtenemos el atributo "año"
                        if (elementoActual.equals("libro")) {
                            String anyo = reader.getAttributeValue(null, "año");
                            System.out.println("Año de publicación: " + anyo);
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        //Capturamos el texto dentro de un elemento
                        String texto = reader.getText().trim();

                        //Si el texto no está vacío, verificamos a qué elemento corresponde
                        if (!texto.isEmpty()) {
                            switch (elementoActual) {
                                case "titulo":
                                    System.out.println("Título: " + texto);
                                    break;
                                case "apellido":
                                    System.out.println("Autor:");
                                    System.out.println("    Apellido: " + texto);
                                    break;
                                case "nombre":
                                    System.out.println("    Nombre: " + texto);
                                    break;
                                case "editorial":
                                    System.out.println("Editorial: " + texto);
                                    break;
                                case "precio":
                                    System.out.println("Precio: " + texto + "\n");
                                    break;
                            }
                        }
                        break;
                }
            }
            
            //Cerrar el lector y el flujo de entrada para liberar recursos
            reader.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            System.out.println("Error al procesar el XML");
            Logger.getLogger(Cursor_API.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cursor_API.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
