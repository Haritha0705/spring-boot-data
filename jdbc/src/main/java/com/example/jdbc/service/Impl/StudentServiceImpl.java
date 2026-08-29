package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.StudentResponse;
import com.example.jdbc.model.Addresses;
import com.example.jdbc.model.Contacts;
import com.example.jdbc.model.Profiles;
import com.example.jdbc.model.Student;
import com.example.jdbc.mapper.StudentMapper;
import com.example.jdbc.repository.AddressesRepository;
import com.example.jdbc.repository.ContactsRepository;
import com.example.jdbc.repository.ProfilesRepository;
import com.example.jdbc.repository.StudentRepository;
import com.example.jdbc.service.StudentService;
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
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ProfilesRepository profilesRepository, ContactsRepository contactsRepository, AddressesRepository addressesRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.profilesRepository = profilesRepository;
        this.contactsRepository = contactsRepository;
        this.addressesRepository = addressesRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {

        // 1. Request DTO -> Student model
        Student student = mapper.toEntity(request);

        // 2. Create student first
        int studentId = studentRepository.save(student);

        // 3. Save Address
        if (student.getAddresses() != null) {

            Addresses address = student.getAddresses();

            address.setStudentId(studentId);

            addressesRepository.save(address);
        }

        // 4. Save Contact
        if (student.getContacts() != null) {

            Contacts contact = student.getContacts();

            contact.setStudentId(studentId);

            contactsRepository.save(contact);
        }

        // 5. Save Profile
        if (student.getProfiles() != null) {

            Profiles profile = student.getProfiles();

            profile.setStudentId(studentId);

            profilesRepository.save(profile);
        }

        // 6. Return response
        return mapper.toResponse(
                student,
                student.getAddresses(),
                student.getProfiles(),
                student.getContacts()
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.findAll().stream().map(e -> mapper.toResponse(e, null, null, null)).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getById(Long id) {
        return mapper.toResponse(studentRepository.findById(id), null, null, null);
    }

    @Override
    public int update(Long id, StudentRequest request) {
        Student entity = mapper.toEntity(request);
        return studentRepository.update(id, entity);
    }

    @Override
    public int delete(Long id) {
        return studentRepository.delete(id);
    }

}