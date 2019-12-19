package sc.whorl.web;

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

import sc.whorl.web.vo.user.UserVo;
import sc.whorl.system.utils.mapper.JsonMapper;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginWebTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/auth/login").content("{\"userName\":\"senssic\",\"passWord\":\"123456\"}").contentType(MediaType.APPLICATION_JSON_UTF8).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();

    }

    @Test
    public void logout() throws Exception {
        //{"userId":1,"loginName":"senssic","userType":"User","jwtToken":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY1OTEyMTY4MDMsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDkxODMyMTYsImlhdCI6MTU0NjU5MTIxNiwianRpIjoiYzJlNzFjMDctYzJlOC00NDg2LTg0NDctZDNkNmI4MzZiYTI0In0.6Y9ZUvLhoKkXZdTvVzNnvOzztXzmhpzLaOJ4txsoCaI","loginTime":"2019-01-04T08:40:16.803+0000"}
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY1OTMyMDM3OTgsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDkxODUyMDMsImlhdCI6MTU0NjU5MzIwMywianRpIjoiNzUyNTAyMzUtOTdkMy00YmVjLTk4MjgtMGM4NmM3NWQzYWVkIn0.D7WmiYArdyvoqOk7kkYqL3MuTqwUPx_kF2dUHeui5vY");
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/user/logout").
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();

    }

    @Test
    public void register() throws Exception {
        UserVo userVo=new UserVo();
        userVo.setAccountname("senssic");
        userVo.setPassword("123456");
        userVo.setUserPhone("17602178369");

        mvc.perform(MockMvcRequestBuilders.post("/sc/user/auth/register").content(JsonMapper.nonDefaultMapper().toJson(userVo)).contentType(MediaType.APPLICATION_JSON_UTF8).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

}