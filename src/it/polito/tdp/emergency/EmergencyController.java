package it.polito.tdp.emergency;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import it.polito.tdp.emergency.model.Simulatore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmergencyController {

	private Simulatore sim;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtIntervalloPazienti"
	private TextField txtIntervalloPazienti; // Value injected by FXMLLoader

	@FXML // fx:id="txtNumStudi"
	private TextField txtNumStudi; // Value injected by FXMLLoader

	@FXML // fx:id="txtNumPazienti"
	private TextField txtNumPazienti; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtIntervalloPazienti != null : "fx:id=\"txtIntervalloPazienti\" was not injected: check your FXML file 'Emergency.fxml'.";
		assert txtNumStudi != null : "fx:id=\"txtNumStudi\" was not injected: check your FXML file 'Emergency.fxml'.";
		assert txtNumPazienti != null : "fx:id=\"txtNumPazienti\" was not injected: check your FXML file 'Emergency.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Emergency.fxml'.";

	}

	@FXML
	void doSimula(ActionEvent event) {

		try {// setto i valori nuovi, se >0
			int numPazienti = Integer.parseInt(txtNumPazienti.getText());
			if (numPazienti != 0)
				sim.setNumPazienti(numPazienti);
			int numStudi = Integer.parseInt(txtNumStudi.getText());
			if (numStudi != 0)
				sim.setNumStudi(numStudi);
			Duration intervalloP = Duration.ofMinutes(Long.parseLong(txtIntervalloPazienti.getText()));
			if (!intervalloP.isZero() && !intervalloP.isNegative())
				sim.setTArrivo(intervalloP);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		}
		sim.init();
		sim.run();

		txtResult.setText(String.format("Pazienti dimessi: %d\n", sim.getNumDimessi()));
		txtResult.appendText(String.format("Pazienti abbandonati: %d\n", sim.getNumAbbandoni()));
		txtResult.appendText(String.format("Pazienti morti: %d\n", sim.getNumMorti()));
	}

	public void setSim(Simulatore sim) {
		this.sim = sim;
		sim.init();

		txtIntervalloPazienti.setText(Long.toString(sim.getNumIntervalloPazienti().toMinutes()));
		txtNumStudi.setText(Integer.toString(sim.getNumStudi()));
		txtNumPazienti.setText(Integer.toString(sim.getNumPazienti()));

	}
}
