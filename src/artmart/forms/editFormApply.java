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
public class editFormApply extends BaseForm {
      ApplyWebService service = new ApplyWebService();
    public editFormApply(Apply e) throws ParseException {
     this.init(Resources.getGlobalResources());
 
 Label cp = new Label("Custom Product Name: " + e.getCustomproduct());
Label artist = new Label("Artist Name: " + e.getArtist());

// set the style for the Labels

cp.getStyle().setFgColor(0x333333);


artist.getStyle().setFgColor(0x333333);

// create a BoxLayout container with Y_AXIS orientation
Container labelsContainer = BoxLayout.encloseY(cp, artist);

// add the Labels container to the form
this.add(labelsContainer);

// create the buttons
Button goToFormButton = new Button("Go back");
goToFormButton.addActionListener(ee -> {
    getApplyForm myForm = new getApplyForm();
    myForm.show();
});

Button applyButton = new Button("Cancel");
applyButton.addActionListener(ee -> {
    ApplyWebService service = new ApplyWebService();
    service.applyCustomProduct(e.getApplyId()); 
});

Button deleteButton = new Button("Delete");
deleteButton.addActionListener(cc -> {
    service.delApply(e);
    getApplyForm myForm = new getApplyForm();
    myForm.show();
});

// add the buttons to the form
this.addAll(goToFormButton, applyButton, deleteButton);

    

}}
