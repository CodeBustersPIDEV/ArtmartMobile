package artmart.forms;

import artmart.entities.PaymentOption;
import artmart.service.PaymentShipWebService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class getpaymntsForm extends BaseForm {

    private MultiList cpList;
    private List<PaymentOption> options;
    private TextField searchField;

    public getpaymntsForm() throws IOException {
        Button resetBtn = new Button(FontImage.MATERIAL_RESTORE);

        resetBtn.addActionListener(ee -> {
            getpaymntsForm f = null;
            try {
                f = new getpaymntsForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        searchField = new TextField("", "Enter Payment Title");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                PaymentOption selectedPromo = null;
                for (PaymentOption p : options) {
                    if (p.getName()== null ? searchId == null : p.getName().equals(searchId)) {
                        selectedPromo = p;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormPaym n = new editFormPaym(selectedPromo);
                    n.show();
                    
                    
                } else {
                    Dialog.show("Error", "Payment not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {System.out.println(ex);
            } catch (IOException ex) {System.out.println(ex);
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Sort by Name");
        sortButton.addActionListener(e -> {
            Collections.sort(options, new Comparator<PaymentOption>() {
                @Override
                public int compare(PaymentOption b1, PaymentOption b2) {
                    return b1.getName().compareToIgnoreCase(b2.getName());
                }
            });
            updateList();
        });
        this.add(resetBtn);
        addComponent(BorderLayout.south(sortButton));
        cpList = new MultiList(new DefaultListModel<>());
        add(cpList);
        getAllOptions();
    }

    private void getAllOptions() {
        PaymentShipWebService service = new PaymentShipWebService();
        options = service.getAllList();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (PaymentOption b : options) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", b.getName());
            item.put("Line2", b.getAvailablecountries());
            item.put("Line5", b.getPaymentoptionId());
            model.addItem(item);
        }
        cpList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    Map<String, Object> selectedItem = (Map<String, Object>) cpList.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line5");
                    PaymentOption selectedEvent = null;
                    for (PaymentOption e : options) {
                        if (e.getPaymentoptionId()== eventId) {
                            selectedEvent = e;
                            break;
                        }
                    }
                  editFormPaym myForm2;
                try {
                    myForm2 = new editFormPaym(selectedEvent);
                  myForm2.show();
                } catch (ParseException ex) {
                    System.out.println("ex");
                } catch (IOException ex) {
                    System.out.println("ex");
                }
            }
        });

    }

    public void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (PaymentOption b : options) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", b.getName());
            item.put("Line2", b.getAvailablecountries());
            item.put("Line5", b.getPaymentoptionId());
            model.addItem(item);
        }
    }
}
