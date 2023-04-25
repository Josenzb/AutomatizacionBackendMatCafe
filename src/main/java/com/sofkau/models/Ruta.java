package com.sofkau.models;
import lombok.Data;

import java.util.List;

@Data
public class Ruta {
    private String duration;
    private List<String> courses;
    private String adminId;
    private String description;
    private String title;

}
