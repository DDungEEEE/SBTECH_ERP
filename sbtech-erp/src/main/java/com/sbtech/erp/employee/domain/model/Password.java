package com.sbtech.erp.employee.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "employee_password", nullable = false)
    private String value;

    private Password(String value){
        this.value = value;
    }

    public static Password encoded(String encodedPassword){
        return new Password(encodedPassword);

    }
}
