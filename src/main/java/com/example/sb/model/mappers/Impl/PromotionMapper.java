
package com.example.sb.model.mappers.Impl;

        import com.example.sb.model.Entities.Promotions;
        import com.example.sb.model.Entities.Rayon;
        import com.example.sb.model.dto.PromotionsDto;
        import com.example.sb.model.dto.RayonDto;
        import com.example.sb.model.mappers.Mapper;
        import org.modelmapper.ModelMapper;
        import org.springframework.stereotype.Component;

@Component

public class PromotionMapper implements Mapper<Promotions, PromotionsDto> {

    private final ModelMapper modelMapper;

    public PromotionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PromotionsDto mapTo(Promotions promotions) {
        return modelMapper.map(promotions, PromotionsDto.class);
    }

    @Override
    public Promotions mapFrom(PromotionsDto promotionsDto) {
        return modelMapper.map(promotionsDto, Promotions.class);
    }



}
