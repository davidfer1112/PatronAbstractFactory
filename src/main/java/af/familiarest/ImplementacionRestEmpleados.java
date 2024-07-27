package af.familiarest;

import servicios.IServiciosEmpleados;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Implementación para el Service de Empleados tipo Restfull
 */
public class ImplementacionRestEmpleados implements IServiciosEmpleados {
    @Override
    public String[] getEmpleados() {
        System.out.println("Tipo de Servicio: Restful");

        String response = sendGET("https://dog.ceo/api/breeds/image/random");
        if (response != null) {
            // Procesar la respuesta y convertirla en un array de Strings
            return new String[]{"Juan Guillermo", "David", "Luis Fernando"};
        }
        return new String[]{"No se pudo obtener la información"};
    }

    private String sendGET(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
