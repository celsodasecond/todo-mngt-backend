package celso.todomanagement.service.impl;

import celso.todomanagement.dto.RegisterDto;
import celso.todomanagement.entity.Role;
import celso.todomanagement.entity.User;
import celso.todomanagement.exception.TodoAPIException;
import celso.todomanagement.repository.RoleRepository;
import celso.todomanagement.repository.UserRepository;
import celso.todomanagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto registerDto) {
        // Check username if it already exists in the db
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        // Check email if it already exists in the db
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User has been successfully registered";
    }
}
