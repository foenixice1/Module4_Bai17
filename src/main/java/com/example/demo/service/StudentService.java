package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepo iStudentRepo;

    @Override
    public Iterable findAll() {
        return iStudentRepo.findAll();
    }

    @Override
    public Optional findById(Integer id) {
        return iStudentRepo.findById(id);
    }

    @Override
    public void save(Student student) {
        iStudentRepo.save(student);

    }

    @Override
    public void delete(Integer id) {
        iStudentRepo.deleteById(id);
    }

    @Override
    public ArrayList<Student> findAllByName(String name) {
        return iStudentRepo.findAllByName(name);
    }

    @Override
    public ArrayList<Student> showAllStudent() {
        return iStudentRepo.sortAllStudent();
    }

    @Override
    public Page<Student> fillAll(Pageable pageable) {
        return iStudentRepo.findAll(pageable);
    }
}
