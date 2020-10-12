package beergame;

public class App {

    public String getGreeting() {
        return "Welcome to the beergame.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        Game game = new Game();
        game.Play();
    }
}
