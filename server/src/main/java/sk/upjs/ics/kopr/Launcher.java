package sk.upjs.ics.kopr;

import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        List<Source> metadata = new ArrayList<>();

        var wsdlSource = new StreamSource(AppointmentsServiceImpl.class.getResourceAsStream("/appointments.wsdl"));
        wsdlSource.setSystemId("https://kopr.ics.upjs.sk/appointments.wsdl");
        metadata.add(wsdlSource);

        var xsdSource = new StreamSource(AppointmentsServiceImpl.class.getResourceAsStream("/appointments.xsd"));
        xsdSource.setSystemId("https://kopr.ics.upjs.sk/appointments.xsd");
        metadata.add(xsdSource);

        var filter = new HashMap<String, Object>();
        filter.put(Endpoint.WSDL_SERVICE, new QName("https://kopr.ics.upjs.sk", "AppointmentsService"));
        filter.put(Endpoint.WSDL_PORT, new QName("https://kopr.ics.upjs.sk", "AppointmentPort"));

        var context = SpringApplication.run(Launcher.class, args);
        var appointmentsService = context.getBean(AppointmentsServiceImpl.class);
        var endpoint = Endpoint.create(appointmentsService);

        endpoint.setProperties(filter);
        endpoint.setMetadata(metadata);
        endpoint.publish("http://localhost:8888/appointments");
        System.out.println("Server running...");
    }
}