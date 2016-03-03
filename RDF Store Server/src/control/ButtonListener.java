package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonListener implements ActionListener {
	private RDFStoreServer server;

	public ButtonListener(RDFStoreServer server) {
		this.server = server;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "start") {
			server.start();
		} else if (e.getActionCommand() == "stop") {
			try {
				server.stop();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (e.getActionCommand() == "addR") {
			server.setRFileVisible();
		}else if (e.getActionCommand() == "ok") {
			System.out.println("jfajfw;lajv");
			server.addRFile();
		}
	}

}
