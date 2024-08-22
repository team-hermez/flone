package org.hermez.instructor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Instructor {
    private int instructorId;
    private int subjectId;
    private String instructorDescription;
    private String instructorStatus;

    private int memberId;
    private int roleId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private LocalDateTime createdAt;

    public Instructor(int memberId, int roleId, String email, String password, String name, String phone, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Instructor(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
