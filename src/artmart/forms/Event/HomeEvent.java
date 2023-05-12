/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms.Event;

import artmart.forms.Event.Admin.AllEventsAdminForm;
import artmart.forms.Event.Artist.AllEventsForm;
import artmart.forms.Event.Client.AllEventsClientForm;
import artmart.forms.BaseForm;
import com.codename1.ui.Button;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author ghzay
 */
public class HomeEvent extends BaseForm {
        public HomeEvent() throws IOException {
            
        this.init(Resources.getGlobalResources());
            
        Button goToAdminBtn = new Button("Admin");
        Button goToArtistBtn = new Button("Artist");
        Button goToClientBtn = new Button("Client");
        
        goToArtistBtn.addActionListener(ee -> {
            AllEventsForm f = null;
            try {
                f = new AllEventsForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        
        goToClientBtn.addActionListener(ee -> {
            AllEventsClientForm f = null;
            try {
                f = new AllEventsClientForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        
        goToAdminBtn.addActionListener(ee -> {
            AllEventsAdminForm f = null;
            try {
                f = new AllEventsAdminForm();
            } catch (IOException ex) {
            }
            f.show();
        });
        
        this.add(goToArtistBtn);
        this.add(goToClientBtn);
        this.add(goToAdminBtn);
    }

}
