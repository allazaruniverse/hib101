package healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = { "patients", "appointments", "offices"})
@Entity
@Table(name="Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="DoctorID")
    private int doctorId;
    @Column(name="FirstName")
    private String firstName;
    @Column(name="LastName")
    private String lastName;
    @Column(name="Specialty")
    private String specialty;
    @Column(name="Email")
    private String email;

    @OneToMany(mappedBy = "doctor" , cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Appointment> appointments= new HashSet<Appointment>();


    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable(
            name ="doctor_patient",
            joinColumns = @JoinColumn(name="DoctorID"),
            inverseJoinColumns = @JoinColumn(name ="PatientID")
    )
    Set<Patient> patients=new HashSet<>();

    @OneToOne(mappedBy = "doctor" , cascade= CascadeType.ALL)
    private Office office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId);
    }
}


