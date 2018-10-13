package forgetmenot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

/* Revisions
Rev #	Name	Date            Description
1	Keith	9/29/2018	Add title and 3 panels. Add the following labels, one per panel: “Add a Date”, “Search for Date”, and “Alerts”. 
2	Keith	9/30/2018	Add name, occasion, event date, reminder date, and wish list fields to “Add a Date” panel. Add name and date fields to “Search for Date” panel.
3	Keith	10/6/2018	Convert all date text fields into drop downs for month, day, and year. Add submit buttons to Add and Search panels. Add a Results field to Search panel
4	Hunter	10/7/2018	Package addition, and month number corrections to start at 1.
5	Jered	10/11/2018	Add functionality to “Add a Date” submit button to send data to database.txt
6	Keith	10/11/2018	Add search functionality to “Search by Name” and “Search by Date” buttons and display results in Results field
7       Keith   10/12/2018      Add next 3 upcoming alerts to Alerts tab
*/


public class UIv4 extends JFrame{
    
    // Fonts
    private final Font fAppTitle = new Font("Serif", Font.BOLD, 40);        // app title font
    private final Font fPanelTitle = new Font("SansSerif", Font.BOLD, 30);  // panel title font
    private final Font fForm = new Font("SansSerif", Font.PLAIN, 20);       // panel title font
    
