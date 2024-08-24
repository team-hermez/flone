package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlySignupResponse {
    private String month;
    private int count;
}