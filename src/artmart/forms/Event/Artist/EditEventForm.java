/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event.Artist;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.forms.getCustomProductForm;
import artmart.service.Event.EventWebService;
import java.io.IOException;

public class EditEventForm extends BaseForm {

    EventWebService service = new EventWebService();

    public EditEventForm(Event e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        TextField nameField = new TextField(e.getName(), "name");
        TextField locationField = new TextField(e.getLocation(), "location");
        TextField typeField = new TextField(e.getType(), "type");
        TextField descriptionField = new TextField(e.getDescription(), "description");
        TextField entryfeeField = new TextField(e.getEntryfee() + "", "entryfee");
        TextField capacityField = new TextField(e.getCapacity() + "", "capacity");
        TextField startdateField = new TextField(e.getStartdate(), "startdate");
        TextField enddateField = new TextField(e.getEnddate(), "enddate");
        TextField imageField = new TextField(e.getImage(), "image");
        TextField statusField = new TextField(e.getStatus(), "status");
        TextField userField = new TextField(e.getUser() + "", "user");

        this.addAll(
                nameField,
                locationField,
                typeField,
                descriptionField,
                entryfeeField,
                capacityField,
                startdateField,
                enddateField,
                imageField,
                statusField,
                userField
        );

        Button submitButton = new Button("Update");

        submitButton.addActionListener(s -> {

            String name = nameField.getText();
            String location = locationField.getText();
            String type = typeField.getText();
            String description = descriptionField.getText();
            double entryfee = Double.parseDouble(entryfeeField.getText());
            int capacity = Integer.parseInt(capacityField.getText());
            String startdate = startdateField.getText();
            String enddate = enddateField.getText();
            String image = imageField.getText();
            String status = statusField.getText();
            int user = Integer.parseInt(userField.getText());

            Event newEvent = new Event(
                    e.getEventid(),
                    user,
                    name,
                    location,
                    type,
                    description,
                    entryfee,
                    capacity,
                    startdate,
                    enddate,
                    image,
                    status
            );
            service.editEvent(newEvent);
            AllEventsForm allEventsForm = null;
            try {
                allEventsForm = new AllEventsForm();
            } catch (IOException ex) {
            }
            allEventsForm.show();
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            AllEventsForm myForm = null;
            try {
                myForm = new AllEventsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.delEvent(e);
            AllEventsForm myForm = null;
            try {
                myForm = new AllEventsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });

        this.add(goToFormButton);
        this.add(deleteButton);
        this.add(submitButton);

    }

}
