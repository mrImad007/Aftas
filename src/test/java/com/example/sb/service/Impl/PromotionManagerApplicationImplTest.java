package com.example.sb.service.Impl;

import com.example.sb.model.Ennum.Statut;
import com.example.sb.model.Entities.Categories;
import com.example.sb.model.Entities.Produits;
import com.example.sb.model.Entities.Promotions;
import com.example.sb.model.Entities.Responsable;
import com.example.sb.model.dto.PromotionRequest;
import com.example.sb.model.dto.PromotionsDto;
import com.example.sb.model.mappers.Mapper;
import com.example.sb.repo.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PromotionManagerApplicationImplTest {

    @InjectMocks
    private PromotionManagerApplicationImpl promotionManager;

    @Mock
    private PromotionRepository repository;

    @Mock
    private Mapper<Promotions, PromotionsDto> promotionMapper;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        PromotionRequest promotionRequest = new PromotionRequest();

        Responsable responsable = new Responsable();
        responsable.setId(1L);
        responsable.setEmail("test@email.com");
        responsable.setPassword("password123");

        Categories categories = new Categories();
        categories.setId(1L);
        categories.setCategorie("Sample Category");

        Produits produits = new Produits();
        produits.setId(1L);
        produits.setProduit("Sample Product");
        produits.setCategorie(categories);

        Promotions promotionEntity = Promotions.builder()
                .id(1L)
                .responsable(responsable)
                .categorie(categories)
                .produit(produits)
                .datepromo(LocalDate.now())
                .statut(Statut.ACCEPTED) 
                .quantity(10)
                .build();

        PromotionsDto expectedPromotionDto = PromotionsDto.builder()
                .id(1L)
                .responsable(responsable)
                .categorie(categories)
                .produit(produits)
                .datepromo(LocalDate.now())
                .statut(Statut.ACCEPTED)
                .quantity(10)
                .build();

        // Mock repository save method
        when(repository.save(any())).thenReturn(promotionEntity);

        // Mock mapper
        when(promotionMapper.mapTo(any())).thenReturn(expectedPromotionDto);

        // Perform the save operation
        PromotionsDto actualPromotionDto = promotionManager.save(promotionRequest);

        // Verify that the save method of the repository was called with the expected entity
        verify(repository, times(1)).save(any(Promotions.class));

        // Verify that the mapper's mapTo method was called with the expected entity
        verify(promotionMapper, times(1)).mapTo(any());

        // Verify the result
        assertEquals(expectedPromotionDto, actualPromotionDto);
    }

    @Test
    public void testDelete() {
        // Mock data
        Long promotionId = 1L;

        // Call the service method
        promotionManager.delete(promotionId);

        // Verify that the repository deleteById method was called with the correct argument
        verify(repository).deleteById(promotionId);
    }
}
