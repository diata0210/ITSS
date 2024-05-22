package app.repositories;

public interface LoginRepository {
    int getRole(String username, String password);
}
