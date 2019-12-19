package sc.whorl.logic.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import sc.whorl.logic.domain.dao.auth.PermissionMapper;
import sc.whorl.logic.domain.dao.system.MenuMapper;
import sc.whorl.logic.domain.model.auth.Permission;
import sc.whorl.logic.domain.model.system.Menu;
import sc.whorl.logic.domain.model.system.ext.ExtMenu;
import sc.whorl.logic.pojo.system.ChildMenuInfo;
import sc.whorl.logic.pojo.system.MenuInfo;
import sc.whorl.system.commons.PageResponse;
import sc.whorl.system.commons.base.BaseService;
import sc.whorl.system.config.springsecurity.utils.UserAuthInfoUtils;
import sc.whorl.system.utils.ScUtils;
import sc.whorl.system.utils.mapper.BeanMapper;
import sc.whorl.web.vo.system.MenuListQueryRequest;
import sc.whorl.web.vo.system.MenuPremInfo;
import tk.mybatis.mapper.entity.Example;

/**
 * 菜单相关管理服务
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
@Slf4j
public class MenuService extends BaseService<MenuMapper, Menu> {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public void upMenu(Long menuId, Menu menu) {
        menu.setTid(menuId);
        updateByPrimaryKeySelective(menu);
    }

    public List<MenuInfo> getMenu() {
        List<MenuInfo> menuInfos = new ArrayList<>();
        if (!ScUtils.isEmpty(UserAuthInfoUtils.getUserId())) {
            List<ExtMenu> menuList = menuMapper.selectMenuByUserId(UserAuthInfoUtils.getUserId());
            if (!ScUtils.isEmpty(menuList)) {
                Map<Long, MenuInfo> parMenuInfo = new HashMap<>();
                for (ExtMenu menu : menuList) {
                    if (parMenuInfo.containsKey(menu.getParnetId())) {
                        MenuInfo menuInfo = parMenuInfo.get(menu.getParnetId());
                        menuInfo.getChildMenuList().add(BeanMapper.map(menu, ChildMenuInfo.class));
                    } else {
                        MenuInfo menuInfo = new MenuInfo();
                        menuInfo.setParentId(menu.getParnetId());
                        menuInfo.setParentMenuName(menu.getParentMenuName());
                        menuInfo.setChildMenuList(ScUtils.transList(BeanMapper.map(menu, ChildMenuInfo.class)));
                        parMenuInfo.put(menu.getParnetId(), menuInfo);
                    }
                }
                menuInfos.addAll(parMenuInfo.values());
            }
        }
        return menuInfos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delMenu(Long menuId) {
        Menu menu = selectByPrimaryKey(menuId);
        if (!ScUtils.isEmpty(menu) && !ScUtils.isEmpty(menu.getPermissionId())) {
            permissionMapper.deleteByPrimaryKey(menu.getPermissionId());
        }
        this.deleteByPrimaryKey(menuId);
    }

    public PageResponse<Menu> searchListMenu(MenuListQueryRequest menuRequest) {
        PageHelper.startPage(menuRequest.getPageIndex(), menuRequest.getPageSize());
        Example example = new Example(Menu.class);
        example.createCriteria().andNotEqualTo("parentId", "0");
        List<Menu> menuList = selectByExample(example);
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        return new PageResponse(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMenu(MenuPremInfo menuPremInfo) {
        Permission permission = new Permission();
        permission.setPermissionName(menuPremInfo.getMenuname());
        permission.setDescription(menuPremInfo.getMenuname());
        permission.setFunctionNumber(menuPremInfo.getFunctionNumber());
        permission.setCreateAt(new Date());
        permission.setCreateBy(UserAuthInfoUtils.getLoginName());
        permission.setModifyAt(new Date());
        permission.setModifyBy(UserAuthInfoUtils.getLoginName());
        permissionMapper.insertSelective(permission);
        Menu menu = new Menu();
        menu.setParentId(menuPremInfo.getParentid());
        menu.setMenuUrl(menuPremInfo.getMenuurl());
        menu.setMenuName(menuPremInfo.getMenuname());
        menu.setPermissionId(permission.getTid());
        menu.setStatus("EBL");
        menu.setApplicationCode(menuPremInfo.getApplicationcode());
        menu.setCreateAt(new Date());
        menu.setCreateBy(UserAuthInfoUtils.getLoginName());
        menu.setModifyAt(new Date());
        menu.setModifyBy(UserAuthInfoUtils.getLoginName());
        menu.setOrderIndex(menuPremInfo.getOrderIndex());
        insertSelective(menu);
    }
}
