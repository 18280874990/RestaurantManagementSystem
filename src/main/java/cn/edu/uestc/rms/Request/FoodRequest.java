package cn.edu.uestc.rms.Request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class FoodRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String sizePrice;

    @NotBlank
    private MultipartFile image;

    @NotBlank
    private String description;

    @NotBlank
    private String type;
}
