package com.example.jdbc.mapper;

import com.example.jdbc.dto.response.ContactsResponse;
import com.example.jdbc.model.Contacts_U;
import org.springframework.stereotype.Component;

@Component
public class ContactsMapper {

    public ContactsResponse toResponse(Contacts_U entity) {
        if (entity == null) return null;
        return new ContactsResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getContactType(),
                entity.getContactValue(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
