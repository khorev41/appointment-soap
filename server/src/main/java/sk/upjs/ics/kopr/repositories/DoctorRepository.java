package sk.upjs.ics.kopr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.upjs.ics.kopr.entities.Doctor;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.specializationCode = :specCode")
    List<Doctor> getDoctorsBySpecializationCode(@Param("specCode") int specCode);

}

