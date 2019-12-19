package sc.whorl.web;
import com.alibaba.fastjson.JSON;

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

import sc.whorl.web.vo.system.MenuPremInfo;
import sc.whorl.web.vo.system.MenuListQueryRequest;

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
public class SystemWebTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void getMenu() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY4NTkzNDkzMzEsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDk0NTEzNDksImlhdCI6MTU0Njg1OTM0OSwianRpIjoiNDJiNTQ0YzctMzRjNC00Mjc4LWFhZWMtNTU5MmQzYmJkM2ZjIn0.mq5NTHdrmmKke_bgQ6YOssbaaYfL1lGvft2E7Eq9SCE");
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/system/getMenu").
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();

    }

    @Test
    public void delMenu() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY4NTkzNDkzMzEsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDk0NTEzNDksImlhdCI6MTU0Njg1OTM0OSwianRpIjoiNDJiNTQ0YzctMzRjNC00Mjc4LWFhZWMtNTU5MmQzYmJkM2ZjIn0.mq5NTHdrmmKke_bgQ6YOssbaaYfL1lGvft2E7Eq9SCE");
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/system/delMenu/8").
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

    @Test
    public void addMenu() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY4NTkzNDkzMzEsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDk0NTEzNDksImlhdCI6MTU0Njg1OTM0OSwianRpIjoiNDJiNTQ0YzctMzRjNC00Mjc4LWFhZWMtNTU5MmQzYmJkM2ZjIn0.mq5NTHdrmmKke_bgQ6YOssbaaYfL1lGvft2E7Eq9SCE");
        MenuPremInfo menuPremInfo = new MenuPremInfo();
        menuPremInfo.setParentid(4L);
        menuPremInfo.setMenuurl("/URL/ADD/TEST");
        menuPremInfo.setMenuname("测试添加菜单");
        menuPremInfo.setApplicationcode("WHORL");
        menuPremInfo.setOrderIndex(0);
        menuPremInfo.setFunctionNumber("URL_ADDTEST_FUNCTION");
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/system/addMenu").content(JSON.toJSONString(menuPremInfo)).contentType(MediaType.APPLICATION_JSON_UTF8).
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

    @Test
    public void searchListMenu() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5zc2ljIiwiSldUX0xPR0lOX05BTUUiOiJzZW5zc2ljIiwiSldUX0xPR0lOX1RJTUUiOjE1NDY4NTkzNDkzMzEsIkpXVF9MT0dJTl9VU0VSSUQiOjEsIkpXVF9MT0dJTl9VU0VSVFlQRSI6IlVzZXIiLCJleHAiOjE1NDk0NTEzNDksImlhdCI6MTU0Njg1OTM0OSwianRpIjoiNDJiNTQ0YzctMzRjNC00Mjc4LWFhZWMtNTU5MmQzYmJkM2ZjIn0.mq5NTHdrmmKke_bgQ6YOssbaaYfL1lGvft2E7Eq9SCE");
        MenuListQueryRequest menuRequest = new MenuListQueryRequest();
        //menuRequest.setMenuName("");
        menuRequest.setPageIndex(1);
        menuRequest.setPageSize(25);
        mvc.perform(MockMvcRequestBuilders.post("/sc/user/system/searchListMenu").content(JSON.toJSONString(menuRequest)).contentType(MediaType.APPLICATION_JSON_UTF8).
                headers(httpHeaders).
                accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).
                andReturn();

    }



}