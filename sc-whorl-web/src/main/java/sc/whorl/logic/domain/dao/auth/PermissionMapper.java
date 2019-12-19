package sc.whorl.logic.domain.dao.auth;

import org.springframework.stereotype.Repository;
import sc.whorl.logic.domain.model.auth.Permission;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface PermissionMapper extends MyMapper<Permission> {
}