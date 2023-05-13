/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event.Artist;

import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.forms.SessionManager;
import artmart.service.Event.EventWebService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 *
 * @author ghzay
 */
public class AddEventForm extends BaseForm {

    EventWebService service = new EventWebService();
      String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();

    public AddEventForm() throws IOException {
        this.init(Resources.getGlobalResources());

        TextField nameField = new TextField("" ,"Name");
        TextField locationField = new TextField("" ,"Location");
//        TextField typeField = new TextField("type");
        TextArea descriptionField = new TextArea();
        descriptionField.setHint("Description");
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
                entryfeeField,
                descriptionField,
                capacityField,
                startdateField,
                enddateField,
                imageField
        );
        
        Validator validator = new Validator();
        validator.addConstraint(nameField, new LengthConstraint(1, "Name is required"));
        validator.addConstraint(locationField, new LengthConstraint(1, "Location is required"));
        validator.addConstraint(comboBoxType, new LengthConstraint(1, "Type is required"));
        validator.addConstraint(descriptionField, new LengthConstraint(1, "Description is required"));
        validator.addConstraint(entryfeeField, new LengthConstraint(1, "Entryfee is required"));
        validator.addConstraint(capacityField, new LengthConstraint(1, "Capacity is required"));
        validator.addConstraint(startdateField, new LengthConstraint(1, "Start date is required"));
        validator.addConstraint(enddateField, new LengthConstraint(1, "End date is required"));
        validator.addConstraint(imageField, new LengthConstraint(1, "Image is required"));
//        validator.addConstraint(userField, new LengthConstraint(1, "User is required"));
        
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s -> {
            if (validator.isValid()) {
                
                String name = nameField.getText();
                String location = locationField.getText();
                String type = comboBoxType.getSelectedItem();
                String description = descriptionField.getText();
                double entryfee = Double.parseDouble(entryfeeField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                String startdate = startdateField.getText();
                String enddate = enddateField.getText();
                String image = imageField.getText();
//                int user = Integer.parseInt(userField.getText());

                Event newEvent = new Event(
                        userId,
                        name,
                        location,
                        type,
                        description,
                        entryfee,
                        capacity,
                        startdate,
                        enddate,
                        image,
                        "Scheduled"
                );
                service.newEvent(newEvent);
                AllEventsForm allEventsForm = null;
                try {
                    allEventsForm = new AllEventsForm();
                } catch (IOException ex) {
                }
                allEventsForm.show();
            }
        });


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
        this.add(submitButton);
    }

}
