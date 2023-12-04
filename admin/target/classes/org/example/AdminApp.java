
package org.example;

import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
import java.text.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.chart.renderer.category.BarRenderer;

public class AdminApp extends javax.swing.JFrame {

    public AdminApp() {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        groupMainPanel.setVisible(false);
        reportMainPanel.setVisible(false);
        dataMainPanel.setVisible(false);

    }

    private void initComponents() {

        navbar = new javax.swing.JPanel();
        groupNavButton = new javax.swing.JButton();
        userNavButton = new javax.swing.JButton();
        dataNavButton = new javax.swing.JButton();
        reportNavButton = new javax.swing.JButton();
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
        dataMainPanel = new javax.swing.JPanel();
        dataTitlePanel = new javax.swing.JPanel();
        statisticTitle = new javax.swing.JLabel();
        ScrollPanel = new javax.swing.JScrollPane();
        dataPanel = new javax.swing.JPanel();
        activeUserMainPanel = new javax.swing.JPanel();
        activeTitle = new javax.swing.JLabel();
        startDateInput = new javax.swing.JTextField();
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
        activeUserMonthlyMainPanel = new javax.swing.JPanel();
        chartActiveUserTitle = new javax.swing.JLabel();
        yearTitle = new javax.swing.JLabel();
        searchYearInput = new javax.swing.JTextField();
        searchYearButton = new javax.swing.JButton();
        activeUserMonthlyChartPanel = new javax.swing.JPanel();
        resetButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        navbar.setBackground(new java.awt.Color(23, 70, 162));
        navbar.setPreferredSize(new java.awt.Dimension(307, 50));

        groupNavButton.setBackground(new java.awt.Color(23, 70, 162));
        groupNavButton.setForeground(new java.awt.Color(255, 255, 255));
        groupNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/users-solid.png"))); // NOI18N
        groupNavButton.setBorderPainted(false);
        groupNavButton.setFocusable(false);
        groupNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        groupNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupNavButtonActionPerformed(evt);
            }
        });

        userNavButton.setBackground(new java.awt.Color(23, 70, 162));
        userNavButton.setForeground(new java.awt.Color(255, 255, 255));
        userNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user-solid.png"))); // NOI18N
        userNavButton.setBorderPainted(false);
        userNavButton.setFocusable(false);
        userNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        userNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNavButtonActionPerformed(evt);
            }
        });

        dataNavButton.setBackground(new java.awt.Color(23, 70, 162));
        dataNavButton.setForeground(new java.awt.Color(255, 255, 255));
        dataNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/database-solid.png"))); // NOI18N
        dataNavButton.setBorderPainted(false);
        dataNavButton.setFocusable(false);
        dataNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        dataNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNavButtonActionPerformed(evt);
            }
        });

        reportNavButton.setBackground(new java.awt.Color(23, 70, 162));
        reportNavButton.setForeground(new java.awt.Color(255, 255, 255));
        reportNavButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flag-solid.png"))); // NOI18N
        reportNavButton.setBorderPainted(false);
        reportNavButton.setFocusable(false);
        reportNavButton.setPreferredSize(new java.awt.Dimension(50, 50));
        reportNavButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportNavButtonActionPerformed(evt);
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

        groupTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        groupTitle.setText("Group name");

        searchInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInputActionPerformed(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchButton.setText("Search");
        searchButton.setFocusable(false);
        searchButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
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
                groupTableMouseClicked(evt);
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
                viewAllGroupButtonActionPerformed(evt);
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

        reportMainPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        reportTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        reportTitle.setForeground(new java.awt.Color(23, 70, 162));
        reportTitle.setText("SPAM REPORT");

        searchReportTimeInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchReportTimeInput.setText("( dd-mm-yyyy)");
        searchReportTimeInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchReportTimeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReportTimeInputActionPerformed(evt);
            }
        });

        searchUsernameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUsernameInput.setText("Enter username ...");
        searchUsernameInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchUsernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUsernameInputActionPerformed(evt);
            }
        });

        searchReportButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchReportButton.setText("Search");
        searchReportButton.setFocusable(false);
        searchReportButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchReportButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReportButtonActionPerformed(evt);
            }
        });

        viewAllReportButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        viewAllReportButton.setText("View all reports");
        viewAllReportButton.setFocusable(false);
        viewAllReportButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        viewAllReportButton.setPreferredSize(new java.awt.Dimension(57, 35));
        viewAllReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllReportButtonActionPerformed(evt);
            }
        });

        disableUserButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        disableUserButton.setText("Disable account");
        disableUserButton.setFocusable(false);
        disableUserButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        disableUserButton.setPreferredSize(new java.awt.Dimension(57, 35));
        disableUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableUserButtonActionPerformed(evt);
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
        dataPanel.setMaximumSize(new java.awt.Dimension(1440, 2147483647));
        dataPanel.setPreferredSize(new java.awt.Dimension(1440, 1053));
        dataPanel.setLayout(new javax.swing.BoxLayout(dataPanel, javax.swing.BoxLayout.Y_AXIS));

        activeUserMainPanel.setPreferredSize(new java.awt.Dimension(2276, 1500));

        activeTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        activeTitle.setForeground(new java.awt.Color(23, 70, 162));
        activeTitle.setText("List of active users");

        startDateInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        startDateInput.setText("(dd-mm-yyyy)");
        startDateInput.setPreferredSize(new java.awt.Dimension(124, 35));
        startDateInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateInputActionPerformed(evt);
            }
        });

        endDateInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        endDateInput.setText("(dd-mm-yyyy)");
        endDateInput.setPreferredSize(new java.awt.Dimension(124, 35));
        endDateInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateInputActionPerformed(evt);
            }
        });

        searchDateButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchDateButton.setText("Search");
        searchDateButton.setFocusable(false);
        searchDateButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        searchDateButton.setPreferredSize(new java.awt.Dimension(57, 35));
        searchDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDateButtonActionPerformed(evt);
            }
        });

        resetButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        resetButton1.setText("Reset");
        resetButton1.setFocusable(false);
        resetButton1.setMargin(new java.awt.Insets(2, 5, 3, 5));
        resetButton1.setPreferredSize(new java.awt.Dimension(57, 35));
        resetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton1ActionPerformed(evt);
            }
        });

        searchNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchNameInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameInputActionPerformed(evt);
            }
        });

        searchSessionInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchSessionInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchSessionInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSessionInputActionPerformed(evt);
            }
        });

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
        activeUserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activeUserTableMouseClicked(evt);
            }
        });
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
        sessionCountDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bằng", "Nhỏ hơn", "Lớn hơn" }));
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

        javax.swing.GroupLayout activeUserMainPanelLayout = new javax.swing.GroupLayout(activeUserMainPanel);
        activeUserMainPanel.setLayout(activeUserMainPanelLayout);
        activeUserMainPanelLayout.setHorizontalGroup(
                activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(activeTitle)
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                                .addComponent(searchNameSessionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(resetButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(activeTitle3)
                                                                        .addComponent(activeTitle6)
                                                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                                                .addComponent(searchDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(resetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(activeTitle5)
                                                                        .addComponent(startDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(sessionCountDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(activeTitle4)
                                                                        .addComponent(endDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(searchSessionInput, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(searchNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(72, 72, 72)
                                                .addComponent(activeUserScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(225, Short.MAX_VALUE))
        );
        activeUserMainPanelLayout.setVerticalGroup(
                activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(activeTitle)
                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(activeUserScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(activeUserMainPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(activeTitle3)
                                                        .addComponent(activeTitle4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(startDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(endDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(45, 45, 45)
                                                .addComponent(activeTitle6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(activeTitle5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sessionCountDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchSessionInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(activeUserMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchNameSessionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resetButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(49, 49, 49)))
                                .addContainerGap(719, Short.MAX_VALUE))
        );

        dataPanel.add(activeUserMainPanel);

        activeUserMonthlyMainPanel.setPreferredSize(new java.awt.Dimension(2276, 1000));

        chartActiveUserTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        chartActiveUserTitle.setForeground(new java.awt.Color(23, 70, 162));
        chartActiveUserTitle.setText("Chart of monthly active users");

        yearTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        yearTitle.setText("Year");

        searchYearInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchYearInput.setPreferredSize(new java.awt.Dimension(124, 35));
        searchYearInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchYearInputActionPerformed(evt);
            }
        });

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
                resetButton3ActionPerformed(evt);
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
                                                .addContainerGap(1111, Short.MAX_VALUE))
                                        .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(activeUserMonthlyMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(activeUserMonthlyMainPanelLayout.createSequentialGroup()
                                                                        .addComponent(searchYearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(resetButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(searchYearInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(yearTitle))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(activeUserMonthlyChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(224, 224, 224))))
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
                                .addContainerGap(374, Short.MAX_VALUE))
        );

        dataPanel.add(activeUserMonthlyMainPanel);

        ScrollPanel.setViewportView(dataPanel);

        dataMainPanel.add(ScrollPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(dataMainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String text = searchInput.getText();
        System.out.println(text);

        if (text.equals("") ){
            JOptionPane.showMessageDialog(null, "You have not entered anything to search", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
            model.setRowCount(0);

            Object[][] data = adminController.searchGroupName(text);
            for (Object[] row : data) {
                model.addRow(row);
            }

            DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
            adminModel.setRowCount(0);

            DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
            memberModel.setRowCount(0);
        }
    }

    private void groupNavButtonActionPerformed(java.awt.event.ActionEvent evt) {

        DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
        model.setRowCount(0);

        Object[][] data = adminController.getAllGroup();
        for (Object[] row : data) {
            model.addRow(row);
        }

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
        getContentPane().add(groupMainPanel);
        groupMainPanel.setVisible(true);


    }

    private void userNavButtonActionPerformed(java.awt.event.ActionEvent evt) {
        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        dataMainPanel.setVisible(false);
        getContentPane().remove(dataMainPanel);
    }

    private void dataNavButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) activeUserTable.getModel();
        model.setRowCount(0);

        Object[][] data = adminController.getAllActiveUser();
        for (Object[] row : data) {
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

        createActiveUsersChart("2023");

        groupMainPanel.setVisible(false);
        getContentPane().remove(groupMainPanel);
        reportMainPanel.setVisible(false);
        getContentPane().remove(reportMainPanel);
        getContentPane().add(dataMainPanel);
        dataMainPanel.setVisible(true);

    }

    private void reportNavButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0);

        Object[][] data = adminController.getAllReport();
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
        getContentPane().add(reportMainPanel);
        reportMainPanel.setVisible(true);
    }

    private void groupTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = groupTable.getSelectedRow();
        TableModel model = groupTable.getModel();
        int selected_id = Integer.parseInt(model.getValueAt(index, 0).toString());

        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        adminModel.setRowCount(0);

        Object[][] admin = adminController.getAllAdmin(selected_id);
        for (Object[] row : admin) {
            adminModel.addRow(row);
        }

        ((DefaultTableCellRenderer)adminTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        adminTable.setPreferredScrollableViewportSize(adminTable.getPreferredSize());

        DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
        memberModel.setRowCount(0);

        Object[][] member = adminController.getAllMember(selected_id);
        for (Object[] row : member) {
            memberModel.addRow(row);
        }

        ((DefaultTableCellRenderer)memberTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
        memberTable.setPreferredScrollableViewportSize(memberTable.getPreferredSize());
    }

    private void viewAllGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
        model.setRowCount(0);

        Object[][] data = adminController.getAllGroup();
        for (Object[] row : data) {
            model.addRow(row);
        }

        searchInput.setText("");

        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        adminModel.setRowCount(0);

        DefaultTableModel memberModel = (DefaultTableModel) memberTable.getModel();
        memberModel.setRowCount(0);
    }

    private void reportTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = reportTable.getSelectedRow();
        TableModel model = reportTable.getModel();

        String status = model.getValueAt(index, 3).toString();

        if (status.equals("Enabled")){
            disableUserButton.setVisible(true);
        }
        else{
            disableUserButton.setVisible(false);
        }

    }

    private void viewAllReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0);

        Object[][] data = adminController.getAllReport();
        for (Object[] row : data) {
            model.addRow(row);
        }

        searchReportTimeInput.setText("");
        searchUsernameInput.setText("");

    }

    private void searchReportTimeInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchUsernameInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = searchUsernameInput.getText();
        String date = searchReportTimeInput.getText();

        if ((username.isEmpty()) && (date.isEmpty() || date.equals("dd-mm-yyyy"))){
            JOptionPane.showMessageDialog(null, "Empty username and date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!isValidDate(date) && (!date.equals("dd-mm-yyyy") && !date.isEmpty())){
            JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
            model.setRowCount(0);

            if (date.equals("dd-mm-yyyy"))
                date = "";

            String[] dateToArray = date.split("-");

            Object[][] data = adminController.searchReport(username, dateToArray);
            for (Object[] row : data) {
                model.addRow(row);
            }
        }
    }

    private void disableUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int index = reportTable.getSelectedRow();
        TableModel model = reportTable.getModel();
        String selected_username = model.getValueAt(index, 1).toString();
        adminController.disableUser(selected_username);

        DefaultTableModel reportModel = (DefaultTableModel) reportTable.getModel();
        reportModel.setRowCount(0);

        Object[][] report = adminController.getAllReport();
        for (Object[] row : report) {
            reportModel.addRow(row);
        }

        disableUserButton.setVisible(false);
    }

    private void startDateInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void endDateInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchDateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void activeUserTableMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void searchNameInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchSessionInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchNameSessionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void resetButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void resetButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchYearInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchYearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void resetButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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

    public void createActiveUsersChart(String year){
        String[] monthNames = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int[] data = adminController.getMonthlyActiveUser(year);
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
            renderer.setSeriesPaint(i, Color.blue);
        }

        activeUserMonthlyChartPanel.add(chartPanel, BorderLayout.NORTH);
    }

    AdminController adminController = new AdminController();
    Color blue = new Color (23,70,162);
    // Variables declaration - do not modify
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JLabel activeTitle;
    private javax.swing.JLabel activeTitle3;
    private javax.swing.JLabel activeTitle4;
    private javax.swing.JLabel activeTitle5;
    private javax.swing.JLabel activeTitle6;
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
    private javax.swing.JLabel statisticTitle;
    private javax.swing.JButton userNavButton;
    private javax.swing.JButton viewAllGroupButton;
    private javax.swing.JButton viewAllReportButton;
    private javax.swing.JLabel yearTitle;
    // End of variables declaration
}
