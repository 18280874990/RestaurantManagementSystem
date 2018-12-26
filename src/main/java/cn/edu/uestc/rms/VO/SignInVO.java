package cn.edu.uestc.rms.VO;

import lombok.Data;

@Data
public class SignInVO {
    private String type;
    private Boolean success;
    private String msg;
}
