package healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name="Offices")
@Getter
@Setter
@ToString(exclude = "doctors")
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="OfficeID")
    int officeId;
    @Column(name="Location")
    String location;
    @Column(name="Phone")
    String phone;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="DoctorID")
    Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return officeId == office.officeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId);

    }

    @Override
    public String toString() {
        return "Office{" +
                "officeId=" + officeId +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", doctorId=" + (doctor != null ? doctor.getDoctorId() : "N/A") +
                '}';
    }
}
