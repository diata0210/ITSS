package app.services;

import app.repositories.LoginRepository;

public class LoginServiceImp {
    private LoginRepository loginRepository;

    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public int getRole(String username, String password){
        return loginRepository.getRole(username, password);
    }

}
