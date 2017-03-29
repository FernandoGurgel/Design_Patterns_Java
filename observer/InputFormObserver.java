package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InputFormObserver implements Observer{

	DisplayForm df = new DisplayForm();
	
	@Override
	public void update(Observable o, Object arg) {
		df.display.setText(arg.toString());
	}
	
	public class DisplayForm extends JFrame{
		JTextField display;
		
		public DisplayForm (){
			addWindowListener(new WindowAdapter() {
				
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
			display = new JTextField(10);
			display.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			getContentPane().add(display);
			setTitle("Observe ");
			setSize(200,100);
			setLocation(200, 100);
			setVisible(true);
		}
		
	}
	
	/*public static void main(String[] args) {
		InputFormObserver observer = new InputFormObserver();
		InputForm form = new InputForm();
		
		observer.update(form.getInputInfo(), form.getText());
		
	}*/
	

}

