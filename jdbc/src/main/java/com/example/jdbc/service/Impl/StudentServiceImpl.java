package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.*;
import com.example.jdbc.exception.CourseNotFoundException;
import com.example.jdbc.model.*;
import com.example.jdbc.mapper.StudentMapper;
import com.example.jdbc.mapper.AddressesMapper;
import com.example.jdbc.mapper.ProfilesMapper;
import com.example.jdbc.mapper.ContactsMapper;
import com.example.jdbc.repository.*;
import com.example.jdbc.service.StudentService;
import com.example.jdbc.service.EnrollmentsService;
import com.example.jdbc.service.OrderService;
import com.example.jdbc.service.PaymentsService;
import com.example.jdbc.service.NotificationsService;
import com.example.jdbc.dto.request.PurchaseRequest;
import com.example.jdbc.dto.request.NotificationsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository studentRepository;
    private final ProfilesRepository profilesRepository;
    private final ContactsRepository contactsRepository;
    private final AddressesRepository addressesRepository;
    private final StudentMapper studentMapper;
    private final AddressesMapper addressesMapper;
    private final ProfilesMapper profilesMapper;
    private final ContactsMapper contactsMapper;
    private final CoursesRepository coursesRepository;
    private final EnrollmentsService enrollmentsService;
    private final OrderService orderService;
    private final PaymentsService paymentsService;
    private final NotificationsService notificationsService;

    public StudentServiceImpl(UserRepository studentRepository, ProfilesRepository profilesRepository, ContactsRepository contactsRepository, AddressesRepository addressesRepository, StudentMapper studentMapper, AddressesMapper addressesMapper, ProfilesMapper profilesMapper, ContactsMapper contactsMapper, CoursesRepository coursesRepository, EnrollmentsService enrollmentsService, OrderService orderService, PaymentsService paymentsService, NotificationsService notificationsService) {
        this.studentRepository = studentRepository;
        this.profilesRepository = profilesRepository;
        this.contactsRepository = contactsRepository;
        this.addressesRepository = addressesRepository;
        this.studentMapper = studentMapper;
        this.addressesMapper = addressesMapper;
        this.profilesMapper = profilesMapper;
        this.contactsMapper = contactsMapper;
        this.coursesRepository = coursesRepository;
        this.enrollmentsService = enrollmentsService;
        this.orderService = orderService;
        this.paymentsService = paymentsService;
        this.notificationsService = notificationsService;
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {

        Student student = studentMapper.toEntity(request);

        int studentId = studentRepository.save(student);

        if (student.getAddresses() != null) {

            Address_U address = student.getAddresses();

            address.setStudentId(studentId);

            addressesRepository.save(address);
        }

        if (student.getContacts() != null) {

            Contacts_U contact = student.getContacts();

            contact.setStudentId(studentId);

            contactsRepository.save(contact);
        }

        if (student.getProfiles() != null) {

            Profile_U profile = student.getProfiles();

            profile.setStudentId(studentId);

            profilesRepository.save(profile);
        }

        return studentMapper.toResponse(
                student,
                addressesMapper.toResponse(student.getAddresses()),
                profilesMapper.toResponse(student.getProfiles()),
                contactsMapper.toResponse(student.getContacts())
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.findAll().stream().map(e -> studentMapper.toResponse(e, null, null, null)).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getById(int id) {
        return studentMapper.toResponse(studentRepository.findById(id), null, null, null);
    }

    @Override
    public int update(int id, StudentRequest request) {
        Student entity = studentMapper.toEntity(request);
        return studentRepository.update(id, entity);
    }

    @Override
    public int delete(int id) {
        return studentRepository.delete(id);
    }

    @Override
    @Transactional
    public String purchaseCourse(Integer studentId, Integer courseId, PurchaseRequest request) {
        // 1. Fetch Course
        Course_U course = coursesRepository.findById(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course not found");
        }

        // 2. Validate Not Enrolled
        enrollmentsService.validateNotEnrolled(studentId, courseId);

        // 3. Create Pending Order
        Order_U order = orderService.createPendingOrder(studentId, course);

        // 4. Create Pending Payment
        Payment_U payment = paymentsService.createPendingPayment(order, request);

        // 5. Process Payment
        PaymentResult result = paymentsService.process(payment);

        if (result.success()) {
            // 6a. Handle Success
            paymentsService.markSuccessful(payment.getId(), result.transactionId());
            orderService.markPaid(order.getId());
            enrollmentsService.enroll(studentId, courseId);

            NotificationsRequest notifReq = new NotificationsRequest(studentId, "Course Purchase Successful", "You have successfully purchased and enrolled in " + course.getName(), false);
            notificationsService.create(notifReq);

            return "Successfully purchased course: " + course.getName();
        } else {
            // 6b. Handle Failure
            paymentsService.markFailed(payment.getId(), result.message());
            orderService.markFailed(order.getId());

            NotificationsRequest notifReq = new NotificationsRequest(studentId, "Course Purchase Failed", "Payment failed for course " + course.getName() + ". Reason: " + result.message(), false);
            notificationsService.create(notifReq);

            throw new RuntimeException("Payment failed: " + result.message());
        }
    }
}