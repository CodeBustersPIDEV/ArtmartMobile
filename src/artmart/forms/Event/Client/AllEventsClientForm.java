
package artmart.forms.Event.Client;

import artmart.forms.Event.Artist.*;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import artmart.entities.Event;
import artmart.forms.BaseForm;
import artmart.service.Event.EventWebService;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class AllEventsClientForm extends BaseForm {

    private MultiList evList;
    private List<Event> events;

    public AllEventsClientForm() throws IOException {
        Label headingLabel = new Label("Events");
        headingLabel.getUnselectedStyle().setFgColor(0xe35d59);
        headingLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
        addComponent(headingLabel);

        this.init(Resources.getGlobalResources());
        evList = new MultiList(new DefaultListModel<>());
        add(evList);
        getAllEvents();
        
    }

    private void getAllEvents() {
        EventWebService service = new EventWebService();
        events = service.getAllEvents();
        System.out.println("YA333333333" + events);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) evList.getModel();
        model.removeAll();
        for (Event event : events) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", event.getName());
            item.put("Line2", event.getType());
            item.put("Line3", event.getLocation());
            item.put("Line4", event.getStartdate() + " > " + event.getEnddate());
            item.put("Line5", event.getEventid());
            model.addItem(item);
        }
        evList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) evList.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line5");
                    Event selectedEvent = null;
                    for (Event event : events) {
                        if (event.getEventid() == eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    ShowEventForm myForm2 = new ShowEventForm(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

    }

}
