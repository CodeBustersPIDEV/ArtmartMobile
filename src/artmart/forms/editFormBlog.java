package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import artmart.entities.BlogCategories;
import artmart.entities.Blogs;
import artmart.service.BlogsWebService;
import artmart.service.BlogCategoriesWebService;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class editFormBlog extends BaseForm {

       BlogsWebService service = new BlogsWebService();       
       BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();

    public editFormBlog(Blogs e) throws ParseException, IOException {
//        this.init(Resources.getGlobalResources());
//        System.out.println(e);
//        Label title = new Label(e.getTitle());        
//        Label category = new Label(e.getCategory());
//        Label content = new Label(e.getContent());       
//        Label date = new Label(e.getPublishDate().toString());
//
//
//        this.add(title);
//
//        this.add(category);
//
//        this.add(content);
//
//        this.add(date);
//
//        Button goToFormButton = new Button("Go back");
//        goToFormButton.addActionListener(ee -> {
//            getBlogsForm myForm = null;
//            try {
//                myForm = new getBlogsForm();
//            } catch (IOException ex) {
//            }
//            myForm.show();
//        });
//        Button deleteButton = new Button("Delete");
//        deleteButton.addActionListener(cc -> {
//            getBlogsForm myForm = null;
//            try {
//                myForm = new getBlogsForm();
//            } catch (IOException ex) {
//            }
//            service.delBlog(e);
//            myForm.updateList();
//            myForm.show();
//        });
//
//
//this.add(deleteButton);
//this.add(goToFormButton);




     this.init(Resources.getGlobalResources());
        System.out.println(e);
        TextField titleField = new TextField(e.getTitle(), "Title");
        TextArea contentField = new TextArea(e.getContent());      
//       TextField imagefield = new TextField(e.getImage(), "image");
        ComboBox<BlogCategories> categorieField = new ComboBox<>();
        List<BlogCategories> categories = serviceCat.getAllCategorie();
        
        for (BlogCategories categorie : categories) {
            categorieField.addItem(categorie);
        }

        this.add(titleField);

        this.add(contentField);

        this.add(categorieField);

        Button submitButton = new Button("Submit");
        
        
//        submitButton.addActionListener(s-> {
//            String title = titleField.getText();
//            String content = contentField.getText();
//            BlogCategories selectedCategorie = categorieField.getSelectedItem();
//
//            Blogs newEvent = new Blogs();
//            newEvent.setId(e.getId());
//            newEvent.setTitle(title);
//            newEvent.setContent(content);
//            newEvent.setIdCategorie(new BlogCategories(selectedCategorie.getCategoriesId()));
//            service.editCp(newEvent);
//          getCustomProductForm myForm = null;
//            try {
//                myForm = new getCustomProductForm();
//            } catch (IOException ex) {
//            }
//            myForm.show();
//        }
//        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
            } catch (IOException ex) {
            }
            myForm.updateList();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
            }
            service.delBlog(e);
            myForm.show();
        });

Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
buttonContainer.add(goToFormButton);
buttonContainer.add(deleteButton);
buttonContainer.add(submitButton);
this.add(buttonContainer);


    }

    }


