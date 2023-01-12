package error;

import javax.swing.JOptionPane;

public class ErrorDialog extends JOptionPane{

	
	
	public ErrorDialog(String text)
	{
		this.showMessageDialog(this, text);
	}
}
