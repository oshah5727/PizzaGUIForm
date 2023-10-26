import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PizzaGUIFrame extends JFrame {
    JRadioButton thinCrustRadioBtn;
    JRadioButton regularCrustRadioBtn;
    JRadioButton deepDishCrustRadioBtn;
    JComboBox sizeComboBox;
    JCheckBox tomatoTopping;
    JCheckBox pepperTopping;
    JCheckBox onionTopping;
    JCheckBox oliveTopping;
    JCheckBox mushroomTopping;

    JCheckBox pineappleTopping;

    JPanel mainPnl;

    JPanel crustPnl;

    JPanel sizePnl;

    JPanel toppingsPnl;

    JPanel displayPnl;

    JPanel buttonPnl;

    JTextArea displayOrderTA;

    JButton orderBtn;

    JButton clearBtn;

    JButton quitBtn;

    private double baseCost = 0.0;
    private static final double toppingCost = 1.00;
    private static final double taxRate = 0.07;

    public PizzaGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createCrustPanel();
        mainPnl.add(crustPnl, BorderLayout.WEST);

        createSizePanel();
        mainPnl.add(sizePnl, BorderLayout.NORTH);

        createToppingsPanel();
        mainPnl.add(toppingsPnl, BorderLayout.EAST);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createButtonPanel();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
    }

    private void createCrustPanel() {
        crustPnl = new JPanel();
        crustPnl.setBorder(BorderFactory.createTitledBorder("Crust Type"));
        thinCrustRadioBtn = new JRadioButton("Thin");
        regularCrustRadioBtn = new JRadioButton("Regular");
        deepDishCrustRadioBtn = new JRadioButton("Deep-dish");

        ButtonGroup crustTypeGrp = new ButtonGroup();
        crustTypeGrp.add(thinCrustRadioBtn);
        crustTypeGrp.add(regularCrustRadioBtn);
        crustTypeGrp.add(deepDishCrustRadioBtn);

        crustPnl.add(thinCrustRadioBtn);
        crustPnl.add(regularCrustRadioBtn);
        crustPnl.add(deepDishCrustRadioBtn);

    }

    private void createSizePanel() {
        sizePnl = new JPanel();
        sizePnl.setBorder(BorderFactory.createTitledBorder("Pizza Size"));
        sizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large", "Super"});
        sizePnl.add(sizeComboBox);

    }

    private void createToppingsPanel() {
        toppingsPnl = new JPanel();
        toppingsPnl.setBorder(BorderFactory.createTitledBorder("Pizza Toppings"));
        toppingsPnl.setLayout(new BoxLayout(toppingsPnl, BoxLayout.Y_AXIS));

        tomatoTopping = new JCheckBox("Tomatoes");
        pepperTopping = new JCheckBox("Peppers");
        onionTopping = new JCheckBox("Onions");
        oliveTopping = new JCheckBox("Olives");
        mushroomTopping = new JCheckBox("Mushrooms");
        pineappleTopping = new JCheckBox("Pineapples");

        toppingsPnl.add(tomatoTopping);
        toppingsPnl.add(pepperTopping);
        toppingsPnl.add(onionTopping);
        toppingsPnl.add(oliveTopping);
        toppingsPnl.add(mushroomTopping);
        toppingsPnl.add(pineappleTopping);

    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new BorderLayout());
        displayPnl.setBorder(BorderFactory.createTitledBorder("Order Details"));
        displayOrderTA = new JTextArea(10, 30);
        displayOrderTA.setEditable(false);
        displayPnl.add(new JScrollPane(displayOrderTA), BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPnl = new JPanel();
        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        buttonPnl.add(orderBtn);
        buttonPnl.add(clearBtn);
        buttonPnl.add(quitBtn);

        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrder();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGUI();
            }
        });
    }
    private void displayOrder() {
        StringBuilder orderText = new StringBuilder();

        if (thinCrustRadioBtn.isSelected() || regularCrustRadioBtn.isSelected() || deepDishCrustRadioBtn.isSelected()) {
            if (sizeComboBox.getSelectedIndex() != -1) {
                orderText.append("==============================");
                orderText.append("==============================\n");

                String selectedSize = (String) sizeComboBox.getSelectedItem();

                baseCost = 0.0;
                switch (selectedSize) {
                    case "Small":
                        baseCost = 8.00;
                        break;
                    case "Medium":
                        baseCost = 12.00;
                        break;
                    case "Large":
                        baseCost = 16.00;
                        break;
                    case "Super":
                        baseCost = 20.00;
                        break;
                }

                if (thinCrustRadioBtn.isSelected()) {
                    orderText.append(String.format("%-20s", "Thin & " + selectedSize)).append("\t\t\t\t$").append(String.format("%.2f", baseCost)).append("\n");
                } else if (regularCrustRadioBtn.isSelected()) {
                    orderText.append("Regular").append(" & ").append(selectedSize).append("\t\t\t\t$").append(String.format("%.2f", baseCost)).append("\n");;
                } else if (deepDishCrustRadioBtn.isSelected()) {
                    orderText.append("Deep-dish").append(" & ").append(selectedSize).append("\t\t\t\t$").append(String.format("%.2f", baseCost)).append("\n");;
                }


                double toppingsCost = 0.0;

                if (tomatoTopping.isSelected()) {
                    orderText.append("Tomatoes\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                if (pepperTopping.isSelected()) {
                    orderText.append("Peppers\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                if (onionTopping.isSelected()) {
                    orderText.append("Onions\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                if (oliveTopping.isSelected()) {
                    orderText.append("Olives\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                if (mushroomTopping.isSelected()) {
                    orderText.append("Mushrooms\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                if (pineappleTopping.isSelected()) {
                    orderText.append("Pineapples\t\t\t\t\t$").append(String.format("%.2f", toppingCost)).append("\n");
                    toppingsCost += toppingCost;
                }

                double subTotal = this.baseCost + toppingsCost;
                double tax = subTotal * taxRate;
                double total = subTotal + tax;

                orderText.append("\n");
                orderText.append("Sub-total:\t\t\t\t\t$").append(String.format("%.2f", subTotal)).append("\n");
                orderText.append("Tax:\t\t\t\t\t$").append(String.format("%.2f", tax)).append("\n");
                orderText.append("-------------------------------------------------------------------------------------------------------------\n");
                orderText.append("Total:\t\t\t\t\t$").append(String.format("%.2f", total)).append("\n");
                orderText.append("============================================================");

                displayOrderTA.setText(orderText.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Please select a size.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a crust type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        thinCrustRadioBtn.setSelected(false);
        regularCrustRadioBtn.setSelected(false);
        deepDishCrustRadioBtn.setSelected(false);

        tomatoTopping.setSelected(false);
        pepperTopping.setSelected(false);
        onionTopping.setSelected(false);
        oliveTopping.setSelected(false);
        mushroomTopping.setSelected(false);
        pineappleTopping.setSelected(false);

        displayOrderTA.setText("");
}

      private void quitGUI() {
          int response = JOptionPane.showConfirmDialog(
            PizzaGUIFrame.this,
            "Are you sure you want to quit?",
            "Quit",
            JOptionPane.YES_NO_OPTION);
          if (response == JOptionPane.YES_OPTION) {
          System.exit(0);
       }

    }
}