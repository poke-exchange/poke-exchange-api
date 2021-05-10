package fr.esgi.poke_exchange_api.domain.authentication;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequestResponse {
    private final String token;
}
