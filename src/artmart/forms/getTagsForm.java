package artmart.forms;

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
import java.io.IOException;

public class getTagsForm extends BaseForm {

    private MultiList eventList;

    public getTagsForm() throws IOException {
        Button addButton = new Button("Add");
        addButton.addActionListener(ee -> {
            newBlogCategoryForm f = null;
            try {
                f = new newBlogCategoryForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        this.add(addButton);
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllCats();
    }

    private void getAllCats() {
        TagWebService service = new TagWebService();
        List<Tags> cats = service.getAllCategorie();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (Tags c : cats) {
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
                    for (Tags c : cats) {
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
        List<Tags> cats = service.getAllCategorie();
        for (Tags c : cats) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", c.getName());

            item.put("Line3", c.getId());

            model.addItem(item);
        }
    }
}
