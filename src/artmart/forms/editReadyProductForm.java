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

/**
 *
 * @author rymae
 */
public class editReadyProductForm extends BaseForm {

    CategorieWebService serviceCat = new CategorieWebService();
    ReadyProductWebService service = new ReadyProductWebService();

    public editReadyProductForm(ReadyProduct e) throws ParseException, IOException {
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
        TextField nameField = new TextField(e.getName(), "name");
        TextField descriptifField = new TextField(e.getDescription(), "description");
        TextField dimfield = new TextField(e.getDimensions(), "dimensions");
        TextField weightfield = new TextField(e.getWeight() + "", "weight");

        TextField userfield = new TextField(e.getUser() + "", "user");
        TextField pricefield = new TextField(e.getPrice() + "", "price");
        TextField materialfield = new TextField(e.getMaterial(), "materiel");
        TextField imagefield = new TextField(e.getImage(), "image");
        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();

        for (Category categorie : categories) {
            categorieField.addItem(categorie);

            if (categorie.getCategoriesId() == e.getCategoryId().getCategoriesId()) {
                categorieField.setSelectedItem(categorie);
            }
        }

        Label nameLabel = new Label("Name");
        this.add(nameLabel);
        this.add(nameField);

        Label descriptifLabel = new Label("Description");
        this.add(descriptifLabel);
        this.add(descriptifField);

        Label dimLabel = new Label("Dimensions");
        this.add(dimLabel);
        this.add(dimfield);

        Label weightLabel = new Label("Weight");
        this.add(weightLabel);
        this.add(weightfield);

        Label userLabel = new Label("User");
        this.add(userLabel);
        this.add(userfield);

        Label priceLabel = new Label("Price");
        this.add(priceLabel);
        this.add(pricefield);

        Label matLabel = new Label("Material");
        this.add(matLabel);
        this.add(materialfield);

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = e.getImage().substring(e.getImage().lastIndexOf("/") + 1);

        System.out.println(filename);
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, e.getImage());

        ImageViewer imageViewer = new ImageViewer(imgUrl);
        this.add(imageViewer);
        this.add(imagefield);

        Label catLabel = new Label("Category");
        this.add(catLabel);
        this.add(categorieField);

        Validator validator = new Validator();
        validator.addConstraint(nameField, new LengthConstraint(1, "Name is required"));
        validator.addConstraint(dimfield, new LengthConstraint(1, "Dimmension is required"));
        validator.addConstraint(weightfield, new LengthConstraint(1, "Weight is required"));
        validator.addConstraint(userfield, new LengthConstraint(1, "User is required"));
        validator.addConstraint(materialfield, new LengthConstraint(1, "Material is required"));
        validator.addConstraint(imagefield, new LengthConstraint(1, "Image is required"));
        validator.addConstraint(categorieField, new LengthConstraint(1, "Category is required"));
        validator.addConstraint(descriptifField, new LengthConstraint(1, "Description is required"));
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s -> {
            if (validator.isValid()) {
                String nom = nameField.getText();
                String descriptif = descriptifField.getText();
                String dim = dimfield.getText();
                float weight = Float.parseFloat(weightfield.getText());
                int user = Integer.parseInt(userfield.getText());
                int price = Integer.parseInt(pricefield.getText());
                String image = imagefield.getText();
                String material = materialfield.getText();
                Category selectedCategorie = categorieField.getSelectedItem();

                ReadyProduct newEvent = new ReadyProduct();
                newEvent.setReadyProductId(e.getReadyProductId());
                newEvent.setName(nom);
                newEvent.setDescription(descriptif);
                newEvent.setDimensions(dim);
                newEvent.setWeight(weight);
                newEvent.setMaterial(material);

                newEvent.setImage(image);
                newEvent.setUser(user);
                newEvent.setPrice(price);
                newEvent.setCategoryId(new Category(selectedCategorie.getCategoriesId()));
                service.editReadyProduct(newEvent);
                getReadyProductForm myForm = null;
                try {
                    myForm = new getReadyProductForm();
                } catch (IOException ex) {
                }
                myForm.show();
            }
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getReadyProductForm myForm = null;
            try {
                myForm = new getReadyProductForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });

      Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getReadyProductForm myForm = null;
            try {
                myForm = new getReadyProductForm();
            } catch (IOException ex) {
            }
            service.deleteReadyProduct(e);
            myForm.show();
        });
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
        buttonContainer.add(deleteButton);
        buttonContainer.add(submitButton);
        this.add(buttonContainer);

    }

}
