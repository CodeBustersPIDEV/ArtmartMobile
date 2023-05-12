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
import java.io.IOException;

public class newProductReviewForm extends BaseForm {

    private int readyProductId;

    public newProductReviewForm(int id) throws IOException {
        this.init(Resources.getGlobalResources());
        this.readyProductId = id;

        TextField titleField = new TextField("", "Title");
        TextField textfField = new TextField("", "Text");
        TextField ratingfield = new TextField("", "Rating");
        TextField datefield = new TextField("" + "", "Date");
        TextField userfield = new TextField("", "user");

        this.add(titleField)
                .add(textfField)
                .add(ratingfield)
                .add(datefield)
                .add(userfield);
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String title = titleField.getText();
            String text = textfField.getText();
            Float rating = Float.parseFloat(ratingfield.getText());
            String date = datefield.getText();
            int user = Integer.parseInt(userfield.getText());

            ProductReview newEvent = new ProductReview();
            newEvent.setTitle(title);
            newEvent.setText(text);
            newEvent.setRating(rating);
            newEvent.setDate(date);
            newEvent.setUser(user);
            newEvent.setReadyProductId(readyProductId);
            getProductReviewsForm myForm = null;
            try {
                myForm = new getProductReviewsForm(readyProductId);
            } catch (IOException ex) {

            }
            myForm.show();
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
