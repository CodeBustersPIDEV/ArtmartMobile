package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import artmart.entities.BlogCategories;
import artmart.entities.Blogs;
import artmart.service.BlogCategoriesWebService;
import artmart.service.BlogsWebService;
import artmart.service.CustomproductWebService;
import com.codename1.ui.TextArea;
import java.io.IOException;

public class newBlogForm extends BaseForm {

    BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();
      String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();

    public newBlogForm() throws IOException {
        this.init(Resources.getGlobalResources());

        TextField titleField = new TextField("", "Title");
        TextArea contentField = new TextArea("");
        ComboBox<BlogCategories> categorieField = new ComboBox<>();
        List<BlogCategories> categories = serviceCat.getAllCategorie();
        for (BlogCategories categorie : categories) {
            categorieField.addItem(categorie);
        }

        this.add(titleField)
                .add(contentField)
                .add(categorieField);
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String title = titleField.getText();
            String content = contentField.getText();
            BlogCategories selectedCategorie = categorieField.getSelectedItem();

            Blogs newEvent = new Blogs();

            newEvent.setTitle(title);
            newEvent.setContent(content);
            newEvent.setAuthor(userId);
            newEvent.setCategory(selectedCategorie);
            BlogsWebService service = new BlogsWebService();
//            System.out.println(newEvent.getCategory());
            service.newBlog(newEvent);
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
            } catch (IOException ex) {

            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
            } catch (IOException ex) {

            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
