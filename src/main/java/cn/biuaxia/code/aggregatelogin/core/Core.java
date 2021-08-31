package cn.biuaxia.code.aggregatelogin.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Core {

    private String appID;
    private String appKey;
    private String redirectUrl;

}
