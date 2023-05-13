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
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 *
 * @author 21697
 */
public class editUserForm extends BaseForm {

    String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String roleC = SessionManager.getInstance().getRole();

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
        Validator validator = new Validator();
        validator.addConstraint(nomField, new LengthConstraint(1, "Nom is required"));

        validator.addConstraint(usernameField, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(emailfield, new LengthConstraint(1, "Nom is required"));

        validator.addConstraint(PNfield, new LengthConstraint(1, "Nom is required"));

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
            if (validator.isValid()) {
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
        Button Block = new Button("Block");
        Button Unblock = new Button("Unblock");

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
        if (roleC.equals("admin")) {
            if (!e.getBlocked()) {
                this.add(Block);
            }
            if (e.getBlocked()) {
                this.add(Unblock);
            }
        }
        Block.addActionListener(cc -> {
            service.block(e.getUser_id());

            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
         Unblock.addActionListener(cc -> {
            service.unblock(e.getUser_id());

            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
    }

}
