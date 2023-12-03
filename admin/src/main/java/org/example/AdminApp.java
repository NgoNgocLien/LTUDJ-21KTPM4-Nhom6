package org.example;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
import java.text.*;

public class AdminApp extends javax.swing.JFrame{

    public AdminApp() {
//         try {
//             // set windows look and feel
//             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//         } catch (Exception e) {
//             System.out.println("Error setting Windows look and feel: " + e);
//         }

        initComponents();
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
        reportMainPanel = new javax.swing.JPanel();
        reportTitle = new javax.swing.JLabel();
        searchReportTimeInput = new javax.swing.JTextField();
        searchUsernameInput = new javax.swing.JTextField();
        searchReportButton = new javax.swing.JButton();
        viewAllReportButton = new javax.swing.JButton();
        disableUserButton = new javax.swing.JButton();
        reportScrollPane = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        dataMainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        navbar.setBackground(new java.awt.Color(23, 70, 162));
        navbar.setPreferredSize(new java.awt.Dimension(307, 50));

        groupNavButton.setBackground(new java.awt.Color(23, 70, 162));
        groupNavButton.setForeground(new java.awt.Color(255, 255, 255));
        groupNavButton.setIcon(new ImageIcon(getClass().getResource("/users-solid.png"))); // NOI18N
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

        groupTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        groupTitle.setForeground(new java.awt.Color(23, 70, 162));
        groupTitle.setText("MANAGE GROUP");

        searchInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchInput.setText("Enter group name ...");
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

        javax.swing.GroupLayout groupMainPanelLayout = new javax.swing.GroupLayout(groupMainPanel);
        groupMainPanel.setLayout(groupMainPanelLayout);
        groupMainPanelLayout.setHorizontalGroup(
                groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
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
                                                .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupMainPanelLayout.setVerticalGroup(
                groupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupMainPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(groupTitle)
                                .addGap(29, 29, 29)
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
        searchReportTimeInput.setText("Enter date ( dd-mm-yyyy) ...");
        searchReportTimeInput.setPreferredSize(new java.awt.Dimension(124, 35));
//        searchReportTimeInput.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                searchReportTimeInputActionPerformed(evt);
//            }
//        });

        searchUsernameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchUsernameInput.setText("Enter username ...");
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
                                                                .addComponent(searchReportTimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(searchUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(searchReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(98, 98, 98)
                                                                .addComponent(viewAllReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(29, 29, 29)
                                                .addComponent(disableUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(625, Short.MAX_VALUE))
        );
        reportMainPanelLayout.setVerticalGroup(
                reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(reportMainPanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(reportTitle)
                                .addGap(29, 29, 29)
                                .addGroup(reportMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchReportTimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(viewAllReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(disableUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(reportScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(1679, Short.MAX_VALUE))
        );

        getContentPane().add(reportMainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String text = searchInput.getText();
        System.out.println(text);

        if (text.equals("") || text.equals("Enter group name ...") ){
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

        searchInput.setText("Enter group name ...");

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

        searchReportTimeInput.setText("Enter date ( dd-mm-yyyy) ...");
        searchUsernameInput.setText("Enter username ...");

    }

    private void searchReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = searchUsernameInput.getText();
        String date = searchReportTimeInput.getText();

        if ((username.isEmpty() || username.equals("Enter username ...")) && (date.isEmpty() || date.equals("Enter date ( dd-mm-yyyy) ..."))){
            JOptionPane.showMessageDialog(null, "Empty username and date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!isValidDate(date) && (!date.equals("Enter date ( dd-mm-yyyy) ...") && !date.isEmpty())){
            JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
            model.setRowCount(0);

            if (username.equals("Enter username ...")){
                username = "";
            }
            if (date.equals("Enter date ( dd-mm-yyyy) ..."))
                date = "";

            String[] dateToArray = date.split("-");

            Object[][] data = adminController.searchReport(username, dateToArray);
            for (Object[] row : data) {
                model.addRow(row);
            }
        }
    }

    private void disableUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
//        int index = reportTable.getSelectedRow();
//        TableModel model = reportTable.getModel();
//        String selected_username = model.getValueAt(index, 1).toString();
//        adminController.disableUser(selected_username);
//
//        DefaultTableModel reportModel = (DefaultTableModel) reportTable.getModel();
//        reportModel.setRowCount(0);
//
//        Object[][] report = adminController.getAllReport();
//        for (Object[] row : report) {
//            reportModel.addRow(row);
//        }
//
//        disableUserButton.setVisible(false);
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

    AdminController adminController = new AdminController();
    Color blue = new Color(23,70,162);

    private javax.swing.JLabel activeTitle;
    private javax.swing.JPanel activeUserMainPanel;
    private javax.swing.JScrollPane activeUserScrollPane1;
    private javax.swing.JTable activeUserTable;
    private javax.swing.JScrollPane adminScrollPanel;
    private javax.swing.JTable adminTable;
    private javax.swing.JLabel adminTitle;
    private javax.swing.JPanel dataMainPanel;
    private javax.swing.JButton dataNavButton;
    private javax.swing.JButton disableUserButton;
    private javax.swing.JTextField endDateInput;
    private javax.swing.JPanel groupMainPanel;
    private javax.swing.JButton groupNavButton;
    private javax.swing.JScrollPane groupScrollPane;
    private javax.swing.JTable groupTable;
    private javax.swing.JLabel groupTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane memberScrollPane;
    private javax.swing.JTable memberTable;
    private javax.swing.JLabel memberTitle;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel reportMainPanel;
    private javax.swing.JButton reportNavButton;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JTable reportTable;
    private javax.swing.JLabel reportTitle;
    private javax.swing.JButton searchActiveButton1;
    private javax.swing.JTextField searchActiveInput1;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton searchDateButton;
    private javax.swing.JTextField searchInput;
    private javax.swing.JButton searchNameButton1;
    private javax.swing.JTextField searchNameInput1;
    private javax.swing.JButton searchReportTimeButton;
    private javax.swing.JTextField searchReportTimeInput;
    private javax.swing.JButton searchUsernameButton;
    private javax.swing.JTextField searchUsernameInput;
    private javax.swing.JTextField startDateInput;
    private javax.swing.JLabel statisticTitle;
    private javax.swing.JButton userNavButton;
    private javax.swing.JButton viewAllGroupButton;
    private javax.swing.JButton viewAllReportButton;
    private javax.swing.JButton searchReportButton;
}
