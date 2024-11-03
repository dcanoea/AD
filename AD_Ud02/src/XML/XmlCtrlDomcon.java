/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package XML;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author David Cano Escario
 */
public class XmlCtrlDomcon {

    //Método para instanciar un nuevo documento vacío
    public static Document instanciarDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.newDocument();
    }

    //Método para escribir un documento XML en un archivo
    public static void escribirDocumentoATextoXml(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        //Formateo para indentar el XML de salida
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        //Transformación y escritura del documento en el archivo
        transformer.transform(source, result);
    }

    //Método para cargar un documento XML desde un archivo
    public static Document instanciarDocument(File fXmlFile) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        //Parseo del archivo y obtención del objeto Document
        Document doc = dBuilder.parse(fXmlFile);

        //Normalización del documento para estructura uniforme
        doc.getDocumentElement().normalize();

        return doc;
    }

    //Método para obtener el valor de una etiqueta en un elemento dado
    public static String getValorEtiqueta(String etiqueta, Element elemento) {
        Node nValue = elemento.getElementsByTagName(etiqueta).item(0);

        if (nValue != null && nValue.getFirstChild() != null) {
            return nValue.getFirstChild().getNodeValue();
        } else {
            return null; //Devuelve null si la etiqueta o el contenido no existen
        }
    }

    //Método para obtener un elemento hijo específico de una etiqueta
    public static Element getElementEtiqueta(String etiqueta, Element elemento) {
        Node nElement = elemento.getElementsByTagName(etiqueta).item(0);

        if (nElement instanceof Element) {
            return (Element) nElement;
        } else {
            return null;// Devuelve null si el elemento no existe o no es un Element
        }
    }

    //Puedes agregar más métodos aquí, según sea necesario
    public static void main(String[] args) {
    }
}
