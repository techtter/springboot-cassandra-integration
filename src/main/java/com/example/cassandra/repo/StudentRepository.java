package com.example.cassandra.repo;

import com.example.cassandra.model.Student;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StudentRepository extends CassandraRepository<Student, Integer> {
}
