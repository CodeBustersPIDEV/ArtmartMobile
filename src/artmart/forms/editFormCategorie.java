package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Category;
import artmart.service.CategorieWebService;
import java.io.IOException;


public class editFormCategorie extends BaseForm {

    CategorieWebService service = new CategorieWebService();
    public editFormCategorie(Category e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField(e.getName(), "Nom");
    
        this.add(nomField);
 
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(s-> {
            String nom = nomField.getText();
  
            Category categorie = new Category();
            categorie.setCategoriesId(e.getCategoriesId());
            categorie.setName(nom);
   
            System.out.println(categorie);
            service.editCategorie(categorie);
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.delCategorie(e);
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }

}
