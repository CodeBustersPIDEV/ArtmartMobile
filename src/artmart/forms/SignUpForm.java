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
        PickerComponent datePicker= PickerComponent.createDate(new Date());
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
            String nom = nomField.getText();
            String username = usernameField.getText();
            String email = emailfield.getText();
            String pwd = pwdfield.getText();
            int phoneNbr = Integer.parseInt(phoneNbrfield.getText());
           String birthdayfield =datePicker.getPicker().getDate().toString();
         
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
                 GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        

           
        }
        );
        
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            GetUserForm myForm = null;
            try {
                myForm = new GetUserForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
