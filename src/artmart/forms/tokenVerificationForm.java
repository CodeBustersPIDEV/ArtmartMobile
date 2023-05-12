/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.service.UserWebService;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author 21697
 */
public class tokenVerificationForm extends BaseForm{
    public tokenVerificationForm(Resources res) throws IOException {
        this.init(Resources.getGlobalResources());
    
        UserWebService userservice = new UserWebService();
        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);

        TextField token = new TextField("", "token", 20, TextField.ANY);
                Button verif = new Button("Verify Token");
                

        }
}
