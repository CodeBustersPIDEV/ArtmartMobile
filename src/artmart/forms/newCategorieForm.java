package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Category;
import artmart.service.CategorieWebService;
import java.io.IOException;

public class newCategorieForm extends BaseForm {

    public newCategorieForm() throws IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField("", "Nom");
 
        this.add(nomField);


        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String nom = nomField.getText();

            Category newCat = new Category();
            newCat.setName(nom);
      
            CategorieWebService service = new CategorieWebService();
            service.newCategorie(newCat);
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
