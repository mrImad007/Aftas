package com.example.sb.repo;

import com.example.sb.model.Entities.Promotions;
import com.example.sb.model.dto.PromotionsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions,Long> {
        }
