package com.example.sb.repo;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.Rayon;
import com.example.sb.model.Entities.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponsableRepository  extends JpaRepository<Responsable,Long> {
    public List<Responsable> findAllByEmail(String email);
    public Optional<Responsable> findByEmail(String email);

    public List<Responsable> findAllByAdmin(Admin admin);
    public List<Responsable> findAllByRayon(Rayon rayon);
}