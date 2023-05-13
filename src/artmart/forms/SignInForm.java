package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

public class SignInForm extends BaseForm {

    User user = new User(); // Variable to store the user ID

    public SignInForm(Resources res) throws IOException {
        this.init(Resources.getGlobalResources());
        UserWebService userservice = new UserWebService();
        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");

        //mp oublié
        Button mp = new Button("Forget Password?", "CenterLabel");
       
        signUp.addActionListener(e -> {
            SignUpForm myForm = null;
            try {
                myForm = new SignUpForm();
            } catch (IOException ex) {

            }
            myForm.show();
        });
        Label doneHaveAnAccount = new Label("No account?");
        Container contactB = new Container(new FlowLayout(CENTER, CENTER));
        Container contactC=new Container(BoxLayout.y());
        contactB.add(signIn);
        contactB.add(signUp);
        contactC.add(mp);
        contactC.add(contactB);
        Container contact = new Container(new FlowLayout(CENTER, CENTER));

        contact.add(scaledImg);
        contact.add(username);
        contact.add(password);
        contact.add(contactC);

        this.add(contact);

        signIn.addActionListener(e -> {
            userservice.signin(username, password, res);
            Storage.getInstance().writeObject("user_id", 1);

        });
    }

}
