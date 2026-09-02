package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.dto.response.StudentResponse;
import com.example.jdbc.exception.CourseNotFoundException;
import com.example.jdbc.mapper.PaymentsMapper;
import com.example.jdbc.model.*;
import com.example.jdbc.mapper.StudentMapper;
import com.example.jdbc.repository.*;
import com.example.jdbc.service.StudentService;
import com.example.jdbc.service.EnrollmentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ProfilesRepository profilesRepository;
    private final ContactsRepository contactsRepository;
    private final AddressesRepository addressesRepository;
    private final StudentMapper studentMapper;
    private final PaymentsMapper paymentsMapper;
    private final PaymentsRepository paymentsRepository;
    private final CoursesRepository coursesRepository;
    private final EnrollmentsService enrollmentsService;

    public StudentServiceImpl(StudentRepository studentRepository, ProfilesRepository profilesRepository, ContactsRepository contactsRepository, AddressesRepository addressesRepository, StudentMapper studentMapper, PaymentsMapper paymentsMapper, PaymentsRepository paymentsRepository, CoursesRepository coursesRepository, EnrollmentsService enrollmentsService) {
        this.studentRepository = studentRepository;
        this.profilesRepository = profilesRepository;
        this.contactsRepository = contactsRepository;
        this.addressesRepository = addressesRepository;
        this.studentMapper = studentMapper;
        this.paymentsMapper = paymentsMapper;
        this.paymentsRepository = paymentsRepository;
        this.coursesRepository = coursesRepository;
        this.enrollmentsService = enrollmentsService;
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {

        Student student = studentMapper.toEntity(request);

        int studentId = studentRepository.save(student);

        if (student.getAddresses() != null) {

            Addresses address = student.getAddresses();

            address.setStudentId(studentId);

            addressesRepository.save(address);
        }

        if (student.getContacts() != null) {

            Contacts contact = student.getContacts();

            contact.setStudentId(studentId);

            contactsRepository.save(contact);
        }

        if (student.getProfiles() != null) {

            Profiles profile = student.getProfiles();

            profile.setStudentId(studentId);

            profilesRepository.save(profile);
        }

        return studentMapper.toResponse(
                student,
                student.getAddresses(),
                student.getProfiles(),
                student.getContacts()
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.findAll().stream().map(e -> studentMapper.toResponse(e, null, null, null)).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getById(Long id) {
        return studentMapper.toResponse(studentRepository.findById(id), null, null, null);
    }

    @Override
    public int update(Long id, StudentRequest request) {
        Student entity = studentMapper.toEntity(request);
        return studentRepository.update(id, entity);
    }

    @Override
    public int delete(Long id) {
        return studentRepository.delete(id);
    }

    @Override
    public PaymentsResponse createPayment(Integer id, PaymentsRequest request) {

        Courses course = coursesRepository.findById(id);

        if (course == null) throw new CourseNotFoundException("Course not found");

        enrollmentsService.validateNotEnrolled(request.studentId(), course.getId());

        Payments payments = paymentsMapper.toEntity(request);
        payments.setStudentId(request.studentId());

        paymentsRepository.save(payments);

        enrollmentsService.enroll(request.studentId(), course.getId());

        return paymentsMapper.toResponse(payments);
    }
}