/*
UMUC CMSC 495 6380
Forget Me Not
Group 8
Members: Robert Hunter Robinson
Jered Russell
Keith Tulloch

Due Date: Sunday October 7th, 2018
Revision History:
October 6th - Keith - Initial Creation
October 7th - Hunter - Package addition, and month number corrections to start at 1.
 */
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

public class UI extends JFrame{
    
    // Fonts
    private final Font fAppTitle = new Font("Serif", Font.BOLD, 40);        // app title font
    private final Font fPanelTitle = new Font("SansSerif", Font.BOLD, 30);  // panel title font
    private final Font fForm = new Font("SansSerif", Font.PLAIN, 20);       // panel title font
    
    // Drop down menus
    private final Integer[] days = new Integer[31];
    private final Integer[] years = new Integer[50];
    private final String[] months = new String[] {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
    
    // "Add a Date" panel info
    String name;
    String occasion;
    Date eventDate;
    Date reminderDate;
    String[] wishlist;
    
    // "Search for Date" panel info
    String sName;
    Date sDateFrom;
    Date sDateTo;
    JTextArea results;
    
    public UI() {
        initComponents();
        setVisible(true);
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
        setSize(1000, 700);
        setResizable(false);
        
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
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                name = addName.getText();
                occasion = addOccasion.getText();
                eventDate = new Date(eventYear.getItemAt(eventYear.getSelectedIndex()) - 1900,
                        monthToInt(eventMonth.getItemAt(eventMonth.getSelectedIndex())),
                        eventDay.getItemAt(eventDay.getSelectedIndex()));
                reminderDate = new Date(reminderYear.getItemAt(reminderYear.getSelectedIndex()) - 1900,
                        monthToInt(reminderMonth.getItemAt(reminderMonth.getSelectedIndex())),
                        reminderDay.getItemAt(reminderDay.getSelectedIndex()));
                wishlist = addWishlist.getText().split("\n");
                
                //Echo user submitted data
                System.out.println("Name: "+ name);
                System.out.println("Occasion: "+ occasion);
                System.out.println("Event Date: "+ eventDate);
                System.out.println("Reminder Date: "+ reminderDate);
                for(int i=0; i<wishlist.length; i++)
                    System.out.println("Wishlist item "+ (i+1) +": "+ wishlist[i]);
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
        submitName.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sName = searchName.getText();
                
                //Echo user submitted data
                System.out.println("Name: "+ sName);
            }
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
        submitDate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sDateFrom = new Date(fromYear.getItemAt(fromYear.getSelectedIndex()) - 1900,
                        monthToInt(fromMonth.getItemAt(fromMonth.getSelectedIndex())),
                        fromDay.getItemAt(fromDay.getSelectedIndex()));
                sDateTo = new Date(toYear.getItemAt(toYear.getSelectedIndex()) - 1900,
                        monthToInt(toMonth.getItemAt(toMonth.getSelectedIndex())),
                        toDay.getItemAt(toDay.getSelectedIndex()));
                
                //Echo user submitted data
                System.out.println("From Date: "+ sDateFrom);
                System.out.println("To Date: "+ sDateTo);
            }
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
        JLabel title = new JLabel("Alerts", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setFont(fPanelTitle);
        title.setForeground(Color.RED);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        alerts.add(title, gbc);
    }
    
    // monthToInt
    private int monthToInt(String s){
        switch(s){
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
        }
        return -1;
    }
} 