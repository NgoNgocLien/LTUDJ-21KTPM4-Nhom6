package org.example.controllers;

import org.example.models.ChatInfo;
import org.example.utilities.DatabaseHandler;
import org.example.views.ChatListPanel;
import org.example.views.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChatListPanelController {
    private MainFrameController MFC;
    private MainFrame MF;
    private DatabaseHandler DB;
    String myUsername;
    private ChatListPanel chatListPanel;
    private ArrayList<ChatListPanel.AChatPanel> chatPanels;
    public ChatListPanelController(MainFrameController mfc) {
        this.MFC = mfc;
        this.MF = mfc.getMainFrame();
        this.DB = mfc.getDB();
        this.myUsername = mfc.getMyUsername();

        chatListPanel = MF.getChatListPanel();
        chatPanels = chatListPanel.getChatPanels();
        chatPanels.forEach(chatPanel -> chatPanel.addMouseListener(new ChatPanelMouseListener(chatPanel)));
    }

    public void renewListener() {
        chatPanels = chatListPanel.getChatPanels();
        chatPanels.forEach(chatPanel -> chatPanel.addMouseListener(new ChatPanelMouseListener(chatPanel)));
    }

    private class ChatPanelMouseListener extends MouseAdapter {
        ChatListPanel.AChatPanel chatPanel;
        public ChatPanelMouseListener(ChatListPanel.AChatPanel chatPanel) {
            this.chatPanel = chatPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            chatListPanel.setFocusChatPanel(chatPanel);
            ChatInfo chatInfo = chatPanel.getInfo();

            if (chatPanel.getMode()) {
                String chatName = chatInfo.getChatName();
                boolean isOnline = chatInfo.isOnline();
                MF.getConversationPanel().setChatName(chatName, isOnline);
                boolean isGroup = chatInfo.isGroup();
                if (isGroup) {

                } else {

                }
            }
            else {
                // open profile
            }
        }
    }
}
