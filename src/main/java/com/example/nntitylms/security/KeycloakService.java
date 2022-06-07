package com.example.nntitylms.security;

import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class KeycloakService {

    public static final String STUDENT_GROUP = "LMS-STUDENT";
    private final RealmResource realmResource;
    private final Logger logger = LoggerFactory.getLogger(KeycloakService.class);

    public KeycloakService(Keycloak keycloak, @Value("${keycloak.realm}") String realmName) {
        this.realmResource = keycloak.realm(realmName);
    }

    public void addUser(RegisterStudentDto registerStudentDto) {
        String createdUserId = createUser(registerStudentDto);
        getUser(createdUserId).resetPassword(createCredentialRepresentation(registerStudentDto.getPassword()));
    }

    private String createUser(RegisterStudentDto registerStudentDto) {
        try {
            return CreatedResponseUtil.getCreatedId(createRealmUser(registerStudentDto.getDisplayName()));
        } catch (WebApplicationException exception) {
            logger.error("Creating user in Keycloak failed :" + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Creating user in Keycloak failed");
        }
    }

    private CredentialRepresentation createCredentialRepresentation(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        logger.info("Initializing credential representation" + passwordCredentials);
        return passwordCredentials;
    }

    private Response createRealmUser(String username) {
        return realmResource.users().create(createUserRepresentation(username));
    }

    private UserResource getUser(String userId) {
        try {
            return realmResource.users().get(userId);
        } catch (WebApplicationException exception) {
            logger.error("Getting user in Keycloak failed :" + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Getting user in Keycloak failed");
        }
    }

    private UserRepresentation createUserRepresentation(String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        user.setGroups(List.of(STUDENT_GROUP));
        logger.info("Initializing user representation" + user);
        return user;
    }
}
