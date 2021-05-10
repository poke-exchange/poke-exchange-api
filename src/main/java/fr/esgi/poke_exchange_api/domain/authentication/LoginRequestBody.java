package fr.esgi.poke_exchange_api.domain.authentication;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequestBody {
    private final String username;
    private final String password;
}
