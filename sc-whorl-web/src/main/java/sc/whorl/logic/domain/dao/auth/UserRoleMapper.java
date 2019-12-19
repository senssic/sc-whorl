package sc.whorl.logic.domain.dao.auth;

import org.springframework.stereotype.Repository;
import sc.whorl.logic.domain.model.auth.UserRole;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface UserRoleMapper extends MyMapper<UserRole> {
}