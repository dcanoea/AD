/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package StAX_XML;

import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author David Cano Escario
 */
public class EventWriter_API {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Nombre del archivo XML de salida
        String archivoXML = "C:\\AccesoDatos\\XML\\catalogo.xml";

        try {
            //Crear una salida de archivo
            OutputStream outputStream = new FileOutputStream(archivoXML);

            //Crear un XMLEventWriter para escribir el XML
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputStream, "UTF-8");

            //Crear un eventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            //Crear y escribir el documento XML
            // Inicio del documento XML con la declaración de versión y encoding
            StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
            eventWriter.add(startDocument);
            //Nueva linea para organizar el XML en una estructura legible
            eventWriter.add(eventFactory.createCharacters("\n"));

            //Crear y escribir el elemento raíz <catalogo>
            StartElement catalogoStartElement = eventFactory.createStartElement("", "", "catalogo");
            eventWriter.add(catalogoStartElement);
            eventWriter.add(eventFactory.createCharacters("\n"));

            // Escribir los elementos <libro>
            escribirLibro(eventWriter, eventFactory, "1", "Java para Principiantes", "Juan", "Pérez", "29.99");
            escribirLibro(eventWriter, eventFactory, "2", "Avanzando con StAX", "María", "Gómez", "39.99");

            // Cerrar el elemento raíz </catalogo>
            EndElement catalogoEndElement = eventFactory.createEndElement("", "", "catalogo");
            eventWriter.add(catalogoEndElement);

            // Fin del documento XML
            EndDocument endDocument = eventFactory.createEndDocument();
            eventWriter.add(endDocument);

            // Cerrar el XMLEventWriter y el OutputStream
            eventWriter.close();
            outputStream.close();

            System.out.println("Archivo XML generado exitosamente: " + archivoXML);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para escribir un elemento <libro> con subelementos
    private static void escribirLibro(XMLEventWriter eventWriter, XMLEventFactory eventFactory,
            String id, String titulo, String nombreAutor, String apellidoAutor, String precio)
            throws XMLStreamException {

        // Crear una nueva línea y sangría para legibilidad
        eventWriter.add(eventFactory.createCharacters("\t"));

        // Iniciar el elemento <libro> con atributo id
        StartElement libroStartElement = eventFactory.createStartElement("", "", "libro");
        eventWriter.add(libroStartElement);
        eventWriter.add(eventFactory.createAttribute("id", id));
        eventWriter.add(eventFactory.createCharacters("\n"));

        // Escribir el elemento <titulo>
        escribirElemento(eventWriter, eventFactory, "titulo", titulo);

        // Escribir el elemento <autor> con subelementos <nombre> y <apellido>
        eventWriter.add(eventFactory.createCharacters("\t\t"));
        StartElement autorStartElement = eventFactory.createStartElement("", "", "autor");
        eventWriter.add(autorStartElement);
        eventWriter.add(eventFactory.createCharacters("\n"));

        escribirElemento(eventWriter, eventFactory, "nombre", nombreAutor);
        escribirElemento(eventWriter, eventFactory, "apellido", apellidoAutor);

        EndElement autorEndElement = eventFactory.createEndElement("", "", "autor");
        eventWriter.add(eventFactory.createCharacters("\t\t"));
        eventWriter.add(autorEndElement);
        eventWriter.add(eventFactory.createCharacters("\n"));

        // Escribir el elemento <precio>
        escribirElemento(eventWriter, eventFactory, "precio", precio);

        eventWriter.add(eventFactory.createCharacters("\t"));
        EndElement libroEndElement = eventFactory.createEndElement("", "", "libro");
        eventWriter.add(libroEndElement);
        eventWriter.add(eventFactory.createCharacters("\n"));
    }

    // Método auxiliar para escribir un elemento simple con texto
    private static void escribirElemento(XMLEventWriter eventWriter, XMLEventFactory eventFactory,
            String nombreElemento, String contenido)
            throws XMLStreamException {
        eventWriter.add(eventFactory.createCharacters("\t\t\t"));

        StartElement startElement = eventFactory.createStartElement("", "", nombreElemento);
        eventWriter.add(startElement);

        XMLEvent texto = eventFactory.createCharacters(contenido);
        eventWriter.add(texto);

        EndElement endElement = eventFactory.createEndElement("", "", nombreElemento);
        eventWriter.add(endElement);
        eventWriter.add(eventFactory.createCharacters("\n"));
    }
}
