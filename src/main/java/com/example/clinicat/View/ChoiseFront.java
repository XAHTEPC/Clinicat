package com.example.clinicat.View;

import com.example.clinicat.DataBase.Postgre;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ChoiseFront {
    static boolean[] vetMas = null;
    static boolean[] gruMas = null;


    public static Pane getPane(int fl, String id) throws FileNotFoundException, SQLException {
        Pane pane = new Pane();
        FileInputStream Url;
        if(fl==0) {
            Url = new FileInputStream("png/ch1.png");
        }
        else {
            Url = new FileInputStream("png/ch2.png");
        }
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        if(fl==0){
            gruMas = new boolean[3];
            if(id=="") {
                for (int i = 0; i < 3; i++)
                    gruMas[i] = false;
            }
            else {
                String[][] mas =  Postgre.getBooking_byID(id);
                for (int i = 0; i < 3; i++)
                    gruMas[i] = false;
                for(int i=0;i<mas.length;i++){
                    if(mas[i][0].equals("Стрижка"))
                        gruMas[1]=true;
                    if(mas[i][0].equals("Мойка шерсти"))
                        gruMas[0] = true;
                    if(mas[i][0].equals("Покрытие бальзамом"))
                        gruMas[2] = true;
                }
            }
            Button t1 = new Button();
            t1.setLayoutX(28);
            t1.setLayoutY(22);
            t1.setPrefSize(20,18);
            t1.setBackground(null);
            if(gruMas[0])
                t1.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t1);
            Button t2 = new Button();
            t2.setLayoutX(27);
            t2.setLayoutY(77);
            t2.setPrefSize(20,19);
            t2.setBackground(null);
            if(gruMas[1])
                t2.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t2);
            Button t3 = new Button();
            t3.setLayoutX(26);
            t3.setLayoutY(132);
            t3.setPrefSize(20,20);
            t3.setBackground(null);
            if(gruMas[2])
                t3.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t3);
            t1.setOnAction(t ->{
                if(!gruMas[0])
                    t1.setStyle("-fx-background-color: rgb(79, 90, 115);");
                else
                    t1.setStyle("-fx-background-color: transparent;");
                gruMas[0] = !gruMas[0];
            });
            t2.setOnAction(t ->{
                if(!gruMas[1])
                    t2.setStyle("-fx-background-color: rgb(79, 90, 115);");
                else
                    t2.setStyle("-fx-background-color: transparent;");
                gruMas[1] = !gruMas[1];
            });
            t3.setOnAction(t ->{
                if(!gruMas[2])
                    t3.setStyle("-fx-background-color: rgb(79, 90, 115);");
                else
                    t3.setStyle("-fx-background-color: transparent;");
                gruMas[2] = !gruMas[2];
            });
        }
        else {
            vetMas = new boolean[5];
            if(id=="") {
                for (int i = 0; i < 5; i++)
                    vetMas[i] = false;
            }
            else {
                String[][] mas =  Postgre.getBooking_byID(id);
                for (int i = 0; i < 5; i++){
                    if(mas[i][0].equals("Кастрация собаки"))
                        vetMas[0]=true;
                    if(mas[i][0].equals("Кастрация кошки"))
                        vetMas[1] = true;
                    if(mas[i][0].equals("Привика от бешенства"))
                        vetMas[2] = true;
                    if(mas[i][0].equals("Лечение лишая"))
                        vetMas[3] = true;
                    if(mas[i][0].equals("Лечение глистов"))
                        vetMas[4] = true;
                }
            }
            Button t1 = new Button();
            t1.setLayoutX(26);
            t1.setLayoutY(22);
            t1.setPrefSize(20,20);
            t1.setBackground(null);
            if(vetMas[0])
                t1.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t1);
            Button t2 = new Button();
            t2.setLayoutX(26);
            t2.setLayoutY(49);
            t2.setPrefSize(20,20);
            t2.setBackground(null);
            if(vetMas[1])
                t2.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t2);
            Button t3 = new Button();
            t3.setLayoutX(26);
            t3.setLayoutY(77);
            t3.setPrefSize(20,20);
            t3.setBackground(null);
            if(vetMas[2])
                t3.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t3);
            Button t4 = new Button();
            t4.setLayoutX(26);
            t4.setLayoutY(104);
            t4.setPrefSize(20,20);
            t4.setBackground(null);
            if(vetMas[3])
                t4.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t4);
            Button t5 = new Button();
            t5.setLayoutX(26);
            t5.setLayoutY(132);
            t5.setPrefSize(20,20);
            t5.setBackground(null);
            if(vetMas[4])
                t5.setStyle("-fx-background-color: rgb(79, 90, 115);");
            pane.getChildren().add(t5);
            t1.setOnAction(t ->{
                if(!vetMas[0])
                    t1.setStyle("-fx-background-color: rgb(79, 90, 115);;");
                else
                    t1.setStyle("-fx-background-color: transparent;");
                vetMas[0] = !vetMas[0];
            });
            t2.setOnAction(t ->{
                if(!vetMas[1])
                    t2.setStyle("-fx-background-color: rgb(79, 90, 115);;");
                else
                    t2.setStyle("-fx-background-color: transparent;");
                vetMas[1] = !vetMas[1];
            });
            t3.setOnAction(t ->{
                if(!vetMas[2])
                    t3.setStyle("-fx-background-color: rgb(79, 90, 115);;");
                else
                    t3.setStyle("-fx-background-color: transparent;");
                vetMas[2] = !vetMas[2];
            });
            t4.setOnAction(t ->{
                if(!vetMas[3])
                    t4.setStyle("-fx-background-color: rgb(79, 90, 115);;");
                else
                    t4.setStyle("-fx-background-color: transparent;");
                vetMas[3] = !vetMas[3];
            });
            t5.setOnAction(t ->{
                if(!vetMas[4])
                    t5.setStyle("-fx-background-color: rgb(79, 90, 115);;");
                else
                    t5.setStyle("-fx-background-color: transparent;");
                vetMas[4] = !vetMas[4];
            });
        }
        return pane;
    }

    public static boolean[] getVetMas() {
        return vetMas;
    }

    public static boolean[] getGruMas() {
        return gruMas;
    }
}
