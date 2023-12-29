package org.example;

import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.text.*;

import org.example.utilities.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.chart.renderer.category.BarRenderer;

public class AdminApp extends javax.swing.JFrame {

    public AdminApp(Socket socket) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        initComponents();
        customStyle();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        groupMainPanel.setVisible(false);
        reportMainPanel.setVisible(false);
        dataMainPanel.setVisible(false);
        userMainPanel.setVisible(true);
        loginHistoryMainPanel.setVisible(false);
        getContentPane().add(userMainPanel);
        this.socket = socket;
    }

    private void initComponents() {

        navbar = new javax.swing.JPanel();
        groupNavButton = new javax.swing.JButton();
        userNavButton = new javax.swing.JButton();
        dataNavButton = new javax.swing.JButton();
        reportNavButton = new javax.swing.JButton();
        reportMainPanel = new javax.swing.JPanel();
        reportTitle = new javax.swing.JLabel();
        searchReportTimeInput = new javax.swing.JTextField();
        searchUsernameInput = new javax.swing.JTextField();
        searchReportButton = new javax.swing.JButton();
        viewAllReportButton = new javax.swing.JButton();
        disableUserButton = new javax.swing.JButton();
        reportScrollPane = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        reportTitle1 = new javax.swing.JLabel();
        reportTitle2 = new javax.swing.JLabel();
        groupMainPanel = new javax.swing.JPanel();
        groupTitle = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        groupScrollPane = new javax.swing.JScrollPane();
        groupTable = new javax.swing.JTable();
        memberScrollPane = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable();
        adminScrollPanel = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();
        adminTitle = new javax.swing.JLabel();
        memberTitle = new javax.swing.JLabel();
        viewAllGroupButton = new javax.swing.JButton();
        groupTitle1 = new javax.swing.JLabel();
        dataMainPanel = new javax.swing.JPanel();
        dataTitlePanel = new javax.swing.JPanel();
        ScrollPanel = new javax.swing.JScrollPane();
        dataPanel = new javax.swing.JPanel();
        activeUserMainPanel = new javax.swing.JPanel();
        activeTitle = new javax.swing.JLabel();
        startDateInput = new javax.swing.JTextField();
        startHourInput = new javax.swing.JTextField();
        startMinInput = new javax.swing.JTextField();
        startSecInput = new javax.swing.JTextField();
        activeTitle7 = new javax.swing.JLabel();
        activeTitle8 = new javax.swing.JLabel();
        endDateInput = new javax.swing.JTextField();
        searchDateButton = new javax.swing.JButton();
        resetButton1 = new javax.swing.JButton();
        searchNameInput = new javax.swing.JTextField();
        searchSessionInput = new javax.swing.JTextField();
        activeUserScrollPane1 = new javax.swing.JScrollPane();
        activeUserTable = new javax.swing.JTable();
        sessionCountDropdown = new javax.swing.JComboBox<>();
        searchNameSessionButton = new javax.swing.JButton();
        resetButton2 = new javax.swing.JButton();
        activeTitle3 = new javax.swing.JLabel();
        activeTitle4 = new javax.swing.JLabel();
        activeTitle5 = new javax.swing.JLabel();
        activeTitle6 = new javax.swing.JLabel();
        endHourInput = new javax.swing.JTextField();
        endMinInput = new javax.swing.JTextField();
        endSecInput = new javax.swing.JTextField();
        activeTitle9 = new javax.swing.JLabel();
        activeTitle10 = new javax.swing.JLabel();
        activeUserMonthlyMainPanel = new javax.swing.JPanel();
        chartActiveUserTitle = new javax.swing.JLabel();
        yearTitle = new javax.swing.JLabel();
        searchYearInput = new javax.swing.JTextField();
        searchYearButton = new javax.swing.JButton();
        activeUserMonthlyChartPanel = new javax.swing.JPanel();
        resetButton3 = new javax.swing.JButton();
        //user manage
        userMainPanel = new javax.swing.JPanel();
        userTableMainPanel = new javax.swing.JPanel();
        userTableScrollPanel = new javax.swing.JScrollPane();
        userDetailTableScrollPanel = new javax.swing.JScrollPane();
        userScrollPanel = new javax.swing.JScrollPane();
        userTitle = new javax.swing.JLabel();
        userTitle1 = new javax.swing.JLabel();
        userTitle2 = new javax.swing.JLabel();
        userTitle3 = new javax.swing.JLabel();
        searchFullNameInput = new javax.swing.JTextField();
        searchUserNameInput = new javax.swing.JTextField();
        searchActiveDropDown = new javax.swing.JComboBox<>();
        searchUserButton = new javax.swing.JButton();
        viewAllUserButton = new javax.swing.JButton();
        addNewUserButton = new javax.swing.JButton();
        userTable = new javax.swing.JTable();
        userDetailTable = new javax.swing.JTable();
        userTitle4 = new javax.swing.JLabel();
        userTitle5 = new javax.swing.JLabel();
        userTitle6 = new javax.swing.JLabel();
        userTitle7 = new javax.swing.JLabel();
        userTitle8 = new javax.swing.JLabel();
        userTitle9 = new javax.swing.JLabel();
        userTitle10 = new javax.swing.JLabel();
        userTitle11 = new javax.swing.JLabel();
        userTitle12 = new javax.swing.JLabel();
        userTitle13 = new javax.swing.JLabel();
        userTitle14 = new javax.swing.JLabel();
        userTitle15 = new javax.swing.JLabel();
        userTitle16 = new javax.swing.JLabel();
        userNameInput = new javax.swing.JTextField();
        fullNameInput = new javax.swing.JTextField();
        pwdInput = new javax.swing.JTextField();
        addressInput = new javax.swing.JTextField();
        birthDateInput = new javax.swing.JTextField();
        genderInput = new javax.swing.JTextField();
        emailInput = new javax.swing.JTextField();
        registrationTimeInput = new javax.swing.JTextField();
        accountStatusInput = new javax.swing.JTextField();
        updateUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        disableEnableUserButton = new javax.swing.JButton();
        updatePasswordButton = new javax.swing.JButton();
        userDetailPanel = new javax.swing.JPanel();
        userPanel = new javax.swing.JPanel();
        historyLoginTable = new javax.swing.JTable();
        listFriendTable = new javax.swing.JTable();
        historyLoginTableScrollPane = new javax.swing.JScrollPane();
        listFriendTableScrollPane = new javax.swing.JScrollPane();
        passRequestTable = new javax.swing.JTable();
        passRequestTableScrollPane = new javax.swing.JScrollPane();
        passRequestPanel = new javax.swing.JPanel();
        loginHistoryMainPanel = new javax.swing.JPanel();
        loginHistoryNavButton = new javax.swing.JButton();
        loginHistoryTitle = new javax.swing.JLabel();
        loginHistoryTable = new javax.swing.JTable();
        loginHistoryTableScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        navbar.setBackground(new java.awt.Color(23, 70, 162));
        navbar.setPreferredSize(new java.awt.Dimension(307, 50));

        groupNavButton.setBackground(new java.awt.Color(23, 70, 162));
        groupNavButton.setForeground(new java.awt.Color(255, 255, 255));
        groupNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org.example//users-solid.png"))); // NOI18N
        groupNavButton.setBorderPainted(false);
        groupNavButton.setFocusable(false);
        groupNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        groupNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    groupNavButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        userNavButton.setBackground(new java.awt.Color(23, 70, 162));
        userNavButton.setForeground(new java.awt.Color(255, 255, 255));
        userNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org.example//user-solid.png"))); // NOI18N
        userNavButton.setBorderPainted(false);
        userNavButton.setFocusable(false);
        userNavButton.setPreferredSize(new java.awt.Dimension(50, 50));

        userNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    userNavButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        dataNavButton.setBackground(new java.awt.Color(23, 70, 162));
        dataNavButton.setForeground(new java.awt.Color(255, 255, 255));
        dataNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org.example//database-solid.png"))); // NOI18N
        dataNavButton.setBorderPainted(false);
        dataNavButton.setFocusable(false);
        dataNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        dataNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    dataNavButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        reportNavButton.setBackground(new java.awt.Color(23, 70, 162));
        reportNavButton.setForeground(new java.awt.Color(255, 255, 255));
        reportNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org.example//flag-solid.png"))); // NOI18N
        reportNavButton.setBorderPainted(false);
        reportNavButton.setFocusable(false);
        reportNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        reportNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    reportNavButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        loginHistoryNavButton.setBackground(new java.awt.Color(23, 70, 162));
        loginHistoryNavButton.setForeground(new java.awt.Color(255, 255, 255));
        loginHistoryNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org.example//history.png"))); // NOI18N
        loginHistoryNavButton.setBorderPainted(false);
        loginHistoryNavButton.setFocusable(false);
        loginHistoryNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        loginHistoryNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    loginHistoryNavButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
                navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(navbarLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(userNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(loginHistoryNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(groupNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(reportNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dataNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navbarLayout.setVerticalGroup(
                navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dataNavButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportNavButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupNavButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginHistoryNavButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userNavButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(navbar, java.awt.BorderLayout.PAGE_START);

        reportMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        reportTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        reportTitle.setForeground(new java.awt.Color(23, 70, 162));
        reportTitle.setText("SPAM REPORT");

        searchReportTimeInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchReportTimeInput.setText("( dd-mm-yyyy)");
        searchReportTimeInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchReportTimeInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchReportTimeInputActionPerformed(evt);
//            }
//        });

        searchUsernameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUsernameInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchUsernameInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchUsernameInputActionPerformed(evt);
//            }
//        });

        searchReportButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchReportButton.setText("Search");
        searchReportButton.setFocusable(false);
        searchReportButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchReportButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    searchReportButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        viewAllReportButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewAllReportButton.setText("View all reports");
        viewAllReportButton.setFocusable(false);
        viewAllReportButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        viewAllReportButton.setPreferredSize(new java.awt.Dimension(57, 35));
        viewAllReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    viewAllReportButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        disableUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        disableUserButton.setText("Disable account");
        disableUserButton.setFocusable(false);
        disableUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        disableUserButton.setPreferredSize(new java.awt.Dimension(57, 35));
        disableUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    disableUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        reportTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        reportTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "No", "Username", "Report time", "Account status"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTable.setRowHeight(30);
        reportTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reportTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reportTable.getTableHeader().setResizingAllowed(false);
        reportTable.getTableHeader().setReorderingAllowed(false);
        reportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTableMouseClicked(evt);
            }
        });
        reportScrollPane.setViewportView(reportTable);
        if (reportTable.getColumnModel().getColumnCount() > 0) {
            reportTable.getColumnModel().getColumn(0).setResizable(false);
            reportTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            reportTable.getColumnModel().getColumn(1).setResizable(false);
            reportTable.getColumnModel().getColumn(1).setPreferredWidth(90);
            reportTable.getColumnModel().getColumn(2).setResizable(false);
            reportTable.getColumnModel().getColumn(2).setPreferredWidth(75);
            reportTable.getColumnModel().getColumn(3).setResizable(false);
            reportTable.getColumnModel().getColumn(3).setPreferredWidth(95);
        }

        reportTitle1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        reportTitle1.setText("Date");

        reportTitle2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        reportTitle2.setText("Username");

        javax.swing.GroupLayout reportMainPanelLayout = new javax.swing.GroupLayout(reportMainPanel);
        reportMainPanel.setLayout(reportMainPanelLayout);
        reportMainPanelLayout.setHorizontalGroup(
                reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(reportTitle)
                                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(reportScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(searchReportTimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(reportTitle1))
                                                                .addGap(12, 12, 12)
                                                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(reportTitle2)
                                                                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                                                                .addComponent(searchUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(98, 98, 98)
                                                                                .addComponent(viewAllReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(29, 29, 29)
                                                .addComponent(disableUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        reportMainPanelLayout.setVerticalGroup(
                reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(reportTitle)
                                .addGap(41, 41, 41)
                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(reportTitle1)
                                        .addComponent(reportTitle2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchUsernameInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchReportTimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(viewAllReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(disableUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addComponent(reportScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(reportMainPanel, java.awt.BorderLayout.CENTER);

        groupTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        groupTitle.setText("Group name");

        searchInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchInputActionPerformed(evt);
//            }
//        });

        searchButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchButton.setText("Search");
        searchButton.setFocusable(false);
        searchButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    searchButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        groupTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        groupTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "No", "Group name", "Registration time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        groupTable.setRowHeight(30);
        groupTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        groupTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        groupTable.getTableHeader().setResizingAllowed(false);
        groupTable.getTableHeader().setReorderingAllowed(false);
        groupTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    groupTableMouseClicked(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        groupScrollPane.setViewportView(groupTable);
        if (groupTable.getColumnModel().getColumnCount() > 0) {
            groupTable.getColumnModel().getColumn(0).setResizable(false);
            groupTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            groupTable.getColumnModel().getColumn(1).setResizable(false);
            groupTable.getColumnModel().getColumn(1).setPreferredWidth(90);
            groupTable.getColumnModel().getColumn(2).setResizable(false);
            groupTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        memberScrollPane.setRowHeaderView(null);

        memberTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        memberTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Username", "Full name"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        memberTable.setRowHeight(30);
        memberScrollPane.setViewportView(memberTable);
        if (memberTable.getColumnModel().getColumnCount() > 0) {
            memberTable.getColumnModel().getColumn(0).setResizable(false);
            memberTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            memberTable.getColumnModel().getColumn(1).setResizable(false);
            memberTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        }

        adminScrollPanel.setRowHeaderView(null);

        adminTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        adminTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Username", "Full name"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        adminTable.setRowHeight(30);
        adminScrollPanel.setViewportView(adminTable);
        if (adminTable.getColumnModel().getColumnCount() > 0) {
            adminTable.getColumnModel().getColumn(0).setResizable(false);
            adminTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            adminTable.getColumnModel().getColumn(1).setResizable(false);
            adminTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        }

        adminTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        adminTitle.setForeground(new java.awt.Color(23, 70, 162));
        adminTitle.setText("LIST OF ADMIN");

        memberTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        memberTitle.setForeground(new java.awt.Color(23, 70, 162));
        memberTitle.setText("LIST OF MEMBER");

        viewAllGroupButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewAllGroupButton.setText("View all group");
        viewAllGroupButton.setFocusable(false);
        viewAllGroupButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        viewAllGroupButton.setPreferredSize(new java.awt.Dimension(57, 35));
        viewAllGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    viewAllGroupButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        groupTitle1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        groupTitle1.setForeground(new java.awt.Color(23, 70, 162));
        groupTitle1.setText("MANAGE GROUP");

        javax.swing.GroupLayout groupMainPanelLayout = new javax.swing.GroupLayout(groupMainPanel);
        groupMainPanel.setLayout(groupMainPanelLayout);
        groupMainPanelLayout.setHorizontalGroup(
                groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(groupTitle1)
                                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                                .addGroup(groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                                                .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(viewAllGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(groupTitle)
                                                        .addComponent(groupScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(85, 85, 85)
                                                .addGroup(groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                                                .addGap(75, 75, 75)
                                                                .addComponent(adminTitle)
                                                                .addGap(270, 270, 270)
                                                                .addComponent(memberTitle))
                                                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(adminScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(100, 100, 100)
                                                                .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupMainPanelLayout.setVerticalGroup(
                groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(groupTitle1)
                                .addGap(30, 30, 30)
                                .addComponent(groupTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(adminTitle)
                                                .addComponent(memberTitle))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(viewAllGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(groupScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(groupMainPanel, java.awt.BorderLayout.CENTER);

        // MANAGE USER
        userMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userMainPanel.setPreferredSize(new java.awt.Dimension(1440, 2079));
        userMainPanel.setLayout(new java.awt.BorderLayout());

        userTitle1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle1.setText("Full name");

        userTitle2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle2.setText("Username");

        userTitle3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle3.setText("Active Status");

        searchFullNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchFullNameInput.setPreferredSize(new java.awt.Dimension(135, 35));

        searchUserNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUserNameInput.setPreferredSize(new java.awt.Dimension(125, 35));

        searchActiveDropDown.setBackground(new java.awt.Color(255, 255, 254));
        searchActiveDropDown.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchActiveDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Active", "Inactive" }));
        searchActiveDropDown.setToolTipText("");
        searchActiveDropDown.setFocusable(false);
        searchActiveDropDown.setPreferredSize(new java.awt.Dimension(88, 35));

        searchUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUserButton.setText("Search");
        searchUserButton.setFocusable(false);
        searchUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchUserButton.setPreferredSize(new java.awt.Dimension(60, 35));
        searchUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    searchUserButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        userTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                },
                new String [] {
                        "No", "Username", "Full name", "Address", "Birth date", "Gender", "Email", "Registration time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setRowHeight(30);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    userTableMouseClicked(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        userTableScrollPanel.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(140);
            userTable.getColumnModel().getColumn(2).setResizable(false);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(170);
            userTable.getColumnModel().getColumn(3).setResizable(false);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(420);
            userTable.getColumnModel().getColumn(4).setResizable(false);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(105);
            userTable.getColumnModel().getColumn(5).setResizable(false);
            userTable.getColumnModel().getColumn(5).setPreferredWidth(95);
            userTable.getColumnModel().getColumn(6).setResizable(false);
            userTable.getColumnModel().getColumn(6).setPreferredWidth(215);
            userTable.getColumnModel().getColumn(7).setResizable(false);
            userTable.getColumnModel().getColumn(7).setPreferredWidth(160);
        }

        viewAllUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewAllUserButton.setText("View all user");
        viewAllUserButton.setFocusable(false);
        viewAllUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        viewAllUserButton.setPreferredSize(new java.awt.Dimension(60, 35));
        viewAllUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    viewAllUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        addNewUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addNewUserButton.setText("Add new user");
        addNewUserButton.setFocusable(false);
        addNewUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        addNewUserButton.setPreferredSize(new java.awt.Dimension(60, 35));
        addNewUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addNewUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        userTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle.setForeground(new java.awt.Color(23, 70, 162));
        userTitle.setText("MANAGE USER");

        userTitle4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle4.setForeground(new java.awt.Color(23, 70, 162));
        userTitle4.setText("USER DETAILS");

        userDetailTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userDetailTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                        "Username", "Full name", "Address", "Birth date", "Gender", "Email", "Registration time", "Account status"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, true, true, true, true, true, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        userDetailTable.setRowHeight(30);
        userDetailTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userDetailTable.getTableHeader().setResizingAllowed(false);
        userDetailTable.getTableHeader().setReorderingAllowed(false);
        userDetailTableScrollPanel.setViewportView(userDetailTable);

        if (userDetailTable.getColumnModel().getColumnCount() > 0) {
            userDetailTable.getColumnModel().getColumn(0).setResizable(false);
            userDetailTable.getColumnModel().getColumn(0).setPreferredWidth(130);
            userDetailTable.getColumnModel().getColumn(1).setResizable(false);
            userDetailTable.getColumnModel().getColumn(1).setPreferredWidth(175);
            userDetailTable.getColumnModel().getColumn(2).setResizable(false);
            userDetailTable.getColumnModel().getColumn(2).setPreferredWidth(400);
            userDetailTable.getColumnModel().getColumn(3).setResizable(false);
            userDetailTable.getColumnModel().getColumn(3).setPreferredWidth(95);
            userDetailTable.getColumnModel().getColumn(4).setResizable(false);
            userDetailTable.getColumnModel().getColumn(4).setPreferredWidth(65);
            userDetailTable.getColumnModel().getColumn(5).setResizable(false);
            userDetailTable.getColumnModel().getColumn(5).setPreferredWidth(190);
            userDetailTable.getColumnModel().getColumn(6).setResizable(false);
            userDetailTable.getColumnModel().getColumn(6).setPreferredWidth(140);
            userDetailTable.getColumnModel().getColumn(7).setResizable(false);
            userDetailTable.getColumnModel().getColumn(7).setPreferredWidth(95);
        }

        updateUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        updateUserButton.setText("Update user");
        updateUserButton.setFocusable(false);
        updateUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        updateUserButton.setPreferredSize(new java.awt.Dimension(120, 35));
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    updateUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        deleteUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        deleteUserButton.setText("Delete user");
        deleteUserButton.setFocusable(false);
        deleteUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        deleteUserButton.setPreferredSize(new java.awt.Dimension(120, 35));
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    deleteUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        disableEnableUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        disableEnableUserButton.setText("Disable / Enable user");
        disableEnableUserButton.setFocusable(false);
        disableEnableUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        disableEnableUserButton.setPreferredSize(new java.awt.Dimension(170, 35));
        disableEnableUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    disableEnableUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        historyLoginTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        historyLoginTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                },
                new String [] {
                        "No", "Login time", "Logout time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        historyLoginTable.setRowHeight(30);
        historyLoginTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        historyLoginTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        historyLoginTable.getTableHeader().setResizingAllowed(false);
        historyLoginTable.getTableHeader().setReorderingAllowed(false);

        historyLoginTableScrollPane.setViewportView(historyLoginTable);
        if (historyLoginTable.getColumnModel().getColumnCount() > 0) {
            historyLoginTable.getColumnModel().getColumn(0).setResizable(false);
            historyLoginTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            historyLoginTable.getColumnModel().getColumn(1).setResizable(false);
            historyLoginTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            historyLoginTable.getColumnModel().getColumn(2).setResizable(false);
            historyLoginTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        listFriendTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        listFriendTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                },
                new String [] {
                        "No", "Username", "Full name"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listFriendTable.setRowHeight(30);
        listFriendTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listFriendTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listFriendTable.getTableHeader().setResizingAllowed(false);
        listFriendTable.getTableHeader().setReorderingAllowed(false);
        listFriendTableScrollPane.setViewportView(listFriendTable);

        if (listFriendTable.getColumnModel().getColumnCount() > 0) {
            listFriendTable.getColumnModel().getColumn(0).setResizable(false);
            listFriendTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            listFriendTable.getColumnModel().getColumn(1).setResizable(false);
            listFriendTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            listFriendTable.getColumnModel().getColumn(2).setResizable(false);
            listFriendTable.getColumnModel().getColumn(2).setPreferredWidth(175);
        }

        userTitle15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle15.setForeground(new java.awt.Color(23, 70, 162));
        userTitle15.setText("USER LOGIN HISTORY");

        userTitle16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle16.setForeground(new java.awt.Color(23, 70, 162));
        userTitle16.setText("USER LIST OF FRIENDS");

        userTableMainPanel.setMaximumSize(new java.awt.Dimension(32767, 600));
        userTableMainPanel.setPreferredSize(new java.awt.Dimension(2276, 500));
        userTableMainPanel.setRequestFocusEnabled(false);

        userDetailPanel.setPreferredSize(new java.awt.Dimension(2276, 800));

        javax.swing.JPanel buttonContainer = new javax.swing.JPanel();
        buttonContainer.setLayout(new javax.swing.BoxLayout(buttonContainer, javax.swing.BoxLayout.X_AXIS));
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(updateUserButton);
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(deleteUserButton);
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(disableEnableUserButton);

        userTitle14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle14.setForeground(new java.awt.Color(23, 70, 162));
        userTitle14.setText("UPDATE PASSWORD REQUEST");

        passRequestTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passRequestTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "No", "Username", "New Password"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        passRequestTable.setRowHeight(30);
        passRequestTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        passRequestTable.getTableHeader().setResizingAllowed(false);
        passRequestTable.getTableHeader().setReorderingAllowed(false);
        passRequestTableScrollPane.setViewportView(passRequestTable);
        if (passRequestTable.getColumnModel().getColumnCount() > 0) {
            passRequestTable.getColumnModel().getColumn(0).setResizable(false);
            passRequestTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            passRequestTable.getColumnModel().getColumn(1).setResizable(false);
            passRequestTable.getColumnModel().getColumn(1).setPreferredWidth(140);
            passRequestTable.getColumnModel().getColumn(2).setResizable(false);
            passRequestTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        }

        passRequestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    passRequestTableMouseClicked(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout userDetailPanelLayout = new javax.swing.GroupLayout(userDetailPanel);
        userDetailPanel.setLayout(userDetailPanelLayout);

        userDetailPanelLayout.setHorizontalGroup(
                userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userDetailPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userTitle4)
                                        .addComponent(userDetailTableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(buttonContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, userDetailPanelLayout.createSequentialGroup()
                                                        .addComponent(userTitle15)
                                                        .addGap(420)
                                                        .addComponent(userTitle16))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, userDetailPanelLayout.createSequentialGroup()
                                                        .addComponent(historyLoginTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(250, 250, 250)
                                                        .addComponent(listFriendTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(userTitle14, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(passRequestTableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userDetailPanelLayout.setVerticalGroup(
                userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userDetailPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(userTitle4)
                                .addGap(30, 30, 30)
                                .addComponent(userDetailTableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonContainer)
                                .addGap(40, 40, 40)
                                .addGroup(userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userTitle15)
                                        .addComponent(userTitle16))
                                .addGap(30, 30, 30)
                                .addGroup(userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(historyLoginTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(listFriendTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(userTitle14)
                                .addGap(30, 30, 30)
                                .addComponent(passRequestTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );



        LineBorder lineBorderPassTable = new LineBorder(Color.BLUE);
        //userDetailPanel.setBorder(BorderFactory.createCompoundBorder(lineBorderPassTable, userDetailPanel.getBorder()));

        javax.swing.GroupLayout userTableMainPanelLayout = new javax.swing.GroupLayout(userTableMainPanel);
        userTableMainPanel.setLayout(userTableMainPanelLayout);
        userTableMainPanelLayout.setHorizontalGroup(
                userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userTableMainPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userTitle)
                                        .addGroup(userTableMainPanelLayout.createSequentialGroup()
                                                .addGroup(userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(userTableMainPanelLayout.createSequentialGroup()
                                                                .addComponent(searchFullNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchUserNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchActiveDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(viewAllUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(addNewUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(userTableMainPanelLayout.createSequentialGroup()
                                                                .addComponent(userTitle1)
                                                                .addGap(120, 120, 120)
                                                                .addComponent(userTitle2)
                                                                .addGap(120, 120, 120)
                                                                .addComponent(userTitle3))
                                                        .addComponent(userTableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        )
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userTableMainPanelLayout.setVerticalGroup(
                userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userTableMainPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(userTitle)
                                .addGap(30, 30, 30)
                                .addGroup(userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userTitle1)
                                        .addComponent(userTitle2)
                                        .addComponent(userTitle3))
                                .addGap(5, 5, 5)
                                .addGroup(userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchFullNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchUserNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchActiveDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(viewAllUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(addNewUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(userTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userTableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        )
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userScrollPanel.setBorder(null);
        userScrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        userScrollPanel.setMaximumSize(new java.awt.Dimension(1440, 32767));
        userScrollPanel.setPreferredSize(new java.awt.Dimension(1440, 4000));

        userPanel.setFocusable(false);
        userPanel.setMaximumSize(new java.awt.Dimension(1440, 4000));
        userPanel.setPreferredSize(new java.awt.Dimension(1440, 1400));
        userPanel.setRequestFocusEnabled(false);
        userPanel.setLayout(new javax.swing.BoxLayout(userPanel, javax.swing.BoxLayout.Y_AXIS));

        LineBorder lineBorderUserTable = new LineBorder(Color.RED);
        //userScrollPanel.setBorder(BorderFactory.createCompoundBorder(lineBorderUserTable, userScrollPanel.getBorder()));

        LineBorder lineBorderBtnTable = new LineBorder(Color.GREEN);
        //buttonContainer.setBorder(BorderFactory.createCompoundBorder(lineBorderBtnTable, buttonContainer.getBorder()));

        userPanel.add(userTableMainPanel);
        userPanel.add(userDetailPanel);

        userScrollPanel.setViewportView(userPanel);
        userMainPanel.add(userScrollPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(userMainPanel, java.awt.BorderLayout.CENTER);

        // MANAGE LOGIN HISTORY
        loginHistoryMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginHistoryMainPanel.setPreferredSize(new java.awt.Dimension(1440, 2079));
        loginHistoryMainPanel.setLayout(new java.awt.BorderLayout());

        loginHistoryTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        loginHistoryTitle.setForeground(new java.awt.Color(23, 70, 162));
        loginHistoryTitle.setText("LIST OF LOGIN HISTORIES");

        loginHistoryTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "No", "Username", "Full name", "Login time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        loginHistoryTable.setRowHeight(30);
        loginHistoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loginHistoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loginHistoryTable.getTableHeader().setResizingAllowed(false);
        loginHistoryTable.getTableHeader().setReorderingAllowed(false);

        loginHistoryTableScrollPane.setViewportView(loginHistoryTable);
        if (loginHistoryTable.getColumnModel().getColumnCount() > 0) {
            loginHistoryTable.getColumnModel().getColumn(0).setResizable(false);
            loginHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            loginHistoryTable.getColumnModel().getColumn(1).setResizable(false);
            loginHistoryTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            loginHistoryTable.getColumnModel().getColumn(2).setResizable(false);
            loginHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            loginHistoryTable.getColumnModel().getColumn(3).setResizable(false);
            loginHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        javax.swing.GroupLayout loginHistoryTableMainPanelLayout = new javax.swing.GroupLayout(loginHistoryMainPanel);
        loginHistoryMainPanel.setLayout(loginHistoryTableMainPanelLayout);
        loginHistoryTableMainPanelLayout.setHorizontalGroup(
                loginHistoryTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginHistoryTableMainPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(loginHistoryTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loginHistoryTitle)
                                        .addComponent(loginHistoryTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginHistoryTableMainPanelLayout.setVerticalGroup(
                loginHistoryTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginHistoryTableMainPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(loginHistoryTitle)
                                .addGap(30, 30, 30)
                                .addGroup(loginHistoryTableMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loginHistoryTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().add(loginHistoryMainPanel, java.awt.BorderLayout.CENTER);

        // DATA MANAGEMENT
        dataMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataMainPanel.setPreferredSize(new java.awt.Dimension(1440, 2079));
        dataMainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout dataTitlePanelLayout = new javax.swing.GroupLayout(dataTitlePanel);
        dataTitlePanel.setLayout(dataTitlePanelLayout);
        dataMainPanel.add(dataTitlePanel, java.awt.BorderLayout.PAGE_START);

        ScrollPanel.setBorder(null);
        ScrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPanel.setMaximumSize(new java.awt.Dimension(1440, 32767));
        ScrollPanel.setPreferredSize(new java.awt.Dimension(1440, 4000));

        dataPanel.setFocusable(false);
        dataPanel.setMaximumSize(new java.awt.Dimension(1440, 3000));
        dataPanel.setPreferredSize(new java.awt.Dimension(1440, 1180));
        dataPanel.setRequestFocusEnabled(false);
        dataPanel.setLayout(new javax.swing.BoxLayout(dataPanel, javax.swing.BoxLayout.Y_AXIS));

        activeUserMainPanel.setMaximumSize(new java.awt.Dimension(32767, 600));
        activeUserMainPanel.setPreferredSize(new java.awt.Dimension(2276, 600));
        activeUserMainPanel.setRequestFocusEnabled(false);

        activeTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        activeTitle.setForeground(new java.awt.Color(23, 70, 162));
        activeTitle.setText("LIST OF ACTIVE USERS");

        startDateInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startDateInput.setText("(dd-mm-yyyy)");
        startDateInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startDateInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startDateInputActionPerformed(evt);
//            }
//        });

        startHourInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startHourInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startHourInput.setText("00");
        startHourInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startHourInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startHourInputActionPerformed(evt);
//            }
//        });

        startMinInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startMinInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startMinInput.setText("00");
        startMinInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startMinInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startMinInputActionPerformed(evt);
//            }
//        });

        startSecInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startSecInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startSecInput.setText("00");
        startSecInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startSecInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startSecInputActionPerformed(evt);
//            }
//        });

        activeTitle7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeTitle7.setText(":");

        activeTitle8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        activeTitle8.setText(":");

        endDateInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endDateInput.setText("(dd-mm-yyyy)");
        endDateInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endDateInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endDateInputActionPerformed(evt);
//            }
//        });

        searchDateButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchDateButton.setText("Search");
        searchDateButton.setFocusable(false);
        searchDateButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchDateButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    searchDateButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        resetButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        resetButton1.setText("Reset");
        resetButton1.setFocusable(false);
        resetButton1.setMargin(new java.awt.Insets(2, 5, 3, 5));
        resetButton1.setPreferredSize(new java.awt.Dimension(57, 35));
        resetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    resetButton1ActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        searchNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchNameInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchNameInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchNameInputActionPerformed(evt);
//            }
//        });

        searchSessionInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchSessionInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchSessionInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchSessionInputActionPerformed(evt);
//            }
//        });

        activeUserScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 600));
        activeUserScrollPane1.setPreferredSize(new java.awt.Dimension(452, 500));

        activeUserTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        activeUserTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "No", "Username", "Full name", "Registration time", "Session count", "Chat partners count", "Group chat count"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        activeUserTable.setRowHeight(30);
        activeUserTable.setRowSelectionAllowed(false);
        activeUserTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        activeUserTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        activeUserTable.getTableHeader().setResizingAllowed(false);
        activeUserTable.getTableHeader().setReorderingAllowed(false);
        activeUserTable.setUpdateSelectionOnSort(false);
//        activeUserTable.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                activeUserTableMouseClicked(evt);
//            }
//        });
        activeUserScrollPane1.setViewportView(activeUserTable);
        if (activeUserTable.getColumnModel().getColumnCount() > 0) {
            activeUserTable.getColumnModel().getColumn(0).setResizable(false);
            activeUserTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            activeUserTable.getColumnModel().getColumn(1).setResizable(false);
            activeUserTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            activeUserTable.getColumnModel().getColumn(2).setResizable(false);
            activeUserTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            activeUserTable.getColumnModel().getColumn(3).setResizable(false);
            activeUserTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            activeUserTable.getColumnModel().getColumn(4).setResizable(false);
            activeUserTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            activeUserTable.getColumnModel().getColumn(5).setResizable(false);
            activeUserTable.getColumnModel().getColumn(5).setPreferredWidth(95);
            activeUserTable.getColumnModel().getColumn(6).setResizable(false);
            activeUserTable.getColumnModel().getColumn(6).setPreferredWidth(95);
        }

        sessionCountDropdown.setBackground(new java.awt.Color(255, 255, 254));
        sessionCountDropdown.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sessionCountDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Equal to", "Greater than", "Less than" }));
        sessionCountDropdown.setToolTipText("");
        sessionCountDropdown.setFocusable(false);
        sessionCountDropdown.setPreferredSize(new java.awt.Dimension(88, 35));

        searchNameSessionButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchNameSessionButton.setText("Search");
        searchNameSessionButton.setFocusable(false);
        searchNameSessionButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchNameSessionButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchNameSessionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameSessionButtonActionPerformed(evt);
            }
        });

        resetButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        resetButton2.setText("Reset");
        resetButton2.setFocusable(false);
        resetButton2.setMargin(new java.awt.Insets(2, 5, 3, 5));
        resetButton2.setPreferredSize(new java.awt.Dimension(57, 35));
        resetButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton2ActionPerformed(evt);
            }
        });

        activeTitle3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle3.setText("Start date");

        activeTitle4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle4.setText("End date");

        activeTitle5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle5.setText("Session count");

        activeTitle6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle6.setText("Name");

        endHourInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endHourInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endHourInput.setText("00");
        endHourInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endHourInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endHourInputActionPerformed(evt);
//            }
//        });

        endMinInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endMinInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endMinInput.setText("00");
        endMinInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endMinInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endMinInputActionPerformed(evt);
//            }
//        });

        endSecInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endSecInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endSecInput.setText("00");
        endSecInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endSecInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endSecInputActionPerformed(evt);
//            }
//        });

        activeTitle9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeTitle9.setText(":");

        activeTitle10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activeTitle10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        activeTitle10.setText(":");

        javax.swing.GroupLayout activeUserMainPanelLayout = new javax.swing.GroupLayout(activeUserMainPanel);
        activeUserMainPanel.setLayout(activeUserMainPanelLayout);
        activeUserMainPanelLayout.setHorizontalGroup(
                activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addComponent(activeTitle)
                                                .addContainerGap(1203, Short.MAX_VALUE))
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(activeTitle3)
                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                .addComponent(searchNameSessionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(resetButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                .addComponent(searchDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(resetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(endDateInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                                                                .addComponent(startDateInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                                                        .addComponent(activeTitle4))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                                .addComponent(startHourInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(activeTitle7)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(startMinInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(activeTitle8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(startSecInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                                .addComponent(endHourInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(activeTitle9)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(endMinInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(activeTitle10, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(endSecInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(activeTitle6)
                                                                        .addComponent(activeTitle5)
                                                                        .addComponent(sessionCountDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                                                .addComponent(searchSessionInput, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(searchNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(activeUserScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 251))))
        );
        activeUserMainPanelLayout.setVerticalGroup(
                activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(activeTitle)
                                .addGap(50, 50, 50)
                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addComponent(activeTitle3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(startDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(startHourInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(startMinInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(startSecInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(activeTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(activeTitle8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(activeTitle4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(endDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(endHourInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(endMinInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(endSecInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(activeTitle9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(activeTitle10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addComponent(activeTitle6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(activeTitle5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sessionCountDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchSessionInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchNameSessionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resetButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(activeUserScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(709, 709, 709))
        );

        dataPanel.add(activeUserMainPanel);

        activeUserMonthlyMainPanel.setPreferredSize(new java.awt.Dimension(2276, 500));

        chartActiveUserTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        chartActiveUserTitle.setForeground(new java.awt.Color(23, 70, 162));
        chartActiveUserTitle.setText("CHART OF MONTLY ACTIVE USERS");

        yearTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        yearTitle.setText("Year");

        searchYearInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchYearInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchYearInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchYearInputActionPerformed(evt);
//            }
//        });

        searchYearButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchYearButton.setText("Apply");
        searchYearButton.setFocusable(false);
        searchYearButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchYearButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchYearButtonActionPerformed(evt);
            }
        });

        activeUserMonthlyChartPanel.setBackground(new java.awt.Color(0, 0, 0));
        activeUserMonthlyChartPanel.setMaximumSize(new java.awt.Dimension(1000, 590));
        activeUserMonthlyChartPanel.setPreferredSize(new java.awt.Dimension(525, 590));
        activeUserMonthlyChartPanel.setLayout(new java.awt.BorderLayout());

        resetButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        resetButton3.setText("Reset");
        resetButton3.setFocusable(false);
        resetButton3.setMargin(new java.awt.Insets(2, 5, 3, 5));
        resetButton3.setPreferredSize(new java.awt.Dimension(57, 35));
        resetButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    resetButton3ActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout activeUserMonthlyMainPanelLayout = new javax.swing.GroupLayout(activeUserMonthlyMainPanel);
        activeUserMonthlyMainPanel.setLayout(activeUserMonthlyMainPanelLayout);
        activeUserMonthlyMainPanelLayout.setHorizontalGroup(
                activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                .addComponent(chartActiveUserTitle)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                                        .addComponent(searchYearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(resetButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(searchYearInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(yearTitle))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                                                .addComponent(activeUserMonthlyChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(251, 251, 251))))
        );
        activeUserMonthlyMainPanelLayout.setVerticalGroup(
                activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chartActiveUserTitle)
                                .addGap(37, 37, 37)
                                .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                .addComponent(yearTitle)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchYearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resetButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(activeUserMonthlyChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(954, Short.MAX_VALUE))
        );

        dataPanel.add(activeUserMonthlyMainPanel);

        ScrollPanel.setViewportView(dataPanel);

        dataMainPanel.add(ScrollPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(dataMainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void customStyle(){
        DefaultTableCellRenderer centerHeaderRenderer = new DefaultTableCellRenderer();

        // căn giữa
        centerHeaderRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // chỉnh màu
        centerHeaderRenderer.setForeground(Color.WHITE);
        centerHeaderRenderer.setBackground(blue);

        // chỉnh header height
        Dimension headerPreferredSize;

        groupTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = groupTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        groupTable.getTableHeader().setPreferredSize(headerPreferredSize);

        adminTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = adminTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        adminTable.getTableHeader().setPreferredSize(headerPreferredSize);

        memberTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = memberTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        memberTable.getTableHeader().setPreferredSize(headerPreferredSize);

        reportTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = reportTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        reportTable.getTableHeader().setPreferredSize(headerPreferredSize);

        activeUserTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = activeUserTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        activeUserTable.getTableHeader().setPreferredSize(headerPreferredSize);

        userTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = userTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        userTable.getTableHeader().setPreferredSize(headerPreferredSize);

        userDetailTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = userDetailTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        userDetailTable.getTableHeader().setPreferredSize(headerPreferredSize);

        listFriendTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = listFriendTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        listFriendTable.getTableHeader().setPreferredSize(headerPreferredSize);

        historyLoginTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = historyLoginTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        historyLoginTable.getTableHeader().setPreferredSize(headerPreferredSize);

        passRequestTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = passRequestTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        passRequestTable.getTableHeader().setPreferredSize(headerPreferredSize);

        loginHistoryTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
        headerPreferredSize = loginHistoryTable.getTableHeader().getPreferredSize();
        headerPreferredSize.height = 30;
        loginHistoryTable.getTableHeader().setPreferredSize(headerPreferredSize);

        // chỉnh center row
        DefaultTableCellRenderer centerDataRenderer = new DefaultTableCellRenderer();
        centerDataRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        groupTable.setDefaultRenderer(Object.class, centerDataRenderer);
        groupTable.setDefaultRenderer(Integer.class, centerDataRenderer);
        adminTable.setDefaultRenderer(Object.class, centerDataRenderer);
        adminTable.setDefaultRenderer(Integer.class, centerDataRenderer);
        memberTable.setDefaultRenderer(Object.class, centerDataRenderer);
        memberTable.setDefaultRenderer(Integer.class, centerDataRenderer);
        reportTable.setDefaultRenderer(Object.class, centerDataRenderer);
        reportTable.setDefaultRenderer(Integer.class, centerDataRenderer);
        activeUserTable.setDefaultRenderer(Object.class, centerDataRenderer);
        activeUserTable.setDefaultRenderer(Integer.class, centerDataRenderer);

        userTable.setDefaultRenderer(Object.class, centerDataRenderer);
        userTable.setDefaultRenderer(Integer.class, centerDataRenderer);
        passRequestTable.setDefaultRenderer(Object.class, centerDataRenderer);
        passRequestTable.setDefaultRenderer(Integer.class, centerDataRenderer);

        // chỉnh selection color
        groupTable.setSelectionForeground(Color.WHITE);
        groupTable.setSelectionBackground(Color.LIGHT_GRAY);
        reportTable.setSelectionForeground(Color.WHITE);
        reportTable.setSelectionBackground(Color.LIGHT_GRAY);

        userTable.setSelectionForeground(Color.WHITE);
        userTable.setSelectionBackground(Color.LIGHT_GRAY);
        passRequestTable.setSelectionForeground(Color.WHITE);
        passRequestTable.setSelectionBackground(Color.LIGHT_GRAY);

        // disable selection
        memberTable.setRowSelectionAllowed(false);
        memberTable.setColumnSelectionAllowed(false);
        memberTable.setIntercellSpacing(new Dimension(0, 0));

        adminTable.setRowSelectionAllowed(false);
        adminTable.setColumnSelectionAllowed(false);
        adminTable.setIntercellSpacing(new Dimension(0, 0));

        activeUserTable.setRowSelectionAllowed(false);
        activeUserTable.setColumnSelectionAllowed(false);
        activeUserTable.setIntercellSpacing(new Dimension(0, 0));

        loginHistoryTable.setRowSelectionAllowed(false);
        loginHistoryTable.setColumnSelectionAllowed(false);
        loginHistoryTable.setIntercellSpacing(new Dimension(0, 0));

        historyLoginTable.setRowSelectionAllowed(false);
        historyLoginTable.setColumnSelectionAllowed(false);
        historyLoginTable.setIntercellSpacing(new Dimension(0, 0));

        userDetailTable.setRowSelectionAllowed(false);
        userDetailTable.setColumnSelectionAllowed(false);
        userDetailTable.setIntercellSpacing(new Dimension(0, 0));

        listFriendTable.setRowSelectionAllowed(false);
        listFriendTable.setColumnSelectionAllowed(false);
        listFriendTable.setIntercellSpacing(new Dimension(0, 0));

        //user + login
        customButton(searchUserButton);
        customButton(viewAllUserButton);
        customButton(addNewUserButton);
        customButton(updateUserButton);
        customButton(deleteUserButton);
        customButton(disableEnableUserButton);
        customButton(updatePasswordButton);

        // group + report + active
        customButton(viewAllGroupButton);
        customButton(searchButton);
        customButton(viewAllReportButton);
        customButton(searchReportButton);
        customButton(disableUserButton);
        customButton(searchDateButton);
        customButton(resetButton1);
        customButton(searchNameSessionButton);
        customButton(resetButton2);
        customButton(searchYearButton);
        customButton(resetButton3);

    }

    private void customButton(JButton button){
        button.setBackground(blue);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change background color on hover
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                button.setBackground(light_blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                button.setBackground(blue);
            }
        });
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        String text = searchInput.getText();
        System.out.println(text);

        if (text.equals("") ){
            JOptionPane.showMessageDialog(null, "You have not entered anything to search", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
            model.setRowCount(0);

            Object[][] data = SearchGroupName.request(text, socket);
            for (Object[] row : data) {
                model.addRow(row);
            }

            DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
            adminModel.setRowCount(0);

            DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
            memberModel.setRowCount(0);
        }
    }

    private void groupNavButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        searchInput.setText("");

        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        adminModel.setRowCount(0);

        DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
        memberModel.setRowCount(0);

        DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllGroup.request(socket);

        for (Object[] row : data) {
            model.addRow(row);
        }

//        if (socket.isClosed())
//            System.out.println("2");

        // sort
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        groupTable.setRowSorter(sorter);

        Comparator<Integer> integerComparator = Comparator.comparing(Integer::valueOf);
        sorter.setComparator(0, integerComparator);
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(0, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(1, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(2, SortOrder.ASCENDING)
                )
        );

        sorter = new TableRowSorter<DefaultTableModel>(model) {
            @Override
            public boolean isSortable(int column) {
                return (column == 0) || (column == 1) || (column == 2) ;
            }
        };
        groupTable.setRowSorter(sorter);



        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        userMainPanel.setVisible(false);
        getContentPane().remove(userMainPanel);
        loginHistoryMainPanel.setVisible(false);
        getContentPane().remove(loginHistoryMainPanel);
        getContentPane().add(groupMainPanel);
        groupMainPanel.setVisible(true);
    }

    private void userNavButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        searchFullNameInput.setText("");
        searchUserNameInput.setText("");
        searchActiveDropDown.setSelectedItem("All");

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllUser.request(socket);
        for (Object[] row : data) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        for (Object[] row : data) {
            model.addRow(row);
        }

        // sort
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        userTable.setRowSorter(sorter);

        Comparator<Integer> integerComparator = Comparator.comparing(Integer::valueOf);
        sorter.setComparator(0, integerComparator);

        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(0, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(1, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(2, SortOrder.ASCENDING)
                )
        );

        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(7, SortOrder.ASCENDING)
                )
        );

        sorter = new TableRowSorter<DefaultTableModel>(model) {
            @Override
            public boolean isSortable(int column) {
                return (column == 0) || (column == 1) || (column == 2) || (column == 7);
            }
        };
        userTable.setRowSorter(sorter);

        // chỉnh center
        ((DefaultTableCellRenderer)userTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)userTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        userTable.setPreferredScrollableViewportSize(userTable.getPreferredSize());

        DefaultTableModel passModel = (DefaultTableModel) passRequestTable.getModel();
        passModel.setRowCount(0);

        Object[][] pass = GetAllPassRequest.request(socket);
        for (Object[] row : pass) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        for (Object[] row : pass) {
            passModel.addRow(row);
        }

        ((DefaultTableCellRenderer)passRequestTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)passRequestTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        passRequestTable.setPreferredScrollableViewportSize(passRequestTable.getPreferredSize());

        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        loginHistoryMainPanel.setVisible(false);
        getContentPane().remove(loginHistoryMainPanel);
        getContentPane().add(userMainPanel);
        userMainPanel.setVisible(true);
    }

    private void loginHistoryNavButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) loginHistoryTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllLoginHistory.request(socket);

        for (Object[] row : data) {
            model.addRow(row);
        }

        ((DefaultTableCellRenderer)loginHistoryTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)loginHistoryTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        loginHistoryTable.setPreferredScrollableViewportSize(loginHistoryTable.getPreferredSize());

        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        userMainPanel.setVisible(false);
        getContentPane().remove(userMainPanel);
        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        getContentPane().add(loginHistoryMainPanel);
        loginHistoryMainPanel.setVisible(true);
    }

    private void searchUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        String username = searchUserNameInput.getText();
        String fullname = searchFullNameInput.getText();
        Object selectedValue = searchActiveDropDown.getSelectedItem();

        if ((username.isEmpty()) && (fullname.isEmpty()) && selectedValue == null) {
            JOptionPane.showMessageDialog(null, "Empty username and fullname and active status", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);

            String selectedString = "";
            selectedString = selectedValue.toString();

            Object[][] data = SearchUser.request(username, fullname, selectedString, socket);
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "No user found", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Object[] row : data) {
                    model.addRow(row);
                }
            }
        }
    }

    private void dataNavButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        startDateInput.setText("(dd-mm-yyyy)");
        endDateInput.setText("(dd-mm-yyyy)");

        startHourInput.setText("00");
        startMinInput.setText("00");
        startSecInput.setText("00");

        endHourInput.setText("00");
        endMinInput.setText("00");
        endSecInput.setText("00");

        searchNameInput.setText("");
        searchSessionInput.setText("");
        sessionCountDropdown.setSelectedItem("Equal to");

        DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
        model.setRowCount(0);

        currentActiveUserList = GetAllActiveUser.request(socket);
        for (Object[] row : currentActiveUserList) {
            model.addRow(row);
        }

        // sort
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        activeUserTable.setRowSorter(sorter);

        Comparator<Integer> integerComparator = Comparator.comparing(Integer::valueOf);
        sorter.setComparator(0, integerComparator);
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(0, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(2, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(3, SortOrder.ASCENDING)
                )
        );

        sorter = new TableRowSorter<DefaultTableModel>(model) {
            @Override
            public boolean isSortable(int column) {
                return (column == 0) || (column == 3) || (column == 2) ;
            }
        };
        activeUserTable.setRowSorter(sorter);

        ((DefaultTableCellRenderer)activeUserTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)activeUserTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        activeUserTable.setPreferredScrollableViewportSize(activeUserTable.getPreferredSize());

        Date date = new Date();
        searchYearInput.setText(Integer.toString(date.getYear() + 1900));
        createActiveUsersChart("2023");

        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        userMainPanel.setVisible(false);
        getContentPane().remove(userMainPanel);
        loginHistoryMainPanel.setVisible(false);
        getContentPane().remove(loginHistoryMainPanel);
        getContentPane().add(dataMainPanel);
        dataMainPanel.setVisible(true);

    }

    private void reportNavButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        searchReportTimeInput.setText("(dd-mm-yyyy)");
        searchUsernameInput.setText("");

        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllReport.request(socket);

        for (Object[] row : data) {
            model.addRow(row);
        }

        // sort
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        reportTable.setRowSorter(sorter);

        Comparator<Integer> integerComparator = Comparator.comparing(Integer::valueOf);
        sorter.setComparator(0, integerComparator);
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(0, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(1, SortOrder.ASCENDING)
                )
        );
        sorter.setSortKeys(
                java.util.Collections.singletonList(
                        new javax.swing.RowSorter.SortKey(2, SortOrder.ASCENDING)
                )
        );

        sorter = new TableRowSorter<DefaultTableModel>(model) {
            @Override
            public boolean isSortable(int column) {
                return (column == 0) || (column == 1) || (column == 2) ;
            }
        };
        reportTable.setRowSorter(sorter);

        // chỉnh center
        ((DefaultTableCellRenderer)reportTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)reportTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        groupTable.setPreferredScrollableViewportSize(reportTable.getPreferredSize());

        disableUserButton.setVisible(false);

        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        userMainPanel.setVisible(false);
        getContentPane().remove(userMainPanel);
        loginHistoryMainPanel.setVisible(false);
        getContentPane().remove(loginHistoryMainPanel);
        getContentPane().add(reportMainPanel);
        reportMainPanel.setVisible(true);
    }

    private void groupTableMouseClicked(java.awt.event.MouseEvent evt) throws IOException, ClassNotFoundException {
        int index = groupTable.getSelectedRow();
        TableModel model = groupTable.getModel();
        int selected_id = Integer.parseInt(model.getValueAt(index, 0).toString());

        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        adminModel.setRowCount(0);

//        System.out.println("*");
//        if (socket.isClosed())
//            System.out.println("Close");
        Object[][] admin = GetAllAdmin.request(Integer.toString(selected_id), socket);
        for (Object[] row : admin) {
            adminModel.addRow(row);
        }

        ((DefaultTableCellRenderer)adminTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        adminTable.setPreferredScrollableViewportSize(adminTable.getPreferredSize());

        DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
        memberModel.setRowCount(0);

        Object[][] member = GetAllMember.request(Integer.toString(selected_id), socket);
        for (Object[] row : member) {
            memberModel.addRow(row);
        }

        ((DefaultTableCellRenderer)memberTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        memberTable.setPreferredScrollableViewportSize(memberTable.getPreferredSize());
    }

    private void viewAllGroupButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllGroup.request(socket);
        for (Object[] row : data) {
            model.addRow(row);
        }

        searchInput.setText("");

        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        adminModel.setRowCount(0);

        DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
        memberModel.setRowCount(0);
    }

    private void viewAllUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllUser.request(socket);
        for (Object[] row : data) {
            model.addRow(row);
        }

        searchFullNameInput.setText("");
        searchUserNameInput.setText("");
        searchActiveDropDown.setSelectedItem("All");
    }

    private void addNewUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        JTextField usernameField = new JTextField();
        JTextField fullnameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField birthdateField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Username:", usernameField,
                "Fullname:", fullnameField,
                "Password:", passwordField,
                "Birthdate (DD-MM-YYYY):", birthdateField,
                "Gender:", genderField,
                "Address:", addressField,
                "Email:", emailField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Add New User", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String fullname = fullnameField.getText();
            String password = passwordField.getText();
            String birthdate = birthdateField.getText();
            String gender = genderField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            Boolean success = AddNewUser.request(username, password, fullname, address, birthdate, gender, email, socket);
            if (success) {
                JOptionPane.showMessageDialog(null, "User added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add user.");
            }
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);

            Object[][] data = GetAllUser.request(socket);
            for (Object[] row : data) {
                model.addRow(row);
            }
        }
    }

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) throws IOException, ClassNotFoundException {
        userPanel.revalidate();
        userPanel.repaint();
        DefaultTableModel userDetailModel = (DefaultTableModel) userDetailTable.getModel();
        userDetailModel.setRowCount(0);
        int index = userTable.getSelectedRow();
        TableModel model = userTable.getModel();
        String username = model.getValueAt(index, 1).toString();
        Object[][] user = GetUserByUsername.request(username, socket);

        for (Object[] row : user) {
            userDetailModel.addRow(row);
        }

        JTableHeader headerDetail = userDetailTable.getTableHeader();
        headerDetail.setBackground(blue);
        headerDetail.setForeground(Color.WHITE);

        ((DefaultTableCellRenderer)userDetailTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        userDetailTable.setPreferredScrollableViewportSize(userDetailTable.getPreferredSize());

        originalEmail = model.getValueAt(0, 5).toString();

        DefaultTableModel historyModel = (DefaultTableModel) historyLoginTable.getModel();
        historyModel.setRowCount(0);

        Object[][] history = GetUserHistoryLogin.request(username, socket);
        for (Object[] row : history) {
            historyModel.addRow(row);
        }

        JTableHeader headerHistory = historyLoginTable.getTableHeader();
        headerHistory.setBackground(blue);
        headerHistory.setForeground(Color.WHITE);

        ((DefaultTableCellRenderer)historyLoginTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)historyLoginTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        historyLoginTable.setPreferredScrollableViewportSize(historyLoginTable.getPreferredSize());

        DefaultTableModel friendModel = (DefaultTableModel) listFriendTable.getModel();
        friendModel.setRowCount(0);

        Object[][] friend = GetUserFriends.request(username, socket);
        if (friend.length == 0 && friend[0][2].equals("No friends found")) {
            friendModel.addRow(new Object[]{"", "", "No friends found"});
        } else {
            for (Object[] row : friend) {
                friendModel.addRow(row);
            }
        }

        JTableHeader headerFriend = listFriendTable.getTableHeader();
        headerFriend.setBackground(blue);
        headerFriend.setForeground(Color.WHITE);

        ((DefaultTableCellRenderer)listFriendTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)listFriendTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        listFriendTable.setPreferredScrollableViewportSize(listFriendTable.getPreferredSize());
    }

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
        String username = model.getValueAt(0, 0).toString();
        String fullname = model.getValueAt(0, 1).toString();
        String address = model.getValueAt(0, 2).toString();
        String birthdate = model.getValueAt(0, 3).toString();
        String gender = model.getValueAt(0, 4).toString();
        String email = model.getValueAt(0, 5).toString();

        if(email.equals(originalEmail)){
            Boolean success = UpdateUser.request(username, fullname, address, birthdate, gender, email, socket);
            if (success) {
                JOptionPane.showMessageDialog(null, "Update successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user.");
            }

            model.setRowCount(0);
            Object[][] user = GetUserByUsername.request(username, socket);

            for (Object[] row : user) {
                model.addRow(row);
            }
            DefaultTableModel model1 = (DefaultTableModel) userTable.getModel();
            model1.setRowCount(0);

            Object[][] data = GetAllUser.request(socket);
            for (Object[] row : data) {
                model1.addRow(row);
            }
        }
        else{
            Boolean check = false;
            Object[][] emails = GetAllEmail.request(username, socket);
            for (Object[] row : emails) {
                System.out.println(row[0].toString());
                if(email.equals(row[0])){
                    check = true;
                    break;
                }
            }
            if(check){
                JOptionPane.showMessageDialog(null, "Failed to update user. Email already exists.");
            }
            else{
                Boolean success = UpdateUser.request(username, fullname, address, birthdate, gender, email, socket);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Update successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update user.");
                }

            }
            model.setRowCount(0);
            Object[][] user = GetUserByUsername.request(username, socket);

            for (Object[] row : user) {
                model.addRow(row);
            }
            DefaultTableModel model1 = (DefaultTableModel) userTable.getModel();
            model1.setRowCount(0);

            Object[][] data = GetAllUser.request(socket);
            for (Object[] row : data) {
                model1.addRow(row);
            }
        }
    }

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
            String username = model.getValueAt(0, 0).toString();
            Boolean success = DeleteUser.request(username, socket);
            if (success) {
                JOptionPane.showMessageDialog(null, "Delete successfully.");
                DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
                userModel.setRowCount(0);

                Object[][] data = GetAllUser.request(socket);
                for (Object[] row : data) {
                    userModel.addRow(row);
                }
                DefaultTableModel userDetailModel = (DefaultTableModel) userDetailTable.getModel();
                userDetailModel.setRowCount(0);

                DefaultTableModel historyModel = (DefaultTableModel) historyLoginTable.getModel();
                historyModel.setRowCount(0);

                DefaultTableModel friendModel = (DefaultTableModel) listFriendTable.getModel();
                friendModel.setRowCount(0);

//                userPanel.revalidate();
//                userPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete user.");
            }
        }
    }

    private void disableEnableUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
        String is_locked = model.getValueAt(0, 7).toString();
        int dialogResult;
        if(is_locked.equals("Disabled")){
            dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to enable this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION){
                String username = model.getValueAt(0, 0).toString();
                Boolean success = EnableUser.request(username, socket);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Enable successfully.");
                    DefaultTableModel userModel = (DefaultTableModel) userDetailTable.getModel();
                    userModel.setRowCount(0);
                    Object[][] user = GetUserByUsername.request(username, socket);

                    for (Object[] row : user) {
                        userModel.addRow(row);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to enable user.");
                }
            }
        }
        else{
            dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to disable this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION){
                String username = model.getValueAt(0, 0).toString();
                Boolean success = DisableUserManage.request(username, socket);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Disable successfully.");
                    DefaultTableModel userModel = (DefaultTableModel) userDetailTable.getModel();
                    userModel.setRowCount(0);
                    Object[][] user = GetUserByUsername.request(username, socket);

                    for (Object[] row : user) {
                        userModel.addRow(row);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to disable user.");
                }
            }
        }

    }

    private void passRequestTableMouseClicked(java.awt.event.MouseEvent evt) throws IOException, ClassNotFoundException {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to update this user password?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel) passRequestTable.getModel();
            int index = passRequestTable.getSelectedRow();
            String username = model.getValueAt(index, 1).toString();
            String new_pwd = model.getValueAt(index, 2).toString();
            Boolean success = UpdatePassUser.request(username, new_pwd, socket);
            if (success) {
                JOptionPane.showMessageDialog(null, "Update password successfully.");

                Object[][] pass = GetAllPassRequest.request(socket);
                for (Object[] row : pass) {
                    for (Object element : row) {
                        System.out.print(element + " ");
                    }
                    System.out.println();
                }
                model.setRowCount(0);
                for (Object[] row : pass) {
                    model.addRow(row);
                }

                DefaultTableModel detailModel = (DefaultTableModel) userDetailTable.getModel();
                String userName = detailModel.getValueAt(0, 0).toString();
                if(userName.equals(username)){
                    detailModel.setRowCount(0);
                    Object[][] user = GetUserByUsername.request(username, socket);

                    for (Object[] row : user) {
                        detailModel.addRow(row);
                    }
                }

                userDetailPanel.revalidate();
                userDetailPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user password.");
            }

        }
    }

    private void reportTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = reportTable.getSelectedRow();
        TableModel model = reportTable.getModel();

        String status = model.getValueAt(index, 3).toString();

        disableUserButton.setVisible(status.equals("Enabled"));

    }

    private void viewAllReportButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllReport.request(socket);
        for (Object[] row : data) {
            model.addRow(row);
        }

        searchReportTimeInput.setText("(dd-mm-yyyy)");
        searchUsernameInput.setText("");

    }

    private void searchReportButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        String username = searchUsernameInput.getText();
        String date = searchReportTimeInput.getText();

        if ((username.isEmpty()) && (date.isEmpty() || date.equals("(dd-mm-yyyy)"))){
            JOptionPane.showMessageDialog(null, "Empty username and date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!isValidDate(date) && (!date.equals("(dd-mm-yyyy)") && !date.isEmpty())){
            JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
            model.setRowCount(0);

            Object[][] data = SearchReport.request(username, date, socket);
            for (Object[] row : data) {
                model.addRow(row);
            }
        }
    }

    private void disableUserButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        int index = reportTable.getSelectedRow();
        TableModel model = reportTable.getModel();
        String selected_username = model.getValueAt(index, 1).toString();

        Object[][] data = DisableUser.request(selected_username, socket);

        DefaultTableModel reportModel = (DefaultTableModel) reportTable.getModel();
        reportModel.setRowCount(0);

        for (Object[] row : data) {
            reportModel.addRow(row);
        }

        disableUserButton.setVisible(false);
    }

    private void searchDateButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        String start_date = startDateInput.getText();
        String start_hour = startHourInput.getText();
        String start_min = startMinInput.getText();
        String start_sec = startSecInput.getText();

        String end_date = endDateInput.getText();
        String end_hour = endHourInput.getText();
        String end_min = endMinInput.getText();
        String end_sec = endSecInput.getText();


        if ((!start_date.equals("(dd-mm-yyyy)") && !start_date.isEmpty()) && !isValidDate(start_date)){
            JOptionPane.showMessageDialog(null, "Invalid start date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ((!end_date.equals("(dd-mm-yyyy)") && !end_date.isEmpty()) && !isValidDate(end_date)){
            JOptionPane.showMessageDialog(null, "Invalid end date", "Error", JOptionPane.ERROR_MESSAGE);
        } else{
            if (start_date.equals("(dd-mm-yyyy)") || start_date.isEmpty() ){
                start_date = "01-01-1990";
                startDateInput.setText(start_date);
            }

            if (end_date.equals("(dd-mm-yyyy)") || end_date.isEmpty()){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                end_date = sdf.format(date);
                endDateInput.setText(end_date);
            }

            String startHour = formatTime(start_hour, "hour");
            String startMin = formatTime(start_min, "min");
            String startSec = formatTime(start_sec, "sec");

            String endHour = formatTime(end_hour, "hour");
            String endMin = formatTime(end_min, "min");
            String endSec = formatTime(end_sec, "sec");

            startHourInput.setText(startHour);
            startMinInput.setText(startMin);
            startSecInput.setText(startSec);

            endHourInput.setText(endHour);
            endMinInput.setText(endMin);
            endSecInput.setText(endSec);

            searchNameInput.setText("");
            searchSessionInput.setText("");
            sessionCountDropdown.setSelectedItem("Equal to");


            String[] startDateArray = start_date.split("-");
            String[] endDateArray = end_date.split("-");

            start_date = startDateArray[2] + "-" + startDateArray[1] + "-" + startDateArray[0] + " " + startHour + ":" + startMin + ":" + startSec;
            end_date = endDateArray[2] + "-" + endDateArray[1] + "-" + endDateArray[0] + " " + endHour + ":" + endMin + ":" + endSec;

//            String name = searchNameInput.getText();
//            String session = searchSessionInput.getText();
//            String option = sessionCountDropdown.getSelectedItem().toString();

//            if (!session.isEmpty()){
//                try {
//                    Integer.parseInt(session);

            DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
            model.setRowCount(0);

            currentActiveUserList = SearchActiveUser.request( start_date, end_date, socket);
            for (Object[] row : currentActiveUserList) {
                model.addRow(row);
            }
//                } catch (NumberFormatException e) {
//                    if (!searchYearInput.getText().isEmpty())
//                        JOptionPane.showMessageDialog(null, "Invalid session count", "Error", JOptionPane.ERROR_MESSAGE);
//                    e.printStackTrace();
//                }
//            }

        }
    }

    private void searchNameSessionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = searchNameInput.getText();
        String session = searchSessionInput.getText();
        String option = sessionCountDropdown.getSelectedItem().toString();


        if (session.isEmpty() && name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty name and session count", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String start_date = startDateInput.getText();
            String end_date = endDateInput.getText();

            if (start_date.equals("(dd-mm-yyyy)") || start_date.isEmpty() ){
                start_date = "01-01-1990";
                startDateInput.setText(start_date);
            }

            if (end_date.equals("(dd-mm-yyyy)") || end_date.isEmpty()){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                end_date = sdf.format(date);
                endDateInput.setText(end_date);
            }

//            int rows = currentActiveUserList.length;
//            int columns = currentActiveUserList[0].length;

            ArrayList<Object[]> tmp = new ArrayList<>();
            for (Object[] row : currentActiveUserList) {
                tmp.add(row);
            }

            try {
                if (!session.isEmpty()){
                    int sessionCount = Integer.parseInt(session);
                    for (int i = 0; i < tmp.size(); i++) {
                        switch (option){
                            case "Equal to":
                                if (!tmp.get(i)[4].toString().equals(Integer.toString(sessionCount)))
                                    tmp.set(i, null);
                                break;
                            case "Greater than":
                                if (Integer.parseInt(tmp.get(i)[4].toString()) <= sessionCount)
                                    tmp.set(i, null);
                                break;
                            case "Less than":
                                if (Integer.parseInt(tmp.get(i)[4].toString()) >= sessionCount)
                                    tmp.set(i, null);
                                break;
                        }
//                        System.out.println(row[4].toString());
                    }
                }

                if (!name.isEmpty()){
                    System.out.println(name);
                    for (int i = 0; i < tmp.size(); i++) {
//                        System.out.println(tmp.get(i)[2].toString().toLowerCase().contains(name.toLowerCase()));
                        if (!Arrays.equals(tmp.get(i), null) && !tmp.get(i)[2].toString().toLowerCase().contains(name.toLowerCase())){

                            tmp.set(i, null);
                        }
                    }
                }

                DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
                model.setRowCount(0);

                for (Object[] row : tmp) {
                    if (!Arrays.equals(row, null))
                        model.addRow(row);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid session count", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void resetButton1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        startDateInput.setText("(dd-mm-yyyy)");
        endDateInput.setText("(dd-mm-yyyy)");

        startHourInput.setText("00");
        startMinInput.setText("00");
        startSecInput.setText("00");

        endHourInput.setText("00");
        endMinInput.setText("00");
        endSecInput.setText("00");

        searchNameInput.setText("");
        searchSessionInput.setText("");
        sessionCountDropdown.setSelectedItem("Equal to");

        DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
        model.setRowCount(0);

        Object[][] data = GetAllActiveUser.request(socket);
        for (Object[] row : data) {
            model.addRow(row);
        }

    }

    private void resetButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        searchNameInput.setText("");
        searchSessionInput.setText("");
        sessionCountDropdown.setSelectedItem("Equal to");

        DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
        model.setRowCount(0);

        for (Object[] row : currentActiveUserList) {
            model.addRow(row);
        }
    }

    private void searchYearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String text = searchYearInput.getText();
            if (text.isEmpty())
                JOptionPane.showMessageDialog(null, "Empty year", "Error", JOptionPane.ERROR_MESSAGE);
            int year = Integer.parseInt(text);

            if (1990 <= year && year <= 2023){
                activeUserMonthlyChartPanel.removeAll();
                activeUserMonthlyChartPanel.revalidate();
                activeUserMonthlyChartPanel.repaint();
                createActiveUsersChart(Integer.toString(year));
            } else{
                JOptionPane.showMessageDialog(null, "Year must be between 1990 and 2023", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | IOException | ClassNotFoundException e) {
            if (!searchYearInput.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Invalid year", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void resetButton3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        Date date = new Date();
        searchYearInput.setText(Integer.toString(date.getYear() + 1900));

        activeUserMonthlyChartPanel.removeAll();
        activeUserMonthlyChartPanel.revalidate();
        activeUserMonthlyChartPanel.repaint();
        createActiveUsersChart("2023");
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String formatTime(String time, String component){
        if (!time.isEmpty()){
            try {
                int num = Integer.parseInt(time);

                if ((component.equals("hour") && (0 <= num && num <= 23)) ||
                        ((component.equals("min")) && (0 <= num && num <= 59)) ||
                        ((component.equals("sec")) && (0 <= num && num <= 59))){
                    return time;
                } else{
                    return "00";
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid " + component, "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        else{
            return "00";
        }
        return "";
    }

    public void createActiveUsersChart(String year) throws IOException, ClassNotFoundException {
        String[] monthNames = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int[] data = GetMonthlyActiveUser.request(year, socket);
        for (int i = 0; i < 12; i++){
            System.out.println(data[i]);
            dataset.addValue(data[i], "Active users", monthNames[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly active users", // Chart title
                "Month", // X-axis label
                "Numbers of active users", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(200, 400));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Get the renderer of the plot
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Set the color for each bar
        for (int i = 0; i < 12; i++) {
            renderer.setSeriesPaint(i, Color.blue); // Set the color to blue
        }

        activeUserMonthlyChartPanel.add(chartPanel, BorderLayout.NORTH);
    }

    private Socket socket;
    private Object[][] currentActiveUserList;
    Color blue = new Color (23, 70, 162);
    Color light_blue = new Color(92, 124, 208);
    private String originalEmail;
    // Variables declaration - do not modify
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JLabel activeTitle;
    private javax.swing.JLabel activeTitle10;
    private javax.swing.JLabel activeTitle3;
    private javax.swing.JLabel activeTitle4;
    private javax.swing.JLabel activeTitle5;
    private javax.swing.JLabel activeTitle6;
    private javax.swing.JLabel activeTitle7;
    private javax.swing.JLabel activeTitle8;
    private javax.swing.JLabel activeTitle9;
    private javax.swing.JPanel activeUserMainPanel;
    private javax.swing.JPanel activeUserMonthlyChartPanel;
    private javax.swing.JPanel activeUserMonthlyMainPanel;
    private javax.swing.JScrollPane activeUserScrollPane1;
    private javax.swing.JTable activeUserTable;
    private javax.swing.JScrollPane adminScrollPanel;
    private javax.swing.JTable adminTable;
    private javax.swing.JLabel adminTitle;
    private javax.swing.JLabel chartActiveUserTitle;
    private javax.swing.JPanel dataMainPanel;
    private javax.swing.JButton dataNavButton;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel dataTitlePanel;
    private javax.swing.JButton disableUserButton;
    private javax.swing.JTextField endDateInput;
    private javax.swing.JTextField endHourInput;
    private javax.swing.JTextField endMinInput;
    private javax.swing.JTextField endSecInput;
    private javax.swing.JPanel groupMainPanel;
    private javax.swing.JButton groupNavButton;
    private javax.swing.JScrollPane groupScrollPane;
    private javax.swing.JTable groupTable;
    private javax.swing.JLabel groupTitle;
    private javax.swing.JLabel groupTitle1;
    private javax.swing.JScrollPane memberScrollPane;
    private javax.swing.JTable memberTable;
    private javax.swing.JLabel memberTitle;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel reportMainPanel;
    private javax.swing.JButton reportNavButton;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JTable reportTable;
    private javax.swing.JLabel reportTitle;
    private javax.swing.JLabel reportTitle1;
    private javax.swing.JLabel reportTitle2;
    private javax.swing.JButton resetButton1;
    private javax.swing.JButton resetButton2;
    private javax.swing.JButton resetButton3;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton searchDateButton;
    private javax.swing.JTextField searchInput;
    private javax.swing.JTextField searchNameInput;
    private javax.swing.JButton searchNameSessionButton;
    private javax.swing.JButton searchReportButton;
    private javax.swing.JTextField searchReportTimeInput;
    private javax.swing.JTextField searchSessionInput;
    private javax.swing.JTextField searchUsernameInput;
    private javax.swing.JButton searchYearButton;
    private javax.swing.JTextField searchYearInput;
    private javax.swing.JComboBox<String> sessionCountDropdown;
    private javax.swing.JTextField startDateInput;
    private javax.swing.JTextField startHourInput;
    private javax.swing.JTextField startMinInput;
    private javax.swing.JTextField startSecInput;
//    private javax.swing.JLabel statisticTitle;
    private javax.swing.JButton userNavButton;
    private javax.swing.JButton viewAllGroupButton;
    private javax.swing.JButton viewAllReportButton;
    private javax.swing.JLabel yearTitle;
    private javax.swing.JPanel userMainPanel;
    private javax.swing.JPanel userTableMainPanel;
    private javax.swing.JTextField searchFullNameInput;
    private javax.swing.JComboBox<String> searchActiveDropDown;
    private javax.swing.JTextField searchUserNameInput;
    private javax.swing.JTable userTable;
    private javax.swing.JTable userDetailTable;
    private javax.swing.JLabel userTitle;
    private javax.swing.JLabel userTitle1;
    private javax.swing.JLabel userTitle2;
    private javax.swing.JLabel userTitle3;
    private javax.swing.JLabel userTitle4;
    private javax.swing.JLabel userTitle5;
    private javax.swing.JLabel userTitle6;
    private javax.swing.JLabel userTitle7;
    private javax.swing.JLabel userTitle8;
    private javax.swing.JLabel userTitle9;
    private javax.swing.JLabel userTitle10;
    private javax.swing.JLabel userTitle11;
    private javax.swing.JLabel userTitle12;
    private javax.swing.JLabel userTitle13;
    private javax.swing.JLabel userTitle14;
    private javax.swing.JLabel userTitle15;
    private javax.swing.JLabel userTitle16;
    private javax.swing.JTextField userNameInput;
    private javax.swing.JTextField fullNameInput;
    private javax.swing.JTextField pwdInput;
    private javax.swing.JTextField addressInput;
    private javax.swing.JTextField birthDateInput;
    private javax.swing.JTextField genderInput;
    private javax.swing.JTextField emailInput;
    private javax.swing.JTextField registrationTimeInput;
    private javax.swing.JTextField accountStatusInput;
    private javax.swing.JScrollPane userTableScrollPanel;
    private javax.swing.JScrollPane userDetailTableScrollPanel;
    private javax.swing.JScrollPane userScrollPanel;
    private javax.swing.JScrollPane historyLoginTableScrollPane;
    private javax.swing.JScrollPane listFriendTableScrollPane;
    private javax.swing.JScrollPane passRequestTableScrollPane;
    private javax.swing.JButton searchUserButton;
    private javax.swing.JButton viewAllUserButton;
    private javax.swing.JButton addNewUserButton;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JButton disableEnableUserButton;
    private javax.swing.JButton updatePasswordButton;
    private javax.swing.JPanel userDetailPanel;
    private javax.swing.JPanel userPanel;
    private javax.swing.JTable historyLoginTable;
    private javax.swing.JTable listFriendTable;
    private javax.swing.JTable passRequestTable;
    private javax.swing.JPanel passRequestPanel;
    private javax.swing.JPanel loginHistoryMainPanel;
    private javax.swing.JButton loginHistoryNavButton;
    private javax.swing.JLabel loginHistoryTitle;
    private javax.swing.JTable loginHistoryTable;
    private javax.swing.JScrollPane loginHistoryTableScrollPane;
    // End of variables declaration
}
