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
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;


public class newCustomProductForm extends BaseForm {
      
        CategorieWebService serviceCat = new CategorieWebService();
           private TextField imageField;
    public newCustomProductForm() throws IOException {
        
        this.init(Resources.getGlobalResources());
     
   TextField nomField = new TextField("", "Nom");
        TextField descriptifField = new TextField("", "Description");
        TextField dimfield = new TextField("", "Dimentions");
        TextField weightfield = new TextField("" + "", "weight");
      
        TextField clientfield = new TextField("", "client");
    TextField materialfield = new TextField("", "materiel");
             imageField = new TextField("", "Image URL");
        Button selectImageButton = new Button("Select Image");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                selectImage();
            }
        });

        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();
        for (Category categorie : categories) {
            categorieField.addItem(categorie);
        }

        this.add(nomField)
                .add(descriptifField)
                .add(dimfield)
                .add(weightfield)
                .add(materialfield)
                .add(imageField)
                .add(categorieField);
        Validator validator = new Validator();
        validator.addConstraint(nomField, new LengthConstraint(1, "Nom is required"));
             validator.addConstraint(dimfield, new LengthConstraint(1, "dimmension is required"));
                    validator.addConstraint(weightfield, new LengthConstraint(1, "weight is required"));
                                  validator.addConstraint(materialfield, new LengthConstraint(1, "material is required"));
                                      validator.addConstraint(imageField, new LengthConstraint(1, "image is required"));
                                          validator.addConstraint(categorieField, new LengthConstraint(1, "category is required"));
                                          validator.addConstraint(descriptifField, new LengthConstraint(1, "description is required"));
                                     
      
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(s
                -> {
             if (validator.isValid()) {
      String nom = nomField.getText();
            String descriptif = descriptifField.getText();
            String dim = dimfield.getText();
      float weight = Float.parseFloat(weightfield.getText());
      int client = 1;
               String image = imageField.getText();
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
        }}
        );
            this.add(selectImageButton);
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
 private void selectImage() {
        Display.getInstance().openGallery(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() != null) {
                    String imageUrl = (String) evt.getSource();
                    imageField.setText(imageUrl);
                }
            }
        }, Display.GALLERY_IMAGE);
    }

    public static void main(String[] args) {
        UITimer.timer(200, true, () -> {
            if (Display.getInstance().isInitialized()) {
                try {
                    Resources theme = UIManager.initFirstTheme("/theme");
                    newCustomProductForm form = new newCustomProductForm();
                    form.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
