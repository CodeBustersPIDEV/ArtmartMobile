/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event;

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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class AllEventsForm extends BaseForm {

    private MultiList evList;
    private List<Event> events;
    private TextField searchField;

    public AllEventsForm() throws IOException {

        Button addEventBtn = new Button("➕");
        addEventBtn.addActionListener(ee -> {
            AddEventForm f = null;
            try {
                f = new AddEventForm();
            } catch (IOException ex) {
            }
            f.show();
        });

        searchField = new TextField("", "Enter Event Name");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                Event ev = null;
                for (Event event : events) {
                    if (event.getName() == null ? searchId == null : event.getName().equals(searchId)) {
                        ev = event;
                        break;
                    }
                }
                if (ev != null) {
                    EditEventForm myForm2 = new EditEventForm(ev);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Event not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Sort by Name");
        sortButton.addActionListener(e -> {
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event p1, Event p2) {
                    return p1.getName().compareToIgnoreCase(p2.getName());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));
        
        this.add(addEventBtn);
        
        evList = new MultiList(new DefaultListModel<>());
        add(evList);
        getAllEvents();
    }

    private void getAllEvents() {
        EventWebService service = new EventWebService();
        events = service.getAllEvents();
        System.out.println(events);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) evList.getModel();
        model.removeAll();
        for (Event event : events) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", event.getName());
            item.put("Line2", event.getDescription());
            item.put("Line3", event.getEventid());
            model.addItem(item);
        }
        evList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) evList.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line3");
                    Event selectedEvent = null;
                    for (Event event : events) {
                        if (event.getEventid() == eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    EditEventForm myForm2 = new EditEventForm(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) evList.getModel();
        model.removeAll();
        for (Event event : events) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", event.getName());
            item.put("Line2", event.getDescription());
            item.put("Line3", event.getEventid());
            model.addItem(item);
        }
    }
}
