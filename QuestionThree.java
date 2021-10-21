import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.border.*;
import javax.swing.event.*;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

public class QuestionThree {
    
    //declaring connection, statement and resultset as public(global)
    static Connection con;
    static Statement st;
    static ResultSet rs;
    
    public static void dbConnection(){
         try{
            //Connection to database 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pointofsale", "vennisha", "vennisha123");
            //accessing sql through statement
            st = con.createStatement();
        }catch (SQLException e) {
            //prints exception if there is one
            System.out.println(e.getMessage());
        }
    }
    
    //Method for Employee login
    public static void empLogin(){
        
        //Creating a frame
        JFrame frmEmpLogin = new JFrame("Employee Login");
        //setting the fram to visible
        //frmEmpLogin.setVisible(true);
        //setting layout to null
        frmEmpLogin.setLayout(null);
        //setting the size of a frame
        frmEmpLogin.setSize(400, 250);
        //setting location of the frame
        frmEmpLogin.setLocation(500, 200);
        //Closing frame when the exit is clicked
        frmEmpLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creating a label and giving it a title
        JLabel lblEmpID = new JLabel("Employee Username");
        //setting bounds of the label (max space/size it uses, in the frame)
        lblEmpID.setBounds(20, 20, 150, 30);
        //adding the label to the frame
        frmEmpLogin.getContentPane().add(lblEmpID);
        
        //Creating a textfield 
        JTextField txtUsername = new JTextField("123456789");
        //setting bounds of the label (max space/size it uses, in the frame)
        txtUsername.setBounds(150, 20, 200, 30);
        //adding the textfield to the frame
        frmEmpLogin.getContentPane().add(txtUsername);

        JLabel lblEmpPass = new JLabel("Employee Password");
        lblEmpPass.setBounds(20, 90, 150, 30);
        frmEmpLogin.getContentPane().add(lblEmpPass);
        
        JTextField txtPass = new JTextField("987654321");
        txtPass.setBounds(150, 90, 200, 30);
        frmEmpLogin.getContentPane().add(txtPass);
        
        //Creating a button and setting a title
        JButton btnLogin = new JButton("Login");
        //setting bounds of the label (max space/size it uses, in the frame)
        btnLogin.setBounds(140, 150, 100, 30);
        //adding the button to the frame
        frmEmpLogin.getContentPane().add(btnLogin);
        
        frmEmpLogin.setVisible(true);
        
        //creation of an action listen. Does what is told when the button is clicked
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checking if the text in the textfields match the required values
                if(txtUsername.getText().equals("123456789") && txtPass.getText().equals("987654321")){
                    //Disposes the frame
                    frmEmpLogin.dispose();
                    //Calls the method
                    PointOfSale();
                }else{
                    //Error dialog that pops if there's an error
                    JOptionPane.showMessageDialog(frmEmpLogin, "Incorrect Login Details.","Alert", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
    }
    
    //gobally declared varaibles, initialised to zero
    static int counter=0;
    static int Quan=0;
    static double finalTotal = 0;
            
    //Creating a method 
    public static void PointOfSale(){
        
        JFrame frmPOS = new JFrame("Point of Sale");
        frmPOS.setVisible(true);
        frmPOS.setLayout(null);
        frmPOS.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmPOS.setLocation(0,0);
        frmPOS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblBarcode = new JLabel("Enter Barcode");
        lblBarcode.setBounds(5, 5, 100, 30);
        frmPOS.add(lblBarcode);
        
        JTextField txtBarcode = new JTextField();
        txtBarcode.setBounds(5, 30, 500, 30);
        frmPOS.add(txtBarcode);
        
        JLabel lblProduct = new JLabel("Product");
        lblProduct.setBounds(5, 65, 100, 30);
        frmPOS.add(lblProduct);
        
        JTextField txtProduct = new JTextField();
        txtProduct.setBounds(5, 90, 120, 30);
        frmPOS.getContentPane().add(txtProduct);
        
        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(135, 65, 100, 30);
        frmPOS.getContentPane().add(lblQuantity);
        
        int min = 1;
        int max = 100;
        int step = 1;
        int i = 1;
        SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
        
        //Creating a spinner
        JSpinner spnQuantity = new JSpinner(value);
        //setting max bounds the component can use in the frame
        spnQuantity.setBounds(135, 90, 100, 30);
        //adding the spinner to the frame
        frmPOS.getContentPane().add(spnQuantity);
        
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(250, 65, 100, 30);
        frmPOS.getContentPane().add(lblPrice);
        
        JTextField txtPrice = new JTextField();
        txtPrice.setBounds(250, 90, 120, 30);
        frmPOS.getContentPane().add(txtPrice);
        
        JLabel lblTotal= new JLabel("Total");
        lblTotal.setBounds(385, 65, 100, 30);
        frmPOS.getContentPane().add(lblTotal);
        
        JTextField txtTotal = new JTextField();
        txtTotal.setBounds(385, 90, 120, 30);
        frmPOS.getContentPane().add(txtTotal);
        
        //Creating a button
        JButton btnAdd = new JButton("Add");
        //setting max bounds that the component can use on the frame
        btnAdd.setBounds(520, 30, 100, 30);
        //adding the button to the frame
        frmPOS.getContentPane().add(btnAdd);
        
        JLabel lblFinalTotal= new JLabel("Final Total");
        lblFinalTotal.setBounds(950, 5, 100, 30);
        frmPOS.getContentPane().add(lblFinalTotal);
        
        JTextField txtFinalTotal = new JTextField();
        txtFinalTotal.setBounds(950, 30, 120, 30);
        frmPOS.getContentPane().add(txtFinalTotal);
        
        JLabel lblPaid= new JLabel("Paid");
        lblPaid.setBounds(1080, 5, 100, 30);
        frmPOS.getContentPane().add(lblPaid);
        
        JTextField txtPaid = new JTextField();
        txtPaid.setBounds(1080, 30, 120, 30);
        frmPOS.getContentPane().add(txtPaid);
        
        JLabel lblChange= new JLabel("Change");
        lblChange.setBounds(1210, 5, 100, 30);
        frmPOS.getContentPane().add(lblChange);
        
        JTextField txtChange = new JTextField();
        txtChange.setBounds(1210, 30, 120, 30);
        frmPOS.getContentPane().add(txtChange);
        
        JButton btnChange = new JButton("Calculate change");
        btnChange.setBounds(1040, 70, 200, 30);
        frmPOS.getContentPane().add(btnChange);
        
        JButton btnPrintBill = new JButton("Print Bill");
        btnPrintBill.setBounds(950, 110, 378, 30);
        frmPOS.getContentPane().add(btnPrintBill);
        
        //Creating textarea
        JTextArea txtaDisplay = new JTextArea();
        //adding textarea to scrollpane
        JScrollPane scrPane = new JScrollPane(txtaDisplay);
        //setting bounds for scrollpane
        scrPane.setBounds(950, 150, 378, 500);
        //adding scrollpane to frame
        frmPOS.getContentPane().add(scrPane);
       
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(1228, 655, 100, 30);
        frmPOS.getContentPane().add(btnLogout);
        
        //Item scanning - bring up details of the item when barcode is entered
        txtBarcode.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent event) {
            }
         
            public void keyReleased(KeyEvent event) {
                //try catch to try code and catch exceptions if there is any
                try{
                    //string variable storing text from Barcode
                    String regex = "[0-9]+";
                    if(!txtBarcode.getText().matches(regex)){
                        JOptionPane.showMessageDialog(null, "Invalid Barcode");
                    }else{
                        
                        String Barcode = txtBarcode.getText();
                        
                        //sql checking through database table where the Barcode exists
                        rs = st.executeQuery("SELECT * FROM salesinformation WHERE Barcode ='"+Barcode+"'");

                        //if the barcode exits, display the require fields
                        if(rs.next()==true){
                            //displaying required data into textfield
                            txtProduct.setText(rs.getString("Product"));
                            txtPrice.setText(rs.getString("Price"));
                        }else{
                            txtProduct.setText("");
                            counter++;
                        }
                    }
		}catch(Exception e){
                    //if exception exists, display a message dialog
                    JOptionPane.showMessageDialog(null, e, "MessageBox", JOptionPane.INFORMATION_MESSAGE);
		}
            }
       
            public void keyTyped(KeyEvent event) { 
            }
        });
        
