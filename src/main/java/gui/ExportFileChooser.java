package gui;

import javax.swing.JFileChooser;

public class ExportFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	public ExportFileChooser() {
		this.setAcceptAllFileFilterUsed(false);
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

}
