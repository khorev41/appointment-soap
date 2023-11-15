package sk.upjs.ics.kopr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import sk.upjs.ics.kopr.entities.Appointment;
import sk.upjs.ics.kopr.entities.Doctor;
import sk.upjs.ics.kopr.repositories.AppointmentRepository;
import sk.upjs.ics.kopr.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Doctor> getDoctorsBySpecializationCode(int specCode) {
        return doctorRepository.getDoctorsBySpecializationCode(specCode);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(int id) {
        return doctorRepository.findById((long) id);
    }
}
