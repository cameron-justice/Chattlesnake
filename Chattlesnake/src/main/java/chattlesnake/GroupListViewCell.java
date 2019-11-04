package chattlesnake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class GroupListViewCell extends ListCell<Group> {

    @FXML
    FlowPane flowPane;
    @FXML
    TextFlow userName;
    @FXML
    TextFlow timeLastText;

    FXMLLoader mLLoader;


    @Override
    protected void updateItem(Group group, boolean empty) {
        super.updateItem(group, empty);

        if(empty || group == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                System.out.println("making it work");
                mLLoader = new FXMLLoader(Main.class.getClassLoader().getResource("GroupTemplate.fxml"));
                //mLLoader.setController(this);

                /*try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }

            /*
            label1.setText(String.valueOf(student.getStudentId()));
            label2.setText(student.getName());

            if(student.getGender().equals(Student.GENDER.MALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.MARS);
            } else if(student.getGender().equals(Student.GENDER.FEMALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.VENUS);
            } else {
                fxIconGender.setIcon(FontAwesomeIcon.GENDERLESS);
            }
            */
            setText(null);
            setGraphic(flowPane);
        }

    }
}
