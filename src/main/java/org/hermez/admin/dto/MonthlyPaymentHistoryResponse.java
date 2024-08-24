package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyPaymentHistoryResponse {
    private String month;
    private int revenue;
    private int refund;
}
