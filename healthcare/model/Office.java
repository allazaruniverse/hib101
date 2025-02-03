package healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@ToString(exclude = "doctors")
@Entity
@Table(name="Offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfficeID")
    private int officeId;
    @Column(name = "Location")
    private String location;
    @Column(name = "Phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;

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
}


