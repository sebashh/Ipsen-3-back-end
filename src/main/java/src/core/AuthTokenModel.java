package src.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class AuthTokenModel {

@NotNull
@JsonProperty("id")
private String id;

@NotNull
private String userId;

@NotNull
private String userRole;

@NotNull
private OffsetDateTime lastAccessUTC;

    public AuthTokenModel (String authTokenId, String userId, String userRole, OffsetDateTime lastAccessUTC) {
        this.id = authTokenId;
        this.userId = userId;
        this.userRole = userRole;
        this.lastAccessUTC = lastAccessUTC;
    }

    public AuthTokenModel (HashMap<String, String> authTokenResult) {
        if (authTokenResult != null) {
            this.id =authTokenResult.getOrDefault("id", null);
            this.userId = authTokenResult.getOrDefault("email", null);
            this.userRole = authTokenResult.getOrDefault("userRole", null);
            this.lastAccessUTC = OffsetDateTime.parse(String.valueOf(authTokenResult.get("lastAccessUTC")));
        }
    }

    public AuthTokenModel() {}


}
