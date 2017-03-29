package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DisplayForm extends JFrame {
	
	Observer input = new InputFormObserver();
	InputForm inputForm;
	Observable obsInput;
	JTextField display;

	public DisplayForm() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		inputForm = new InputForm();
		obsInput = inputForm.getInputInfo();
		obsInput.addObserver(input);

		display = new JTextField(10);
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		getContentPane().add(display);
		setTitle("Observe form");
		setSize(200, 100);
		setLocation(200, 100);
		setVisible(true);
	}

	private class InputFormObserver implements Observer {
		public void update(Observable ob, Object o) {
			doSomeUpdate();
			if (obsInput.countObservers() > 0)
				obsInput.deleteObservers();
			obsInput = inputForm.getInputInfo();
			obsInput.addObserver(input);
		}
	}

	public void doSomeUpdate() {
		display.setText(inputForm.getText());
		JOptionPane.showMessageDialog(DisplayForm.this, "This form has been updated");
	}

	public static void main(String args[]) {
		DisplayForm df = new DisplayForm();
	}
}