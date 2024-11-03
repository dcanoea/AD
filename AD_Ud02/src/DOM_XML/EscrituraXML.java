/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DOM_XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author David Cano Escario
 */
public class EscrituraXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("C:\\AccesoDatos\\XML\\instrumentos_musicales.xml");
        try {
            //PASO 1
            //Instanciar el documento DOM en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            //Crear el documento DOM con elemento raíz llamado instrumentos
            Document document = implementation.createDocument(null, "instrumentos_musicales", null);

            //El documento DOM se crea en memoria creando primero el elemento raíz
            Element raiz = document.getDocumentElement();

            //---------------------------------------------------------------------------------------------------------
            //PASO 2
            //Crear y añadir elementos al documento
            //Creamos un elemento "instrumento" con atributos y lo añadimos a la raíz
            Element instrumento = document.createElement("instrumento");
            instrumento.setAttribute("id", "1");

            //Añadir subelementos al "instrumento"
            Element nombre = document.createElement("nombre");
            nombre.appendChild(document.createTextNode("Clarinete"));
            instrumento.appendChild(nombre);

            Element tipo = document.createElement("tipo");
            tipo.appendChild(document.createTextNode("Viento"));
            instrumento.appendChild(tipo);

            Element precio = document.createElement("precio");
            precio.appendChild(document.createTextNode("1600"));
            instrumento.appendChild(precio);

            Element cantidad = document.createElement("cantidad");
            cantidad.appendChild(document.createTextNode("4"));
            instrumento.appendChild(cantidad);

            //Añadir el elemento "instrumento" a la raiz
            raiz.appendChild(instrumento);

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //Repetir el proceso para añadir más elementos si es necesario
            //Creamos un elemento "instrumento" con atributos y lo añadimos a la raíz
            Element instrumento2 = document.createElement("instrumento");
            instrumento2.setAttribute("id", "2");

            //Añadir subelementos al "instrumento"
            Element nombre2 = document.createElement("nombre");
            nombre2.appendChild(document.createTextNode("Guitarra"));
            instrumento2.appendChild(nombre2);

            Element tipo2 = document.createElement("tipo");
            tipo2.appendChild(document.createTextNode("Cuerda"));
            instrumento2.appendChild(tipo2);

            Element precio2 = document.createElement("precio");
            precio2.appendChild(document.createTextNode("800"));
            instrumento2.appendChild(precio2);

            Element cantidad2 = document.createElement("cantidad");
            cantidad2.appendChild(document.createTextNode("10"));
            instrumento2.appendChild(cantidad2);

            //Añadir el elemento "instrumento" a la raiz
            raiz.appendChild(instrumento2);

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //Y repetimos para un tercer instrumento
            //Creamos un elemento "instrumento" con atributos y lo añadimos a la raíz
            Element instrumento3 = document.createElement("instrumento");
            instrumento3.setAttribute("id", "3");

            //Añadir subelementos al "instrumento"
            Element nombre3 = document.createElement("nombre");
            nombre3.appendChild(document.createTextNode("Tambor"));
            instrumento3.appendChild(nombre3);

            Element tipo3 = document.createElement("tipo");
            tipo3.appendChild(document.createTextNode("Percusión"));
            instrumento3.appendChild(tipo3);

            Element precio3 = document.createElement("precio");
            precio3.appendChild(document.createTextNode("500"));
            instrumento3.appendChild(precio3);

            Element cantidad3 = document.createElement("cantidad");
            cantidad3.appendChild(document.createTextNode("15"));
            instrumento3.appendChild(cantidad3);

            //Añadir el elemento "instrumento" a la raiz
            raiz.appendChild(instrumento3);

            //---------------------------------------------------------------------------------------------------------
            
            //PASO 3
            //Configuración de Transformer y transformación del DOM a archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //Configuración para que el archivo XML esté indentado y sea legible
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            //Crear adaptadores Source y Result
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            
            //Transformar y guardar en el archivo XML
            transformer.transform(source, result);
            
            System.out.println("Archivo XML creado exitosamente en " + file.getAbsolutePath());
            
            
            
        } catch (Exception e) {
        }

    }

}
