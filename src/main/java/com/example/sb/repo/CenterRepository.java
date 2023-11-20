package com.example.sb.repo;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Centre,Long> {
}
