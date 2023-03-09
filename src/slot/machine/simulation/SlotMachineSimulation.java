/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package slot.machine.simulation;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author stipanmadzar
 */
public class SlotMachineSimulation extends Application {
    
    double totalSpend = 0.00;
    double totalWin = 0.00;
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Slot Machine Simulator");
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(20);
        hbox1.setPadding(new Insets(10, 0, 0, 0));
        Image img1 = new Image("slot/machine/simulation/Koala.png");
        Image img2 = new Image("slot/machine/simulation/BlankFruit.png");
        Image img3 = new Image("slot/machine/simulation/BlankFruit.png");
        ImageView view1 = new ImageView(img1);
        ImageView view2 = new ImageView(img2);
        ImageView view3 = new ImageView(img3);
        hbox1.getChildren().addAll(view1, view2, view3);
        hbox1.setAlignment(Pos.CENTER);
        VBox vb = new VBox(10);
        Label lb = new Label("Amount Won This Spin: $  0.00\nTotal Amount Won: $  0.00\nTotal Amount Spent: $  0.00");
        lb.setTextAlignment(TextAlignment.RIGHT);
        lb.setLineSpacing(6);
        vb.getChildren().addAll(lb);
        Label l1 = new Label("Amount Inserted: $");
        TextField text = new TextField();
        text.setPrefWidth(100);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.add(l1, 0, 0);
        grid.add(text, 1, 0);
        GridPane.setMargin(l1, new Insets(15, 0, 0, 0));
        GridPane.setMargin(text, new Insets(15, 0, 0, 0));
        HBox hb = new HBox(45);
        hb.setPadding(new Insets(0, 0, 0, 0));
        hb.getChildren().addAll(grid, vb);
        hb.setAlignment(Pos.CENTER);
        HBox bb = new HBox(20);
        Button b1 = new Button("Spin");
        b1.setPrefWidth(43);
        Button b2 = new Button("Reset");
        b2.setPrefWidth(49);
        bb.getChildren().addAll(b1, b2);
        bb.setAlignment(Pos.CENTER);
        Label label = new Label("Insert an amount to play.");
        HBox hb2 = new HBox(label);
        hb2.setAlignment(Pos.CENTER);
        hb2.setPadding(new Insets(3, 0, 0, 0));
        vbox.getChildren().addAll(hbox1, hb, bb, hb2);
        
         b1.setOnAction(e ->
        {
             try{
             
                double input = Double.parseDouble(text.getText());
                 
                 if(input > 0) {
                     
                     Random rand = new Random();
                     int random1 = rand.nextInt(10) + 1;
                     int random2 = rand.nextInt(10) + 1;
                     int random3 = rand.nextInt(10) + 1;
                     Image image1 = new Image("slot/machine/simulation/" + random1 + "fruit.bmp");
                     Image image2 = new Image("slot/machine/simulation/" + random2 + "fruit.bmp");
                     Image image3 = new Image("slot/machine/simulation/" + random3 + "fruit.bmp");
                     view1.setImage(image1);
                     view2.setImage(image2);
                     view3.setImage(image3);
                     totalSpend += input;
                     double win = 0.00;
                     
                    if((random1 != random2) && (random2 != random3) && (random3 != random1)) {
                         
                        lb.setText("Amount Won This Spin: $  " + String.format("%.2f", win) + 
                                    "\nTotal Amount Won: $  " + String.format("%.2f", totalWin) + "\nTotal Amount Spent: $  " 
                                    + String.format("%.2f", totalSpend));
                        label.setText("No luck. Play again!");        
                    }
                    else if((random1 == random2) && (random2 == random3) && (random1 == random3)) {
                         
                        win = input * 3;
                        totalWin += win;
                        lb.setText("Amount Won This Spin: $  " + String.format("%.2f", win) + 
                                    "\nTotal Amount Won: $  " + String.format("%.2f", totalWin) + "\nTotal Amount Spent: $  " 
                                    + String.format("%.2f", totalSpend));
                        label.setText("Sweet! TRIPLE WIN x 3!!");   
                    }
                     
                    else {
                         
                        win = input * 2;
                        totalWin += win;
                        lb.setText("Amount Won This Spin: $  " + String.format("%.2f", win) + 
                                    "\nTotal Amount Won: $  " + String.format("%.2f", totalWin) + "\nTotal Amount Spent: $  " 
                                    + String.format("%.2f", totalSpend));
                        label.setText("Sweet! DOUBLE WIN x 2!!");
                    }
                }
                 
                else {
                     label.setText("Error. Try a different amount.");
                }
                 
                 
            }
             
            catch(NumberFormatException e2) {
                 label.setText("Error. Try a different amount.");
            }
    });
         
        b2.setOnAction(e -> 
        {
             totalSpend = 0.00;
             totalWin = 0.00;
             text.setText("");
             lb.setText("Amount Won This Spin: $  0.00\nTotal Amount Won: $  0.00\nTotal Amount Spent: $  0.00");
             label.setText("Insert an amount to play.");
             view1.setImage(img1);
             view2.setImage(img2);
             view3.setImage(img3);
        });
        vbox.setStyle("-fx-background-color: bisque");
        Scene scene = new Scene(vbox, 550, 300);
        scene.getStylesheets().add("slot/machine/simulation/slotMachine.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
}
