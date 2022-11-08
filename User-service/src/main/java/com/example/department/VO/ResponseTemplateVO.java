
//Wraper class
package com.example.department.VO;

import com.example.department.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
	
	private Department deparment;
	private User user;

}
