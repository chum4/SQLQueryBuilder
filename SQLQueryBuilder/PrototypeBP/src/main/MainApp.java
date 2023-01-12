package main;

import model.MainModel;
import view.MainFrame;

public class MainApp {

	public static void main(String[] args) {
		MainModel model = new MainModel();
		MainFrame m = MainFrame.getInstance();
		m.setModel(model);
		/*
		m.getModel().readDataFromTable("EMPLOYEES");
		m.getModel().loadResource();
		/*
		try 
		{
			Thread.sleep(1000);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		m.getModel().readDataFromTable("JOBS");
		*/

	}

}
