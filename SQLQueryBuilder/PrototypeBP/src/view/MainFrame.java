package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controler.Controller;
import lombok.Data;
import model.MainModel;
import observer.Notification;
import observer.NotificationEnum;
import observer.Subscriber;
import repository.implementation.Information;
import table.model.TableModel;

@Data
public class MainFrame extends JFrame implements Subscriber{
	
	private static MainFrame instance = null;
	private MainModel model;
	private JTable table;
	private JScrollPane paneNorth;
	private JScrollPane panelSouth;
	private JTextArea inputText;
	private Controller controller;
	private JSplitPane paneSplit;
	private JButton compileB;
	
	private MainFrame()
	{
		
	}
	
	public static MainFrame getInstance()
	{
		if(instance == null)
		{
			instance = new MainFrame();
			instance.initialise();
		}
		
		return instance;
	}
	
	private void initialise()
	{
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);
		//controller = new Controller(model);
		compileB = new JButton("Compile");
		//addController(controller);
		inputText = new JTextArea();
		paneNorth = new JScrollPane(inputText);
		panelSouth = new JScrollPane(table);
		paneSplit = new JSplitPane(SwingConstants.HORIZONTAL, paneNorth, panelSouth);
		paneSplit.setDividerLocation(300);
		JPanel panel = new JPanel();
		panel.add(compileB);
		//inputText = new JTextArea();
		
		//paneNorth.add(inputText);
		
		//this.add("Center", paneNorth);
		//this.add("Center", panelSouth);
		
		this.add("Center", paneSplit);
		this.add("South", panel);
		
		this.pack();
		this.setLocale(null);
		this.setSize(600,600);
		this.setVisible(true);
		
		paneSplit.setDividerLocation(0.4);
		
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void setModel(MainModel model)
	{
		this.model = model;
		this.model.addSubrscriber(this);
		this.table.setModel(model.getTableModel());
		this.controller = new Controller(this.model);
		addController(controller);
	}

	@Override
	public void update(Notification notification) {
		
		if(notification.getType() == NotificationEnum.RESOURCE_LOADED)
		{
			System.out.println((Information)notification.getData());
		}else
		{
			table.setModel((TableModel) notification.getData());
		}
		
	}
	
	public void addController(ActionListener controller)
	{
		System.out.println("View adding controller");
		compileB.addActionListener(controller);
	}
	
	public String getSelectedCode()
	{
		return this.inputText.getSelectedText();
	}
	
	

}
