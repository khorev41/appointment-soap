package sk.upjs.ics.kopr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.upjs.ics.kopr.AppointmentResponse;
import sk.upjs.ics.kopr.AppointmentServiceClient;
import sk.upjs.ics.kopr.Launcher;
import sk.upjs.ics.kopr.entities.Appointment;
import sk.upjs.ics.kopr.entities.Doctor;
import sk.upjs.ics.kopr.services.AppointmentService;
import sk.upjs.ics.kopr.services.DoctorService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Launcher.class)
public class AppointmentServiceClientTest {

    private static AppointmentServiceClient client;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @BeforeAll
    public static void setUp() {
        Launcher.main(new String[]{});
        client = new AppointmentServiceClient();
    }

    @Test
    public void testGetAppointmentsNoMoreThan3() {
        try {
            List<AppointmentResponse.Appointment> appointments = client.getAppointments("123456/7890", 201, "");

            assertTrue(appointments.size() <= 3);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetAppointmentsContainsOnlyAppointments() {
        try {
            List<AppointmentResponse.Appointment> appointments = client.getAppointments("123456/7890", 201, "");

            for (AppointmentResponse.Appointment appointment : appointments) {
                assertTrue(appointment instanceof AppointmentResponse.Appointment);
            }
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDoctorsFromAppointmentsAreOfCorrectSpecialization() {
        try {
            List<AppointmentResponse.Appointment> appointments = client.getAppointments("123456/7890", 202, "");

            appointments.forEach(appointment -> {
                assertEquals(doctorService.getDoctorById(appointment.getDoctorID()).get().getSpecializationCode(), 202);
            });
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }


    @Test
    public void testAppointmentAreMaximumPossibleEarly() {
        try {
            List<AppointmentResponse.Appointment> appointments = client.getAppointments("123456/7890", 202, "");

            List<Appointment> freeAppointments = appointmentService.getAllFreeAppointmentsBySpecializationCode(202);
            ;

            for (int i = 0; i < 3; i++) {
                LocalDateTime datetime = freeAppointments.get(i).getDatetime();
                LocalDate date = LocalDate.parse(appointments.get(i).getDate());
                LocalTime time = LocalTime.parse(appointments.get(i).getTime());
                LocalDateTime receivedDateTime = LocalDateTime.of(date, time);

                assertTrue(datetime.getDayOfYear() == receivedDateTime.getDayOfYear() );
                assertTrue(datetime.getMonthValue() == receivedDateTime.getMonthValue());
                assertTrue(datetime.getYear() == receivedDateTime.getYear());
                assertTrue(datetime.getHour() == receivedDateTime.getHour());
                assertTrue(datetime.getMinute() == receivedDateTime.getMinute());
                assertTrue(datetime.getSecond() == receivedDateTime.getSecond());

                // ci sedi doctor
                Optional<Doctor> doctorById = doctorService.getDoctorById(appointments.get(i).getDoctorID());
                assertTrue(freeAppointments.get(i).getDoctor().getName().equals(doctorById.get().getName()));


                assertTrue( freeAppointments.get(i).getDuration() == appointments.get(i).getDuration());
            }
            appointments.forEach(appointment -> {


                assertEquals(doctorService.getDoctorById(appointment.getDoctorID()).get().getSpecializationCode(), 202);
            });
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }


}
