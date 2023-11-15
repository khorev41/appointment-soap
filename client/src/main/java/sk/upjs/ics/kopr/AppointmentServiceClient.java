package sk.upjs.ics.kopr;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.net.URL;
import java.util.List;

public class AppointmentServiceClient {

    public List<AppointmentResponse.Appointment> getAppointments(String patientId, int specCode, String description) throws Exception {
        URL url = new URL("http://localhost:8888/appointments?wsdl");

        AppointmentsService appointmentsService
                = new AppointmentsService(url);

        return appointmentsService.getAppointmentsPort().appointmentRequest(patientId, specCode, description);
    }

    public static void main(String[] args) throws Exception {
        AppointmentServiceClient client = new AppointmentServiceClient();
    }
}
