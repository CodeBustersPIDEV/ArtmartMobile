package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Tags;
import artmart.service.TagWebService;
import java.io.IOException;


public class newTagsForm extends BaseForm {

    public newTagsForm() throws IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField("", "Nom");
 
        this.add(nomField);


        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String nom = nomField.getText();

            Tags newCat = new Tags();
            newCat.setName(nom);
      
            TagWebService service = new TagWebService();
            service.newCategorie(newCat);
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
