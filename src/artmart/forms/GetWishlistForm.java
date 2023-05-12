package artmart.forms;

import artmart.entities.Wishlist;
import artmart.service.WishlistWebService;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetWishlistForm extends BaseForm {
    int totalMoney=0;
    private MultiList uList;
    private List<Wishlist> listWish;
    private TextField searchField;

    public GetWishlistForm() throws IOException {
        this.init(Resources.getGlobalResources());
        uList = new MultiList(new DefaultListModel<>());
        getAllUs();
        add(uList);
    }

    private void getAllUs() {
        WishlistWebService service = new WishlistWebService();
        listWish = service.getAllList();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) uList.getModel();
        model.removeAll();
        for (Wishlist u : listWish) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Price: " + u.getPrice() + " * " + u.getQuantity());
            item.put("Line2", "Date : " + u.getDate());
            totalMoney += u.getPrice() * u.getQuantity();
            model.addItem(item);
        }
        add(new Label("Your Total : " + totalMoney));
        Button b = new Button("Order Now");
        b.addActionListener(e -> {
            try {
                newOrderForm f;
                f = new newOrderForm(totalMoney,listWish.get(0).getProductid());
                f.show();
            } catch (IOException ex) {
                System.out.println("ex");  
            }
        });
        add(b);
    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) uList.getModel();
        model.removeAll();
        for (Wishlist u : listWish) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Price: " + u.getPrice());
            item.put("Line2", "Date : " + u.getDate());
            model.addItem(item);

        }
    }

}
