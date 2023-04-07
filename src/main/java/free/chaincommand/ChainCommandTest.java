package free.chaincommand;

public class ChainCommandTest {

    private static Server server;

    public static void main(String[] args) {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // All checks are linked. Client can build various chains using the same
        // components.
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
        System.out.println("--------------------------------");
        server.logIn("admin@example.com", "admin_pass");
        System.out.println("--------------------------------");
        server.logIn("wq@example.com", "user_pass");
        System.out.println("--------------------------------");
        server.logIn("wq@example.com", "user_pass");

    }

}
