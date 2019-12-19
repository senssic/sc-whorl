package sc.whorl.web;

import com.sun.org.apache.xpath.internal.operations.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sc.whorl.logic.domain.model.system.Menu;
import sc.whorl.logic.pojo.system.MenuInfo;
import sc.whorl.logic.service.system.MenuService;
import sc.whorl.system.commons.MsgResponseBody;
import sc.whorl.system.commons.PageResponse;
import sc.whorl.system.utils.mapper.BeanMapper;
import sc.whorl.web.vo.system.MenuListQueryRequest;
import sc.whorl.web.vo.system.MenuPremInfo;
import sc.whorl.web.vo.system.UpMenuRequest;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/sc/user/system")
@Api(value = "SystemWeb", description = "系统相关接口,包括菜单缓存字典等")
public class SystemWeb {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMenu", method = RequestMethod.POST, produces = {"application/json"})
    public MsgResponseBody<MenuInfo> getMenu() {
        return MsgResponseBody.success().setResult(menuService.getMenu());
    }

    @ApiOperation(value = "删除菜单", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delMenu/{menuId}", method = RequestMethod.PUT)
    public MsgResponseBody<String> delMenu(@PathVariable Long menuId) {
        menuService.delMenu(menuId);
        return MsgResponseBody.success().setResult("删除成功!");
    }

    @ApiOperation(value = "添加菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST, produces = {"application/json"})
    public MsgResponseBody<String> addMenu(@RequestBody MenuPremInfo menuPremInfo) {
        menuService.addMenu(menuPremInfo);
        return MsgResponseBody.success().setResult("添加成功!");
    }

    @ApiOperation(value = "查询菜单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/searchListMenu", method = RequestMethod.POST, produces = {"application/json"})
    public MsgResponseBody<PageResponse<Menu>> searchListMenu(@RequestBody MenuListQueryRequest menuRequest) {
        return MsgResponseBody.success().setResult(menuService.searchListMenu(menuRequest));
    }


    @ApiOperation(value = "修改菜单", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/upMenu/{menuId}", method = RequestMethod.PUT)
    public MsgResponseBody<String> upMenu(@PathVariable Long menuId, @RequestBody UpMenuRequest upMenuRequest) {
        menuService.upMenu(menuId, BeanMapper.map(upMenuRequest,Menu.class));
        return MsgResponseBody.success().setResult("添加成功!");
    }


}
