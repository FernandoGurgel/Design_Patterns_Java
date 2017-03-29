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


public class InformDisplay extends Observable{

	public String atualiza;
	
	InputForm inputForm = new InputForm();
	
	public void setUtimaNoticia(String at){
		atualiza = at;
		setChanged();
		notifyObservers(atualiza);
	}
	
	public class InputForm extends JFrame{
		JTextField input = new JTextField(10);
		
		public InputForm (){
			JPanel panel = new JPanel();
			
			panel.add(new JLabel("Enter: "));
			panel.add(input);
			input.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setUtimaNoticia(input.getText());
					input.setText(null);
				}
			});
			
			addWindowListener(new WindowAdapter() {
				
				public void windowCloing (WindowEvent e){
					System.exit(0);
				}
			});
			
			getContentPane().add(panel);
			setTitle("Obeser ");
			setSize(300,100);
			setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		
		InformDisplay display = new InformDisplay();
		InputFormObserver observer = new InputFormObserver();
		display.addObserver(observer);		
		
	}
}