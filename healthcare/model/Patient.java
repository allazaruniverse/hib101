package healthcare.model;

import jakarta.persistence.*;
import healthcare.model.Appointment;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Data
@Entity
@Table(name = "Patients")


@ToString(exclude = "doctors")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private int patientId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @OneToMany( mappedBy ="patient" ,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments= new HashSet<>();

    @ManyToMany(mappedBy = "patients",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Doctor> doctors= new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientId == patient.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }
}