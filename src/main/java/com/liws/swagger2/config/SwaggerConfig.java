package com.liws.swagger2.config;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// 访问swagger2: http://localhost:8080/doc.html
@Configuration
public class SwaggerConfig {

    private static final String SPLITOR = ";";
    /**
     * 切割扫描的包生成Predicate<RequestHandler>
     * @param basePackage
     * @return
     */
    public static Predicate<RequestHandler> scanBasePackage(final String basePackage) {
        if(StrUtil.isBlank(basePackage)){
            throw new NullPointerException("basePackage不能为空，多个包扫描使用"+SPLITOR+"分隔");
        }
        String[] controllerPack = basePackage.split(SPLITOR);
        Predicate<RequestHandler> predicate = null;
        for (int i = controllerPack.length -1; i >= 0 ; i--) {
            String strBasePackage = controllerPack[i];
            if(StrUtil.isNotBlank(strBasePackage)){
                Predicate<RequestHandler> tempPredicate = RequestHandlerSelectors.basePackage(strBasePackage);
                predicate = predicate == null ? tempPredicate : Predicates.or(tempPredicate,predicate);
            }
        }
        if(predicate == null){
            throw new NullPointerException("basePackage配置不正确，多个包扫描使用"+SPLITOR+"分隔");
        }
        return predicate;
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(scanBasePackage("com.liws.swagger2.usercontroller"+SPLITOR+"com.liws.swagger2.testcontroller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }


}
