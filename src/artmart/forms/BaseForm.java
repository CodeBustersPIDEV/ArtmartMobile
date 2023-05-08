package artmart.forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

public class BaseForm extends com.codename1.ui.Form {

    public void init(Resources theme) {
        Toolbar tb = getToolbar();

        tb.getAllStyles().setBgColor(0xffffff);

        Image logo = theme.getImage("logo.png");
        Label logoLabel = new Label(logo);
        Container logoContainer = BorderLayout.center(logoLabel);
        logoContainer.setUIID("SideCommandLogo");
        tb.addComponentToSideMenu(logoContainer);

        Label taglineLabel = new Label("Custom Products");
        taglineLabel.setUIID("SideCommandTagline");
        Container taglineContainer = BorderLayout.south(taglineLabel);
        taglineContainer.setUIID("SideCommand");

        tb.addComponentToSideMenu(taglineContainer);

        tb.addMaterialCommandToSideMenu("Custom Products", FontImage.MATERIAL_HOME, e -> {
            getCustomProductForm f = new getCustomProductForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Add Custom Products", FontImage.MATERIAL_ADD, e -> {
            newCustomProductForm f = new newCustomProductForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Categories", FontImage.MATERIAL_LIST, e -> {
            getCategorieForm f = new getCategorieForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Add Categories", FontImage.MATERIAL_ADD, e -> {
            newCategorieForm f = new newCategorieForm();
            f.show();
        });
    }
}