package bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import user.Book;
import user.Customer;
import user.Status;

import java.net.URL;
import java.util.ResourceBundle;
import user.GoldStatus;
import user.SilverStatus;

public class CustomerCostScreenController extends SceneController implements Initializable {

    @FXML
    private Label TotalCost;

    @FXML
    private Label PointsStatus;

    @FXML
    private Button LogOut;

    private double totalCost;
    private Customer customer;

      // Set the logout button action
    @FXML
    public void logout(ActionEvent ev) throws Exception{
        switchScene(ev, "LoginScreen.fxml");
    }
    
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the UI components
        //TotalCost.setText("Total Cost: $" + totalCost);
        //PointsStatus.setText("Points: " + customer.getPoints() + ", Status: " + customer.getState().giveStatus());

    }

  

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    @FXML
    public void handleBuyButtonAction() {
        // Calculate the total cost
        double transactionCost = 0;
        for (Book book : customer.getSelectedBooks()) {
            transactionCost += book.getPrice();
        }

        // Check if the customer has enough points to redeem
        if (customer.getPoints() >= transactionCost * 10) {
            // Redeem all available points
            double pointsToRedeem = Math.floor(customer.getPoints() / 100) * 100;
            double pointsRedeemed = pointsToRedeem / 10;
            double effectiveTransactionCost = Math.max(transactionCost - pointsRedeemed, 0);

            // Update customer's points and status
            customer.setPoints(customer.getPoints() - pointsToRedeem);
            if (customer.getState() instanceof SilverStatus && customer.getPoints() >= 5000) {
                customer.changeState(new GoldStatus() {
                    @Override
                    public String getStatus() {
                        return "Gold";
                    }
                });
            } else if (customer.getState() instanceof GoldStatus && customer.getPoints() < 5000) {
                customer.changeState(new SilverStatus());
            }

            // Update the total cost label
            TotalCost.setText("Total Cost: $" + effectiveTransactionCost);

            // Update the points and status label
            PointsStatus.setText("Points: " + customer.getPoints() + ", Status: " + customer.getState().getStatus());

        } else {
            // Update the total cost label
            TotalCost.setText("Total Cost: $" + transactionCost);

            // Update the points and status label
            PointsStatus.setText("Points: " + customer.getPoints() + ", Status: " + customer.getState().getStatus());
        }
    }
   
}
