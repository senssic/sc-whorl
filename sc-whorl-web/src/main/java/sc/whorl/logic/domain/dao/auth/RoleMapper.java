package sc.whorl.logic.domain.dao.auth;

import org.springframework.stereotype.Repository;
import sc.whorl.logic.domain.model.auth.Role;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface RoleMapper extends MyMapper<Role> {
}