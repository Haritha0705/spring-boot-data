package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.StudentResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class  StudentMapper {

    public Student toEntity(StudentRequest request) {
        if (request == null) return null;
        Student entity = new Student();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setAge(request.age());
        
        if (request.addresses() != null) {
            Addresses addresses = new Addresses();
            addresses.setAddressLine(request.addresses().addressLine());
            addresses.setCity(request.addresses().city());
            addresses.setCountry(request.addresses().country());
            addresses.setAddressType(request.addresses().addressType());
            entity.setAddresses(addresses);
        }
        if (request.contacts() != null) {
            Contacts contacts = new Contacts();
            contacts.setContactType(request.contacts().contactType());
            contacts.setContactValue(request.contacts().contactValue());
            entity.setContacts(contacts);
        }
        if (request.profiles() != null) {
            Profiles profiles = new Profiles();
            profiles.setDateOfBirth(request.profiles().dateOfBirth());
            profiles.setGender(request.profiles().gender());
            profiles.setBio(request.profiles().bio());
            entity.setProfiles(profiles);
        }
        return entity;
    }

    public StudentResponse toResponse(Student entity, Addresses addresses, Profiles profiles, Contacts contacts) {
        if (entity == null) return null;
        return new StudentResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge(),
                addresses,
                profiles,
                contacts,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}