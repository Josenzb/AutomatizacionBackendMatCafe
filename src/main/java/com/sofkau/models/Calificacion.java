package com.sofkau.models;

import lombok.Data;

@Data
public class Calificacion {
    private int grade;
    private String comment;
    private String courseId;
    private String learnerId;
}
