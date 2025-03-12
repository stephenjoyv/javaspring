package ru.mtuci.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mtuci.demo.model.ApplicationUser;
import ru.mtuci.demo.model.UserDetailsImpl;
import ru.mtuci.demo.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден!"));

        return UserDetailsImpl.fromApplicationUser(user);

    }

    public Optional<ApplicationUser> getUserById(Long id) {

        return userRepository.findById(id);

    }

    public Optional<ApplicationUser> getUserByEmail(String email) {

        return userRepository.findByEmail(email);

    }

}