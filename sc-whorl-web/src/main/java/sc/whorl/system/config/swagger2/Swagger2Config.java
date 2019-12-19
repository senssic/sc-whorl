package sc.whorl.system.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	/**
	 * 通过 createRestApi函数来构建一个DocketBean
	 * 函数名,可以随意命名,喜欢什么命名就什么命名
	 */
	@Bean
    public Docket createRestApi() {
    	return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("sc.whorl.logic.rmiservice.units.service"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.enableUrlTemplating(true);
    }
    //构建 api文档的详细信息函数
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("RESTful API")
				//创建人
				.contact("sc-whorl")
				//版本号
				.version("1.0")
				//描述
				.description("初始化api")
				.build();
	}
}
