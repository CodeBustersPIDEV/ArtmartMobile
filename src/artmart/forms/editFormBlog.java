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
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

public class editFormBlog extends BaseForm {

    BlogsWebService service = new BlogsWebService();
    BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();
    TextArea contentField = null;

    public editFormBlog(Blogs e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        Label titleField = new Label(e.getTitle(), "Title");
        titleField.getStyle().setFgColor(0x000000);
        titleField.setWidth(380);
        contentField = new TextArea(e.getContent());

//       TextField imagefield = new TextField(e.getImage(), "image");
        Label catField = new Label(e.getCategory().getName());
        ComboBox<Language> toComboBox = new ComboBox<>();
        toComboBox.setModel(new DefaultListModel<>(Language.values()));
//        ComboBox<BlogCategories> categorieField = new ComboBox<>();
//        List<BlogCategories> categories = serviceCat.getAllCategorie();

//        for (BlogCategories categorie : categories) {
//            categorieField.addItem(categorie);
//        }
        ImageViewer imageViewer = null;
        URLImage imgUrl = null;
        if (e.getImage().equals("N/A")) {
            EncodedImage placeholder = EncodedImage.create(
                    Display.getInstance().getResourceAsStream(getClass(), "/default-product.png")
            );
            imageViewer = new ImageViewer(placeholder);
        } else {
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(500, 500, 0xffcccccc), true);
            String filename = e.getImage().substring(e.getImage().lastIndexOf("/") + 1);

            System.out.println(filename);
            imgUrl = URLImage.createToStorage(placeholder, filename, e.getImage());
            imageViewer = new ImageViewer(imgUrl);
        }
        Button translateButton = new Button("Translate");
        translateButton.addActionListener(ee -> {
            contentField.setText(Translator.translate(Language.ENGLISH, toComboBox.getSelectedItem(), e.getContent()));
        });
        Button goToCommentsButton = new Button("Comment");
        goToCommentsButton.addActionListener(ee -> {

// Load the desired URL
            String url = "http://localhost:8000/blogs/show/" + e.getId(); // Replace with the desired website URL
            System.out.println(e.getId());
            Display.getInstance().execute(url);
        });
        this.add(titleField);

        this.add(contentField);
        this.add(catField);
        this.add(imageViewer);

//        this.add(categorieField);
//        Button submitButton = new Button("Submit");
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
                myForm.updateList();
                myForm.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getBlogsForm myForm = null;
            try {
                myForm = new getBlogsForm();
                service.delBlog(e);
                myForm.updateList();
                myForm.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

        Container buttonContainer2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

        buttonContainer2.add(toComboBox);
        buttonContainer2.add(translateButton);
        buttonContainer.add(goToFormButton);
        buttonContainer.add(deleteButton);
        buttonContainer.add(goToCommentsButton);

//        buttonContainer.add(submitButton);
        this.add(buttonContainer2);
        this.add(buttonContainer);

    }

}
