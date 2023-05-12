package artmart.forms;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Order;
import artmart.entities.PaymentOption;
import artmart.entities.Shippingoption;
import artmart.entities.User;
import artmart.service.OrderWebService;
import artmart.service.PaymentShipWebService;
import com.codename1.payment.Product;
import com.codename1.ui.ComboBox;
import java.io.IOException;
import java.util.List;

public class newOrderForm extends BaseForm {
    PaymentShipWebService service = new PaymentShipWebService();
    public newOrderForm(int TotalCost,int pd) throws IOException {
        this.init(Resources.getGlobalResources());
        TextField shippingaddressField = new TextField("", "shippingaddress");
        
        ComboBox<Shippingoption> shippingmethodField = new ComboBox<>();
        List<Shippingoption> sos = service.getAllListSo();
        
       for (Shippingoption so : sos) {
            shippingmethodField.addItem(so);
       }
        
        ComboBox<PaymentOption> paymentField = new ComboBox<>();
        List<PaymentOption> payments = service.getAllList();
        
        for (PaymentOption payment : payments) {
            paymentField.addItem(payment);
        }
        this.add(shippingaddressField);
        this.add(shippingmethodField);
        this.add(paymentField);
        
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String So = shippingaddressField.getText();
            int S = shippingmethodField.getSelectedItem().getShippingoptionId();
            int PO = paymentField.getSelectedItem().getPaymentoptionId();

            Order order = new Order();
            order.setQuantity(1);
            order.setShippingaddress(So);
            order.setTotalcost(TotalCost);
            order.setUserid(1);
            order.setProductid(pd);
            order.setShippingmethod(S);
            order.setPaymentmethod(PO);
      
            OrderWebService service = new OrderWebService();
            service.newOrder(order);
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getCategorieForm myForm = null;
            try {
                myForm = new getCategorieForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
