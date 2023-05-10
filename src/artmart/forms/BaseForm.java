package artmart.forms;

import artmart.forms.Event.AllEventsForm;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;



public class BaseForm extends com.codename1.ui.Form {

    public void init(Resources theme) throws IOException {
        Toolbar tb = getToolbar();

        tb.getAllStyles().setBgColor(0xffffff);




EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);
Label taglineLabel = new Label(scaledImg);
taglineLabel.setAlignment(CENTER);
taglineLabel.setVerticalAlignment(CENTER);
Container taglineContainer = BorderLayout.south(taglineLabel);
taglineContainer.setUIID("SideCommand");



        tb.addComponentToSideMenu(taglineContainer);
        tb.addMaterialCommandToSideMenu("ArtMart", FontImage.MATERIAL_HOME, e -> {

        });
        tb.addMaterialCommandToSideMenu("Custom Products", FontImage.MATERIAL_LIST, e -> {
            getCustomProductForm f = null;
            try {
                f = new getCustomProductForm();
            } catch (IOException ex) {
            }
            f.show();
        });
   
   
        tb.addMaterialCommandToSideMenu("Categories", FontImage.MATERIAL_LIST, e -> {
            getCategorieForm f = null;
            try {
                f = new getCategorieForm();
            } catch (IOException ex) {
            }
            f.show();
        });
               tb.addMaterialCommandToSideMenu("Applies", FontImage.MATERIAL_LIST, e -> {
            getApplyForm f = null;
            try {
                f = new getApplyForm();
            } catch (IOException ex) {
            }
            f.show();
        });
                    tb.addMaterialCommandToSideMenu("Blogs", FontImage.MATERIAL_LIST, e -> {
            getBlogsForm f = null;
            try {
                f = new getBlogsForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Blogs Categories", FontImage.MATERIAL_LIST, e -> {
            getBlogCategoryForm f = null;
            try {
                f = new getBlogCategoryForm();
            } catch (IOException ex) {
            }
            f.show();
        });
       tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_LIST, e -> {
            AllEventsForm f = null;
            try {
                f = new AllEventsForm();
            } catch (IOException ex) {
            }
            f.show();
        });  
    }
}
