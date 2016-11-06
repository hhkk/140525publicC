package com.ustodo.utilj.utdrequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.ustodo.utilg.O;
import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpRequest;


/**
 * Created by henryms on 12/31/13.
 */
public class UtdRequest {

    public static String testWebCall() throws Exception
    {
        O.oNoFilter("=========== in test() put example http call here =============== ");
        try
        {
            // http://hc.apache.org/httpcomponents-core-ga/tutorial/html/fundamentals.html#d5e56
            HttpRequest request = new BasicHttpRequest("GET", "/",
                    HttpVersion.HTTP_1_1);
            O.oNoFilter("o:" + request.getRequestLine().getMethod());
            O.oNoFilter("o:" + request.getRequestLine().getUri());
            O.oNoFilter("o:" + request.getProtocolVersion().toString());
            O.oNoFilter("o:" + request.getRequestLine().toString());

        }  catch (Exception e) {
            //return "fail";
            O.oerr("mecalling", e);
            throw e;
        }
        return "success";
    }


    public static String getResponse() throws MalformedURLException,
            IOException {

        //Code to make a webservice HTTP request
        String responseString = "";
        String outputString = "";
        String wsURL = "http://localhost:8089";
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        String xmlInput =
//                "  <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://litwinconsulting.com/webservices/\">\n" +
//                        "   <soapenv:Header/>\n" +
//                        "   <soapenv:Body>\n" +
//                        "      <web:GetWeather>\n" +
//                        "         <!--Optional:-->\n" +
//                        "         <web:City>" + city + "</web:City>\n" +
//                        "      </web:GetWeather>\n" +
//                        "   </soapenv:Body>\n" +
//                        "  </soapenv:Envelope>";
        String xmlInput =
                "  <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://litwinconsulting.com/webservices/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <web:GetWeather>\n" +
                        "         <!--Optional:-->\n" +
                        "         <web:City>CLEVELAND</web:City>\n" +
                        "      </web:GetWeather>\n" +
                        "   </soapenv:Body>\n" +
                        "  </soapenv:Envelope>";

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        String SOAPAction = wsURL;
        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length",
                String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        //Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
        //Ready with sending the request.

        //Read the response.
        InputStreamReader isr =
                new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);

        //Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }
        System.out.println ("outputString:" + outputString);
        //Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
        //        Document document = parseXmlFile(outputString);
        //        NodeList nodeLst = document.getElementsByTagName("GetWeatherResult");
        //        String weatherResult = nodeLst.item(0).getTextContent();
        //        System.out.println("Weather: " + weatherResult);
        //
        //        //Write the SOAP message formatted to the console.
        //        String formattedSOAPResponse = formatXML(outputString);
        //        System.out.println(formattedSOAPResponse);
        //        return weatherResult;
        return outputString;
    }


}


