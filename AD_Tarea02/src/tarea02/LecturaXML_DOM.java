/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea02;

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
public class LecturaXML_DOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("src\\resources\\libros.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement();// Accede al nodo raiz del documento
            doc.normalize();//Elimina nodos vacios y combina adyacentes en caso de haberlos

            //Almacenamos los snodos(LIBROS) para luego mostrar la cantidad de ells con el método getLength()
            NodeList nList = doc.getElementsByTagName("libro");
            System.out.println("Número de libros: " + nList.getLength());
            System.out.println("----------------------------------------------------------------------");

            //Lectura del contenido (hay que saber la estructura y etiquetas utilizadas)
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Libro");
                    System.out.println("año: " + eElement.getAttribute("año"));
                    System.out.println("titulo: " + eElement.getElementsByTagName("titulo").item(0).getTextContent());

                    NodeList listaAutores = eElement.getElementsByTagName("autor");
                    for (int j = 0; j < listaAutores.getLength(); j++) {
                        Element autor = (Element) listaAutores.item(j);
                        System.out.println("autor: ");
                        System.out.println("       apellido: " + autor.getElementsByTagName("apellido").item(0).getTextContent());
                        System.out.println("       nombre: " + autor.getElementsByTagName("nombre").item(0).getTextContent());
                    }

                    System.out.println("editorial: " + eElement.getElementsByTagName("editorial").item(0).getTextContent());
                    System.out.println("precio: " + eElement.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println("----------------------------------------------------------------------");
                }
            }

        } catch (Exception e) {
        }

    }

}
