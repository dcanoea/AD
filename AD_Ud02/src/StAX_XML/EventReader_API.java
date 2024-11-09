/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package StAX_XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author David Cano Escario
 */
public class EventReader_API {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String archivoXML = "C:\\AccesoDatos\\XML\\libros.xml";

        //Crear una instancia de XMLInputFactory para configurar el lector de eventos
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try {
            //Crear un FileInputStream para leer el archivo XML
            FileInputStream fis = new FileInputStream(archivoXML);

            //Crear un XMLEventReader a partir de la fábrica de entradas XML
            XMLEventReader eventReader = factory.createXMLEventReader(fis);

            //Variables auxiliares para almacenar el contenido de los elementos
            String titulo = "";
            String autor = "";
            String apellido = "";
            String nombre = "";
            String editorial = "";
            String precio = "";

            //Iterar a través de cada evento en el documento XML
            while (eventReader.hasNext()) {
                //Obtener el siguiente evento en el documento
                XMLEvent event = eventReader.nextEvent();

                //Verificar si es un evento de inicio de un elemento
                if (event.isStartElement()) {
                    //Obtener el nombre del elemento de inicio
                    StartElement startElement = event.asStartElement();
                    String elementoActual = startElement.getName().getLocalPart();

                    //si es el elemento "libro", obtener el atributo "año"
                    if (elementoActual.equals("libro")) {
                        Attribute anyoAttr = startElement.getAttributeByName(new javax.xml.namespace.QName("año"));
                        if (anyoAttr != null) {
                            System.out.println("Año de publicación: " + anyoAttr.getValue());
                        }
                    }

                    //Procesar los elementos específicos "titulo", "autor", "apellido", "nombre", "editorial", "precio"
                    if (elementoActual.equals("titulo")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            titulo = event.asCharacters().getData();
                            System.out.println("Título: " + titulo);
                        }
                    } else if (elementoActual.equals("autor")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            System.out.println("Autor: ");
                        }
                    } else if (elementoActual.equals("apellido")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            apellido = event.asCharacters().getData();
                            System.out.println("    Apellido: " + apellido);
                        }
                    } else if (elementoActual.equals("nombre")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            nombre = event.asCharacters().getData();
                            System.out.println("    Nombre: " + nombre);
                        }
                    } else if (elementoActual.equals("editorial")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            editorial = event.asCharacters().getData();
                            System.out.println("Editorial: " + editorial);
                        }
                    } else if (elementoActual.equals("precio")) {
                        event = eventReader.nextEvent();
                        if (event instanceof Characters) {
                            precio = event.asCharacters().getData();
                            System.out.println("Precio: " + precio);
                            System.out.println("-------------------------------------------------------------------");
                        }
                    }

                    //Verificar si es un evento de fin de elemento
                    if (event.isEndElement()) {
                        //Si es un evento de fin de elemtno, reiniciamos la variable elementoActual
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals("libro")) {
                            System.out.println("-----------------------");
                        }
                        elementoActual = "";
                    }
                }
            }
            //Cerrar el lector y el flujo de entrada para liberar recursos
            eventReader.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
            Logger.getLogger(EventReader_API.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(EventReader_API.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventReader_API.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
