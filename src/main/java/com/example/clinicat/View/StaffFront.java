package com.example.clinicat.View;

import com.example.clinicat.Front;
import com.example.clinicat.PaneModel.Client;
import com.example.clinicat.PaneModel.Visit;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class StaffFront {
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
            Url = new FileInputStream("png/staff.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button client = new Button();
        client.setBackground(null);
        client.setLayoutY(142);
        client.setLayoutX(41);
        client.setPrefSize(178,25);
        client.setOnAction(t ->{
            try {
                pane_scroll = Client.getPane(false, true,false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,3);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button add = new Button();
        add.setLayoutX(219);
        add.setLayoutY(142);
        add.setPrefSize(50,25);
        add.setBackground(null);
        add.setOnAction(t->{
            try {
                Client.addClient(false, false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button visit = new Button();
        visit.setBackground(null);
        visit.setLayoutX(75);
        visit.setLayoutY(242);
        visit.setPrefSize(161,25);
        visit.setOnAction(t1 ->{
            try {
                pane_scroll = Visit.getPane(false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,3);
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
                Front.pane = StaffFront.getStartFront();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });
        if(pushB!=0){
            pane.getChildren().add(visit);
            pane.getChildren().add(add);
            pane.getChildren().add(client);
        }


        return pane;
    }
}
