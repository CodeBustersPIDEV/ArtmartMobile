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
import com.codename1.ui.Display;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

public class newBlogForm extends BaseForm {

    BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();
    String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();
    TextField imageField = null;

    public newBlogForm() throws IOException {
        this.init(Resources.getGlobalResources());

        TextField titleField = new TextField("", "Title");
        TextArea contentField = new TextArea("");
        imageField = new TextField("", "Image URL");
        ComboBox<BlogCategories> categorieField = new ComboBox<>();
        List<BlogCategories> categories = serviceCat.getAllCategorie();
        for (BlogCategories categorie : categories) {
            categorieField.addItem(categorie);
        }
        Button selectImageButton = new Button("Select Image");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                selectImage();
            }
        });

        this.add(titleField)
                .add(contentField)
                .add(categorieField)
                .add(imageField);

        Validator validator = new Validator();
        validator.addConstraint(titleField, new LengthConstraint(1, "Blog Title is required"));
        validator.addConstraint(contentField, new LengthConstraint(1, "Blog Content is required"));
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String title = titleField.getText();
            String content = contentField.getText();
            String image = imageField.getText();

            BlogCategories selectedCategorie = categorieField.getSelectedItem();

            Blogs newEvent = new Blogs();

            newEvent.setTitle(title);
            newEvent.setContent(content);
            newEvent.setAuthor(userId);
            newEvent.setCategory(selectedCategorie);
            newEvent.setImage(image);
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
        this.add(selectImageButton);
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

}
