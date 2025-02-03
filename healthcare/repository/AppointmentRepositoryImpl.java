package healthcare.repository;

import healthcare.model.Appointment;
import healthcare.model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppointmentRepositoryImpl {
    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public void createAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
        }
    }
    public Appointment getAppointmentById(int appointmentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Appointment.class, appointmentId);
        }
    }

    public void updateAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(appointment);
            transaction.commit();
        }
    }

    public void deleteAppointment(int appointmentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            if (appointment != null) {
                session.delete(appointment);
            }
            transaction.commit();
        }
    }

    public List<Appointment> getAllAppointments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Appointment ", Appointment.class).list();
        }
    }

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        try (Session session = sessionFactory.openSession()) {
            String query = "SELECT COUNT(a) FROM Appointment a " +
                    "WHERE a.doctor.doctorId = :doctorId " +
                    "AND a.patient.patientId = :patientId";
            Long count = session.createQuery(query, Long.class)
                    .setParameter("doctorId", doctorId)
                    .setParameter("patientId", patientId)
                    .uniqueResult();
            return count != null && count > 1;
        }
    }

}
