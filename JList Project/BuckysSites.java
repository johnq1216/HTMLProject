import java.applet.AppletContext;
import java.awt.BorderLayout;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BuckysSites extends JApplet {
	// URLs are objects rather than strings. Java cannot open Strings, only
	//URLS
	private HashMap<String, URL> websiteInfo;
	private ArrayList<String> titles;
	private JList mainList;

	// init
	public void init() {
		websiteInfo = new HashMap<String, URL>();
		titles = new ArrayList<String>();

		grabHTMLInfo();
		add(new JLabel("What website do you want to visit?"),
				BorderLayout.NORTH);
		mainList = new JList(titles.toArray());

		mainList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//gets the string object of whereever the mouse clicked on
				Object object = mainList.getSelectedValue();
				//passes that string object to a hashmap to get the URL stored
				URL newDocument = websiteInfo.get(object);
				//get the current browser.  Which is most likely Chrome
				AppletContext browser = getAppletContext();
				browser.showDocument(newDocument);
			}

		});
		add(new JScrollPane(mainList), BorderLayout.CENTER);
	}

	//get website info
	private void grabHTMLInfo() {
		String title;
		String address;
		URL url;
		int counter = 0;
		title = getParameter("title" + counter);
		
		while(title != null){
			address = getParameter("address" + counter);
			try{
				url = new URL(address);
				//add to hashmap
				websiteInfo.put(title, url);
				titles.add(title);
			}catch(MalformedURLException urlException){
				urlException.printStackTrace();
			}
			++counter;
			title = getParameter("title" + counter);
		}
	}
}
