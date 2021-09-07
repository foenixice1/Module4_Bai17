package com.example.demo.validate;

import com.example.demo.model.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class StudentValidate implements Validator {
    @Autowired
    IStudentService iStudentService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Iterable<Student> list = iStudentService.findAll();
        Student student = (Student) target;
        for (Student s : list) {
            if(s.getEmail().equals(student.getEmail())) {
                errors.rejectValue("email" , "email.duplicate");
                break;
            }
        }
    }
}
