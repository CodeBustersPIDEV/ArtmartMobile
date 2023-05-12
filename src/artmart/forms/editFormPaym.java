package artmart.forms;

import artmart.entities.PaymentOption;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import artmart.entities.Tags;
import artmart.service.PaymentShipWebService;
import java.io.IOException;


public class editFormPaym extends BaseForm {

    PaymentShipWebService service = new PaymentShipWebService();

    public editFormPaym(PaymentOption e) throws ParseException, IOException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField(e.getName(), "Nom");
        TextField countryField = new TextField(e.getAvailablecountries(), "Country");

        this.add(nomField);
        this.add(countryField);

        Button submitButton = new Button("Submit");
        submitButton.addActionListener(s -> {
            String nom = nomField.getText();
            String us = countryField.getText();

            PaymentOption es = new PaymentOption();
            es.setPaymentoptionId(e.getPaymentoptionId());
            es.setName(nom);
            es.setAvailablecountries(us);

            service.editPo(es);
            getpaymntsForm myForm = null;
            try {
                myForm = new getpaymntsForm();
            } catch (IOException ex) {
            }
            myForm.updateList();
            myForm.show();
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.deletePO(e);
            getTagsForm myForm = null;
            try {
                myForm = new getTagsForm();
            } catch (IOException ex) {
            }
            myForm.updateList();
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }

}
