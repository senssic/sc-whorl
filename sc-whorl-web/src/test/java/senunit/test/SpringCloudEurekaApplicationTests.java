package senunit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * SpringBoot提供了spring-boot-start-test启动器，该启动器提供了常见的单元测试库：
 * JUnit： 一个Java语言的单元测试框架
 * Spring Test & Spring Boot Test：为Spring Boot应用提供集成测试和工具支持
 * AssertJ：支持流式断言的Java测试框架
 * Hamcrest：一个匹配器库
 * Mockito：一个java mock框架
 * JSONassert：一个针对JSON的断言库
 * JsonPath：JSON XPath库
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//此注解自动注入MockMvc
@AutoConfigureMockMvc
public class SpringCloudEurekaApplicationTests {
    @Autowired
    private MockMvc mvc;


    @Test
    public void testJsonUserDetail() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzYwMjE3ODM2OSIsIkpXVF9MT0dJTl9OQU1FIjoiMTc2MDIxNzgzNjkiLCJKV1RfTE9HSU5fVElNRSI6MTU0NTIxMjI3MDczNCwiSldUX0xPR0lOX1VTRVJJRCI6MTU5NDU2LCJKV1RfTE9HSU5fVVNFUlRZUEUiOiJVc2VyIiwiZXhwIjoxNTQ3ODA0MjcwLCJpYXQiOjE1NDUyMTIyNzAsImp0aSI6IjAwN2RhZWI1LWVjNTYtNGQ4Ny05MmQ1LTM3Nzg0ZTU5Yzk0MCJ9.8v8kXAtOx1OpWTzARrdpAUFP6Wolgw0oBx6SvmmCRVA");
        mvc.perform(MockMvcRequestBuilders.post("/jsonUser/12312").
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

}
