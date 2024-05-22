package app.repositories;

public interface LoginRepository {
    boolean login(String username, String password);
    int getRole();
}
