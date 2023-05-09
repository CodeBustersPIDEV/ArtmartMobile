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
import java.util.HashMap;
import java.util.Map;

public class editFormBlog extends BaseForm {

       BlogsWebService service = new BlogsWebService();
    public editFormBlog(Blogs e) throws ParseException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        Label title = new Label(e.getTitle());        
        Label category = new Label(e.getCategory());
        Label content = new Label(e.getContent());       
        Label date = new Label(e.getPublishDate().toString());


        this.add(title);

        this.add(category);

        this.add(content);

        this.add(date);

        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getBlogsForm myForm = new getBlogsForm();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getBlogsForm myForm = new getBlogsForm();
            service.delBlog(e);
            myForm.updateList();
            myForm.show();
        });


this.add(deleteButton);
this.add(goToFormButton);

    }

}
