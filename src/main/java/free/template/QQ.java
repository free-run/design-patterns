package free.template;

public class QQ extends Network {

    public QQ(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    @Override
    boolean logIn(String userName, String password) {
        System.out.println("Checking user's parameters");
        System.out.println("Name: " + this.userName);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        System.out.println("LogIn success on qq");
        return true;
    }

    @Override
    boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on qq");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logOut() {
        System.out.println("User: '" + userName + "' was logged out from qq");
    }

}
