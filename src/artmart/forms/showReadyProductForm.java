/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.Category;
import artmart.entities.ReadyProduct;
import artmart.service.CategorieWebService;
import artmart.service.ProductReviewWebService;
import artmart.service.ReadyProductWebService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.util.List;

public class showReadyProductForm extends BaseForm {

    CategorieWebService serviceCat = new CategorieWebService();
    ReadyProductWebService service = new ReadyProductWebService();

    public showReadyProductForm(ReadyProduct e) throws ParseException, IOException {
        Label headingLabel = new Label("Edit Product");
        headingLabel.getUnselectedStyle().setFgColor(0xe35d59);
        headingLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));

        Container headContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        headContainer.add(headingLabel);
        this.add(headContainer);

        Button viewReviewsButton = new Button("Check Reviews");
        viewReviewsButton.addActionListener(evt -> {
            try {
                getProductReviewsForm reviewsForm = new getProductReviewsForm(e.getReadyProductId());
                reviewsForm.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Container checkContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        checkContainer.add(viewReviewsButton);
        this.add(checkContainer);

        this.init(Resources.getGlobalResources());
        System.out.println(e);
        Label nameLabel = new Label("Name:");
        Label nameValueLabel = new Label(e.getName());
        Label descriptifLabel = new Label("Description:");
        Label descriptifValueLabel = new Label(e.getDescription());
        Label dimLabel = new Label("Dimensions:");
        Label dimValueLabel = new Label(e.getDimensions());
        Label weightLabel = new Label("Weight:");
        Label weightValueLabel = new Label(Float.toString(e.getWeight()));
        Label userLabel = new Label("User:");
        Label userValueLabel = new Label(Integer.toString(e.getUser()));
        Label priceLabel = new Label("Price:");
        Label priceValueLabel = new Label(Float.toString(e.getPrice()));
        Label matLabel = new Label("Material:");
        Label matValueLabel = new Label(e.getMaterial());
        Label imageLabel = new Label("Image:");
        Label imageValueLabel = new Label(e.getImage());
        Label catLabel = new Label("Category:");
        Label catValueLabel = new Label(e.getCategoryId().getName());
        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();

        for (Category categorie : categories) {
            categorieField.addItem(categorie);

            if (categorie.getCategoriesId() == e.getCategoryId().getCategoriesId()) {
                categorieField.setSelectedItem(categorie);
            }
        }
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = e.getImage().substring(e.getImage().lastIndexOf("/") + 1);

        System.out.println(filename);
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, e.getImage());

        ImageViewer imageViewer = new ImageViewer(imgUrl);
        
        this.add(imageViewer);
        this.add(nameValueLabel);
        this.add(descriptifLabel);
        this.add(descriptifValueLabel);
        this.add(dimLabel);
        this.add(dimValueLabel);
        this.add(weightLabel);
        this.add(weightValueLabel);
        this.add(userLabel);
        this.add(userValueLabel);
        this.add(priceLabel);
        this.add(priceValueLabel);
        this.add(matLabel);
        this.add(matValueLabel);

        
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getReadyProductForm myForm = null;
            try {
                myForm = new getReadyProductForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
        this.add(buttonContainer);

    }

}
