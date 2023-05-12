package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Tags;
import artmart.service.TagWebService;
import java.io.IOException;


public class editFormTags extends BaseForm {

    TagWebService service = new TagWebService();

    public editFormTags(Tags e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField(e.getName(), "Nom");

        this.add(nomField);

        Button submitButton = new Button("Submit");
        submitButton.addActionListener(s -> {
            String nom = nomField.getText();

            Tags categorie = new Tags();
            categorie.setId(e.getId());
            categorie.setName(nom);

            System.out.println(categorie);
            service.editCategorie(categorie);
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.updateList();
            myForm.show();
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.delCategorie(e);
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.updateList();
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }

}
