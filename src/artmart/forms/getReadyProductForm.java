/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.Category;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artmart.entities.ReadyProduct;
import artmart.service.CategorieWebService;
import artmart.service.ReadyProductWebService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Collections;
import java.util.Comparator;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;
import com.codename1.ui.ComboBox;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class getReadyProductForm extends BaseForm {

    private MultiList rpList;
    private List<ReadyProduct> readyproduct;
    private TextField searchField;
    CategorieWebService serviceCat = new CategorieWebService();

    ReadyProduct readyp = new ReadyProduct();

    public getReadyProductForm() throws IOException {

        Button addButton = new Button("Add");
        addButton.addActionListener(ee -> {
            newReadyProductForm f = null;
            try {
                f = new newReadyProductForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        searchField = new TextField("", "Enter Product Name");
        Button searchButton = new Button("Search");
        searchField.setColumns(14);

        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                ReadyProduct selectedPromo = null;
                for (ReadyProduct p : readyproduct) {
                    if (p.getName() == null ? searchId == null : p.getName().equals(searchId)) {
                        selectedPromo = p;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editReadyProductForm myForm2 = new editReadyProductForm(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Ready Product not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);

        ComboBox<Category> categoryComboBox = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();
        for (Category category : categories) {
            categoryComboBox.addItem(category);
        }

        categoryComboBox.addActionListener(e -> {
            Category selectedCategory = categoryComboBox.getSelectedItem();
            List<ReadyProduct> filteredProducts = filterProductsByCategory(readyproduct, selectedCategory);
            updateCatList(filteredProducts);
        });

        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Price (asc)");
        sortButton.addActionListener(e -> {
            Collections.sort(readyproduct, new Comparator<ReadyProduct>() {
                @Override
                public int compare(ReadyProduct p1, ReadyProduct p2) {
                    return Integer.compare(p1.getPrice(), p2.getPrice());
                }
            });
            updateList();
        });

        this.init(Resources.getGlobalResources());
        Button sortButton2 = new Button("Price (desc)");
        sortButton2.addActionListener(e -> {
            Collections.sort(readyproduct, new Comparator<ReadyProduct>() {
                @Override
                public int compare(ReadyProduct p1, ReadyProduct p2) {
                    return Integer.compare(p2.getPrice(), p1.getPrice());
                }
            });
            updateList();
        });

        this.init(Resources.getGlobalResources());
        Button cancel = new Button(FontImage.MATERIAL_CANCEL);
        cancel.addActionListener(e -> {
            getAllReadyProducts();
        });

        Container addContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        addContainer.add(addButton);
        this.add(addContainer);

        Container sContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        sContainer.add(searchContainer);
        this.add(sContainer);

        Container sortContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        sortContainer.add(sortButton);
        sortContainer.add(sortButton2);
        sortContainer.add(cancel);
        this.add(sortContainer);

        Container catContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        catContainer.add(categoryComboBox);
        this.add(catContainer);

        rpList = new MultiList(new DefaultListModel<>());
        add(rpList);
        getAllReadyProducts();

    }

    private void getAllReadyProducts() {
        ReadyProductWebService service = new ReadyProductWebService();
        readyproduct = service.getAllReadyProducts();
        System.out.println(readyproduct);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) rpList.getModel();
        model.removeAll();
        for (ReadyProduct rp : readyproduct) {
            Map<String, Object> item = new HashMap<>();

            // Create and add the image to the first column
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
            String filename = rp.getImage().substring(rp.getImage().lastIndexOf("/") + 1);
            URLImage imgUrl = URLImage.createToStorage(placeholder, filename, rp.getImage());
            ImageViewer imageViewer = new ImageViewer(imgUrl);
            Label label = new Label();
            label.setIcon(imgUrl);
            item.put("Line1", label);

            // Add the name, description, and price to the second column
            item.put("Line2", rp.getName());
            item.put("Line3", rp.getDescription());
            item.put("Line4", rp.getPrice());
            model.addItem(item);
        }

        rpList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) rpList.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line4");
                    ReadyProduct selectedEvent = null;
                    for (ReadyProduct event : readyproduct) {
                        if (event.getPrice() == eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    editReadyProductForm myForm2 = new editReadyProductForm(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) rpList.getModel();
        model.removeAll();
        for (ReadyProduct p : readyproduct) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", p.getName());
            item.put("Line2", p.getDescription());
            item.put("Line3", p.getPrice());
            model.addItem(item);
        }
    }

    private void updateCatList(List<ReadyProduct> products) {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) rpList.getModel();
        model.removeAll();
        for (ReadyProduct p : products) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", p.getName());
            item.put("Line2", p.getDescription());
            item.put("Line3", p.getPrice());
            model.addItem(item);
        }
    }

    private List<ReadyProduct> filterProductsByCategory(List<ReadyProduct> products, Category selectedCategory) {
        List<ReadyProduct> filteredProducts = new ArrayList<>();
        for (ReadyProduct product : products) {
            if (product.getCategoryId().equals(selectedCategory)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
