package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.AddressesResponse;
import com.example.jdbc.dto.response.ContactsResponse;
import com.example.jdbc.dto.response.ProfilesResponse;
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
            Address_U address = new Address_U();
            address.setAddressLine(request.addresses().addressLine());
            address.setCity(request.addresses().city());
            address.setCountry(request.addresses().country());
            address.setAddressType(request.addresses().addressType());
            entity.setAddresses(address);
        }
        if (request.contacts() != null) {
            Contacts_U contacts = new Contacts_U();
            contacts.setContactType(request.contacts().contactType());
            contacts.setContactValue(request.contacts().contactValue());
            entity.setContacts(contacts);
        }
        if (request.profiles() != null) {
            Profile_U profiles = new Profile_U();
            profiles.setDateOfBirth(request.profiles().dateOfBirth());
            profiles.setGender(request.profiles().gender());
            profiles.setBio(request.profiles().bio());
            entity.setProfiles(profiles);
        }
        return entity;
    }

    public StudentResponse toResponse(Student entity, AddressesResponse addresses, ProfilesResponse profiles, ContactsResponse contacts) {
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