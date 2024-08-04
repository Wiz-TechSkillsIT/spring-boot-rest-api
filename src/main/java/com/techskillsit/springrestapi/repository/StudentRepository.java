package com.techskillsit.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.techskillsit.springrestapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

/*
 * JpaRepository<Interface> : 
 * 1. findAll() - List<T>
 * 2. findById(int id) : T
 * 3. deleteById(int id) : void 
 * 4. save(T) : T 
 * */
