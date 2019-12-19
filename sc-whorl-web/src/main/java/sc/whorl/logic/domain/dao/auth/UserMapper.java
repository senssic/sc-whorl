package sc.whorl.logic.domain.dao.auth;

import org.springframework.stereotype.Repository;

import java.util.List;

import sc.whorl.logic.domain.model.auth.Role;
import sc.whorl.logic.domain.model.auth.User;
import sc.whorl.system.commons.MyMapper;

@Repository
public interface UserMapper extends MyMapper<User> {
    List<Role> selectRolsByUserId(Long userId);
}