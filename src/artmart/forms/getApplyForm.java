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
public class getApplyForm extends BaseForm  {
      private MultiList eventList;

    public getApplyForm() {
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllApply();
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
}
