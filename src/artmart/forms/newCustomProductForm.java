package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import artmart.entities.Category;
import artmart.entities.CustomProduct;
import artmart.service.CategorieWebService;
import artmart.service.CustomproductWebService;
import java.io.IOException;


public class newCustomProductForm extends BaseForm {
        CategorieWebService serviceCat = new CategorieWebService();

    public newCustomProductForm() throws IOException {
        this.init(Resources.getGlobalResources());

   TextField nomField = new TextField("", "Nom");
        TextField descriptifField = new TextField("", "Description");
        TextField dimfield = new TextField("", "Dimentions");
        TextField weightfield = new TextField("" + "", "weight");
      
        TextField clientfield = new TextField("", "client");
    TextField materialfield = new TextField("", "materiel");
       TextField imagefield = new TextField("", "imageURL");
        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();
        for (Category categorie : categories) {
            categorieField.addItem(categorie);
        }

        this.add(nomField)
                .add(descriptifField)
                .add(dimfield)
                .add(weightfield)
                .add(clientfield)
                .add(materialfield)
                .add(imagefield)
                .add(categorieField);
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
      String nom = nomField.getText();
            String descriptif = descriptifField.getText();
            String dim = dimfield.getText();
      float weight = Float.parseFloat(weightfield.getText());
            int client = Integer.parseInt(clientfield.getText());
               String image = imagefield.getText();
                        String material = materialfield.getText();
            Category selectedCategorie = categorieField.getSelectedItem();

            CustomProduct newEvent = new CustomProduct();
    
            newEvent.setName(nom);
            newEvent.setDescription(descriptif);
            newEvent.setDimensions(dim);
            newEvent.setWeight(weight);
            newEvent.setMaterial(material);
            newEvent.setImage(image);
            newEvent.setClient(client);
            newEvent.setIdCategorie(new Category(selectedCategorie.getCategoriesId()));
            CustomproductWebService service = new CustomproductWebService();
            service.newCp(newEvent);
                  getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
             
            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
           
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
