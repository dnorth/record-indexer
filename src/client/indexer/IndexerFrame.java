package client.indexer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import client.UI.FrameController;
import client.listeners.MenuListener;

@SuppressWarnings("serial")
public class IndexerFrame extends JFrame {
	// Add subcomponents to the window
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem downloadBatch;
	JMenuItem logout;
	JMenuItem exit;
	
	ImagePanel image;
	JTabbedPane bottomLeftPane;
	JTabbedPane bottomRightPane;
	JPanel tableEntry;
	JPanel formEntry;
	JPanel fieldHelp;
	JPanel imageNav;
	
	//All of the buttons
	JButton zoomInButton;
	JButton zoomOutButton;
	JButton invertButton;
	JButton highlightsButton;
	JButton saveButton;
	JButton submitButton;
	
	FrameController controller;
	
	public IndexerFrame(FrameController controller) {
		
		this.controller = controller;
		// Set the window's title
		this.setTitle("Indexer");
		
		// Specify what should happen when the user clicks the window's close icon
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 900, 40);
		zoomInButton = new JButton("Zoom In");
		panel.add(zoomInButton);
		zoomOutButton = new JButton("Zoom Out");
		panel.add(zoomOutButton);
		invertButton = new JButton("Invert Image");
		panel.add(invertButton);
		highlightsButton = new JButton("Toggle Highlights");
		panel.add(highlightsButton);
		saveButton = new JButton("Save");
		panel.add(saveButton);
		submitButton = new JButton("Submit");
		panel.add(submitButton);
		
		GridBagConstraints gbc_actions = new GridBagConstraints();
		gbc_actions.insets = new Insets(3, 0, 2, 0);
		gbc_actions.anchor = GridBagConstraints.WEST;
		gbc_actions.gridx = 0;
		gbc_actions.gridy = 0;
		this.getContentPane().add(panel, gbc_actions);
		
		JSplitPane mainSplitter = new JSplitPane();
		mainSplitter.setContinuousLayout(true);
		mainSplitter.setResizeWeight(0.5);
		mainSplitter.setDividerLocation(400);
		mainSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_mainSplitter = new GridBagConstraints();
		gbc_mainSplitter.fill = GridBagConstraints.BOTH;
		gbc_mainSplitter.weightx = 1;
		gbc_mainSplitter.gridx = 0;
		gbc_mainSplitter.gridy = 1;
		this.getContentPane().add(mainSplitter, gbc_mainSplitter);
		
		JSplitPane bottomSplitter = new JSplitPane();
		bottomSplitter.setContinuousLayout(true);
		bottomSplitter.setResizeWeight(0.5);
		mainSplitter.setRightComponent(bottomSplitter);
		
		//Initialize the Panels
		image = new ImagePanel("http://localhost:8080/images/draft_image3.png");
		tableEntry = new TablePanel();
		formEntry = new FormPanel();
		
		bottomLeftPane = new JTabbedPane(JTabbedPane.TOP);
		bottomRightPane = new JTabbedPane(JTabbedPane.TOP);
		
				
		bottomSplitter.setLeftComponent(bottomLeftPane);
		bottomSplitter.setRightComponent(bottomRightPane);
		
		bottomLeftPane.addTab("Table Entry", tableEntry);
		bottomLeftPane.addTab("Form Entry", formEntry);
		
		bottomRightPane.addTab("Image Navigation", imageNav);
		bottomRightPane.addTab("Field Help", fieldHelp);

		mainSplitter.setLeftComponent(image);
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		//Adding items to the menu
		downloadBatch = new JMenuItem("Download Batch");
		downloadBatch.addActionListener(new MenuListener(this, "download"));
		menu.add(downloadBatch);
		
		logout = new JMenuItem("Logout");
		logout.addActionListener(new MenuListener(this, "logout"));
		menu.add(logout);
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuListener(this, "exit"));
		menu.add(exit);
		
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);

		// Set the location of the window on the desktop
		this.setLocation(100, 100);

		// Size the window according to the preferred sizes and layout of its subcomponents
		this.setSize(900, 700);
	}

	public void exitProgram()
	{
		controller.exitProgram();
	}
	
	public void logout()
	{
		controller.logout();
	}
	
	public void downloadBatch()
	{
		DownloadFrame download = new DownloadFrame(controller);
		download.setVisible(true);
	}

	public JMenu getMenu() {
		return menu;
	}

	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	public JMenuItem getDownloadBatch() {
		return downloadBatch;
	}

	public void setDownloadBatch(JMenuItem downloadBatch) {
		this.downloadBatch = downloadBatch;
	}

	public JMenuItem getLogout() {
		return logout;
	}

	public void setLogout(JMenuItem logout) {
		this.logout = logout;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}

	public ImagePanel getImage() {
		return image;
	}

	public void setImage(ImagePanel image) {
		this.image = image;
	}

	public JTabbedPane getBottomLeftPane() {
		return bottomLeftPane;
	}

	public void setBottomLeftPane(JTabbedPane bottomLeftPane) {
		this.bottomLeftPane = bottomLeftPane;
	}

	public JTabbedPane getBottomRightPane() {
		return bottomRightPane;
	}

	public void setBottomRightPane(JTabbedPane bottomRightPane) {
		this.bottomRightPane = bottomRightPane;
	}

	public JPanel getTableEntry() {
		return tableEntry;
	}

	public void setTableEntry(JPanel tableEntry) {
		this.tableEntry = tableEntry;
	}

	public JPanel getFormEntry() {
		return formEntry;
	}

	public void setFormEntry(JPanel formEntry) {
		this.formEntry = formEntry;
	}

	public JPanel getFieldHelp() {
		return fieldHelp;
	}

	public void setFieldHelp(JPanel fieldHelp) {
		this.fieldHelp = fieldHelp;
	}

	public JPanel getImageNav() {
		return imageNav;
	}

	public void setImageNav(JPanel imageNav) {
		this.imageNav = imageNav;
	}

	public JButton getZoomInButton() {
		return zoomInButton;
	}

	public void setZoomInButton(JButton zoomInButton) {
		this.zoomInButton = zoomInButton;
	}

	public JButton getZoomOutButton() {
		return zoomOutButton;
	}

	public void setZoomOutButton(JButton zoomOutButton) {
		this.zoomOutButton = zoomOutButton;
	}

	public JButton getInvertButton() {
		return invertButton;
	}

	public void setInvertButton(JButton invertButton) {
		this.invertButton = invertButton;
	}

	public JButton getHighlightsButton() {
		return highlightsButton;
	}

	public void setHighlightsButton(JButton highlightsButton) {
		this.highlightsButton = highlightsButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	public FrameController getController() {
		return controller;
	}

	public void setController(FrameController controller) {
		this.controller = controller;
	}
}
