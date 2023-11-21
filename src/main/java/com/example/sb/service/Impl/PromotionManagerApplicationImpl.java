
package com.example.sb.service.Impl;
        import com.example.sb.model.Entities.Promotions;
        import com.example.sb.model.dto.PromotionRequest;
        import com.example.sb.model.dto.PromotionsDto;
        import com.example.sb.model.mappers.Mapper;
        import com.example.sb.repo.PromotionRepository;
        import com.example.sb.service.PromotionManagerApplication;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@Service
public class PromotionManagerApplicationImpl implements PromotionManagerApplication {
    private final PromotionRepository repository;
    private final Mapper<Promotions, PromotionsDto> promotionmapper;
    @Autowired
    public PromotionManagerApplicationImpl(
            PromotionRepository repository,
            @Qualifier("promotionMapper") Mapper<Promotions, PromotionsDto> promotionmapper) {
        this.repository = repository;
        this.promotionmapper = promotionmapper;
    }
    @Override
    public PromotionsDto save(PromotionRequest promotionRequest) {
        Promotions PromotionEntity =promotionRequest.toModel();
        PromotionsDto createdPromotion = promotionmapper.mapTo(repository.save(PromotionEntity));
        return createdPromotion;
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
