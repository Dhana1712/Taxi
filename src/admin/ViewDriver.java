package admin;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
public class ViewDriver extends JPanel {
    JTextField stateTextField,drivingLicenseTextField,addressTextField,drivingExpTextField,fNameTextField,lNameTextField,carIDTextField,genderTextField,DOBTextField,cityTextField,mobileTextField,emailTextField,approvedTextField,availabilityTextField,xpTextField;
    Label stateLabel,drivingLicenseLabel,addressLabel,drivingExpLabel,driverDetailLabel,driversTableLabel,fNameLabel,lNameLabel,carIDLabel,genderLabel,DOBLabel,cityLabel,mobileLabel,emailLabel,approvedLabel,availabilityLabel,xpLabel;
    JButton deleteDriverButton,refreshButton;
    JTable driversTable;
    JScrollPane driversTableScrollPane;
    Statement statement;
    Object[][] arr;
    String query;
    ResultSet resultSet;
    int noOfApprovedDrivers;
    ViewDriver(){
        setBounds(0,0,1900,1000);
        setVisible(true);
        setLayout(null);
        setFont(TabServer.font);

        //Label
        driversTableLabel=new Label("Drivers List : ");
        driverDetailLabel=new Label("Driver's Details : ");
        fNameLabel=new Label("First Name : ");
        lNameLabel=new Label("Last Name : ");
        carIDLabel=new Label("Car ID : ");
        genderLabel=new Label("Gender : ");
        DOBLabel=new Label("DOB : ");
        cityLabel=new Label("City : ");
        mobileLabel=new Label("Mobile number : ");
        emailLabel=new Label("Email ID : ");
        approvedLabel=new Label("Approved : ");
        availabilityLabel=new Label("Availability : ");
        xpLabel=new Label("XP gained : ");
        drivingLicenseLabel=new Label("License ID : ");
        drivingExpLabel=new Label("Driving experience : ");
        addressLabel=new Label("Address : ");
        stateLabel=new Label("State : ");

        //TextFields
        fNameTextField=new JTextField();
        lNameTextField=new JTextField();
        carIDTextField=new JTextField();
        genderTextField=new JTextField();
        DOBTextField=new JTextField();
        cityTextField=new JTextField();
        mobileTextField=new JTextField();
        availabilityTextField=new JTextField();
        xpTextField=new JTextField();
        emailTextField=new JTextField();
        approvedTextField=new JTextField();
        drivingLicenseTextField=new JTextField();
        drivingExpTextField=new JTextField();
        addressTextField=new JTextField();
        stateTextField=new JTextField();

        //Button
        deleteDriverButton=new JButton("Remove Driver");
        refreshButton=new JButton("Refresh");

        //Table
        fillApprovedDriversDetailsAsArr();

        //Labels
        driversTableLabel.setBounds(0,0,200,60);
        driverDetailLabel.setBounds(820,0,200,60);
        fNameLabel.setBounds(820,70,200,60);
        lNameLabel.setBounds(820,140,200,60);
        carIDLabel.setBounds(820,210,200,60);
        genderLabel.setBounds(820,280,200,60);
        DOBLabel.setBounds(820,350,200,60);
        cityLabel.setBounds(820,420,200,60);
        addressLabel.setBounds(820,490,200,60);
        drivingExpLabel.setBounds(820,560,200,60);
        drivingLicenseLabel.setBounds(1240,70,200,60);
        stateLabel.setBounds(1240,140,200,60);
        mobileLabel.setBounds(1240,210,200,60);
        emailLabel.setBounds(1240,280,200,60);
        approvedLabel.setBounds(1240,350,200,60);
        availabilityLabel.setBounds(1240,420,200,60);
        xpLabel.setBounds(1240,490,200,60);

        //TextFields
        fNameTextField.setBounds(1030,70,200,60);
        lNameTextField.setBounds(1030,140,200,60);
        carIDTextField.setBounds(1030,210,200,60);
        genderTextField.setBounds(1030,280,200,60);
        DOBTextField.setBounds(1030,350,200,60);
        cityTextField.setBounds(1030,420,200,60);
        addressTextField.setBounds(1030,490,200,60);
        drivingExpTextField.setBounds(1030,560,200,60);
        drivingLicenseTextField.setBounds(1440,70,200,60);
        stateTextField.setBounds(1440,140,200,60);
        mobileTextField.setBounds(1440,210,200,60);
        emailTextField.setBounds(1440,280,200,60);
        approvedTextField.setBounds(1440,350,200,60);
        availabilityTextField.setBounds(1440,420,200,60);
        xpTextField.setBounds(1440,490,200,60);

        //Button
        refreshButton.setBounds(1240,560,190,60);
        deleteDriverButton.setBounds(1440,560,200,60);
        deleteDriverButton.setEnabled(false);

        //Button
        refreshButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillApprovedDriversDetailsAsArr();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        deleteDriverButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(deleteDriverButton.isEnabled())){
                    return;
                }
                removeDriver(emailTextField.getText().trim());
                JOptionPane.showMessageDialog(null,"Driver removed successfully");
                clearFields();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //Label
        add(fNameLabel);
        add(lNameLabel);
        add(carIDLabel);
        add(genderLabel);
        add(DOBLabel);
        add(cityLabel);
        add(addressLabel);
        add(drivingExpLabel);
        add(drivingLicenseLabel);
        add(stateLabel);
        add(mobileLabel);
        add(emailLabel);
        add(approvedLabel);
        add(availabilityLabel);
        add(xpLabel);

