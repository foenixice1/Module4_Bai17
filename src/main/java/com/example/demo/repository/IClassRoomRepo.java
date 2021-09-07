package com.example.demo.repository;

import com.example.demo.model.ClassRoom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassRoomRepo extends PagingAndSortingRepository<ClassRoom, Integer> {
}