        //Action occurs upon change of the JSpinner
        spnQuantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
                //Variable storing value from JSnipper
                int Quantity = Integer.parseInt(spnQuantity.getValue().toString());
                //Variable storing vale from the Price textfied
                double Price = Double.parseDouble(txtPrice.getText());
                
                //Calculation of total, of selected item
                double Total = Quantity * Price;
                
                //Displaying total in the tectfield
                txtTotal.setText(String.valueOf(Total));
                
            }
        });
        
        //array for storing the teable headings
        String[] tableHeadings = {"Product","Quantity","Price","Total"};
        //Using the default table model
        DefaultTableModel model = new DefaultTableModel(0, tableHeadings.length) ;
        
        //adding the model to the table
        JTable tbSale = new JTable(model);
        
        //adding the headings to the model
        model.setColumnIdentifiers(tableHeadings);
        
        //Creating a scrollpane and adding the table to the scroll
        JScrollPane srcpn = new JScrollPane(tbSale);
        srcpn.setBounds(5, 150, 900, 500);
        frmPOS.getContentPane().add(srcpn);
        
        //Action listener, performs actions when button is declared
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //creating a default table model 
                    DefaultTableModel model = new DefaultTableModel();
                    //setting defauly model to the tbSale
                    model = (DefaultTableModel)tbSale.getModel();
                    
                    //Checking if textfield is empty
                    if(txtBarcode.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Barcode cannot be empty.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                        counter++;
                    }else{
                        //setting counter variable to zero
                        counter = 0;
                        
                        //fetching a resultset using the sql
                        rs = st.executeQuery("SELECT * FROM salesinformation WHERE Barcode = '"+txtBarcode.getText().toString()+"'");
                        
                        //loops through database
                        while(rs.next()){
                            
                            //gets the existing quantity in the database table
                            Quan = rs.getInt("Quantity");
                            
                            //if the quantity in the database is less than that in that in the spinner, it alerts the employee
                            if(Quan<Integer.parseInt(spnQuantity.getValue().toString())){
                                JOptionPane.showMessageDialog(null, "Not enough stock.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                            }else{

                            //getting values from the components    
                            String product_name = txtProduct.getText();
                            int quantity =  Integer.parseInt(spnQuantity.getValue().toString());
                            double price = Double.parseDouble(txtPrice.getText());
                            double total = Double.parseDouble(txtTotal.getText());

                            //adding values from the components to the table
                                if(counter==0){
                                    //adding values from textfields and spinner to the table row
                                    model.addRow(new Object[]{
                                        product_name,
                                        quantity,
                                        price,
                                        total
                                    });
                                    
                                    
                                    //Calculating final total
                                    finalTotal = finalTotal + total;
                                    //setting the fianl total to display in the textfield
                                    txtFinalTotal.setText(String.valueOf(finalTotal));
                                        
                                    double Sum = 0;
                                    //for loop declaration
                                    for(int i=0; i<tbSale.getRowCount(); i++){
                                        //sum calculating and adding the value at row i and column 3
                                        Sum = Sum + Double.parseDouble(tbSale.getValueAt(i, 3).toString());
                                    }
                                    
                                }else{                            
                                    JOptionPane.showMessageDialog(null, "Unsuccessful.", "Alert", JOptionPane.INFORMATION_MESSAGE);                    
                                }
                            }
                        }
                        //Getting value of quantity to be updated
                        int UpdateQuan = Quan - (int)spnQuantity.getValue();
                        
                        //executing the update of quantity
                        st.executeUpdate("UPDATE salesinformation SET Quantity = "+UpdateQuan+" WHERE Barcode = '"+txtBarcode.getText()+"'");
                        
                    }
                //catches exception if exists    
                }catch(Exception s){
                    JOptionPane.showMessageDialog(null, s, "Alert", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting values from textfield
                double Paid = Double.parseDouble(txtPaid.getText());
                double Total = Double.parseDouble(txtFinalTotal.getText());
                
                //calculating the change
                double Change = Paid - Total;
                
                //rounding off the change to two decimal places
                double roundOffChange = Math.round(Change * 100.0) / 100.0;
                
                //displaying change in textfield
                txtChange.setText(String.valueOf(roundOffChange));
                
            }
        });
        
        btnPrintBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //checking if paid textfield is empty
                if(txtPaid.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot print bill without payment.");
                }else{
                    //checking if payment is less than final total
                    if((Double.parseDouble(txtPaid.getText())<Double.parseDouble(txtFinalTotal.getText()))){
                        JOptionPane.showMessageDialog(null, "Payment insufficient.");
                    }else{
                    
                        //checking if the change was calculated
                        if(txtChange.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Cannot print bill without change calculation.");
                        }else{
                            
                            //Getting values from textfields
                            String Total = txtFinalTotal.getText();
                            String Paid = txtPaid.getText();
                            String Change = txtChange.getText();

                            DefaultTableModel model = new DefaultTableModel();
                            model = (DefaultTableModel)tbSale.getModel();

                            //settinf texts to print the bill
                            txtaDisplay.setText(txtaDisplay.getText() + "______________________________________________________\n");
                            txtaDisplay.setText(txtaDisplay.getText() + "                                              Venn POS System               \n");
                            txtaDisplay.setText(txtaDisplay.getText() + "______________________________________________________\n\n");
                            txtaDisplay.setText(txtaDisplay.getText() + "Product" + "\t\t" + "Quantity" + "\t       " + " Price" + "\n");

                            //loop declaration
                            for(int i=0; i<model.getRowCount(); i++){

                                //assigning values to variables from table model columns
                                String pro_Item = (String)model.getValueAt(i, 0);
                                int pro_Quan = (int)model.getValueAt(i, 1);
                                double pro_Price = (double)model.getValueAt(i, 3);

                                //displaying values in bill
                                txtaDisplay.setText(txtaDisplay.getText() + pro_Item + "\t\t" + pro_Quan + " \t        " + "R " +  pro_Price + "\n");

                            }

                            //printing Subtotal, Paid and Change
                            txtaDisplay.setText(txtaDisplay.getText() + "\nSubtotal \t\t" + "R " + txtFinalTotal.getText() + "\n");
                            txtaDisplay.setText(txtaDisplay.getText() + "Paid \t\t" + "R " + txtPaid.getText() + "\n");
                            txtaDisplay.setText(txtaDisplay.getText() + "Change \t\t" + "R " + txtChange.getText() + "\n");
                        }
                    }    
                }    
            }
        });
        
        btnLogout.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                //closes the freame
                frmPOS.dispose();
                //opens the frame, within the function/method
                empLogin();
            }
        });
        
}
    
    public static void main(String[] args) {
        //calling functions  
        dbConnection();
        empLogin();
    }
    
}
