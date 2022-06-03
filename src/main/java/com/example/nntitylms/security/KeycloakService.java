package com.example.nntitylms.security;

import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
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
    private final String clientID;

    public KeycloakService(Keycloak keycloak, @Value("${keycloak.realm}") String realmName, @Value("${keycloak.resource}") String clientId) {
        this.clientID = clientId;
        this.realmResource = keycloak.realm(realmName);
    }

    public void addUser(RegisterStudentDto registerStudentDto) {
        String createdUserId = createUser(registerStudentDto);
        getUser(createdUserId).resetPassword(createCredentialRepresentation(registerStudentDto.getPassword()));
//        addGroup(getUser(createdUserId));
    }

    private String createUser(RegisterStudentDto registerStudentDto) {
        try {
            return CreatedResponseUtil.getCreatedId(createUser(registerStudentDto.getDisplayName()));
        } catch (WebApplicationException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Creating user in Keycloak failed");
        }
    }

    private CredentialRepresentation createCredentialRepresentation(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

//    public void addGroup(UserResource user)  {
//        user.. (getGroup(STUDENT_GROUP));
//    }

    private Response createUser(String username) {
        return realmResource.users().create(createUserRepresentation(username));
    }

    private UserResource getUser(String userId) {
        return realmResource.users().get(userId);
    }

    private GroupRepresentation getGroup(String groupToAdd) {
        return realmResource.groups().groups()
                .stream()
                .filter(groupRepresentation -> groupRepresentation.getName().equals(groupToAdd))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group not found in Keycloak"));
    }

    private UserRepresentation createUserRepresentation(String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        user.setGroups(List.of(STUDENT_GROUP));
        return user;
    }
}
