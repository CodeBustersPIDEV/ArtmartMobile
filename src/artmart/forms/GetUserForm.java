/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.User;
import artmart.service.UserWebService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21697
 */
public class GetUserForm extends BaseForm {

    private MultiList uList;
    private List<User> user;
    private TextField searchField;

    public GetUserForm() throws IOException {
        this.init(Resources.getGlobalResources());
      
        searchField = new TextField("", "Enter Username");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                User selectedPromo = null;
                for (User u : user) {
                    if (u.getUsername() == null ? searchId == null : u.getUsername().equals(searchId)) {
                        selectedPromo = u;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editUserForm myForm2 = new editUserForm(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "User not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        Button sortButton = new Button("Sort by Username");
        sortButton.addActionListener(e -> {
            Collections.sort(user, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return u1.getUsername().compareToIgnoreCase(u2.getUsername());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));

        uList = new MultiList(new DefaultListModel<>());
        add(uList);
        getAllUs();
    }

    private void getAllUs() {
        UserWebService service = new UserWebService();
        user = service.getAllU();
        System.out.println(user);
       
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) uList.getModel();
        model.removeAll();
        for (User u : user) {
            Map<String, Object> item = new HashMap<>();
      
             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffcccccc), true);
        String filename = u.getPicture().substring(u.getPicture().lastIndexOf("/") + 1);

        System.out.println(filename);
// Create a URLImage with the image URL and placeholder
        URLImage imgUrl = URLImage.createToStorage(placeholder, filename, u.getPicture());

// Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(imgUrl);
        Label label=new Label();
        label.setIcon(imgUrl);
            item.put("Line1", label); 
      
            item.put("Line2", u.getUsername());
            item.put("Line3", u.getEmail());

            item.put("Line5", u.getUser_id());
            
            model.addItem(item);

        }

        uList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) uList.getSelectedItem();
                    int selectedUserId = (int) selectedItem.get("Line5");
                    User selectedUser = null;
                    for (User u : user) {
                        if (u.getUser_id() == selectedUserId) {
                            selectedUser = u;
                            break;
                        }
                    }
                    if (selectedUser != null) {
                        editUserForm myForm2 = new editUserForm(selectedUser);
                        myForm2.show();
                    }
                } catch (ParseException | IOException ex) {
                    System.out.println(ex);
                }
            }
        });

    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) uList.getModel();
        model.removeAll();
        for (User u : user) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", u.getUsername());
            item.put("Line2", u.getName());
            item.put("Line3", u.getEmail());
            item.put("Line4", u.getPhone_nbr());
            item.put("Line5", u.getUser_id());

            model.addItem(item);
        }
    }

}
