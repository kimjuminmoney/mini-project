package com.mini.company.Schedule;

import lombok.Data;

@Data
public class EmailMessage {
    private String to;
    private String subject;
    private String message;
}
