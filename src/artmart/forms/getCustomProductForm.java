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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Collections;
import java.util.Comparator;
import artmart.entities.CustomProduct;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;
import java.io.IOException;
import java.io.OutputStream;


public class getCustomProductForm extends BaseForm {

    private MultiList cpList;
    private List<CustomProduct> customproduct;
    private TextField searchField;
    private Button statisticsButton;
 
CustomProduct product = new CustomProduct();

    public getCustomProductForm() throws IOException {
        Button applyButton = new Button("+");
applyButton.addActionListener(ee -> {
    newCustomProductForm f = null;
            try {
                f = new newCustomProductForm();
            } catch (IOException ex) {
            }
            f.show();
});
  Button chat = new Button("ArtSmart");
chat.addActionListener(ee -> {
    chatbot f = null;
            try {
                f = new chatbot();
            } catch (IOException ex) {
            }
            f.show();
});
     Button searchButton = new Button("Search");
             searchField = new TextField("", "Enter Name");
             
   searchField.setColumns(15);
      statisticsButton = new Button("Show Statistics");
        statisticsButton.addActionListener(e -> {
   int totalProducts = customproduct.size();
double totalWeight = 0.0;
double totalPrice = 0.0;
double highestPrice = Double.MIN_VALUE;
double lowestPrice = Double.MAX_VALUE;

for (CustomProduct product : customproduct) {
    double weight = product.getWeight();
    totalWeight += weight;

    double price = product.getWeight();
    totalPrice += price;

    if (price > highestPrice) {
        highestPrice = price;
    }

    if (price < lowestPrice) {
        lowestPrice = price;
    }
}

double averageWeight = totalWeight / customproduct.size();
double averagePrice = totalPrice / customproduct.size();


            Dialog statisticsDialog = new Dialog("Statistics");
            statisticsDialog.setLayout(new BorderLayout());

            Container statisticsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            statisticsContainer.add(new Label("Total Products: " + totalProducts));
            statisticsContainer.add(new Label("Average weight: " + averagePrice));
            statisticsContainer.add(new Label("Highest weight: " + highestPrice));
            statisticsContainer.add(new Label("Lowest weight: " + lowestPrice));
            statisticsDialog.add(BorderLayout.CENTER, statisticsContainer);
String fileName = "product_statistics.txt";
String fileContents = "Total Products: " + totalProducts + "\n"
        + "Average weight: " + averagePrice + "\n"
        + "Highest weight: " + highestPrice + "\n"
        + "Lowest weight: " + lowestPrice;

FileSystemStorage fs = FileSystemStorage.getInstance();
String[] roots = fs.getRoots();
String filePath = roots[0] + fileName;

try (OutputStream os = fs.openOutputStream(filePath)) {
    os.write(fileContents.getBytes("UTF-8"));
    os.flush();
    os.close();
          Dialog.show("Success", "File downloaded successfully", "OK", null);
} catch (IOException ex) {
    ex.printStackTrace();
}


            Button closeButton = new Button("Close");
            closeButton.addActionListener(evt -> statisticsDialog.dispose());
            statisticsDialog.add(BorderLayout.SOUTH, closeButton);

            statisticsDialog.show();
            
        });

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
            }    catch (IOException ex) {
                 }
        });
        this.add(statisticsButton);
           this.add(chat);
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
        this.add(applyButton);
   
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
                } catch (IOException ex) {
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
