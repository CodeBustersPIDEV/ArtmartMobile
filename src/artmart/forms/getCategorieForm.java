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
import artmart.entities.Category;
import artmart.service.CategorieWebService;

public class getCategorieForm extends BaseForm {

    private MultiList eventList;

    public getCategorieForm() {
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllCats();
    }

    private void getAllCats() {
        CategorieWebService service = new CategorieWebService();
        List<Category> cats = service.getAllCategorie();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (Category c : cats) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Nom Categorie:" + c.getName());
        
            item.put("Line3", c.getCategoriesId());
            model.addItem(item);
        }
        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) eventList.getSelectedItem();
                    int catId = (int) selectedItem.get("Line3");
                    Category selectedEvent = null;
                    for (Category c : cats) {
                        if (c.getCategoriesId() == catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormCategorie myForm2 = new editFormCategorie(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

    }
}
