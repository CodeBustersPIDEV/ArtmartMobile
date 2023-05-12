/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import artmart.entities.Category;
import artmart.entities.ReadyProduct;
import artmart.service.CategorieWebService;
import artmart.service.ReadyProductWebService;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

public class newReadyProductForm extends BaseForm {

    CategorieWebService serviceCat = new CategorieWebService();

    public newReadyProductForm() throws IOException {
        this.init(Resources.getGlobalResources());

        TextField nameField = new TextField("", "Name");
        TextField descriptifField = new TextField("", "Description");
        TextField dimfield = new TextField("", "Dimentions");
        TextField weightfield = new TextField("" + "", "weight");

        TextField userfield = new TextField("", "user");
        TextField pricefield = new TextField("", "price");
        TextField materialfield = new TextField("", "materiel");
        TextField imagefield = new TextField("", "imageURL");
        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();
        for (Category categorie : categories) {
            categorieField.addItem(categorie);
        }

        this.add(nameField)
                .add(descriptifField)
                .add(dimfield)
                .add(weightfield)
                .add(userfield)
                .add(pricefield)
                .add(materialfield)
                .add(imagefield)
                .add(categorieField);

        Validator validator = new Validator();
        validator.addConstraint(nameField, new LengthConstraint(1, "Name is required"));
        validator.addConstraint(dimfield, new LengthConstraint(1, "Dimension is required"));
        validator.addConstraint(weightfield, new LengthConstraint(1, "Weight is required"));
        validator.addConstraint(userfield, new LengthConstraint(1, "User is required"));
        validator.addConstraint(materialfield, new LengthConstraint(1, "Material is required"));
        validator.addConstraint(imagefield, new LengthConstraint(1, "Image is required"));
        validator.addConstraint(categorieField, new LengthConstraint(1, "Category is required"));
        validator.addConstraint(descriptifField, new LengthConstraint(1, "Description is required"));
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
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

                newEvent.setName(nom);
                newEvent.setDescription(descriptif);
                newEvent.setDimensions(dim);
                newEvent.setWeight(weight);
                newEvent.setMaterial(material);
                newEvent.setImage(image);
                newEvent.setUser(user);
                newEvent.setCategoryId(new Category(selectedCategorie.getCategoriesId()));
                ReadyProductWebService service = new ReadyProductWebService();
                service.newReadyProduct(newEvent);
                getReadyProductForm myForm = null;
                try {
                    myForm = new getReadyProductForm();
                } catch (IOException ex) {

                }
                myForm.show();
            }
        }
        );

        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");

        goToFormButton.addActionListener(e
                -> {
            getReadyProductForm myForm = null;
            try {
                myForm = new getReadyProductForm();
            } catch (IOException ex) {

            }
            myForm.show();
        }
        );

        this.add(goToFormButton);
    }
}
