/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DOM_XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author David Cano Escario
 */
public class LecturaXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("C:\\AccesoDatos\\XML\\concesionario.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement();//Accede al nodo raíz del documento
            doc.normalize();//Elimina nodos vacíos y combina adyacentes en casa de haberlos
            //doc.getDocumentElement().normalize();

            // almacenamos los nodos para luego mostrar la cantidad de ellos con el método getLength()
            NodeList nList = doc.getElementsByTagName("coche");
            System.out.println("Número de coches: " + nList.getLength());

            //Lectura del contenido (hay que saber la estructura y etiquetas utilizadas)
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("\nCoche id: " + eElement.getAttribute("id"));
                    System.out.println("Marca: " + eElement.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Modelo: " + eElement.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("Cilindrada: " + eElement.getElementsByTagName("cilindrada").item(0).getTextContent());
                }
            }

            //Lectura del contenido sin conocer la estructura y etiquetas
            nList = doc.getElementsByTagName("coche");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.hasChildNodes()) {
                        NodeList nl = node.getChildNodes();
                        for (int j = 0; j < nl.getLength(); j++) {
                            Node nd = nl.item(j);
                            System.out.println(nd.getTextContent());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
