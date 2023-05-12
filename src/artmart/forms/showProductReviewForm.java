/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.Category;
import artmart.entities.ProductReview;
import artmart.entities.ReadyProduct;
import artmart.service.CategorieWebService;
import artmart.service.ProductReviewWebService;
import artmart.service.ReadyProductWebService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.util.List;

public class showProductReviewForm extends BaseForm {    
    
    CategorieWebService serviceCat = new CategorieWebService();
    ReadyProductWebService service = new ReadyProductWebService();
    ProductReviewWebService serviceReview = new ProductReviewWebService();
    
    public showProductReviewForm(ProductReview e) throws ParseException, IOException {
        Label headingLabel = new Label("Product Review");
        headingLabel.getUnselectedStyle().setFgColor(0xe35d59);
        headingLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));

        Container headContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        headContainer.add(headingLabel);
        this.add(headContainer);

        this.init(Resources.getGlobalResources());
        System.out.println(e);
        Label titleLabel = new Label("Title:");
        Label titleValueLabel = new Label(e.getTitle());
        Label userLabel = new Label("By:");
        Label userValueLabel = new Label(Integer.toString(e.getUser()));
        Label dateLabel = new Label("Date:");
        Label dateValueLabel = new Label(e.getDate());
        Label textLabel = new Label("Text:");
        Label textValueLabel = new Label(e.getText());
        Label ratingLabel = new Label("Rating:");
        Label ratingValueLabel = new Label(Float.toString(e.getRating()));
        
        this.add(titleValueLabel);
        this.add(userLabel);
        this.add(userValueLabel);
        this.add(dateLabel);
        this.add(dateValueLabel);
        this.add(textLabel);
        this.add(textValueLabel);
        this.add(ratingLabel);
        this.add(ratingValueLabel);

        
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getProductReviewsForm myForm = null;
            try {
                myForm = new getProductReviewsForm(e.getReadyProductId());
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
        this.add(buttonContainer);

    }

}
