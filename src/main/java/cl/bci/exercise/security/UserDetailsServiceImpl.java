package cl.bci.exercise.security;

import cl.bci.exercise.repositories.AdminRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl
implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername (String email)
    throws UsernameNotFoundException {

        val adminEntity = adminRepository.findByEmail (email);

        return new UserDetailsImpl (adminEntity);

    }

}