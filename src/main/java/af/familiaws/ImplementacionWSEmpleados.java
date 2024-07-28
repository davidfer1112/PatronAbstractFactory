package af.familiaws;

import servicios.IServiciosEmpleados;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ImplementacionWSEmpleados implements IServiciosEmpleados {
    private static final String[] EMPLEADOS = new String[]{"Juan Guillermo", "David", "Luis Fernando"};

    private static SOAPMessage createSOAPRequest() throws Exception {
        // Crear un mensaje SOAP
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Crear el sobre SOAP y el contenido
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Crear el cuerpo SOAP
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("HelloRequest", "", "http://learnwebservices.com/services/hello");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Name");
        soapBodyElem1.addTextNode("Usca");

        // Guardar los cambios del mensaje SOAP
        soapMessage.saveChanges();

        // Imprimir la solicitud SOAP para depuración
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

    private static String soapMessageToString(SOAPMessage soapMessage) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapMessage.getSOAPPart().getContent();
        StreamResult result = new StreamResult(new StringWriter());
        transformer.transform(sourceContent, result);
        return result.getWriter().toString();
    }

    private static String extractMessageValue(SOAPMessage soapResponse) throws Exception {
        SOAPBody body = soapResponse.getSOAPBody();
        NodeList returnList = body.getElementsByTagNameNS("http://learnwebservices.com/services/hello", "Message");
        if (returnList.getLength() > 0) {
            Node returnNode = (Node) returnList.item(0);
            return returnNode.getTextContent();
        }
        return null;
    }

    public String getSolicitud() {
        try {
            // Crear la conexión SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // URL del servicio SOAP
            String url = "https://apps.learnwebservices.com/services/hello";

            // Enviar la solicitud SOAP al servidor
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

            // Procesar la respuesta
            System.out.println("Tipo de Servicio: WebServices");

            // Extraer el valor del elemento Message
            String response = extractMessageValue(soapResponse);
            soapConnection.close();

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String[] getEmpleados() {
        System.out.println("Tipo de Servicio: WebServices");
        return EMPLEADOS;
    }
}