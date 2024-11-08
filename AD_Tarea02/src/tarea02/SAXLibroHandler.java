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

    //M�todo que maneja el inicio del documento
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Inicio del documento XML\n");
    }

    //M�todo que maneja el fin del documento
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin del documento XML");
    }

    //SAX tiene 3 m�todos que hay que sobreescribir, por lo que pasar� cada elemento (etiqueta)
    //Los m�todos son startElement, characters y endElement
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);//Reinicio de la longitud del StringBuilder a 0, su valor se rellena en el m�todo characters y se muesta en el m�todo endElement

        //Los Atributos los coje desde el par�metro attributes. 
        //qName es el nombre de la etiqueta
        //Si la etiqueta es "libro", coge el valor del atributo a�o
        if (qName.equals("libro")) {
            String anyo = attributes.getValue("a�o");
            System.out.println("Libro publicado en el a�o: " + anyo);

        }
    }

    //En el m�todo characters se a�ade el contenido de la etiqueta al StringBuilder
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length);
    }

    //El m�todo endElement act�a cuando se termina la etiqueta. Se muestra el contenido del StringBuilder
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("titulo")) {
            System.out.println("T�tulo: " + this.value.toString());
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