    // Drop down menus
    private final Integer[] days = new Integer[31];
    private final Integer[] years = new Integer[50];
    private final String[] months = new String[] {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
    
    // Stores array representation of database.txt
    private String[][] db = new String[50][];   // can store up to 50 entries from database.txt
    private String[][] dbSort = new String[50][];   // stores a sorted version of db. Used by Alerts panel
    private int dbN = 0;                        // number of entries in db
    
    // Displays search results
    JTextArea results;
    
    // Displays next alert
    JLabel alertA = new JLabel("");
    JLabel alertB = new JLabel("");
    JLabel alertC = new JLabel("");
    
    public UIv4() {
        initComponents();
        setVisible(true);
        dbUpdate();
    }
                       
    private void initComponents(){
        
        // fill days
        for(int i=0; i<days.length; i++) days[i] = i+1;
        
        // fill years
        for(int i=0; i<years.length; i++) years[i] = 2018 + i;

        // Window
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setTitle("v1.0");
        setSize(1400, 800);
        setResizable(true);
        
        // Setup panels
        appTitlePanel();
        addDatePanel();
        searchPanel();
        alertsPanel();
    }
    
    // appTitlePanel - displays title of application
    private void appTitlePanel(){
        
        // Set gbc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        
        // Create JLabel appName
        JLabel appName = new JLabel("Forget Me Not", SwingConstants.CENTER);
        appName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        appName.setOpaque(true);
        appName.setBackground(Color.DARK_GRAY);
        appName.setFont(fAppTitle);
        appName.setForeground(Color.CYAN);
        getContentPane().add(appName, gbc);
    }
    
    // addDatePanel - add a new date to the database
    private void addDatePanel(){
        
        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        
        // addDate Panel
        JPanel addDate = new JPanel();
        addDate.setLayout(new GridBagLayout());
        addDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        addDate.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(addDate, gbc);
        
        // Panel title
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel title = new JLabel("Add a Date", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setFont(fPanelTitle);
        title.setForeground(Color.GREEN);
        addDate.add(title, gbc);
        
        gbc.weighty = 2;
        gbc.gridx = 0; gbc.gridy = 1;
        addDate.add(new JLabel("Fill in fields below, then select \"add\" button", SwingConstants.CENTER), gbc);
        
        // --------------------------- Add Date Form ---------------------------
        // set gbc
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        
        // Name
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lName = new JLabel("Name:     ");
        lName.setFont(fForm);
        addDate.add(lName, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 2;
        JTextField addName = new JTextField(10);
        addDate.add(addName, gbc);
        
        // Occasion
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lOccasion = new JLabel("Occasion:     ");
        lOccasion.setFont(fForm);
        addDate.add(lOccasion, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 3;
        JTextField addOccasion = new JTextField(10);
        addDate.add(addOccasion, gbc);
        
        // Event Date
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lEventDate = new JLabel("Event Date:     ");
        lEventDate.setFont(fForm);
        addDate.add(lEventDate, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;   // month
        JComboBox<String> eventMonth = new JComboBox<>(months);
        addDate.add(eventMonth, gbc);
        gbc.anchor = GridBagConstraints.CENTER;  // day
        JComboBox<Integer> eventDay = new JComboBox<>(days);
        addDate.add(eventDay, gbc);
        gbc.anchor = GridBagConstraints.EAST;  // year
        JComboBox<Integer> eventYear = new JComboBox<>(years);
        addDate.add(eventYear, gbc);
        
        // Reminder Date
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel lReminderDate = new JLabel("Reminder Date:     ");
        lReminderDate.setFont(fForm);
        addDate.add(lReminderDate, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;   // month
        JComboBox<String> reminderMonth = new JComboBox<>(months);
        addDate.add(reminderMonth, gbc);
         gbc.anchor = GridBagConstraints.CENTER;  // day
        JComboBox<Integer> reminderDay = new JComboBox<>(days);
        addDate.add(reminderDay, gbc);
        gbc.anchor = GridBagConstraints.EAST;  // year
        JComboBox<Integer> reminderYear = new JComboBox<>(years);
        addDate.add(reminderYear, gbc);
        
        // Wishlist
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lWishlist = new JLabel("Wish List:     ");
        lWishlist.setFont(fForm);
        addDate.add(lWishlist, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 6;
        JTextArea addWishlist = new JTextArea(4, 15);
        addDate.add(addWishlist, gbc);
        
        // Add
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;// submit button
        gbc.gridx = 0; gbc.gridy = 7;
        JButton submit = new JButton("Add Date");
        addDate.add(submit, gbc);
       
        
        // Writes Add Date entry to database.txt file -- JRR
        submit.addActionListener(e1->{
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("database.txt",true));

                bw.write(addName.getText() + "; " + addOccasion.getText()
                + "; " + eventMonth.getItemAt(eventMonth.getSelectedIndex()) 
                + " " + eventDay.getItemAt(eventDay.getSelectedIndex())
                + ", " + eventYear.getItemAt(eventYear.getSelectedIndex())
                + "; " + addWishlist.getText() + "\n");
                bw.close();
                dbUpdate();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        // place holder
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.weighty = 3;
        addDate.add(new JLabel(""), gbc);
    }    
    
    // Search for stored dates    
    private void searchPanel(){ 
    	
        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        
        // search - search for dates stored in database
        JPanel search = new JPanel();
        search.setLayout(new GridBagLayout());
        search.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        search.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(search, gbc);
        
        // Panel title
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel title = new JLabel("Search for Date", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setFont(fPanelTitle);
        title.setForeground(Color.YELLOW);
        search.add(title, gbc);
        
        gbc.weighty = 2;
        gbc.gridx = 0; gbc.gridy = 1;
        search.add(new JLabel("Use either search field below", SwingConstants.CENTER), gbc);
        
        // Search by name
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lName = new JLabel("Name:     ");
        lName.setFont(fForm);
        search.add(lName, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 2;
        JTextField searchName = new JTextField(10);
        search.add(searchName, gbc);
        gbc.anchor = GridBagConstraints.CENTER;     // search button
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 3;
        JButton submitName = new JButton("Search by Name");
        search.add(submitName, gbc);

        // Search by name action listener
        submitName.addActionListener((ActionEvent e) -> {
            
            String match = "";
            
            for(int i=0; i<dbN; i++){
                if(db[i][0].contains(searchName.getText())){
                    match += db[i][0] + " ; " + db[i][1] + " ; "
                            + db[i][2] + " ; " + db[i][3] + "\n";
                }
            }
            results.setText(match);
        });
        
        // divider
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel div = new JLabel("---------------------------------------------");
        div.setFont(fForm);
        search.add(div, gbc);
        
        // Search by date
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;   // From Date -------------------
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel lDateFrom = new JLabel("From:     ");
        lDateFrom.setFont(fForm);
        search.add(lDateFrom, gbc);
        gbc.anchor = GridBagConstraints.WEST;   // month
        gbc.gridx = 1; gbc.gridy = 5;
        JComboBox<String> fromMonth = new JComboBox<>(months);
        search.add(fromMonth, gbc);
        gbc.anchor = GridBagConstraints.CENTER;  // day
        JComboBox<Integer> fromDay = new JComboBox<>(days);
        search.add(fromDay, gbc);
        gbc.anchor = GridBagConstraints.EAST;  // year
        JComboBox<Integer> fromYear = new JComboBox<>(years);
        search.add(fromYear, gbc);
        gbc.anchor = GridBagConstraints.EAST;   // To Date ---------------------
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lDateTo = new JLabel("To:     ");
        lDateTo.setFont(fForm);
        search.add(lDateTo, gbc);
        gbc.anchor = GridBagConstraints.WEST;   // month
        gbc.gridx = 1; gbc.gridy = 6;
        JComboBox<String> toMonth = new JComboBox<>(months);
        search.add(toMonth, gbc);
        gbc.anchor = GridBagConstraints.CENTER;  // day
        JComboBox<Integer> toDay = new JComboBox<>(days);
        search.add(toDay, gbc);
        gbc.anchor = GridBagConstraints.EAST;  // year
        JComboBox<Integer> toYear = new JComboBox<>(years);
        search.add(toYear, gbc);
        gbc.anchor = GridBagConstraints.CENTER;   // Search button -------------
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 7;
        JButton submitDate = new JButton("Search by Date");
        search.add(submitDate, gbc);
        
        // Search by date action listener
        submitDate.addActionListener((ActionEvent e) -> {
            
            String match = "";
            
            for(int i=0; i<dbN; i++){
                String[] split = db[i][2].trim().replaceAll(",", "").split(" ");
                Calendar date = Calendar.getInstance();
                Calendar from = Calendar.getInstance();
                Calendar to = Calendar.getInstance();
                date.set(Integer.parseInt(split[2]), monthToInt(split[0]), Integer.parseInt(split[1]));
                from.set(fromYear.getItemAt(fromYear.getSelectedIndex()),
                        monthToInt(fromMonth.getItemAt(fromMonth.getSelectedIndex())),
                        fromDay.getItemAt(fromDay.getSelectedIndex()));
                to.set(toYear.getItemAt(toYear.getSelectedIndex()),
                        monthToInt(toMonth.getItemAt(toMonth.getSelectedIndex())),
                        toDay.getItemAt(toDay.getSelectedIndex()));
                if(date.after(from) & date.before(to)){
                    match += db[i][0] + " ; " + db[i][1] + " ; "
                            + db[i][2] + " ; " + db[i][3] + "\n";
                }
            }
            results.setText(match);
            
        });
        
        // Search Results
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 8;
        JLabel lResults = new JLabel("Results:");
        lResults.setFont(new Font("SansSerif", Font.BOLD, 24));
        search.add(lResults, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 9;
        results = new JTextArea(5, 30);
        results.setEditable(false);
        search.add(results, gbc);
        
        // place holder
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.weighty = 4;
        search.add(new JLabel(""), gbc);
    }
    
    // Alerts
    private void alertsPanel(){
        
        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 2;
        
        // alerts - displays upcoming dates
        JPanel alerts = new JPanel();
        alerts.setLayout(new GridBagLayout());
        alerts.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        alerts.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(alerts, gbc);
        
        // title
        JLabel title = new JLabel("Next Alerts", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setFont(fPanelTitle);
        title.setForeground(Color.RED);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        alerts.add(title, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0; gbc.gridy = 1;
        alerts.add(alertA, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        alerts.add(alertB, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        alerts.add(alertC, gbc);
    }
    
    // Update db[][]
    private void dbUpdate(){
        try {
            dbN = 0;
            BufferedReader br = new BufferedReader(new FileReader("database.txt"));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                db[dbN]= line.split(";");
                dbN++;
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        dbSortUpdate();
    }
    
    // Update dbSort[][]
    private void dbSortUpdate(){
        
        dbSort = db;
        
        for(int i=0; i<dbN-1; i++)
            for(int j=0; j<dbN-i-1; j++){
                String[] splitA = db[j][2].trim().replaceAll(",", "").split(" ");
                String[] splitB = db[j+1][2].trim().replaceAll(",", "").split(" ");
                Calendar dateA = Calendar.getInstance();
                Calendar dateB = Calendar.getInstance();
                dateA.set(Integer.parseInt(splitA[2]), monthToInt(splitA[0]), Integer.parseInt(splitA[1]));
                dateB.set(Integer.parseInt(splitB[2]), monthToInt(splitB[0]), Integer.parseInt(splitB[1]));
                
                if(dateA.after(dateB)){
                    String[] temp = dbSort[j];
                    dbSort[j] = dbSort[j+1];
                    dbSort[j+1] = temp;
                }
            }
        
        alertA.setText("Name: " + dbSort[0][0] + ";    Occasion: " + dbSort[0][1] +
                ";    Date: " + dbSort[0][2] + ";    Wishlist: " + dbSort[0][3]);
        alertB.setText("Name: " + dbSort[1][0] + ";    Occasion: " + dbSort[1][1] +
                ";    Date: " + dbSort[1][2] + ";    Wishlist: " + dbSort[1][3]);
        alertC.setText("Name: " + dbSort[2][0] + ";    Occasion: " + dbSort[2][1] +
                ";    Date: " + dbSort[2][2] + ";    Wishlist: " + dbSort[2][3]);
    }
    
    // monthToInt
    private int monthToInt(String s){
        switch(s){
            case "January": return 0;
            case "February": return 1;
            case "March": return 2;
            case "April": return 3;
            case "May": return 4;
            case "June": return 5;
            case "July": return 6;
            case "August": return 7;
            case "September": return 8;
            case "October": return 9;
            case "November": return 10;
            case "December": return 11;
        }
        return -1;
    }
} 