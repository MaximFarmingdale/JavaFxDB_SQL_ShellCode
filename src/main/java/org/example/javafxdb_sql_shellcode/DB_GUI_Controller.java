package org.example.javafxdb_sql_shellcode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class DB_GUI_Controller implements Initializable {

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person(1, "Jacob", "Smith", "jacoz@hotmail.com", "6314573678", "29 Main Street", "12345"),
                    new Person(2, "John", "Smith", "coolcatz@hotmail.com", "631457458", "29 Main Street", "54321")
            );


    @FXML
    TextField first_name, last_name, email, phone, address, password;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_fn, tv_ln, tv_email, tv_phone, tv_address, tv_password;

    @FXML
    ImageView img_view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tv_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tv_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tv_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tv.setItems(data);
    }


    @FXML
    protected void addNewRecord() {
        data.add(new Person(
                data.size()+1,
                first_name.getText(),
                last_name.getText(),
                email.getText(),
                address.getText(),
                phone.getText(),
                password.getText()
        ));

    }

    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.setText("");
        email.setText("");
        address.setText("");
        phone.setText("");
        password.setText("");
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }


    @FXML
    protected void editRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        int c=data.indexOf(p);
        Person p2= new Person();
        p2.setId(c+1);
        p2.setFirstName(first_name.getText());
        p2.setLastName(last_name.getText());
        p2.setEmail(email.getText());
        p2.setAddress(address.getText());
        p2.setPhone(phone.getText());
        p2.setPassword(password.getText());
        data.remove(c);
        data.add(c,p2);
        tv.getSelectionModel().select(c);
    }

    @FXML
    protected void deleteRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        data.remove(p);
    }



    @FXML
    protected void showImage() {
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));
        }
    }





    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        if(tv.getSelectionModel().getSelectedItem() != null) {
            Person p = tv.getSelectionModel().getSelectedItem();
            first_name.setText(p.getFirstName());
            last_name.setText(p.getLastName());
            email.setText(p.getEmail());
            address.setText(p.getAddress());
            phone.setText(p.getPhone());
            password.setText(p.getPassword());
        }

    }
}