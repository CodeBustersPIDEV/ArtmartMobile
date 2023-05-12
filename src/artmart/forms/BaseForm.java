package artmart.forms;

import artmart.forms.Event.Admin.AllEventsAdminForm;
import artmart.forms.Event.Artist.AllEventsForm;
import artmart.forms.Event.Client.AllEventsClientForm;
import com.codename1.io.Storage;
import artmart.forms.Event.HomeEvent;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import artmart.service.UserWebService;
import com.codename1.l10n.ParseException;

public class BaseForm extends com.codename1.ui.Form {

    String session = SessionManager.getInstance().getSession();
    int userId = SessionManager.getInstance().getUserId();
    String role = SessionManager.getInstance().getRole();

    public void init(Resources theme) throws IOException {
        Toolbar tb = getToolbar();

        tb.getAllStyles().setBgColor(0xffffff);

        EncodedImage originalImg = EncodedImage.createFromImage(Image.createImage("/img.png"), false);
        EncodedImage scaledImg = (EncodedImage) originalImg.scaled(800, 500);
        Label taglineLabel = new Label(scaledImg);
        taglineLabel.setAlignment(CENTER);
        taglineLabel.setVerticalAlignment(CENTER);
        Container taglineContainer = BorderLayout.south(taglineLabel);

        taglineContainer.setUIID("SideCommand");
        if (userId != 0) {

            tb.addComponentToSideMenu(taglineContainer);
            tb.addMaterialCommandToSideMenu("ArtMart", FontImage.MATERIAL_HOME, e -> {

            });

            tb.addMaterialCommandToSideMenu(" My Cart", FontImage.MATERIAL_EVENT, e -> {
                GetWishlistForm f = null;
                try {
                    f = new GetWishlistForm();
                } catch (IOException ex) {
                }
                f.show();
            });
            tb.addMaterialCommandToSideMenu("Ready Products", FontImage.MATERIAL_HOME, e -> {
                getReadyProductForm f = null;
                try {
                    f = new getReadyProductForm();
                } catch (IOException ex) {
                }
                f.show();
            });

            tb.addMaterialCommandToSideMenu("Custom Products", FontImage.MATERIAL_INVENTORY, e -> {
                getCustomProductForm f = null;
                try {
                    f = new getCustomProductForm();
                } catch (IOException ex) {
                }
                f.show();
            });

            tb.addMaterialCommandToSideMenu("Categories", FontImage.MATERIAL_CATEGORY, e -> {
                getCategorieForm f = null;
                try {
                    f = new getCategorieForm();
                } catch (IOException ex) {
                }
                f.show();
            });
            tb.addMaterialCommandToSideMenu("Applies", FontImage.MATERIAL_DONE, e -> {
                getApplyForm f = null;
                try {
                    f = new getApplyForm();
                } catch (IOException ex) {
                }
                f.show();
            });
            tb.addMaterialCommandToSideMenu("Blogs", FontImage.MATERIAL_LIST, e -> {
                getBlogsForm f = null;
                try {
                    f = new getBlogsForm();
                } catch (IOException ex) {
                }
                f.show();
            });

            tb.addMaterialCommandToSideMenu(" Events", FontImage.MATERIAL_EVENT, e -> {
                if (role.equals("admin")) {
                    AllEventsAdminForm f = null;
                    try {
                        f = new AllEventsAdminForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                }
                if (role.equals("artist")) {
                    AllEventsForm f = null;
                    try {
                        f = new AllEventsForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                }
                if (role.equals("client")) {
                    AllEventsClientForm f = null;
                    try {
                        f = new AllEventsClientForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                }
            });

            if (role.equals("admin")) {
                tb.addMaterialCommandToSideMenu("Users list", FontImage.MATERIAL_LIST, e -> {
                    GetUserForm f = null;
                    try {
                        f = new GetUserForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                });
                tb.addMaterialCommandToSideMenu("Tags", FontImage.MATERIAL_CATCHING_POKEMON, e -> {
                    getTagsForm f = null;
                    try {
                        f = new getTagsForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                });
                tb.addMaterialCommandToSideMenu("Blogs Categories", FontImage.MATERIAL_LIST, e -> {
                    getBlogCategoryForm f = null;
                    try {
                        f = new getBlogCategoryForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                });

                tb.addMaterialCommandToSideMenu(" Pay Options", FontImage.MATERIAL_EVENT, e -> {
                    getpaymntsForm f = null;
                    try {
                        f = new getpaymntsForm();
                    } catch (IOException ex) {
                    }
                    f.show();
                });
            }
            if (role.equals("artist") || role.equals("client")) {
                UserWebService userv = new UserWebService();
                tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_LIST, e -> {
                    editUserForm f = null;
                    try {
                        try {
                            f = new editUserForm(userv.getUserInfo(userId));
                        } catch (ParseException ex) {
                        }
                    } catch (IOException ex) {
                    }
                    f.show();
                });
            }
            tb.addMaterialCommandToSideMenu("LogOut", FontImage.MATERIAL_LIST, e -> {
                Storage.getInstance().writeObject("user_authenticated", false);
                SessionManager.getInstance().clearSession();
                SignInForm f = null;
                try {
                    f = new SignInForm(theme);
                } catch (IOException ex) {
                }
                f.show();
            });

        } else {
            tb.addComponentToSideMenu(taglineContainer);
            tb.addMaterialCommandToSideMenu("ArtMart", FontImage.MATERIAL_HOME, e -> {

            });
            tb.addMaterialCommandToSideMenu("Ready Products", FontImage.MATERIAL_INVENTORY, e -> {
                getReadyProductForm f = null;
                try {
                    f = new getReadyProductForm();
                } catch (IOException ex) {
                }
                f.show();
            });

            tb.addMaterialCommandToSideMenu("SignIn", FontImage.MATERIAL_LIST, e -> {
                SignInForm f = null;
                try {
                    f = new SignInForm(theme);
                } catch (IOException ex) {
                }
                f.show();
            });
            tb.addMaterialCommandToSideMenu("Blogs", FontImage.MATERIAL_LIST, e -> {
                getBlogsForm f = null;
                try {
                    f = new getBlogsForm();
                } catch (IOException ex) {
                }
                f.show();
            });
        }
    }
}
