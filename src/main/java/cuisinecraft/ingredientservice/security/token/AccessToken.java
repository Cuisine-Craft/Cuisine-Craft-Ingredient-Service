package cuisinecraft.ingredientservice.security.token;

public interface AccessToken {
    String getSubject();

    Long getUserid();

    String getRole();



}
