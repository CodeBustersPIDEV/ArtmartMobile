/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import artmart.entities.Category;
import artmart.entities.ProductReview;
import artmart.entities.ReadyProduct;
import artmart.service.CategorieWebService;
import artmart.service.ReadyProductWebService;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

public class newProductReviewForm extends BaseForm {

    private int readyProductId;

    public newProductReviewForm(int id) throws IOException {
        Label headingLabel = new Label("Add Review");
        headingLabel.getUnselectedStyle().setFgColor(0xe35d59);
        headingLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
        addComponent(headingLabel);
        
        this.init(Resources.getGlobalResources());
        this.readyProductId = id;

        TextField titleField = new TextField("", "Title");
        TextField textfField = new TextField("", "Text");
        TextField ratingfield = new TextField("", "Rating");
        TextField userfield = new TextField("", "user");

        this.add(titleField)
                .add(textfField)
                .add(ratingfield)
                .add(userfield);

        Validator validator = new Validator();
        validator.addConstraint(titleField, new LengthConstraint(1, "Title is required"));
        validator.addConstraint(textfField, new LengthConstraint(1, "Text is required"));
        validator.addConstraint(ratingfield, new LengthConstraint(1, "Rating is required"));
        validator.addConstraint(userfield, new LengthConstraint(1, "User is required"));
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            if (validator.isValid()) {
                String title = titleField.getText();
                String text = textfField.getText();
                Float rating = Float.parseFloat(ratingfield.getText());
                int user = Integer.parseInt(userfield.getText());

                ProductReview newEvent = new ProductReview();
                newEvent.setTitle(title);
                newEvent.setText(text);
                newEvent.setRating(rating);
                newEvent.setUser(user);
                newEvent.setReadyProductId(readyProductId);
                getProductReviewsForm myForm = null;
                try {
                    myForm = new getProductReviewsForm(readyProductId);
                } catch (IOException ex) {

                }
                myForm.show();
            }
        }
        );

        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");

        goToFormButton.addActionListener(e
                -> {
            getProductReviewsForm myForm = null;
            try {
                myForm = new getProductReviewsForm(readyProductId);
            } catch (IOException ex) {

            }
            myForm.show();
        }
        );

        this.add(goToFormButton);
    }
}
