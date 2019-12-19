package sc.whorl.logic.domain.dao.auth;

import org.springframework.stereotype.Repository;
import sc.whorl.logic.domain.model.auth.RolePermission;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface RolePermissionMapper extends MyMapper<RolePermission> {
}