
package healthcare;

import healthcare.model.Appointment;
import healthcare.model.Office;
import healthcare.model.Patient;
import healthcare.model.Doctor;
import healthcare.repository.AppointmentRepositoryImpl;
import healthcare.repository.DoctorRepositoryImpl;
import healthcare.repository.OfficeRepositoryImpl;
import healthcare.service.AppointmentService;
import healthcare.service.DoctorService;
import healthcare.service.OfficeService;
import healthcare.service.PatientService;
import healthcare.repository.PatientRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.println("3. Appointment");
        System.out.println("4.Office");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            ManageDoctors();
        } else if (choice == 2) {
            ManagePatients();
        } else if (choice == 3) {
            ManageAppointments();
        } else if (choice == 4) {
            ManageOffices();

        }else {
            System.out.println("Invalid choice ");
        }
        System.out.println("Exiting Healthcare Management System.");
        scanner.close();
    }

    public static void ManageDoctors() {

        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");
        System.out.println("5. List All Doctors");


        int choice = scanner.nextInt();
        scanner.nextLine();
//
        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.println("Please enter the Specialty ");
                    newDoctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());

                    doctorService.createDoctor(newDoctor);  // Use service here
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    int doctortId = scanner.nextInt();
                    Doctor doctor = doctorService.getDoctorById(doctortId);  // Use service here
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Specialty: " + doctor.getSpecialty());
                        System.out.println("Email: " + doctor.getEmail());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3:
