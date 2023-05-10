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
import artmart.entities.Blogs;
import artmart.service.BlogsWebService;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;




public class getBlogsForm extends BaseForm {

    private MultiList cpList;
    private List<Blogs> blogs;
    private TextField searchField;
    
    public getBlogsForm() throws IOException {
             searchField = new TextField("", "Enter Blog Title");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                Blogs selectedPromo = null;
                for (Blogs blog : blogs) {
                    if (blog.getTitle() == null ? searchId == null : blog.getTitle().equals(searchId)) {
                        selectedPromo = blog;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormBlog myForm2 = new editFormBlog(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Blog not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            }    catch (ParseException ex) {
                    System.out.println(ex);
                 } catch (IOException ex) {
                 }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Sort by Name");
        sortButton.addActionListener(e -> {
            Collections.sort(blogs, new Comparator<Blogs>() {
                @Override
                public int compare(Blogs b1, Blogs b2) {
                    return b1.getTitle().compareToIgnoreCase(b2.getTitle());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));      
        cpList = new MultiList(new DefaultListModel<>());
        add(cpList);
        getAllBlogs();   
    }
    private void getAllBlogs() {
        BlogsWebService service = new BlogsWebService();
        blogs = service.getAllBlogs();
        System.out.println(blogs);
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (Blogs b : blogs) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", b.getTitle());              
            item.put("Line2", b.getCategory());  
            item.put("Line4", b.getAuthor());   
            item.put("Line3", b.getPublishDate());            
            item.put("Line5", b.getId());


            model.addItem(item);
        }
        cpList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                Map<String, Object> selectedItem = (Map<String, Object>) cpList.getSelectedItem();
                int eventId = (int) selectedItem.get("Line5");
                Blogs selectedEvent = null;
                for (Blogs event : blogs) {
                    if (event.getId() == eventId) {
                        selectedEvent = event;
                        break;
                    }
                }
                    editFormBlog myForm2 = new editFormBlog(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                }
            }
        });

   
    }

    public void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) cpList.getModel();
        model.removeAll();
        for (Blogs b : blogs) {
            Map<String, Object> item = new HashMap<>();
              item.put("Line1", b.getTitle());              
            item.put("Line2", b.getCategory());  
            item.put("Line4", b.getAuthor());   
            item.put("Line3", b.getPublishDate());

            model.addItem(item);
        }
    }
}
