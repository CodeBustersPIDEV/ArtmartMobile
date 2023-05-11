package artmart.forms;

import artmart.service.UserWebService;
import com.codename1.ui.Button;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import java.io.IOException;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class SignInForm extends BaseForm {

    public SignInForm(Resources res) throws IOException {

        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");

        //mp oublié
        Button mp = new Button("oublier mot de passe?", "CenterLabel");

        signUp.addActionListener(e -> {
            SignUpForm myForm = null;
            try {
                myForm = new SignUpForm();
            } catch (IOException ex) {

            }
            myForm.show();
        });
        Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");
        Container contactB = new Container(BoxLayout.x());
        contactB.add(signIn);
        contactB.add(signUp);

        Container contact = new Container(BoxLayout.y());
        this.add(scaledImg);
        this.add(username);
        this.add(password);
        this.add(contactB);

        signIn.addActionListener(e
                -> {
            UserWebService.getInstance().signin(username, password, res);

        });

    }
}
