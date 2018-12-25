package cn.edu.uestc.rms.VO;

import lombok.Data;

@Data
public class ResponseVO<T> {
    private T data;
    private Boolean success;
    private String msg;

    public ResponseVO<T> success(T data){
        this.data = data;
        this.success = true;
        return this;
    }

    public ResponseVO<T> fail(String msg){
        this.msg = msg;
        this.success = false;
        return this;
    }

}
