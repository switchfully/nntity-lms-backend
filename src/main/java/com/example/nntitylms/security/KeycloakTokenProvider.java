package com.example.nntitylms.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class KeycloakTokenProvider {
    private final Logger logger = LoggerFactory.getLogger(KeycloakTokenProvider.class);
        @Value("${keycloak.resource}")
    private String CLIENT_ID;
        @Value("${keycloak.credentials.secret}")
    private String CLIENT_SECRET;
    private static final String GRANT_TYPE = "password";

    public String getToken(String username, String password) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", CLIENT_ID);
        map.add("client_secret", CLIENT_SECRET);
        map.add("grant_type", GRANT_TYPE);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        logger.info("Trying to connect to Keycloak with " + username + ", " + CLIENT_ID + ", " + CLIENT_SECRET + ", " + GRANT_TYPE);
        ResponseEntity<Map> response =
                restTemplate.exchange("https://keycloak.switchfully.com/auth/realms/java-feb2022/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        Map.class);

        String token = (String) response.getBody().get("access_token");
        logger.info("received token from Keycloak: " + token);
        return token;
    }
}
