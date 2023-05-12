package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

public class editFormCustomProduct extends BaseForm {

        CategorieWebService serviceCat = new CategorieWebService();
        CustomproductWebService service = new CustomproductWebService();
    public editFormCustomProduct(CustomProduct e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        TextField nomField = new TextField(e.getName(), "name");
        TextField descriptifField = new TextField(e.getDescription(), "description");
        TextField dimfield = new TextField(e.getDimensions(), "dimensions");
        TextField weightfield = new TextField(e.getWeight() + "", "weight");
      
        TextField clientfield = new TextField(e.getClient() + "", "client");
        TextField materialfield = new TextField(e.getMaterial(), "materiel");
       TextField imagefield = new TextField(e.getImage(), "image");
        ComboBox<Category> categorieField = new ComboBox<>();
        List<Category> categories = serviceCat.getAllCategorie();
        
        for (Category categorie : categories) {
            categorieField.addItem(categorie);
        }
        Label nameLabel = new Label("Name:");
        this.add(nameLabel);
        this.add(nomField);
        Label desc = new Label("Description:");
        this.add(desc);
        this.add(descriptifField);
     Label dims = new Label("Dimension:");
        this.add(dims);
        this.add(dimfield);
  Label weightss = new Label("Weight:");
        this.add(weightss);
        this.add(weightfield);
  Label clientss = new Label("Client:");
        this.add(clientss);
        this.add(clientfield);
  Label mat = new Label("Material:");
        this.add(mat);
        this.add(materialfield);

       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = e.getImage().substring(e.getImage().lastIndexOf("/") + 1);

        System.out.println(filename);
// Create a URLImage with the image URL and placeholder
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, e.getImage());

// Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(imgUrl);
    this.add(imageViewer);
        this.add(imagefield);
        this.add(categorieField);
     Validator validator = new Validator();
        validator.addConstraint(nomField, new LengthConstraint(1, "Nom is required"));
             validator.addConstraint(dimfield, new LengthConstraint(1, "dimmension is required"));
                    validator.addConstraint(weightfield, new LengthConstraint(1, "weight is required"));
                           validator.addConstraint(clientfield, new LengthConstraint(1, "client is required"));
                                  validator.addConstraint(materialfield, new LengthConstraint(1, "material is required"));
                                      validator.addConstraint(imagefield, new LengthConstraint(1, "image is required"));
                                          validator.addConstraint(categorieField, new LengthConstraint(1, "category is required"));
                                          validator.addConstraint(descriptifField, new LengthConstraint(1, "description is required"));
        Button submitButton = new Button("Submit");
        
        
        submitButton.addActionListener(s-> {
                 if (validator.isValid()) {
            String nom = nomField.getText();
            String descriptif = descriptifField.getText();
            String dim = dimfield.getText();
        float weight = Float.parseFloat(weightfield.getText());
            int client = Integer.parseInt(clientfield.getText());
               String image = imagefield.getText();
                        String material = materialfield.getText();
            Category selectedCategorie = categorieField.getSelectedItem();

            CustomProduct newEvent = new CustomProduct();
            newEvent.setCustomProductId(e.getCustomProductId());
            newEvent.setName(nom);
            newEvent.setDescription(descriptif);
            newEvent.setDimensions(dim);
            newEvent.setWeight(weight);
            newEvent.setMaterial(material);
            newEvent.setImage(image);
            newEvent.setClient(client);
            newEvent.setIdCategorie(new Category(selectedCategorie.getCategoriesId()));
            service.editCp(newEvent);
          getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }}
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
            }
            service.delcp(e);
            myForm.show();
        });
        Button applyButton = new Button("Apply");
applyButton.addActionListener(ee -> {
    CustomproductWebService service = new CustomproductWebService();
    service.applyCustomProduct(e.getCustomProductId()); // replace customProductId with the ID of the custom product you want to apply to
    
              Dialog.show("Success", "Application successfully sent", "OK", null);
});
Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
buttonContainer.add(goToFormButton);
buttonContainer.add(deleteButton);
buttonContainer.add(submitButton);
this.add(buttonContainer);


    }

}
