package cn.biuaxia.code.aggregatelogin;

import cn.biuaxia.code.aggregatelogin.core.Core;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class AggregateLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregateLoginApplication.class, args);
    }

    @Bean
    public Core core() {
        // 读取配置文件
        Props prop = Props.getProp("classpath:app.config");
        log.debug("{}", JSONUtil.toJsonPrettyStr(prop));
        // 将读取到的内容填充到对象
        return prop.toBean(Core.class);
    }

}
