/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author 21697
 */
public class editUserForm extends BaseForm {

    public editUserForm(User e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        System.out.println(e);
        TextField nomField = new TextField(e.getName(), "name");
        TextField usernameField = new TextField(e.getUsername(), "username");
        TextField emailfield = new TextField(e.getEmail(), "email");
        TextField pwdfield = new TextField("", "password");
        TextField PNfield = new TextField(e.getPhone_nbr() + "", "phoneNumber");
       // Create an EncodedImage to hold the image data
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = e.getPicture().substring(e.getPicture().lastIndexOf("/") + 1);

        System.out.println(filename);
// Create a URLImage with the image URL and placeholder
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, e.getPicture());

// Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(imgUrl);
        UserWebService service = new UserWebService();

        Label role = new Label(e.getRole());
        this.add(imageViewer);

        this.add(nomField);

        this.add(usernameField);

        this.add(emailfield);

        this.add(pwdfield);

        this.add(PNfield);

        this.add(role);

        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s -> {
            String nom = nomField.getText();
            String username = usernameField.getText();
            String email = emailfield.getText();
            String pwd;

            int phoneNbr = Integer.parseInt(PNfield.getText());
            if (pwdfield == null) {
                pwd = e.getPwd();
            } else {
                pwd = pwdfield.getText();
            }
            User newEvent = new User();
            newEvent.setUser_id(e.getUser_id());
            newEvent.setName(nom);
            newEvent.setUsername(username);
            newEvent.setPwd(pwd);
            newEvent.setPhone_nbr(phoneNbr);
            newEvent.setEmail(email);
            service.editU(newEvent);
            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(b -> {
            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.delU(e);

            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });

        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
        buttonContainer.add(deleteButton);
        buttonContainer.add(submitButton);
        this.add(buttonContainer);

    }

}