package auth.LogIn;

import auth.controller.Roles;

public interface Login {
    public void login(Roles role);
    public void login(String email, String password, Roles role);

}
