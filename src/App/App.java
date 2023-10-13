package App;

import javax.swing.SwingUtilities;
import Controllers.Controllers;
import Views.Views;

public class App {
    public static void main(String[] args) {
        // Schedule the application to run on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the controller
                Controllers controller = new Controllers(null);

                // Create the view
                Views view = new Views(controller);

                // Set the view for the controller
                controller.setView(view);

                // Show the view
                view.show();
            }
        });
    }
}
