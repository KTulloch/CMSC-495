import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

public class UI extends JFrame {
    
    // Fonts
    private final Font fAppTitle = new Font("Serif", Font.BOLD, 36);        // app title font
    private final Font fPanelTitle = new Font("SansSerif", Font.BOLD, 24);  // panel title font
    private final Font fForm = new Font("SansSerif", Font.PLAIN, 18);       // panel title font
    
    String name;
    String occasion;
    Date eventDate;
    Date reminderDate;
    String[] wishlist;
    
    public UI() {
        initComponents();
        setVisible(true);
    }
                       
    private void initComponents(){

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
        gbc.weightx = 1; gbc.weighty = 0;
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
        gbc.anchor = GridBagConstraints.PAGE_START;
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
        
        // Add Date Form
        // set gbc
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        
        // name
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lName = new JLabel("Name: ");
        lName.setFont(fForm);
        addDate.add(lName, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JTextField addName = new JTextField(10);
        addDate.add(addName, gbc);
        
        // occasion
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lOccasion = new JLabel("Occasion: ");
        lOccasion.setFont(fForm);
        addDate.add(lOccasion, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        JTextField addOccasion = new JTextField(10);
        addDate.add(addOccasion, gbc);
        
        // eventDate
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lEventDate = new JLabel("Event date: ");
        lEventDate.setFont(fForm);
        addDate.add(lEventDate, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        JTextField addEventDate = new JTextField(10);
        addDate.add(addEventDate, gbc);
        
        // reminderDate
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel lReminderDate = new JLabel("Reminder date: ");
        lReminderDate.setFont(fForm);
        addDate.add(lReminderDate, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        JTextField addReminderDate = new JTextField(10);
        addDate.add(addReminderDate, gbc);
        
        // wishlist
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lWishlist = new JLabel("Wish list: ");
        lWishlist.setFont(fForm);
        addDate.add(lWishlist, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        JTextArea addWishlist = new JTextArea(4, 10);
        addDate.add(addWishlist, gbc);
        
        gbc.gridwidth = 2;// submit button
        gbc.gridx = 0; gbc.gridy = 7;
        JButton submit = new JButton("Add");
        addDate.add(submit, gbc);
        
        // place holder
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.weighty = 10;
        addDate.add(new JLabel(""), gbc);
    }
    
    // Search for stored dates    
    private void searchPanel(){
        
        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
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
        
        // title
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
        
        // search by name
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lName = new JLabel("Name: ");
        lName.setFont(fForm);
        search.add(lName, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JTextField searchName = new JTextField(10);
        search.add(searchName, gbc);
        gbc.gridwidth = 2;// search button
        gbc.gridx = 0; gbc.gridy = 3;
        JButton submitName = new JButton("Search by Name");
        search.add(submitName, gbc);
        
        // place holder
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.weighty = 10;
        search.add(new JLabel(""), gbc);
        
        // search by date
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel lDateFrom = new JLabel("From: ");
        lDateFrom.setFont(fForm);
        search.add(lDateFrom, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        JTextField searchDateFrom = new JTextField(10);
        search.add(searchDateFrom, gbc);
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lDateTo = new JLabel("To: ");
        lDateTo.setFont(fForm);
        search.add(lDateTo, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        JTextField searchDateTo = new JTextField(10);
        search.add(searchDateTo, gbc);
        gbc.gridwidth = 2;// search button
        gbc.gridx = 0; gbc.gridy = 7;
        JButton submitDate = new JButton("Search by Date");
        search.add(submitDate, gbc);
        
        // place holder
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.weighty = 10;
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
} 