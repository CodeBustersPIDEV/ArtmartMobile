
package artmart.forms.Event.Client;

import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.forms.Event.Client.AllEventsClientForm;
import artmart.forms.SessionManager;
import artmart.service.Event.EventWebService;
import artmart.service.Event.ParticipationWebService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
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
        Label statusLabel = new Label("Status: " + e.getStatus());
        Label imageLabel = new Label("Image: " + e.getImage());

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
                statusLabel,
                imageLabel
//                userLabel,
//                eventLabel
        );
        addComponent(container);

        Button submitButton = new Button("Update");

        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            AllEventsClientForm myForm = null;
            try {
                myForm = new AllEventsClientForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });

        this.add(goToFormButton);

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
