package driver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
public class TabServer extends JFrame{
    JTabbedPane tabs;
    static Pattern pattern;
    String s;
    static Matcher matcher;
    JPanel login,signup,viewRide,feedback,update;
    static Driver driver=new Driver("Madhi","V","12325","male","sdfd","dfsd","dslfds","sdfgdsf","sdfgds","dsfdsf",34565434,3434);
    public TabServer(){
        setBackground(new Color(35, 176, 212));
        setTitle("DRIVER'S HOME");
        tabs=new JTabbedPane();
        setBounds(0,0,1900,1000);
        setVisible(true);
        setFont(new Font("Times new roman",Font.BOLD,18));
        add(tabs,BorderLayout.CENTER);
        login=new Login();
        signup=new SignUp();
        viewRide=new ViewRides();
        feedback =new Feedback();
        update=new ViewProfile();
        tabs.addTab("LOGIN",login);
        tabs.addTab("SIGNUP",signup);
        tabs.addTab("VIEW RIDES",viewRide);
        tabs.addTab("VIEW PROFILE",update);
        tabs.addTab("PROVIDE FEEDBACK",feedback);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(tabs);
    }
    static public boolean isValidEmail(String email){
        pattern=Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        matcher=pattern.matcher(email);
        return matcher.matches();
    }
    static public boolean isValidName(String name){
        pattern=Pattern.compile("[a-zA-Z][a-zA-Z ]+");
        matcher=pattern.matcher(name);
        return matcher.matches();
    }
    static public boolean isValidNumber(String text){
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)-48<0 || text.charAt(i)-48>9){
                return false;
            }
        }
        return true;
    }
    static public boolean isValidDate(String txt){
        pattern=Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
        matcher=pattern.matcher(txt);
        return matcher.matches();
    }
    static public boolean isValidPassword(String txt){
        pattern=Pattern.compile("((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
        matcher=pattern.matcher(txt);
        return matcher.matches();
    }
    static public boolean isValidID(String txt){
        pattern=Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");
        matcher=pattern.matcher(txt);
        return matcher.find();
    }
}
