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
import artmart.entities.BlogCategories;
import artmart.service.BlogCategoriesWebService;
import artmart.service.CategorieWebService;

public class getBlogCategoryForm extends BaseForm {

    private MultiList eventList;

    public getBlogCategoryForm() {
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllCats();
    }

    private void getAllCats() {
        BlogCategoriesWebService service = new BlogCategoriesWebService();
        List<BlogCategories> cats = service.getAllCategorie();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (BlogCategories c : cats) {
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
                    BlogCategories selectedEvent = null;
                    for (BlogCategories c : cats) {
                        if (c.getId() == catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormBlogCategories myForm2 = new editFormBlogCategories(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

    }
    
     public void updateList() {
                 BlogCategoriesWebService service = new BlogCategoriesWebService();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
                List<BlogCategories> cats = service.getAllCategorie();
        for (BlogCategories c : cats) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", c.getName());
        
            item.put("Line3", c.getId());

            model.addItem(item);
        }
    }
}
