package sk.upjs.ics.kopr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.upjs.ics.kopr.entities.Appointment;
import sk.upjs.ics.kopr.entities.Doctor;
import sk.upjs.ics.kopr.repositories.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllFreeAppointmentsForDoctor(Doctor doctor) {
        return appointmentRepository.findAllByDoctorAndPatientIdIsNull(doctor);
    }

    public List<Appointment> getAllFreeAppointmentsBySpecializationCode(int specializationCode) {
        return appointmentRepository.findAllByDoctorSpecializationCodeAndPatientIdIsNullOrderByDatetimeAsc(specializationCode);
    }


    public void saveAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }
}
