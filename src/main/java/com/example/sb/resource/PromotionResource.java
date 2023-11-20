
package com.example.sb.resource;

        import com.example.sb.model.dto.PromotionRequest;
        import com.example.sb.model.dto.PromotionsDto;

        import com.example.sb.service.Impl.PromotionManagerApplicationImpl;
        import lombok.AllArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/promotions")
public class PromotionResource extends Resource<PromotionsDto, PromotionRequest,Long>{
    @Autowired
    public void setService(
            PromotionManagerApplicationImpl service) {
        this.service = service;
    }

}