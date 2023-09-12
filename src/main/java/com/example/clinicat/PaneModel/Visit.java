package com.example.clinicat.PaneModel;

import com.example.clinicat.DataBase.Postgre;
import com.example.clinicat.View.ChoiseFront;
import com.example.clinicat.View.ScrollFront;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Visit {String id;
    String client_name;
    String structure_name;
    String service_name;
    String data;
    String employee_name;
    String summa;
    String points;
    static boolean flag;
    static String Name="Чистенько";
    static  String[] mas3={""};
    public Visit(String id, String client_name, String structure_name,
                 String service_name, String data, String employee_name, String summa, String points) {
        this.id = id;
        this.client_name = client_name;
        this.structure_name = structure_name;
        this.service_name = service_name;
        this.data = data;
        this.employee_name = employee_name;
        this.summa = summa;
        this.points = points;
    }

    public static Pane getPane(boolean fl) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Pane res = new Pane();
        flag = fl;
        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text cl = new Text("Клиент");
        cl.setLayoutX(70);
        cl.setLayoutY(60);
        cl.setFont(Font.font("Verdana",13));

        Text em = new Text("Заведение");
        em.setLayoutX(350);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text price = new Text("Цена");
        price.setLayoutX(500);
        price.setLayoutY(60);
        price.setFont(Font.font("Verdana",13));

        Visit[] mas = Postgre.getAllVisit_Staff();
        int u = 80;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null)
                continue;
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[i].id);
            num_text.setLayoutX(10);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea cl_text = new TextArea();
            cl_text.setText(mas[i].client_name);
            cl_text.setEditable(false);
            cl_text.setLayoutX(50);
            cl_text.setLayoutY(0 + u);
            cl_text.setMaxHeight(40);
            cl_text.setMaxWidth(200);
            cl_text.setMinWidth(200);

            TextArea emp_text = new TextArea();
            emp_text.setText(mas[i].structure_name);
            emp_text.setEditable(false);
            emp_text.setLayoutX(300);
            emp_text.setLayoutY(0 + u);
            emp_text.setMaxHeight(40);
            emp_text.setMaxWidth(140);
            emp_text.setMinHeight(40);
            emp_text.setMinWidth(140);

            TextArea price_text = new TextArea();
            price_text.setText(mas[i].summa);
            price_text.setEditable(false);
            price_text.setLayoutX(500);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(100);


            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(740);
            edit.setLayoutY(0+u);

            final String id = mas[i].id;
            if(flag) {
                res.getChildren().add(edit);
                edit.setOnAction(v -> {
                    try {
                        edit_pos(id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            res.getChildren().addAll(num_text,cl_text,emp_text,price_text);
        }

        res.getChildren().addAll(num, cl, em, price);
        return res;
    }
    static ObservableList<String> service = FXCollections.observableArrayList(mas3);
    static Pane choise;
    public static void edit_pos(String id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Visit visit = Postgre.getReceipt_byID(id);
        String nameClient = visit.client_name;
        Scene scene_add = new Scene(root_add, 418, 598);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.DECORATED);

        FileInputStream Url;
        Url = new FileInputStream("png/editVisit.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root_add.getChildren().add(front);


        TextField name = new TextField();
        name.setText(nameClient);
        name.setBackground(null);
        name.setLayoutX(180);
        name.setLayoutY(70);
        name.setMaxHeight(32);
        name.setMaxWidth(215);
        name.setEditable(false);

        String[] mas1 = Postgre.getStructure_name();
        ObservableList<String> work = FXCollections.observableArrayList(mas1);
        ComboBox<String> comboBox2 = new ComboBox<String>(work);
        comboBox2.setValue(visit.structure_name);
        comboBox2.setMaxWidth(215);
        comboBox2.setEditable(false);
        comboBox2.setMinWidth(215);
        comboBox2.setBackground(null);
        comboBox2.setLayoutX(180);
        comboBox2.setLayoutY(110);

        choise = new Pane();
        if(visit.structure_name.equals("Чистый пес")||visit.structure_name.equals("Чистый лис"))
            choise = ChoiseFront.getPane(0, id);
        else
            choise =ChoiseFront.getPane(1,id);
        choise.setLayoutX(30);
        choise.setLayoutY(200);
        root_add.getChildren().addAll(choise);
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                root_add.getChildren().remove(choise);
                Name = (comboBox2.getSelectionModel().getSelectedItem());
                System.out.println("NAme:"+Name);
                if(Name.equals("Чистый пес")||Name.equals("Чистый лис")){
                    try {
                        choise = ChoiseFront.getPane(0, "");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        choise = ChoiseFront.getPane(1, "");
                    } catch (FileNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                choise.setLayoutX(30);
                choise.setLayoutY(200);
                root_add.getChildren().addAll(choise);
            }
        });

        TextField DATA = new TextField();
        DATA.setText(visit.data);
        DATA.setLayoutX(180);
        DATA.setBackground(null);
        DATA.setLayoutY(416);
        DATA.setStyle("-fx-background-color: transparent;");
        DATA.setMaxHeight(32);
        DATA.setMaxWidth(215);

        TextField points = new TextField();
        points.setText(visit.points);
        points.setBackground(null);
        points.setLayoutX(180);
        points.setLayoutY(460);
        points.setMaxHeight(32);
        points.setMaxWidth(215);

        Button save = new Button();
        save.setLayoutX(30);
        save.setLayoutY(514);
        save.setBackground(null);
        save.setPrefSize(150,32);

        Button del = new Button();
        del.setBackground(null);
        del.setLayoutX(216);
        del.setLayoutY(514);
        del.setPrefSize(150,32);
        root_add.getChildren().addAll(del,save);

        String finalId_visit = id;
        save.setOnAction(x ->{
            System.out.println("mi tut");
            String t1,t2,t3,t4,t5,t6,t7 = "";
            String[][] mas = null;
            try {
                mas =  Postgre.getBooking_byID(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for(int i=0;i<mas.length;i++){
                try {
                    Postgre.delBooking(mas[i][1]);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            t1 = name.getText();
            t2 = comboBox2.getSelectionModel().getSelectedItem();
            t4 = DATA.getText();
            t6 = points.getText();
            String service_id ="";
            System.out.println("t4;"+t4 +"  t6: "+ t6);
            boolean fl = chechPos(t4,t6);
            System.out.println(fl);
            {
                boolean[] mas2 = ChoiseFront.getGruMas();
                if (mas2 == null) {
                    mas2 = ChoiseFront.getVetMas();
                }
                String[] arr = {""};
                String lastID = "";
                try {
                    arr = Postgre.getEmployee_from(t2);
                    lastID = id;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                boolean fla = mas2.length==3;
                int kol = mas2.length;
                for(int i=0;i<mas2.length;i++){
                    if(mas2[i]){
                        if(fla&&i==0){
                            service_id = "1";
                        }
                        else  if(i==0) {
                            service_id = "4";
                        } else if(fla&&i==1){
                            service_id = "2";
                        }
                        else  if(i==1) {
                            service_id = "5";
                        }if(fla&&i==2){
                            service_id = "3";
                        }
                        else  if(i==2) {
                            service_id = "6";
                        }
                        else  if(i==3) {
                            service_id = "7";
                        }
                        else  if(i==4) {
                            service_id = "8";
                        }
                        int emp_id = (int) Math.random()*(arr.length-1)+0;
                        String emp = arr[emp_id];
                        try {
                            System.out.println("i");
                            Postgre.addBooking(finalId_visit, service_id, t4, t6,emp,lastID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    Postgre.UpdateReceipt(lastID, finalId_visit);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ok");
                newWindow.close();
            }
            name.setText("Проверьте данные");
        });

        del.setOnAction(va ->{
            String[][] mas = null;
            try {
                mas =  Postgre.getBooking_byID(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                for(int i=0;i<mas.length;i++){
                    try {
                        Postgre.delBooking(mas[i][1]);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                Postgre.delBooking2(id);
                Pane p = Visit.getPane(flag);
                ScrollFront.scrollPane.setContent(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });
        root_add.getChildren().addAll(name,comboBox2);
        root_add.getChildren().addAll(DATA, points);
        newWindow.setTitle("Редактирование посещения");
        newWindow.setScene(scene_add);
        newWindow.show();
    }
    public static boolean chechPos(String date, String rate) {
        boolean isData=true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            System.out.println("date error");
            isData = false;
            return false;
        }
        boolean isRate = false;
        char[] r = rate.toCharArray();
        if (rate.length() == 1 && r[0] >'0' && r[0] <= '5')
            isRate = true;
        return isData & isRate;
    }

}
