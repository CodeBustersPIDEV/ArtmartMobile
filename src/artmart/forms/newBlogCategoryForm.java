package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.BlogCategories;
import artmart.service.BlogCategoriesWebService;
import java.io.IOException;


public class newBlogCategoryForm extends BaseForm {

    public newBlogCategoryForm() throws IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField("", "Nom");
 
        this.add(nomField);


        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String nom = nomField.getText();

            BlogCategories newCat = new BlogCategories();
            newCat.setName(nom);
      
            BlogCategoriesWebService service = new BlogCategoriesWebService();
            service.newCategorie(newCat);
            getBlogCategoryForm myForm = null;
            try {
                myForm = new getBlogCategoryForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getBlogCategoryForm myForm = null;
            try {
                myForm = new getBlogCategoryForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
