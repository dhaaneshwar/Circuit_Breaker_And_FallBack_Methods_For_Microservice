package com.example.department.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@Id
	@SequenceGenerator(
			name="department_sequence",
			sequenceName="department_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator="department_sequence"
	)
	private Long departmentId;
	private String departmentName;
	private String departmentAddress; 
	private String departmentCode;

}
