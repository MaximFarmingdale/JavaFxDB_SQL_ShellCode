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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class DB_GUI_Controller implements Initializable {

    private final ObservableList<Person> data = FXCollections.observableArrayList();


    @FXML
    TextField name, email, phone, address, password;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_name, tv_email, tv_phone, tv_address, tv_password;

    private int index = 0;

    @FXML
    ImageView img_view;

    ConnDbOps database = new ConnDbOps();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tv_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tv_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tv.setItems(data);
        index = database.getCount();
        System.out.println(index);
        database.addUsersToList(data); //fill list with previous elements in the database
    }


    @FXML
    protected void addNewRecord() {
        if (uniqueEmail(email.getText())) {
            email.setStyle("-fx-border-color: null"); //changes the border color back to normal
            index++;
            Person person = new Person(index, name.getText(), email.getText(), address.getText(), phone.getText(), password.getText());
            data.add(person);
            database.insertUser(person); // adds a user to the database
        } else
            email.setStyle("-fx-border-color: red");//changes the border color to red if you type a non-unique email
    }

    @FXML
    protected void clearForm() {
        name.setText("");
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
        email.setStyle("-fx-border-color: null"); //changes border color back to normal
        Person p = tv.getSelectionModel().getSelectedItem();
        if (p == null) {
            return; //returns if no selected item
        }
        Person p2 = new Person();
        p2.setId(p.getId());
        p2.setName(name.getText());
        p2.setAddress(address.getText());
        p2.setPhone(phone.getText());
        p2.setPassword(password.getText());
        if(uniqueEmail(email.getText())) { //making sure the email is still unique
            p2.setEmail(email.getText());
        }
        else
            p2.setEmail(p.getEmail()); //sets email to previous email
        int indexToUpdate = data.indexOf(p);
        database.updateUser(p2); //updates user in the database
        data.remove(indexToUpdate);
        data.add(indexToUpdate, p2);
        tv.getSelectionModel().select(indexToUpdate);
    }

    @FXML
    protected void deleteRecord() {
        email.setStyle("-fx-border-color: null"); //changes border color back to normal
        Person p = tv.getSelectionModel().getSelectedItem();
        if (p == null) {
            return; //returns if no selected item
        }
        if (index == p.getId())
            index--;
        database.removeUser(p);//deletes the person from the database
        data.remove(p);
    }


    @FXML
    protected void showImage() {
        File file = (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if (file != null) {
            img_view.setImage(new Image(file.toURI().toString()));
        }
    }


    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        if (tv.getSelectionModel().getSelectedItem() != null) {
            Person p = tv.getSelectionModel().getSelectedItem();
            name.setText(p.getName());
            email.setText(p.getEmail());
            address.setText(p.getAddress());
            phone.setText(p.getPhone());
            password.setText(p.getPassword());
        }

    }

    public boolean uniqueEmail(String email) {
        if (email != null)
            return !data.stream().anyMatch(person -> person.getEmail().equals(email));
        return false;
    }

    @FXML
    void shortCuts(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.F) {
            showImage();
        }
        if (event.isControlDown() && event.getCode() == KeyCode.C) {
            clearForm();
        }
        if (event.isControlDown() && event.getCode() == KeyCode.D) {
            deleteRecord();
        }
        if (event.isControlDown() && event.getCode() == KeyCode.E) {
            editRecord();
        }
        if (event.isControlDown() && event.getCode() == KeyCode.D) {
            deleteRecord();
        }
    }
}