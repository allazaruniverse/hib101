package healthcare.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;

@Entity
@Table(name="Appointments")
@Getter
@Setter
@ToString(exclude = {"patient", "doctor"})
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="AppointmentID")
    int appointmentId;
    //    @Column(name="PatientID")
//    int patientId;
//    @Column(name="DoctorID")
//    int doctorId;
    @Column(name="AppointmentDate")
    String appointmentDate;
    @Column(name="Notes")
    String notes;

    @ManyToOne
    @JoinColumn(name="PatientID")
    Patient patient;

    @ManyToOne
    @JoinColumn(name="DoctorID")
    Doctor doctor;

    public Appointment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" +  (patient != null ? patient.getPatientId() : "N/A") +
                ", doctorId=" + (doctor != null ? doctor.getDoctorId() : "N/A") +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", notes='" + notes  +
                '}';
    }
}
