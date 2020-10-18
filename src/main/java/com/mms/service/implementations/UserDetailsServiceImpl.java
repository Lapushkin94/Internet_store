package com.mms.service.implementations;

import com.mms.model.ClientEntity;
import com.mms.model.RoleEntity;
import com.mms.repository.interfaces.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        ClientEntity clientEntity;
        try {
            logger.info("finding client by email " + email);
            clientEntity = clientRepository.findByEmail(email);
            }
        catch (NoResultException e) {
            logger.info("fail finding client by email " + email);
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = buildUserAuthority(clientEntity.getRole());
        return buildUserForAuthentication(clientEntity,authorities);
    }

    private User buildUserForAuthentication(ClientEntity client, List<GrantedAuthority> authorities) {
        return new User(client.getEmail(),client.getPassword(),authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(RoleEntity clientRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(clientRole.getName()));
        return new ArrayList<>(setAuths);
    }

}
