/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event;

import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.service.Event.EventWebService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author ghzay
 */
public class AddEventForm extends BaseForm {

    EventWebService service = new EventWebService();

    public AddEventForm() throws IOException {
        this.init(Resources.getGlobalResources());

        TextField nameField = new TextField("" ,"Name");
        TextField locationField = new TextField("" ,"Location");
//        TextField typeField = new TextField("type");
        TextField descriptionField = new TextField("" ,"Description");
        TextField entryfeeField = new TextField("" ,"Entryfee");
        TextField capacityField = new TextField("" ,"Capacity");
        TextField startdateField = new TextField("" ,"Start Date");
        TextField enddateField = new TextField("" ,"End Date");
        TextField imageField = new TextField("" ,"Image");
        TextField userField = new TextField("" ,"User");

        ComboBox<String> comboBoxType = new ComboBox();

        comboBoxType.addItem("Auction");
        comboBoxType.addItem("Art fair");
        comboBoxType.addItem("Open Gallery");
        comboBoxType.addItem("Exhibition");

        this.addAll(
                nameField,
                locationField,
                comboBoxType,
                descriptionField,
                entryfeeField,
                capacityField,
                startdateField,
                enddateField,
                imageField,
                userField
        );
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s -> {

            String name = nameField.getText();
            String location = locationField.getText();
            String type = comboBoxType.getSelectedItem();
            String description = descriptionField.getText();
            double entryfee = Double.parseDouble(entryfeeField.getText());
            int capacity = Integer.parseInt(capacityField.getText());
            String startdate = startdateField.getText();
            String enddate = enddateField.getText();
            String image = imageField.getText();
            int user = Integer.parseInt(userField.getText());

            Event newEvent = new Event(
                    user,
                    name,
                    location,
                    type,
                    description,
                    entryfee,
                    capacity,
                    startdate,
                    enddate,
                    "http://localhost/PIDEV/BlogUploads/imagec.png",
                    "Scheduled"
            );
            service.newEvent(newEvent);
            AllEventsForm allEventsForm = null;
            try {
                allEventsForm = new AllEventsForm();
            } catch (IOException ex) {
            }
            allEventsForm.show();
        });


        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            AllEventsForm myForm = null;
            try {
                myForm = new AllEventsForm();
            } catch (IOException ex) {

            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
