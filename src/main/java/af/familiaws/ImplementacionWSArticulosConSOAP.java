package af.familiaws;

import org.w3c.dom.NodeList;
import servicios.IServiciosArticulos;

import javax.xml.soap.*;

public class ImplementacionWSArticulosConSOAP extends ImplementacionWSArticulos {

    @Override
    public String[] getArticulos() {
        String[] originalArticulos = super.getArticulos();
        String flagUrl = getCountryFlag("COL");

        System.out.println("URL de la bandera: " + flagUrl);

        return originalArticulos;
    }

    private String getCountryFlag(String countryISOCode) {
        String flagUrl = "";
        try {
            // Crear el mensaje SOAP
            SOAPMessage soapMessage = createSOAPRequest(countryISOCode);

            // Crear la conexión SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Enviar el mensaje SOAP y obtener la respuesta
            String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
            SOAPMessage soapResponse = soapConnection.call(soapMessage, url);

            // Procesar la respuesta SOAP
            flagUrl = parseSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flagUrl;
    }

    private SOAPMessage createSOAPRequest(String countryISOCode) throws SOAPException {
        // Crear el mensaje SOAP
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Crear el objeto SOAP
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("web", "http://www.oorsprong.org/websamples.countryinfo");

        // Crear el cuerpo SOAP
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElement = soapBody.addChildElement("CountryFlag", "web");
        SOAPElement soapElement = soapBodyElement.addChildElement("sCountryISOCode", "web");
        soapElement.addTextNode(countryISOCode);

        soapMessage.saveChanges();

        return soapMessage;
    }

    private String parseSOAPResponse(SOAPMessage soapResponse) throws SOAPException {
        SOAPBody soapBody = soapResponse.getSOAPBody();
        NodeList nodeList = soapBody.getElementsByTagName("m:CountryFlagResult");
        if (nodeList.getLength() > 0) {
            Node node = (Node) nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }
}
