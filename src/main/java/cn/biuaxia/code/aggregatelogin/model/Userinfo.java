package cn.biuaxia.code.aggregatelogin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Userinfo implements Serializable {

    private String msg;
    private String faceimg;
    private Integer code;
    private String gender;
    private String ip;
    private String type;
    private String socialUid;
    private String accessToken;
    private String nickname;
    private String location;

}
