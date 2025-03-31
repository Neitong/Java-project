package auth.SignIn;

public class SignInAsUser implements SignIn {


    public void SignIn(String email,String password) {
        System.out.println("Logout as User");
    }

    @Override
    public void SignIn(){

    }
}
