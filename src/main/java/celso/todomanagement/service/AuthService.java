package celso.todomanagement.service;

import celso.todomanagement.dto.LoginDto;
import celso.todomanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
