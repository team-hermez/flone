package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMainResponse {
    int DailySignUpCount;
    int MonthlySignUpCount;
    int TotalSignUpCount;
}
