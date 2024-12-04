package cuisinecraft.ingredientservice.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
