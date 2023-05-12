package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Apply;
import artmart.entities.Category;
import artmart.service.ApplyWebService;
import artmart.service.CustomproductWebService;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


public class editFormApply extends BaseForm {
      ApplyWebService service = new ApplyWebService();
    public editFormApply(Apply e) throws ParseException, IOException {
     this.init(Resources.getGlobalResources());
  String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();
     
 Label cp = new Label("Custom Product Name: " + e.getCustomproduct());
Label artist = new Label("Artist Name: " + e.getArtist());
Label status = new Label("Status: " + e.getStatus());

// set the style for the Labels

cp.getStyle().setFgColor(0x333333);


artist.getStyle().setFgColor(0x333333);

status.getStyle().setFgColor(0x333333);

// create a BoxLayout container with Y_AXIS orientation
Container labelsContainer = BoxLayout.encloseY(cp, artist,status);

// add the Labels container to the form
this.add(labelsContainer);

// create the buttons
Button goToFormButton = new Button("Go back");
goToFormButton.addActionListener(ee -> {
    getApplyForm myForm = null;
         try {
             myForm = new getApplyForm();
         } catch (IOException ex) {
         }
    myForm.show();
});

Button applyButton = new Button("done");
applyButton.addActionListener(ee -> {
    ApplyWebService service = new ApplyWebService();
    service.applyCustomProduct(e.getApplyId()); 
        getApplyForm myForm = null;
         try {
             myForm = new getApplyForm();
         } catch (IOException ex) {
         }
    myForm.show();
});

Button deleteButton = new Button("Delete");
deleteButton.addActionListener(cc -> {
    service.delApply(e);
    getApplyForm myForm = null;
         try {
             myForm = new getApplyForm();
         } catch (IOException ex) {
         }
    myForm.show();
});
this.add(goToFormButton);
// add the buttons to the form
if(role.equals("admin")||role.equals("artist")){
this.addAll( applyButton, deleteButton);

}

}}
