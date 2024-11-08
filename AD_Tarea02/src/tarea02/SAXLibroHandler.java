/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea02;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author David Cano Escario
 */
//Para empezar necesitamos una clase que herede de DefaultHandler
public class SAXLibroHandler extends DefaultHandler {

    //Creamos un StringBuilder para almacenar el contenido de los elementos
    private StringBuilder value;

    public SAXLibroHandler() {
        this.value = new StringBuilder();
    }

    //Método que maneja el inicio del documento
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Inicio del documento XML\n");
    }

    //Método que maneja el fin del documento
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin del documento XML");
    }

    //SAX tiene 3 métodos que hay que sobreescribir, por lo que pasará cada elemento (etiqueta)
    //Los métodos son startElement, characters y endElement
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);//Reinicio de la longitud del StringBuilder a 0, su valor se rellena en el método characters y se muesta en el método endElement

        //Los Atributos los coje desde el parámetro attributes. 
        //qName es el nombre de la etiqueta
        //Si la etiqueta es "libro", coge el valor del atributo año
        if (qName.equals("libro")) {
            String anyo = attributes.getValue("año");
            System.out.println("Libro publicado en el año: " + anyo);

        }
    }

    //En el método characters se añade el contenido de la etiqueta al StringBuilder
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length);
    }

    //El método endElement actúa cuando se termina la etiqueta. Se muestra el contenido del StringBuilder
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("titulo")) {
            System.out.println("Título: " + this.value.toString());
        } else if (qName.equalsIgnoreCase("apellido")) {
            System.out.println("Autor:");
            System.out.println("    Apellido: " + this.value.toString());
        } else if (qName.equalsIgnoreCase("nombre")) {
            System.out.println("    Nombre: " + this.value.toString());
        } else if (qName.equalsIgnoreCase("editorial")) {
            System.out.println("Editorial: " + this.value.toString());
        } else if (qName.equalsIgnoreCase("precio")) {
            System.out.println("Precio: " + this.value.toString() + "\n");
        }
    }
}
