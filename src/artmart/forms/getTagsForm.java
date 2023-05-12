package artmart.forms;

import artmart.entities.Blogs;
import com.codename1.l10n.ParseException;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import artmart.entities.Tags;
import artmart.service.TagWebService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class getTagsForm extends BaseForm {

    private MultiList eventList;
    private List<Tags> tags;
    private TextField searchField;

    public getTagsForm() throws IOException {
        Button addButton = new Button(FontImage.MATERIAL_ADD);
        Button resetBtn = new Button(FontImage.MATERIAL_RESTORE);
        addButton.addActionListener(ee -> {
            newTagsForm f = null;
            try {
                f = new newTagsForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        resetBtn.addActionListener(ee -> {
            getTagsForm f = null;
            try {
                f = new getTagsForm();
            } catch (IOException ex) {
            }
            f.show();
        });

        searchField = new TextField("", "Enter Tag Name");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                Tags selectedPromo = null;
                for (Tags tag : tags) {
                    if (tag.getName() == null ? searchId == null : tag.getName().equals(searchId)) {
                        selectedPromo = tag;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormTags myForm2 = new editFormTags(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Tag not found", "OK", null);
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
            Collections.sort(tags, new Comparator<Tags>() {
                @Override
                public int compare(Tags b1, Tags b2) {
                    return b1.getName().compareToIgnoreCase(b2.getName());
                }
            });
            updateList();
        });

        this.add(addButton);
        this.add(resetBtn);

        addComponent(BorderLayout.south(sortButton));
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllCats();
    }

    private void getAllCats() {
        TagWebService service = new TagWebService();
        tags = service.getAllCategorie();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (Tags c : tags) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", c.getName());

            item.put("Line3", c.getId());
            model.addItem(item);
        }
        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) eventList.getSelectedItem();
                    int catId = (int) selectedItem.get("Line3");
                    Tags selectedEvent = null;
                    for (Tags c : tags) {
                        if (c.getId() == catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormTags myForm2 = new editFormTags(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

    }

    public void updateList() {
        TagWebService service = new TagWebService();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
         tags = service.getAllCategorie();
        for (Tags c : tags) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", c.getName());

            item.put("Line3", c.getId());

            model.addItem(item);
        }
    }
}
