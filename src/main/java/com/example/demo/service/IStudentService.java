package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface IStudentService extends IGeneralService<Student> {
    ArrayList<Student> findAllByName(String name);
    ArrayList<Student> showAllStudent();
    Page<Student> fillAll(Pageable pageable);
}
