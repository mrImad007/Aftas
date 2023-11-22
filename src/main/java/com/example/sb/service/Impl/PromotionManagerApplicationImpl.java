
package com.example.sb.service.Impl;
        import com.example.sb.model.Entities.Categories;
        import com.example.sb.model.Entities.Produits;
        import com.example.sb.model.Entities.Promotions;
        import com.example.sb.model.dto.PromotionRequest;
        import com.example.sb.model.dto.PromotionsDto;
        import com.example.sb.model.mappers.Mapper;
        import com.example.sb.repo.CategoryRepository;
        import com.example.sb.repo.ProductRepository;
        import com.example.sb.repo.PromotionRepository;
        import com.example.sb.service.PromotionManagerApplication;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Service;
        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@Service
public class PromotionManagerApplicationImpl implements PromotionManagerApplication {
    private final PromotionRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final Mapper<Promotions, PromotionsDto> promotionmapper;
    @Autowired
    public PromotionManagerApplicationImpl(
            PromotionRepository repository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository, @Qualifier("promotionMapper") Mapper<Promotions, PromotionsDto> promotionmapper) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.promotionmapper = promotionmapper;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public PromotionsDto save(PromotionRequest promotionRequest) {
        try {
            System.out.println("Before save - PromotionRequest: " + promotionRequest);


            Categories categoriesEntity = categoryRepository.findById(promotionRequest.getCategorie_id())
                    .orElseThrow(() -> new RuntimeException("Categories not found"));

            Produits produitsEntity = productRepository.findById(promotionRequest.getCategorie_id())
                    .orElseThrow(() -> new RuntimeException("product not found"));

//            System.out.println("the product price:: "+ produitsEntity.getPrice());


            Promotions promotionsEntity = promotionRequest.toModel();
            promotionsEntity.setCategorie(categoriesEntity);

            System.out.println("Before save - Promotions: " + promotionsEntity);

            PromotionsDto createdPromotion = promotionmapper.mapTo(repository.save(promotionsEntity));
            return createdPromotion;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add promotion");
        }
    }



    @Override
    public List<PromotionsDto> getAll() {
        return repository.findAll()
                .stream()
                .map(promotionmapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionsDto update(final Long id, final PromotionsDto promotionsDto) {
        promotionsDto.setId(id);
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PromotionsDto find(final Long id) {
        Optional<Promotions> optionalEntity = repository.findById(id);
        return optionalEntity.map(promotionmapper::mapTo).orElse(null);
    }


    @Override
    public PromotionsDto partialUpdate(final Long id, final PromotionsDto promotionsDto) {
        promotionsDto.setId(id);
        var  responsable = promotionmapper.mapFrom(promotionsDto);

        return repository.findById(id).map(founded -> {
            Optional.ofNullable(responsable.getResponsable()).ifPresent(founded::setResponsable);
            Optional.ofNullable(responsable.getDatepromo()).ifPresent(founded::setDatepromo);
            Optional.ofNullable(responsable.getCategorie()).ifPresent(founded::setCategorie);
            Optional.ofNullable(responsable.getStatut()).ifPresent(founded::setStatut);
            Optional.ofNullable(responsable.getProduit()).ifPresent(founded::setProduit);
            Optional.ofNullable(responsable.getQuantity()).ifPresent(founded::setQuantity);
            return promotionmapper.mapTo(repository.save(founded));
        }).orElseThrow(() -> new RuntimeException("level not found"));
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


}
