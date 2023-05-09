package artmart.forms;

import artmart.entities.Apply;
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
import artmart.service.ApplyWebService;
import artmart.service.CategorieWebService;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
public class getApplyForm extends BaseForm  {
      private MultiList eventList;

    public getApplyForm() {
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllApply();
         // Create and add the "Download" button
        Button downloadButton = new Button("Download as CSV");
        downloadButton.addActionListener(e -> downloadCSV());
        Container buttonContainer = new Container(new BorderLayout());
        buttonContainer.add(BorderLayout.EAST, downloadButton);
        add(buttonContainer);
    }

    private void getAllApply() {
        ApplyWebService service = new ApplyWebService();
        List<Apply> cats = service.getAllApply();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (Apply c : cats) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line2",  c.getStatus());
  
               item.put("Line1", c.getCustomproduct());
                     item.put("Line4", c.getApplyId());
     
            model.addItem(item);
        }
        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) eventList.getSelectedItem();
                    int catId = (int) selectedItem.get("Line4");
                    Apply selectedEvent = null;
                    for (Apply c : cats) {
                        if (c.getApplyId()== catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormApply myForm2 = new editFormApply(selectedEvent);
                       myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

    }
    
    private void downloadCSV() {
        ApplyWebService service = new ApplyWebService();
        List<Apply> applies = service.getAllApply();
        String csvContent = "Apply ID,Custom Product,Status\n";
        for (Apply apply : applies) {
            csvContent += apply.getApplyId() + "," + apply.getCustomproduct() + "," + apply.getStatus() + "\n";
        }
        String fileName = "apply_list.csv";
  FileSystemStorage fs = FileSystemStorage.getInstance();
String[] roots = fs.getRoots();
String filePath = roots[0] + fileName;
        try (OutputStream os = Storage.getInstance().createOutputStream(filePath)) {
            os.write(csvContent.getBytes("UTF-8"));
            Dialog.show("Success", "File downloaded successfully", "OK", null);
        } catch (IOException ex) {
            Dialog.show("Error", "Failed to download the file", "OK", null);
        }
    }
}
