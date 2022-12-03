package cl.bci.exercise.security;

import cl.bci.exercise.entities.AdminEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl
implements UserDetails {

    private AdminEntity adminEntity;

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities () {

        return Collections.emptyList ();

    }

    @Override
    public String getPassword () {

        return adminEntity.getPassword ();

    }

    @Override
    public String getUsername () {

        return adminEntity.getEmail ();

    }

    @Override
    public boolean isAccountNonExpired () {

        return true;

    }

    @Override
    public boolean isAccountNonLocked () {

        return true;

    }

    @Override
    public boolean isCredentialsNonExpired () {

        return true;

    }

    @Override
    public boolean isEnabled () {

        return true;

    }

    public String getName () {

        return adminEntity.getUsername ();

    }

}