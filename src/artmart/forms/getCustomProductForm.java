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
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;


public class getCustomProductForm extends BaseForm {

    private MultiList cpList;
    private List<CustomProduct> customproduct;
    private TextField searchField;
    private Button statisticsButton;
     private Timer messageTimer;
      String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();
     
CustomProduct product = new CustomProduct();
 
    public getCustomProductForm() throws IOException {
          Button cancel = new Button(FontImage.MATERIAL_RESTORE);
        cancel.addActionListener(e -> {
            getAllCp();
        });
Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);

        // Show the welcome message for 5 seconds
        showMessage("Welcome To Custom Product", 5000);
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
    this.add(cancel);
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
            
             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = cp.getImage().substring(cp.getImage().lastIndexOf("/") + 1);

        System.out.println(filename);
// Create a URLImage with the image URL and placeholder
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, cp.getImage());

// Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(imgUrl);
        Label label=new Label();
        label.setIcon(imgUrl);
            item.put("Line1", label);  
                   item.put("Line2", cp.getName());  
                       item.put("Line3", cp.getDescription());  
      
            model.addItem(item);
        }
        cpList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) cpList.getSelectedItem();
                    String eventId = (String) selectedItem.get("Line3");
                    CustomProduct selectedEvent = null;
                    for (CustomProduct event : customproduct) {
                        if (event.getDescription()== eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    if(role.equals("client")||role.equals("admin")){
                    editFormCustomProduct myForm2 = new editFormCustomProduct(selectedEvent);
                    myForm2.show();}else{
                        
                    editFormCpArtist myForm2 = new editFormCpArtist(selectedEvent);
                    myForm2.show();
                    }
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

   
    }
private void showMessage(String message, int duration) {
        if (messageTimer != null) {
            messageTimer.cancel();
        }

        // Set the message in the toolbar title
        getToolbar().setTitle(message);

        // Create a timer to reset the title after the duration
        messageTimer = new Timer();
        messageTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Display.getInstance().callSerially(() -> {
                    // Reset the toolbar title
                    getToolbar().setTitle("ArtMart");
                });
            }
        }, duration);
    }
    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (CustomProduct p : customproduct) {
            Map<String, Object> item = new HashMap<>();
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = p.getImage().substring(p.getImage().lastIndexOf("/") + 1);

        System.out.println(filename);
// Create a URLImage with the image URL and placeholder
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, p.getImage());

// Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(imgUrl);
        Label label=new Label();
        label.setIcon(imgUrl);
            item.put("Line1", label);  
                   item.put("Line2", p.getName());  
                       item.put("Line3", p.getDescription());  
            model.addItem(item);
        }
    }
}
