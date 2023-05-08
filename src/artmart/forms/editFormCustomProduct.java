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

public class editFormCustomProduct extends BaseForm {

        CategorieWebService serviceCat = new CategorieWebService();
        CustomproductWebService service = new CustomproductWebService();
    public editFormCustomProduct(CustomProduct e) throws ParseException {
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

        this.add(nomField);

        this.add(descriptifField);

        this.add(dimfield);

        this.add(weightfield);

        this.add(clientfield);

        this.add(materialfield);

     
       this.add(imagefield);
        this.add(categorieField);

        Button submitButton = new Button("Submit");
        
        
        submitButton.addActionListener(s-> {
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
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getCustomProductForm myForm = new getCustomProductForm();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getCustomProductForm myForm = new getCustomProductForm();
            service.delcp(e);
            myForm.show();
        });
        Button applyButton = new Button("Apply");
applyButton.addActionListener(ee -> {
    CustomproductWebService service = new CustomproductWebService();
    service.applyCustomProduct(e.getCustomProductId()); // replace customProductId with the ID of the custom product you want to apply to
});

this.add(deleteButton);
this.add(goToFormButton);
this.add(submitButton);
this.add(applyButton);

    }

}