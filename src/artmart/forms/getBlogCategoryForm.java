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
import artmart.entities.Blogs;
import artmart.entities.Tags;
import artmart.service.BlogCategoriesWebService;
import artmart.service.CategorieWebService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class getBlogCategoryForm extends BaseForm {

    private MultiList eventList;
    private List<BlogCategories> bcats;
    private TextField searchField;

    public getBlogCategoryForm() throws IOException {
        Button addButton = new Button(FontImage.MATERIAL_ADD);
        Button resetBtn = new Button(FontImage.MATERIAL_RESTORE);
        addButton.addActionListener(ee -> {
            newBlogCategoryForm f = null;
            try {
                f = new newBlogCategoryForm();
            } catch (IOException ex) {
            }
            f.show();
        });

        resetBtn.addActionListener(ee -> {
            getBlogCategoryForm f = null;
            try {
                f = new getBlogCategoryForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        searchField = new TextField("", "Enter Blog Category Name");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                BlogCategories selectedPromo = null;
                for (BlogCategories cat : bcats) {
                    if (cat.getName() == null ? searchId == null : cat.getName().equals(searchId)) {
                        selectedPromo = cat;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormBlogCategories myForm2 = new editFormBlogCategories(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Blog Category not found", "OK", null);
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
            Collections.sort(bcats, (BlogCategories b1, BlogCategories b2) -> b1.getName().compareToIgnoreCase(b2.getName()));
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
        BlogCategoriesWebService service = new BlogCategoriesWebService();
         bcats = service.getAllCategorie();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (BlogCategories c : bcats) {
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
                    for (BlogCategories c : bcats) {
                        if (c.getId() == catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormBlogCategories myForm2 = new editFormBlogCategories(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

    }

    public void updateList() {
        BlogCategoriesWebService service = new BlogCategoriesWebService();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        bcats = service.getAllCategorie();
        for (BlogCategories c : bcats) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", c.getName());

            item.put("Line3", c.getId());

            model.addItem(item);
        }
    }
}
