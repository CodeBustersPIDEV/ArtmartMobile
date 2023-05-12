/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artmart.entities.ReadyProduct;
import artmart.entities.ProductReview;
import artmart.service.ReadyProductWebService;
import artmart.service.ProductReviewWebService;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Collections;
import java.util.Comparator;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;
import com.codename1.ui.Font;
import com.codename1.ui.plaf.Border;
import java.io.IOException;
import java.io.OutputStream;

public class getProductReviewsForm extends BaseForm {

    private MultiList prList;
    private List<ProductReview> productreview;
    private TextField searchField;

    ProductReview pr = new ProductReview();
    ProductReviewWebService serviceReviews = new ProductReviewWebService();
    ReadyProductWebService serviceReady = new ReadyProductWebService();
 String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();
    public getProductReviewsForm(int id) throws IOException {
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            editReadyProductForm myForm = null;
            try {
                
                ReadyProduct selectedProduct = serviceReady.getReadyProductById(id);
                
                myForm = new editReadyProductForm(selectedProduct);
            } catch (IOException ex) {
            } catch (ParseException ex) {

            }
            
            myForm.show();
        });
        
        Label headingLabel = new Label("Product Reviews");
        headingLabel.getUnselectedStyle().setFgColor(0xe35d59);
        headingLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
    

        this.init(Resources.getGlobalResources());
        float averageRatings = serviceReviews.getAverageRatings(id);

        Label averageRatingsLabel = new Label("Average Ratings: " + averageRatings + " /10");
        averageRatingsLabel.getUnselectedStyle().setFgColor(0x000000);
        averageRatingsLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL));
        averageRatingsLabel.getUnselectedStyle().setBgColor(0xFFFFFF);

        Button addButton = new Button("Add");
        addButton.addActionListener(ee -> {
            newProductReviewForm f = null;
            try {
                f = new newProductReviewForm(id);
            } catch (IOException ex) {
            }
            f.show();
        });
        
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.add(goToFormButton);
               
        this.add(buttonContainer);
                
        Container headContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        headContainer.add(headingLabel);
        headContainer.add(addButton);
         if(role.equals("client")){
        this.add(headContainer);}

        Container ratingContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ratingContainer.add(averageRatingsLabel);
        this.add(ratingContainer);

        prList = new MultiList(new DefaultListModel<>());
        add(prList);
        getAllProductReviews(id);

    }

    private void getAllProductReviews(int id) {
        ProductReviewWebService service = new ProductReviewWebService();
        productreview = service.getAllProductReviews(id);
        System.out.println(productreview);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) prList.getModel();
        model.removeAll();
        for (ProductReview rp : productreview) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", rp.getTitle());
            item.put("Line2", rp.getText());
            item.put("Line3", rp.getRating());
            model.addItem(item);
        }
    }
}
