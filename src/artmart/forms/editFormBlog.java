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
import artmart.entities.Blogs;
import artmart.service.BlogsWebService;
import artmart.service.CategorieWebService;
import artmart.service.CustomproductWebService;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class editFormBlog extends BaseForm {

    BlogsWebService service = new BlogsWebService();
    BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();

    public editFormBlog(Blogs e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        Label titleField = new Label(e.getTitle(), "Title");
        TextArea contentField = new TextArea(e.getContent());
//       TextField imagefield = new TextField(e.getImage(), "image");
        Label catField = new Label(e.getCategory().getName());
//        ComboBox<BlogCategories> categorieField = new ComboBox<>();
//        List<BlogCategories> categories = serviceCat.getAllCategorie();

        this.add(title);

            System.out.println(filename);
            imgUrl = URLImage.createToStorage(placeholder, filename, e.getImage());
            imageViewer = new ImageViewer(imgUrl);
        }
        this.add(titleField);

        this.add(date);

        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
            } catch (IOException ex) {
            }
            service.delBlog(e);
            myForm.updateList();
            myForm.show();
        });

        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
        buttonContainer.add(deleteButton);
//        buttonContainer.add(submitButton);
        this.add(buttonContainer);

    }

}
