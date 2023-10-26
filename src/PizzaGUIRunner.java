import javax.swing.*;

public class PizzaGUIRunner {
    public static void main(String[] args) {
        PizzaGUIFrame pizzaFrame = new PizzaGUIFrame();
        pizzaFrame.setTitle("Pizza Order Form");
        pizzaFrame.setSize(800, 800);
        pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pizzaFrame.setVisible(true);
    }
}