package healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Doctors")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "patients", "appointments", "offices"})
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="DoctorID")
    int doctorId;
    @Column(name="FirstName")
    String firstName;
    @Column(name="LastName")
    String lastName;
    @Column(name="Specialty")
    String specialty;
    @Column(name="Email")
    String email;

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
    Office office;

    public Doctor(int doctorId, String firstName, String lastName, String specialty, String email) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

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

/*https://github.com/sravana1881/Healthcare_hibernate_1.0.1.git

 */
