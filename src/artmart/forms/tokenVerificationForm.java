/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author 21697
 */
public class tokenVerificationForm extends BaseForm {

    public tokenVerificationForm(int id) throws IOException {
        this.init(Resources.getGlobalResources());

        UserWebService userservice = new UserWebService();
        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);
        System.out.println(id);
        TextField token = new TextField("", "token", 20, TextField.ANY);
        Button verif = new Button("Verify Token");

        Container contact = new Container(new FlowLayout(CENTER, CENTER));
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            SignInForm myForm = null;
            try {
                myForm = new SignInForm(Resources.getGlobalResources());
            } catch (IOException ex) {
            }
            myForm.show();
        });
        UserWebService userWebService = new UserWebService();
        contact.add(scaledImg);
        contact.add(token);
        contact.add(verif);
        contact.add(goToFormButton);

        this.add(contact);
        verif.addActionListener(e -> {
            userWebService.verifT(token, id);
 SignInForm myForm = null;
            try {
                myForm = new SignInForm(Resources.getGlobalResources());
            } catch (IOException ex) {
            }
            myForm.show();
        });

    }
}
