package cn.edu.uestc.rms.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignRequest {

    @NotBlank
    String accountNum;

    @NotBlank
    String password;
}
