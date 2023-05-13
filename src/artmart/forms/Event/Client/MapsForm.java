
package artmart.forms.Event.Client;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.List;

public class MapsForm {

    Form f = new Form();
    MapContainer cnt = null;

    public MapsForm() {

        try {
            cnt = new MapContainer("AIzaSyA8pHkAf4Pb-CoeQ7zF3njyOBjMvAKOB04");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            AllEventsClientForm myForm = null;
            try {
                myForm = new AllEventsClientForm();
            } catch (IOException ex) {
            }
            myForm.show();
        });

//        Button btnMoveCamera = new Button("Mon Pays");
//        btnMoveCamera.addActionListener(e -> {
//            cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
//        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        cnt.addTapListener(e -> {

            cnt.clearMapLayers();
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCoordAtPosition(e.getX(), e.getY()),
                    "" + cnt.getCameraPosition().toString(),
                    "",
                    e3 -> {
                        ToastBar.showMessage("You clicked " + cnt.getName(), FontImage.MATERIAL_PLACE);
                    }
            );
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://maps.google.com/maps/api/geocode/json?latlng=" + cnt.getCameraPosition().getLatitude() + "," + cnt.getCameraPosition().getLongitude() + "&oe=utf8&sensor=false");
            NetworkManager.getInstance().addToQueueAndWait(r);

            JSONParser jsonp = new JSONParser();
            try {
                java.util.Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(r.getResponseData()).toCharArray()));
                System.out.println("roooooot:" + tasks.get("results"));
                List<java.util.Map<String, Object>> list1 = (List<java.util.Map<String, Object>>) tasks.get("results");

            } catch (IOException ex) {
            }
            System.out.println("wa3333333333333333s" + cnt.getCameraPosition().getLongitude());
        });
        cnt.addTapListener(e -> {
            Coord coord = cnt.getCoordAtPosition(e.getX(), e.getY());
            double lat = coord.getLatitude();
            double lng = coord.getLongitude();
            cnt.setCameraPosition(coord);
            System.out.println("Latitude: " + lat + ", Longitude: " + lng);
        });
        Container root = new Container();
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, cnt);
//        f.addComponent(BorderLayout.SOUTH, btnMoveCamera);
        f.addComponent(BorderLayout.SOUTH, goToFormButton);
        f.show();
//        f.getToolbar().addCommandToRightBar("back", null, (ev)->{ new AllEventsClientForm().show();});

    }

}
