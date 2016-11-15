package org.tallymed.ui.views;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class InventoryController implements Initializable {

    @FXML
    private FlowPane main;
    @FXML
    private JFXTreeTableView<User> treeView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXTreeTableColumn<User, String> deptName = new JFXTreeTableColumn<>("Department");
        deptName.setPrefWidth(150);
        deptName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().department;
            }
        });

        JFXTreeTableColumn<User, String> ageCol = new JFXTreeTableColumn<>("Age");
        ageCol.setPrefWidth(150);
        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().age;
            }
        });

        JFXTreeTableColumn<User, String> nameCol = new JFXTreeTableColumn<>("Name");
        nameCol.setPrefWidth(150);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().userName;
            }
        });

        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("Computer Department", "23", "CD 1"));
        users.add(new User("Sales Department", "22", "Employee 1"));
        users.add(new User("Sales Department", "22", "Employee 2"));
        users.add(new User("Sales Department", "25", "Employee 4"));
        users.add(new User("Sales Department", "25", "Employee 5"));
        users.add(new User("IT Department", "42", "ID 2"));
        users.add(new User("HR Department", "22", "HR 1"));
        users.add(new User("HR Department", "22", "HR 2"));

        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(deptName, ageCol, nameCol);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }

    @FXML
    private void filter(ActionEvent event) {
    }

    class User extends RecursiveTreeObject<User> {

        StringProperty userName;
        StringProperty age;
        StringProperty department;

        public User(String department, String age, String userName) {
            this.department = new SimpleStringProperty(department);
            this.userName = new SimpleStringProperty(userName);
            this.age = new SimpleStringProperty(age);
        }

    }

}
