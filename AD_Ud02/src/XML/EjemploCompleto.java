/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author David Cano Escario
 */
public class EjemploCompleto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException {
        //Fichero XML
        File file = new File("C:\\AccesoDatos\\XML\\clase.xml");

        try {
            //Crear un DocumentBuilder
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();

            //Parseo del archivo XML
            Document doc = dbuilder.parse(file);
            doc.getDocumentElement().normalize();

            //Extraer el elemento raiz
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

            //Recorrer la Lista de Elementos <alumno>
            NodeList nList = doc.getElementsByTagName("alumno");

            //Extracción de Datos por Elemento
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("\nnumero de alumno: " + eElement.getAttribute("numero"));
                    System.out.println("nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent());
                    System.out.println("apodo: " + eElement.getElementsByTagName("apodo").item(0).getTextContent());
                    System.out.println("marcas: " + eElement.getElementsByTagName("marcas").item(0).getTextContent());
                }
            }
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
