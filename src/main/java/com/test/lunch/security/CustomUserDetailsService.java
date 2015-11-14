package com.test.lunch.security;

import com.test.lunch.model.UserModel;
import com.test.lunch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * UserModel authentication service
 */
@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Value("${default.admin.name}")
    private String defaultAdminName;

    @Value("${default.admin.password}")
    private String defaultAdminPassword;

    @Autowired
    private UserRepository userRepository;

    private UserModel defaultAdmin;

    @PostConstruct
    public void init()
    {
        defaultAdmin = new UserModel(defaultAdminName, defaultAdminPassword, true);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        UserModel user = userRepository.findByLogin(login);

        if(user == null)
        {
            if ((defaultAdminName.equals(login)) && (userRepository.count() == 0))
            {
                user = defaultAdmin;
            }
            else
            {
                throw new UsernameNotFoundException(String.format("UserModel with the username %s doesn't exist", login));
            }
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getIsAdmin() ? "ROLE_ADMIN" : "ROLE_USER");
        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
