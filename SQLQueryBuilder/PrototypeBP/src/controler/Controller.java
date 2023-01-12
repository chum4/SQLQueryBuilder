package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.MainModel;
import view.MainFrame;

public class Controller implements ActionListener{
	
	private MainModel model;
	private MainFrame frame;
	
	
	public Controller(MainModel model)
	{
		this.model = model;
		frame = MainFrame.getInstance();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try 
		{
			String s = frame.getSelectedCode();
			System.out.println(s);
			String tmp = this.model.buildQuery(s);
			this.model.readDataFromTable(tmp);
			this.model.loadResource();
		} catch (Exception e) 
		{
			System.out.println("Text is not selected");
		}
		
	}
	
	

}
