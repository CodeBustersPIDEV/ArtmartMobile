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
import artmart.service.Event.EventWebService;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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

    public ShowEventForm(Event e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        SpanLabel nameLabel = new SpanLabel(e.getName());
        SpanLabel locationLabel = new SpanLabel(e.getLocation());
        SpanLabel typeLabel = new SpanLabel(e.getType());
        SpanLabel descriptionLabel = new SpanLabel(e.getDescription());
        SpanLabel entryfeeLabel = new SpanLabel(e.getEntryfee() + "");
        SpanLabel capacityLabel = new SpanLabel(e.getCapacity() + "");
        SpanLabel startdateLabel = new SpanLabel(e.getStartdate());
        SpanLabel enddateLabel = new SpanLabel(e.getEnddate());
        SpanLabel imageLabel = new SpanLabel(e.getImage());
        SpanLabel statusLabel = new SpanLabel(e.getStatus());
        SpanLabel userLabel = new SpanLabel(e.getUser() + "");

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
            userLabel
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
//        this.add(submitButton);

    }

}
