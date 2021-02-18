/*
 * Copyright 2020 SvenAugustus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.flysium.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 文档配置
 *
 * @author zeno
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile("development")
public class SwaggerConfiguration {

  @Bean
  public Docket docket() throws IOException {
    ApiInfo apiInfo = new ApiInfoBuilder()
      .title(" 慕夏手记 ")
      .version(" 1.0.0 ")
      .termsOfServiceUrl("https://github.com/SvenAugustus/much-notes/")
      .contact(new Contact("zeno",
        "https://github.com/SvenAugustus/much-notes/",
        "zeno531@outlook.com"))
      .licenseUrl("https://opensource.org/licenses/MIT")
      .build();
    // @formatter:off
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo)
      .useDefaultResponseMessages(false)
      .select()
      .apis(RequestHandlerSelectors.basePackage("xyz.flysium.web.controller"))
//        .paths(PathSelectors.ant("/mp/*/**"))
      .paths(PathSelectors.any())
      .build();
    // @formatter:on
  }

}