//                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctortId= scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    doctor = doctorService.getDoctorById(doctortId);  // Use service here
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());
                        System.out.print("Enter Doctor's Speciality: ");
                        doctor.setSpecialty(scanner.nextLine());
                        doctorService.updateDoctor(doctor);  // Use service here
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctortId = scanner.nextInt();
                    doctorService.deleteDoctor(doctortId);  // Use service here
                    System.out.println("Doctor deleted successfully.");
                    break;
                case 5:
                    System.out.println("Listing All Doctors:");
                    for (Doctor d : doctorService.getAllDoctors()) {
                        System.out.println(d);
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally{
            scanner.close();
            sessionFactory.close();
        }
    }
    public static void ManagePatients () {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);
        Scanner scanner = new Scanner(System.in);


        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. List all patients");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientService.createPatient(newPatient);  // Use service here
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(patient);  // Use service here
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    patientService.deletePatient(patientId);  // Use service here
                    System.out.println("Patient deleted successfully.");
                    break;
                case 5:
                    System.out.println("Listing All Patients:");
                    for (Patient p : patientService.getAllPatients()) {
                        System.out.println(p);
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
            scanner.close();
            sessionFactory.close();
        }
    }
    public static void ManageAppointments() {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();

        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. List All Appointments");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Appointment newAppointment = new Appointment();
                    System.out.print("Enter Patient ID: ");
                    Patient patient=new Patient();
                    patient.setPatientId(scanner.nextInt());
                    newAppointment.setPatient(patient);
                    scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    Doctor doctor = new Doctor();
                    doctor.setDoctorId(scanner.nextInt());
                    newAppointment.setDoctor(doctor);
                    scanner.nextLine();
                    System.out.print("Enter appointment date (YYYY-MM-DD): ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter Notes: ");
                    newAppointment.setNotes(scanner.nextLine());
                    appointmentService.createAppointment(newAppointment);  // Use service here
                    System.out.println("Appointment created successfully.");

                    // Now, add patient to doctor and doctor to patient
                    doctorService.addPatientToDoctor(doctor.getDoctorId(), patient);
                    patientService.addDoctorToPatient(patient.getPatientId(), doctor);

                    System.out.println("Patient added to doctor and doctor added to patient successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);// Use service here

                    if (appointment != null) {
                        System.out.println("Patient ID: " + appointment.getPatient().getPatientId());
                        System.out.println("Doctor ID " + appointment.getDoctor().getDoctorId());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                        System.out.println(" Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    appointment = appointmentService.getAppointmentById(appointmentId);  // Use service here
                    if (appointment != null) {
                        System.out.print("Enter Patient Id: ");
                        Patient patient1 = new Patient();
                        patient1.setPatientId(scanner.nextInt());
                        appointment.setPatient(patient1);
                        scanner.nextLine();
                        System.out.print("Enter Doctor Id: ");
                        Doctor doctor1 = new Doctor();
                        doctor1.setDoctorId(scanner.nextInt());
                        appointment.setDoctor(doctor1);
                        scanner.nextLine();
                        System.out.print("Enter Appointment Date(YYYY-MM-DD): ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter Notes: ");
                        appointment.setNotes(scanner.nextLine());

                        // Check if there are any other appointments for the original patient and doctor
                        Doctor originalDoctor = appointment.getDoctor();
                        Patient originalPatient = appointment.getPatient();
                        if (!appointmentService.hasOtherAppointmentsBetween(originalDoctor.getDoctorId(), originalPatient.getPatientId())) {
                            // Remove the patient from the doctor and vice versa
                            doctorService.removePatientFromDoctor(originalDoctor.getDoctorId(), originalPatient);
                            patientService.removeDoctorFromPatient(originalPatient.getPatientId(), originalDoctor);
                        }

                        appointmentService.updateAppointment(appointment);
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointments not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();

                    // Retrieve the appointment object from the service layer (not repository directly)
                    appointment = appointmentService.getAppointmentById(appointmentId);

                    if (appointment == null) {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    Doctor doctorToCheck = appointment.getDoctor();
                    Patient patientToCheck=appointment.getPatient();
                    if (!appointmentService.hasOtherAppointmentsBetween(
                            doctorToCheck.getDoctorId(), patientToCheck.getPatientId())) {
                        doctorService.removePatientFromDoctor(doctorToCheck.getDoctorId(), patientToCheck);
                        patientService.removeDoctorFromPatient(patientToCheck.getPatientId(), doctorToCheck);
                    }
                    appointmentService.deleteAppointment(appointmentId);  // Use service here
                    System.out.println("Appointment deleted successfully.");
                    break;
                case 5:
                    System.out.println("Listing All Appointments:");
                    for (Appointment a : appointmentService.getAllAppointments()) {
                        System.out.println(a);
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
            scanner.close();
            sessionFactory.close();
        }
    }

    public static void ManageOffices(){
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);
        OfficeService officeService = new OfficeService(officeRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create an Office");
        System.out.println("2. Get Office Details");
        System.out.println("3. Update Office");
        System.out.println("4. Delete Office");
        System.out.println("5. List All Offices");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Office newOffice = new Office();
                System.out.print("Enter location: ");
                newOffice.setLocation(scanner.nextLine());
                System.out.print("Enter phone: ");
                newOffice.setPhone(scanner.nextLine());
                System.out.print("Enter doctor ID: ");
                Doctor doctor = new Doctor();
                doctor.setDoctorId(scanner.nextInt());
                newOffice.setDoctor(doctor);
                officeService.createOffice(newOffice);
                System.out.println("Office created successfully.");
                break;
            case 2:
                System.out.print("Enter Office ID: ");
                int officeId = scanner.nextInt();
                Office office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.println("Office ID: " + office.getOfficeId());
                    System.out.println("Location: " + office.getLocation());
                    System.out.println("Phone: " + office.getPhone());
                    System.out.println("Doctor ID: " + office.getDoctor().getDoctorId());
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 3:
                System.out.print("Enter Office ID: ");
                officeId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.print("Enter new location: ");
                    office.setLocation(scanner.nextLine());
                    System.out.print("Enter new phone: ");
                    office.setPhone(scanner.nextLine());
                    officeService.updateOffice(office);
                    System.out.println("Office updated successfully.");
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 4:
                System.out.print("Enter Office ID: ");
                officeId = scanner.nextInt();
                officeService.deleteOffice(officeId);
                System.out.println("Office deleted successfully.");
                break;
            case 5:
                System.out.println("Listing All Offices:");
                for (Office o : officeService.getAllOffices()) {
                    System.out.println(o);
                    System.out.println();
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    //
}
