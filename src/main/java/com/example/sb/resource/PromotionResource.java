
package com.example.sb.resource;

        import com.example.sb.model.dto.PromotionRequest;
        import com.example.sb.model.dto.PromotionsDto;

        import com.example.sb.service.Impl.PromotionManagerApplicationImpl;
        import lombok.AllArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/promotions")
public class PromotionResource extends Resource<PromotionsDto, PromotionRequest,Long>{
    @Autowired
    public void setService(
            PromotionManagerApplicationImpl service) {
        this.service = service;
    }
    private final PromotionManagerApplicationImpl promotionService;

    @GetMapping("{promotion_id}")
    public boolean findResource(@PathVariable("id") Long id) {
        return promotionService.isExist(id);
    }

    @PostMapping("/add_promotion")
    public PromotionsDto add_promotion(@RequestBody PromotionRequest promotionRequest) {
        try {
            PromotionsDto createdPromotion = promotionService.save(promotionRequest);
            return createdPromotion;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add promotion");
        }
    }


}
