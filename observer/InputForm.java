package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputForm extends JFrame{
	
	public InformDisplay inform = new InformDisplay();
	JTextField input = new JTextField(10);

	public InputForm(){
		JPanel panel = new JPanel();
		input.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			inform.notifyObservers();
			}
		});
		panel.add(new JLabel("Enter: "));
		panel.add(input);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		getContentPane().add(panel);
		setTitle("Observable Form");
		setSize(200, 100);
		setVisible(true);
	}

	public Observable getInputInfo(){
       return inform;
    }
	public String getText(){
		return input.getText();
	}

	private class InformDisplay extends Observable{
		public void notifyObservers(){
			setChanged();
			super.notifyObservers();
		}

		public String getChange(){
			return input.getText();
		}
	}
	
}


