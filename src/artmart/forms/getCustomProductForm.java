package artmart.forms;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import artmart.entities.CustomProduct;
import artmart.service.CustomproductWebService;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Collections;
import java.util.Comparator;
public class getCustomProductForm extends BaseForm {

    private MultiList cpList;
    private List<CustomProduct> customproduct;
    private TextField searchField;
    
    public getCustomProductForm() {
             searchField = new TextField("", "Enter Custom Product Name");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                CustomProduct selectedPromo = null;
                for (CustomProduct p : customproduct) {
                    if (p.getName() == null ? searchId == null : p.getName().equals(searchId)) {
                        selectedPromo = p;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormCustomProduct myForm2 = new editFormCustomProduct(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Custom Product not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Sort by Name");
        sortButton.addActionListener(e -> {
            Collections.sort(customproduct, new Comparator<CustomProduct>() {
                @Override
                public int compare(CustomProduct p1, CustomProduct p2) {
                    return p1.getName().compareToIgnoreCase(p2.getName());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));      
        cpList = new MultiList(new DefaultListModel<>());
        add(cpList);
        getAllCp();   
    }
    private void getAllCp() {
        CustomproductWebService service = new CustomproductWebService();
        customproduct = service.getAllcp();
        System.out.println(customproduct);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (CustomProduct cp : customproduct) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", cp.getName());  
                   item.put("Line2", cp.getDescription());  
                      ;  
            item.put("Line3", cp.getCustomProductId());
            model.addItem(item);
        }
        cpList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) cpList.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line3");
                    CustomProduct selectedEvent = null;
                    for (CustomProduct event : customproduct) {
                        if (event.getCustomProductId() == eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    editFormCustomProduct myForm2 = new editFormCustomProduct(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

   
    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (CustomProduct p : customproduct) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1",   p.getName());
            item.put("Line2", p.getDescription());  
            item.put("Line3", p.getCustomProductId());
            model.addItem(item);
        }
    }
}
