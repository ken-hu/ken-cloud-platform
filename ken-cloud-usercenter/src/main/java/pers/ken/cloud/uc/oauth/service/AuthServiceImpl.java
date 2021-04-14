package pers.ken.cloud.uc.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.ken.cloud.uc.account.entity.User;
import pers.ken.cloud.uc.account.service.UserService;
import pers.ken.cloud.uc.oauth.model.AuthUser;

import java.util.Collections;

/**
 * <code>AuthService</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/14 22:23.
 *
 * @author _Ken.Hu
 */
@Component
public class AuthServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.get(username);
        if (null == user) {
            throw new UsernameNotFoundException("Cannot found username:" + username);
        }

        return new AuthUser(user.getUsername(), user.getPassword(), true, true, true, true, Collections.EMPTY_SET);
    }
}
