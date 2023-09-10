package com.example.clinicat.View;

import com.example.clinicat.Front;
import com.example.clinicat.PaneModel.Employee;
import com.example.clinicat.PaneModel.Structure;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class LocalManagerFront {
    static Pane pane_scroll;
    static int pushB = 0;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        if(pushB ==0) {
            Url = new FileInputStream("png/main.png");
        }
        else
            Url = new FileInputStream("png/localmanager.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button structure = new Button();
        structure.setBackground(null);
        structure.setLayoutY(242);
        structure.setLayoutX(37);
        structure.setPrefSize(236,25);
        structure.setOnAction(t ->{
            try {
                pane_scroll = Structure.getPane(true);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll, 1);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button employee = new Button();
        employee.setBackground(null);
        employee.setLayoutX(24);
        employee.setLayoutY(142);
        employee.setPrefSize(262,25);
        employee.setOnAction(t1 ->{
            try {
                pane_scroll = Employee.getPane(true, false, false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,1);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button exit = new Button();
        exit.setBackground(null);
        exit.setLayoutX(1076);
        exit.setLayoutY(8);
        exit.setPrefSize(100,100);
        pane.getChildren().add(exit);
        exit.setOnAction(t ->{
            try {
                Front.exit();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button push = new Button();
        push.setPrefSize(63,70);
        push.setBackground(null);
        push.setLayoutX(20);
        push.setLayoutY(10);
        pane.getChildren().add(push);
        push.setOnAction(t1->{
            if(pushB==0)
                pushB = 1;
            else
                pushB = 0;
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane =LocalManagerFront.getStartFront();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });
        if(pushB!=0){
            pane.getChildren().add(employee);
            pane.getChildren().add(structure);
        }


        return pane;
    }
}

