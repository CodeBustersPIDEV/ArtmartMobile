/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event.Client;

import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.forms.Event.Artist.EditEventForm;
import artmart.forms.Event.Client.AllEventsClientForm;
import artmart.forms.SessionManager;
import artmart.service.CustomproductWebService;
import artmart.service.Event.EventWebService;
import artmart.service.Event.ParticipationWebService;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghzay
 */
public class ShowEventForm extends BaseForm {

    EventWebService service = new EventWebService();
      String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();

    public ShowEventForm(Event e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        
        
        
        Label nameLabel = new Label("Event Name: " + e.getName());
        Label locationLabel = new Label("Location: " + e.getLocation());
        Label typeLabel = new Label("Event Type: " + e.getType());
        Label descriptionLabel = new Label("Description: " + e.getDescription());
        Label entryfeeLabel = new Label("Entry Fee: " + e.getEntryfee() + "");
        Label capacityLabel = new Label("Capacity: " + e.getCapacity() + "");
        Label startdateLabel = new Label("Start Date: " + e.getStartdate());
        Label enddateLabel = new Label("End Date: " + e.getEnddate());
        Label imageLabel = new Label("Image: " + e.getImage());
        Label statusLabel = new Label("Status: " + e.getStatus());
        Label userLabel = new Label("User: " + e.getUser() + "");
        Label eventLabel = new Label("Event: " + e.getEventid()+ "");

// Set up label properties for text wrapping
        descriptionLabel.setUIID("MultiLineLabel");
        descriptionLabel.getAllStyles().setPadding(LEFT, 5);
        descriptionLabel.getAllStyles().setPadding(RIGHT, 5);
        descriptionLabel.getAllStyles().setPadding(TOP, 10);
        descriptionLabel.getAllStyles().setPadding(BOTTOM, 10);
// Get the current screen width
int screenWidth = Display.getInstance().getDisplayWidth();

// Set the preferred width of the label to be 500 pixels
int preferredWidth = 500;
if (screenWidth < preferredWidth) {
    preferredWidth = screenWidth;
}
descriptionLabel.setWidth(preferredWidth);

// Set the UIID to "MultiLine" to enable text wrapping
descriptionLabel.setUIID("MultiLine");

// Set up label properties for text wrapping
        imageLabel.setUIID("MultiLineLabel");

        Container container = BoxLayout.encloseY().addAll(
                nameLabel,
                locationLabel,
                typeLabel,
                descriptionLabel,
                entryfeeLabel,
                capacityLabel,
                startdateLabel,
                enddateLabel,
                imageLabel,
                statusLabel,
                userLabel,
                eventLabel
        );
        addComponent(container);

        Button submitButton = new Button("Update");

//        submitButton.addActionListener(s -> {
//
//            String name = nameLabel.getText();
//            String location = locationLabel.getText();
//            String type = typeLabel.getText();
//            String description = descriptionLabel.getText();
//            double entryfee = Double.parseDouble(entryfeeLabel.getText());
//            int capacity = Integer.parseInt(capacityLabel.getText());
//            String startdate = startdateLabel.getText();
//            String enddate = enddateLabel.getText();
//            String image = imageLabel.getText();
//            String status = statusLabel.getText();
//            int user = Integer.parseInt(userLabel.getText());
//
//            Event newEvent = new Event(
//                    e.getEventid(),
//                    user,
//                    name,
//                    location,
//                    type,
//                    description,
//                    entryfee,
//                    capacity,
//                    startdate,
//                    enddate,
//                    image,
//                    status
//            );
//            service.editEvent(newEvent);
//            AllEventsForm allEventsForm = null;
//            try {
//                allEventsForm = new AllEventsForm();
//            } catch (IOException ex) {
//            }
//            allEventsForm.show();
//        }
//        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            AllEventsClientForm myForm = null;
            try {
                myForm = new AllEventsClientForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
//        Button goToMapsButton = new Button("Open maps");
//        goToFormButton.addActionListener(ee -> {
//            MapsForm myForm = null;
//            try {
//                myForm = new MapsForm();
//            } catch (IOException ex) {
//            }
//            myForm.show();
//        });
        Button deleteButton = new Button("Delete");
//        deleteButton.addActionListener(cc -> {
//            service.delEvent(e);
//            AllEventsForm myForm = null;
//            try {
//                myForm = new AllEventsForm();
//            } catch (IOException ex) {
//            }
//            myForm.show();
//        });

        this.add(goToFormButton);
//        this.add(deleteButton);

        Button participateButton = new Button("Participate");
        participateButton.addActionListener(ee -> {
            System.out.println("PARRRRR" +e.getEventid());
            ParticipationWebService service = new ParticipationWebService();
            service.participateToEvent(e.getEventid(), userId); 
            Dialog.show("Success", "Application successfully sent", "OK", null);
        });
        this.add(participateButton);

        Button mapButton = new Button("map");
        mapButton.addActionListener(ee -> {
            new MapsForm();
        });
        this.add(mapButton);

    }

}
