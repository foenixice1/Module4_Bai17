package com.example.demo.service;

import com.example.demo.model.ClassRoom;
import com.example.demo.repository.IClassRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements IClassService{
    @Autowired
    IClassRoomRepo iClassRoomRepo;
    @Override
    public Iterable findAll() {
        return iClassRoomRepo.findAll();
    }

    @Override
    public Optional findById(Integer id) {
        return iClassRoomRepo.findById(id);
    }

    @Override
    public void save(ClassRoom classRoom) {
        iClassRoomRepo.save(classRoom);
    }


    @Override
    public void delete(Integer id) {
        iClassRoomRepo.deleteById(id);
    }
}
