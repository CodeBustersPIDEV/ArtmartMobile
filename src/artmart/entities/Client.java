/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;

/**
 *
 * @author 21697
 */
public class Client extends User{
    private int client_id, nbr_orders, nbr_cus_demands;

    public Client() {
    }

    public Client(int client_id, int nbr_orders, int nbr_cus_demands) {
        this.client_id = client_id;
        this.nbr_orders = nbr_orders;
        this.nbr_cus_demands = nbr_cus_demands;
    }



    public Client(int nbr_orders, int nbr_cus_demands) {
        this.nbr_orders = nbr_orders;
        this.nbr_cus_demands = nbr_cus_demands;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getNbr_orders() {
        return nbr_orders;
    }

    public int getNbr_cus_demands() {
        return nbr_cus_demands;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setNbr_orders(int nbr_orders) {
        this.nbr_orders = nbr_orders;
    }

    public void setNbr_cus_demands(int nbr_cus_demands) {
        this.nbr_cus_demands = nbr_cus_demands;
    }

}
