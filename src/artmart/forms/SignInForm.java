package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class SignInForm extends BaseForm {

     User user=new User(); // Variable to store the user ID
      UserWebService  userservice = new UserWebService();
    public SignInForm(Resources res) throws IOException {
        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
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
        Label doneHaveAnAccount = new Label("No account?");
        Container contactB = new Container(BoxLayout.x());
        contactB.add(signUp);
        contactB.add(signIn);

        Container contact = new Container(BoxLayout.y());

        this.add(scaledImg);
        this.add(username);
        this.add(password);
        this.add(doneHaveAnAccount);

        this.add(contactB);

        signIn.addActionListener(e -> {
             userservice.signin(username, password, res);
            Storage.getInstance().writeObject("user_id", 1);

                    });
    }

    
}
