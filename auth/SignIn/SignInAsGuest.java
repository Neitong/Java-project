package auth.SignIn;

public class SignInAsGuest implements SignIn{

    public void SignIn( String email,String password) {

        System.out.println("logged out as guest");
    }

    @Override
    public void SignIn() {

    }
}