        //TextFields
        add(fNameTextField);
        add(lNameTextField);
        add(carIDTextField);
        add(genderTextField);
        add(DOBTextField);
        add(cityTextField);
        add(drivingLicenseTextField);
        add(drivingExpLabel);
        add(stateTextField);
        add(mobileTextField);
        add(emailTextField);
        add(approvedTextField);
        add(addressTextField);
        add(drivingExpTextField);
        add(availabilityTextField);
        add(xpTextField);

        //Button
        add(deleteDriverButton);
        add(refreshButton);

        add(driverDetailLabel);
        add(driversTableScrollPane);
        add(driversTableLabel);
    }
    public int getNoOfApprovedDrivers(){
        int n=0;
        try {
            query="select * from driver where approved='true'";
            statement = TabServer.connection.createStatement();
            resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                n++;
            }
        }
        catch(Exception e){
            System.out.println("getNoOfApprovedDrivers()"+e);
        }
        return n;
    }
    public void fillApprovedDriversDetailsAsArr(){
        try{
            int size=getNoOfApprovedDrivers();
            arr=new Object[getNoOfApprovedDrivers()][5];
            driversTable=new JTable(arr,new Object[]{"SNO.","Name","State","Mobile Number","Email"});
            driversTableScrollPane=new JScrollPane(driversTable);
            driversTableScrollPane.setBounds(0,70,800,800);
            driversTable.setRowHeight(60);
            query="select * from driver where approved ='true'";
            statement=TabServer.connection.createStatement();
            resultSet=statement.executeQuery(query);
            resultSet.next();
            for(int i=0;i<size;i++){
                arr[i][0]=i+1;
                arr[i][1]=resultSet.getString(1).trim()+" "+resultSet.getString(2).trim();
                arr[i][2]=resultSet.getString(10).trim();
                arr[i][3]=resultSet.getString(11).trim();
                arr[i][4]=resultSet.getString(12).trim();
                resultSet.next();
            }
        }
        catch(Exception e){
            System.out.println("getApprovedDriverDetailsAsArr()"+e);
        }
    }
    public void removeDriver(String email){
        try{
            query="delete from driver where email='"+email+"'";
            statement=TabServer.connection.createStatement();
            statement.executeQuery(query);
        }
        catch(Exception e){
            System.out.println("removeDriver"+e);
        }
    }
    public void clearFields(){
        drivingExpTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        carIDTextField.setText("");
        genderTextField.setText("");
        DOBTextField.setText("");
        cityTextField.setText("");
        addressTextField.setText("");
        drivingExpTextField.setText("");
        emailTextField.setText("");
        drivingLicenseTextField.setText("");
        stateTextField.setText("");
        mobileTextField.setText("");
        approvedTextField.setText("");
        availabilityTextField.setText("");
        xpTextField.setText("");
    }
}
