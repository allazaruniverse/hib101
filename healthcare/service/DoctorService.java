
package healthcare.service;

import healthcare.model.Doctor;
import healthcare.model.Patient;
import healthcare.repository.DoctorRepositoryImpl;


import java.util.List;

public class DoctorService {
    private final DoctorRepositoryImpl doctorRepository;

    public DoctorService(DoctorRepositoryImpl doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.createDoctor(doctor);
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.getDoctorById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.updateDoctor(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteDoctor(id);
    }

    public void addPatientToDoctor(int id, Patient patient) {
        doctorRepository.addPatientToDoctor(id,patient);
    }

    public void removePatientFromDoctor(int id, Patient patient) {
        doctorRepository.removePatientFromDoctor(id,patient);
    }


}