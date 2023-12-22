package org.example;

import javax.swing.table.*;
import java.awt.*;
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

    public AdminApp(Socket socket) {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        groupMainPanel.setVisible(false);
        reportMainPanel.setVisible(false);
        dataMainPanel.setVisible(false);

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
        statisticTitle = new javax.swing.JLabel();
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
        userScrollPane = new javax.swing.JScrollPane();
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

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
                navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(navbarLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(userNavButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        groupTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
            groupTable.getColumnModel().getColumn(2).setPreferredWidth(75);
        }

        memberScrollPane.setRowHeaderView(null);

        memberTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
            memberTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            memberTable.getColumnModel().getColumn(1).setResizable(false);
            memberTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        adminScrollPanel.setRowHeaderView(null);

        adminTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
            adminTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            adminTable.getColumnModel().getColumn(1).setResizable(false);
            adminTable.getColumnModel().getColumn(1).setPreferredWidth(90);
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
                                                                .addGap(69, 69, 69)
                                                                .addComponent(adminTitle)
                                                                .addGap(166, 166, 166)
                                                                .addComponent(memberTitle))
                                                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(adminScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(60, 60, 60)
                                                                .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
        userTitle1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle1.setText("Full name");

        userTitle2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle2.setText("Username");

        userTitle3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userTitle3.setText("Active Status");

        searchFullNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchFullNameInput.setPreferredSize(new java.awt.Dimension(124, 35));

        searchUserNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUserNameInput.setPreferredSize(new java.awt.Dimension(124, 35));

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
        searchUserButton.setPreferredSize(new java.awt.Dimension(57, 35));
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

        userTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                new String [] {
                        "No", "Username", "Full name", "Address", "Birth date", "Gender", "Email"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        userTable.setRowHeight(30);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.getTableHeader().setReorderingAllowed(false);
//        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                try {
//                    groupTableMouseClicked(evt);
//                } catch (IOException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
        userScrollPane.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(2).setResizable(false);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(3).setResizable(false);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(500);
            userTable.getColumnModel().getColumn(4).setResizable(false);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            userTable.getColumnModel().getColumn(5).setResizable(false);
            userTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            userTable.getColumnModel().getColumn(6).setResizable(false);
            userTable.getColumnModel().getColumn(6).setPreferredWidth(300);
        }

        viewAllUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewAllUserButton.setText("View all user");
        viewAllUserButton.setFocusable(false);
        viewAllUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        viewAllUserButton.setPreferredSize(new java.awt.Dimension(57, 35));
        viewAllUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    viewAllUserButtonActionPerformed(evt);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        userTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        userTitle.setForeground(new java.awt.Color(23, 70, 162));
        userTitle.setText("MANAGE USER");

        javax.swing.GroupLayout userMainPanelLayout = new javax.swing.GroupLayout(userMainPanel);
        userMainPanel.setLayout(userMainPanelLayout);
        userMainPanelLayout.setHorizontalGroup(
                userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userMainPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userTitle)
                                        .addGroup(userMainPanelLayout.createSequentialGroup()
                                                .addGroup(userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(userMainPanelLayout.createSequentialGroup()
                                                                .addComponent(searchFullNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchUserNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchActiveDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(viewAllUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(userMainPanelLayout.createSequentialGroup()
                                                                .addComponent(userTitle1)
                                                                .addGap(120, 120, 120)
                                                                .addComponent(userTitle2)
                                                                .addGap(120, 120, 120)
                                                                .addComponent(userTitle3))
                                                        .addComponent(userScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userMainPanelLayout.setVerticalGroup(
                userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userMainPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(userTitle)
                                .addGap(30, 30, 30)
                                .addGroup(userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userTitle1)
                                        .addComponent(userTitle2)
                                        .addComponent(userTitle3))
                                .addGap(5, 5, 5)
                                .addGroup(userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchFullNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchUserNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchActiveDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(viewAllUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(userMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(userMainPanel, java.awt.BorderLayout.CENTER);

        dataMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataMainPanel.setPreferredSize(new java.awt.Dimension(1440, 2079));
        dataMainPanel.setLayout(new java.awt.BorderLayout());

        statisticTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        statisticTitle.setForeground(new java.awt.Color(23, 70, 162));
        statisticTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statisticTitle.setText("STATISTICS");

        javax.swing.GroupLayout dataTitlePanelLayout = new javax.swing.GroupLayout(dataTitlePanel);
        dataTitlePanel.setLayout(dataTitlePanelLayout);
        dataTitlePanelLayout.setHorizontalGroup(
                dataTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dataTitlePanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(statisticTitle)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataTitlePanelLayout.setVerticalGroup(
                dataTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dataTitlePanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(statisticTitle)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        activeTitle.setText("List of active users");

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
        startHourInput.setText("0");
        startHourInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startHourInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startHourInputActionPerformed(evt);
//            }
//        });

        startMinInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startMinInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startMinInput.setText("0");
        startMinInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        startMinInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                startMinInputActionPerformed(evt);
//            }
//        });

        startSecInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startSecInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startSecInput.setText("0");
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
            activeUserTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            activeUserTable.getColumnModel().getColumn(3).setResizable(false);
            activeUserTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            activeUserTable.getColumnModel().getColumn(4).setResizable(false);
            activeUserTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            activeUserTable.getColumnModel().getColumn(5).setResizable(false);
            activeUserTable.getColumnModel().getColumn(5).setPreferredWidth(95);
            activeUserTable.getColumnModel().getColumn(6).setResizable(false);
            activeUserTable.getColumnModel().getColumn(6).setPreferredWidth(75);
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
        endHourInput.setText("0");
        endHourInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endHourInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endHourInputActionPerformed(evt);
//            }
//        });

        endMinInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endMinInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endMinInput.setText("0");
        endMinInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        endMinInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                endMinInputActionPerformed(evt);
//            }
//        });

        endSecInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endSecInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endSecInput.setText("0");
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
                                                .addComponent(activeUserScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(251, 251, 251))))
        );
        activeUserMainPanelLayout.setVerticalGroup(
                activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(activeTitle)
                                .addGap(18, 18, 18)
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
        chartActiveUserTitle.setText("Chart of monthly active users");

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

        // chỉnh header
        JTableHeader headerAdmin = adminTable.getTableHeader();
        headerAdmin.setBackground(blue);
        headerAdmin.setForeground(Color.WHITE);

        JTableHeader headerMember = memberTable.getTableHeader();
        headerMember.setBackground(blue);
        headerMember.setForeground(Color.WHITE);

        // chỉnh center
        ((DefaultTableCellRenderer)groupTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)groupTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        groupTable.setPreferredScrollableViewportSize(groupTable.getPreferredSize());

        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        userMainPanel.setVisible(false);
        getContentPane().remove(userMainPanel);
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

        // chỉnh center
        ((DefaultTableCellRenderer)userTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)userTable.getDefaultRenderer(Integer.class)).setHorizontalAlignment(SwingConstants.CENTER);
        userTable.setPreferredScrollableViewportSize(userTable.getPreferredSize());

        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
        getContentPane().add(userMainPanel);
        userMainPanel.setVisible(true);
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

        startHourInput.setText("0");
        startMinInput.setText("0");
        startSecInput.setText("0");

        endHourInput.setText("0");
        endMinInput.setText("0");
        endSecInput.setText("0");

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

        startHourInput.setText("0");
        startMinInput.setText("0");
        startSecInput.setText("0");

        endHourInput.setText("0");
        endMinInput.setText("0");
        endSecInput.setText("0");

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
                    return Integer.toString(num);
                } else{
                    return "0";
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid " + component, "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        else{
            return "0";
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
    AdminController adminController = new AdminController();
    Color blue = new Color (23,70,162);
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
    private javax.swing.JLabel statisticTitle;
    private javax.swing.JButton userNavButton;
    private javax.swing.JButton viewAllGroupButton;
    private javax.swing.JButton viewAllReportButton;
    private javax.swing.JLabel yearTitle;
    private javax.swing.JPanel userMainPanel;
    private javax.swing.JTextField searchFullNameInput;
    private javax.swing.JComboBox<String> searchActiveDropDown;
    private javax.swing.JTextField searchUserNameInput;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel userTitle;
    private javax.swing.JLabel userTitle1;
    private javax.swing.JLabel userTitle2;
    private javax.swing.JLabel userTitle3;
    private javax.swing.JScrollPane userScrollPane;
    private javax.swing.JButton searchUserButton;
    private javax.swing.JButton viewAllUserButton;
    private javax.swing.JButton addNewUserButton;
    // End of variables declaration
}
