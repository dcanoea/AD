/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CrearArchivoXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String con la ruta de origen del fichero DAT y lo instanciamos en un File
        String rutaorigen = "src/resources/EMPLEADOS.DAT";
        File origen = new File(rutaorigen);

        //String con la ruta de destino del XML y lo instanciamos en un File
        String rutadestino = "src/resources/EMPLEADOS.XML";
        File destino = new File(rutadestino);

        //ArrayList donde guardaremos los empleados para después escribir el XML
        ArrayList<Empleado> arrayListEmpleados = new ArrayList<Empleado>();

        try {
            //Creamos el RandomAccesFile con permisos de lectura (r)
            RandomAccessFile raf = new RandomAccessFile(origen, "r");

            //Mediante un puntero comprobamos si se ha llegado al final del fichero. Si no, sigue leyendo
            //Recogemos los atributos de cada empleado y una vez creado el objeto lo añadimos al ArrayList
            while (raf.getFilePointer() < origen.length()) {
                Empleado empleado = Empleado.leerEmpleado(raf);
                arrayListEmpleados.add(empleado);
            }

            try {
                //Instanciamos el documento DOM en memoria
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();

                //Creamos el documento DOM con el elemento raíz "empleados"
                Document document = implementation.createDocument(null, "empleados", null);
                //El documento DOM se crea en memoria creando primero el elemento raíz
                Element raiz = document.getDocumentElement();

                //Con este bucle foreach añadiremos cada empleado al xml
                for (Empleado e : arrayListEmpleados) {
                    //Recuperamos los atributos del empleado
                    int codigo = e.getCodigo();
                    String nom = e.getNombre();
                    String dir = e.getDireccion();
                    float sal = e.getSalario();
                    float com = e.getComision();

                    //Creamos un elemento "empleado" con su código como atributyo y lo añadimos
                    Element empleado = document.createElement("empleado");
                    empleado.setAttribute("codigo", String.valueOf(codigo));

                    //Añadimos subelementos al empleado
                    Element nombre = document.createElement("nombre");
                    nombre.appendChild(document.createTextNode(nom));
                    empleado.appendChild(nombre);

                    Element direccion = document.createElement("direccion");
                    direccion.appendChild(document.createTextNode(dir));
                    empleado.appendChild(direccion);

                    Element salario = document.createElement("salario");
                    salario.appendChild(document.createTextNode(String.valueOf(sal)));
                    empleado.appendChild(salario);

                    Element comision = document.createElement("comision");
                    comision.appendChild(document.createTextNode(String.valueOf(com)));
                    empleado.appendChild(comision);

                    //Añadimos elemento "empleado" a la raiz
                    raiz.appendChild(empleado);
                }

                //Configuración de Transformer y transformación del DOM a archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                //Configuración para que el archivo XML esté indentado y sea legible
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                //Crear adaptadores Source y Result
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(rutadestino);

                //Transformar y guardar en el file de destino
                transformer.transform(source, result);

                System.out.println("Archivo XML creado correctamente en " + rutadestino);
            } catch (Exception e) {
                System.out.println("Error al instanciar DOM");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
            Logger.getLogger(CrearArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
            Logger.getLogger(CrearArchivoXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
