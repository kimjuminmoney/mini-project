package com.mini.company.domain.member;

import jakarta.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role,String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null){
            throw new IllegalArgumentException("Role가 null입니다");
        }
        return role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isBlank()){
            throw new IllegalArgumentException("dbData가 비어있습니다");
        }
        return Role.valueOf(dbData);
    }
}
