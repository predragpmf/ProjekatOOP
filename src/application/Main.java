//
//	Main klasa
//
package application;

import login.LoginWindow;

public class Main{
	public static String[] args;
	
	public static void main(String[] args) {
		UcitavanjeBaze.ucitavanje();
		LoginWindow.main(args);
	}
}
