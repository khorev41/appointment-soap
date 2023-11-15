package sk.upjs.ics.kopr;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.upjs.ics.kopr.entities.Appointment;
import sk.upjs.ics.kopr.entities.Doctor;
import sk.upjs.ics.kopr.services.AppointmentService;
import sk.upjs.ics.kopr.services.DoctorService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@WebService(endpointInterface = "sk.upjs.ics.kopr.AppointmentPortType")
public class AppointmentsServiceImpl implements AppointmentPortType {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @Override
    public List<AppointmentResponse.Appointment> appointmentRequest(String patientID, int specializationCode, String conditionDescription) {

        List<Doctor> doctors = doctorService.getDoctorsBySpecializationCode(specializationCode);

        List<AppointmentResponse.Appointment> list = new ArrayList<>();
        findNext3AvailableAppointment(doctors).forEach(appointment -> {
            var appointmentResponse = new AppointmentResponse.Appointment();
            appointmentResponse.setDoctorID(appointment.getDoctor().getId());
            appointmentResponse.setDoctorName(appointment.getDoctor().getName());
            appointmentResponse.setDuration(appointment.getDuration());
            appointmentResponse.setDate(formatDate(appointment.getDatetime()));
            appointmentResponse.setTime(formatTime(appointment.getDatetime()));

            list.add(appointmentResponse);
        });

        return list;
    }

    private List<Appointment> findNext3AvailableAppointment(List<Doctor> doctors) {
        List<Appointment> appointments = new ArrayList<>();

        doctors.forEach(doctor -> {
            appointments.addAll(appointmentService.getAllFreeAppointmentsForDoctor(doctor));
        });
        appointments.sort(Comparator.comparing(Appointment::getDatetime));

        return appointments.subList(0, Math.min(3, appointments.size()));
    }

    private static String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(dateFormatter);
    }

    private static String formatTime(LocalDateTime localDateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localDateTime.format(timeFormatter);
    }
}
