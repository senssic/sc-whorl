package sc.whorl.logic.domain.dao.system;

import org.springframework.stereotype.Repository;

import java.util.List;

import sc.whorl.logic.domain.model.system.Menu;
import sc.whorl.logic.domain.model.system.ext.ExtMenu;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface MenuMapper extends MyMapper<Menu> {

    public List<ExtMenu> selectMenuByUserId(Long userId);
}