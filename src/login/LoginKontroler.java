//
//	Kontroler login prozora
//
package login;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import application.PristupniPodaci;
import application.Ucenik;
import application.Korisnik;

public class LoginKontroler implements Initializable {
	
	public static String[] args;
	
	@FXML
    private TextField korisnikUnos;
    @FXML
    private PasswordField lozinkaUnos;
    @FXML
    private Button prijavaTipka;
    @FXML
    private MenuItem exitMeni;
    @FXML
    private MenuItem aboutMeni;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exitMeni.setOnAction(event -> {
			System.exit(0);
		});
		aboutMeni.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("OOP projekat");
			alert.setContentText("v1.0");
			alert.showAndWait();
		});
		prijavaTipka.setOnAction(event -> {
			//System.out.println("Pritisak tipke!");
            String korisnickoIme = korisnikUnos.getText().strip();
            //System.out.println(korisnickoIme);
            String sifra = lozinkaUnos.getText().strip();
            //System.out.println(sifra);
            for(PristupniPodaci pp : PristupniPodaci.sviPristupniPodaci) {
            	//System.out.println(pp.getKorisnickoIme() == korisnickoIme.strip());
            	if((pp.getKorisnickoIme().equals(korisnickoIme)) && (pp.getSifra().equals(hesirajSifru(sifra)))) {
            		System.out.println("Postoji korisnik!");
            		new Korisnik();
            		for(Korisnik k : Korisnik.sviKorisnici) {
            			if(pp.getId() == k.getPristupId()) {
            				if(k instanceof Ucenik) {
            					System.out.println("Korisnik je ucenik!");
								try {
									FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/Ucenik.fxml"));
	            					Scene scene = new Scene(fxmlLoader.load(), 600, 400);
	            					LoginWindow.getStage().setResizable(false);
	            					LoginWindow.getStage().setTitle("Ucenik");
	            					LoginWindow.getStage().setScene(scene);
	            					//LoginWindow.getStage().show();
	            					break;
								} catch (IOException e) {
									e.printStackTrace();
								}
            				} else {
            					System.out.println("Korisnik je profesor!");
            					try {
									FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/Profesor.fxml"));
	            					Scene scene = new Scene(fxmlLoader.load(), 600, 400);
	            					LoginWindow.getStage().setResizable(false);
	            					LoginWindow.getStage().setTitle("Profesor");
	            					LoginWindow.getStage().setScene(scene);
	            					//LoginWindow.getStage().show();
	            					break;
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
            			}
            		}
				}
            }
            
		});
	}
	
	private String hesirajSifru(String sifra) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(sifra.getBytes());
	        BigInteger no = new BigInteger(1, messageDigest);
	        String hashtext = no.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
