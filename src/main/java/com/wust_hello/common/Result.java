package com.wust_hello.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private Integer code;//0失败，1成功
    private String message;//响应信息
    private Object data;//响应数据
    //失败响应
    //增删改成功响应
    public static Result success(){return new Result(1,"success",null);}
    public static Result success(Object data){return new Result(1,"success",data);}
    public static Result error(String message){return new Result(0,message,null);}
}
