package com.sofkau.models;
import java.util.List;
import lombok.Data;

@Data
public class Curso{
	private String duration;
	private String requirements;
	private String adminId;
	private String description;
	private String title;
	private List<String> content;
}