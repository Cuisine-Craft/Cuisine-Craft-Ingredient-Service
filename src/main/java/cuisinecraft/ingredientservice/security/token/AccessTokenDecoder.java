package cuisinecraft.ingredientservice.security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}