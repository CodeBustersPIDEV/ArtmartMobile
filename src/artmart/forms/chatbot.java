package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class chatbot extends BaseForm {
  
    private TextField inputField;
    private Label responseLabel;
    private Button sendButton;

    public chatbot() throws IOException {
     this.init(Resources.getGlobalResources());
responseLabel = new Label("ARTSMART Your Creative Bot ! ");
add(responseLabel);




        // Create components
        inputField = new TextField();
        responseLabel = new Label("");
        sendButton = new Button("Send");

        // Add action listener to send button
        sendButton.addActionListener(e -> {
           String inputText = inputField.getText();
if (inputText.equalsIgnoreCase("hi") || inputText.equalsIgnoreCase("hello") || inputText.equalsIgnoreCase("hey")) {
    responseLabel.setText("ArtSmart: Hello! How can I help you");
} else if (inputText.contains("custom product")) {
    responseLabel.setText("ArtSmart: Yes, we specialize in custom products");
} else if (inputText.contains("weight")) {
    responseLabel.setText("ArtSmart: You can add the weight you want");
} else if (inputText.contains("gaston")) {
    responseLabel.setText("ArtSmart: Gaston is known as G.G.G *.");
} else if (inputText.contains("meher")) {
    responseLabel.setText("ArtSmart: Meher is the 'pipon' of CodeBusters.");
} else if (inputText.contains("marwan")) {
    responseLabel.setText("ArtSmart: Marwan is the King.");
} else if (inputText.contains("are you")) {
    responseLabel.setText("ArtSmart: I am ArtSmart Your Personal Product Assistant");
} else if (inputText.contains("material")) {
    responseLabel.setText("ArtSmart: Materials depend on your product");
} else if (inputText.contains("funny")) {
    responseLabel.setText("ArtSmart: Fatouma is fufunny");
} else if (inputText.contains("price")) {
    responseLabel.setText("ArtSmart: the price will be discussed via WhatsApp!");
} else if (inputText.contains("king")) {
    responseLabel.setText("ArtSmart: The king is Marwan.");
} else if (inputText.contains("ibtihel")) {
    responseLabel.setText("ArtSmart: Ibtihel jendouuuuuuuuuubaaaa.");
} else if (inputText.contains("samar")) {
    responseLabel.setText("ArtSmart: Samar is the god and the user manager.");
} else if (inputText.contains("thanks") || inputText.contains("thank you")) {
    responseLabel.setText("ArtSmart: You are welcome!");
} else if (inputText.contains("admin")) {
    responseLabel.setText("ArtSmart: You can contact amir soltani");
} else if (inputText.contains("artmart")) {
    responseLabel.setText("ArtSmart: ArtMart is an online marketplace for artists and art lovers.");
} else {
    responseLabel.setText("ArtSmart: Welcome to ArtMart! You are in custom products. Let me know what you need!");
}

        });

        // Add components to form
        Container inputContainer = new Container();
        inputContainer.add(new Label("You: "));
        inputContainer.add(inputField);

        Container responseContainer = new Container();
        responseContainer.add(new Label("ArtSmart: "));
        responseContainer.add(responseLabel);

        addComponent(inputContainer);
        addComponent(responseContainer);
        addComponent(sendButton);
            Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getCustomProductForm myForm = null;
            try {
                myForm = new getCustomProductForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        add(goToFormButton);
    }
}
