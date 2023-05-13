/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author 21697
 */
public class SignUpForm extends BaseForm {

    public SignUpForm() throws IOException {

        this.init(Resources.getGlobalResources());
        PickerComponent datePicker = PickerComponent.createDate(new Date());
        TextField nomField = new TextField("", "Name");
        TextField usernameField = new TextField("", "Username");
        TextField emailfield = new TextField("", "Email");
        TextField pwdfield = new TextField("" + "", "Password");
        TextField phoneNbrfield = new TextField("", "Phonenumeber");
        ComboBox<String> roleField = new ComboBox<>();
        List<String> roles = new ArrayList<>(Arrays.asList("admin", "artist", "client"));
        for (String role : roles) {
            roleField.addItem(role);
        }
        Validator validator = new Validator();
        validator.addConstraint(nomField, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(usernameField, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(pwdfield, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(phoneNbrfield, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(emailfield, new LengthConstraint(1, "Nom is required"));
        validator.addConstraint(roleField, new LengthConstraint(1, "Nom is required"));
        this
                .add(nomField)
                .add(usernameField)
                .add(pwdfield)
                .add(phoneNbrfield)
                .add(datePicker)
                .add(emailfield)
                .add(roleField);
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            if (validator.isValid()) {
                String nom = nomField.getText();
                String username = usernameField.getText();
                String email = emailfield.getText();
                String pwd = pwdfield.getText();
                int phoneNbr = Integer.parseInt(phoneNbrfield.getText());
                String birthdayfield = datePicker.getPicker().getDate().toString();

                String selectedRole = roleField.getSelectedItem();

                User newEvent = new User();
                newEvent.setBirthday(birthdayfield);
                newEvent.setName(nom);
                newEvent.setUsername(username);
                newEvent.setPwd(pwd);
                newEvent.setPhone_nbr(phoneNbr);
                newEvent.setEmail(email);
                newEvent.setRole(selectedRole);
                UserWebService service = new UserWebService();
                service.newU(newEvent);
            }
        }
        );

        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            SignInForm myForm = null;
            try {
                myForm = new SignInForm(Resources.getGlobalResources());
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
