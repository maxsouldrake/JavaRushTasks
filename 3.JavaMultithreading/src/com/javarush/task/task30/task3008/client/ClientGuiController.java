package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    public static void main(String[] args) {
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }

    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    protected String getServerAddress() {
        return view.getServerAddress();
    }

    protected int getServerPort() {
        return view.getServerPort();
    }

    protected String getUserName() {
        return view.getUserName();
    }

    public ClientGuiModel getModel() {
        return model;
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.run();
    }

    public class GuiSocketThread extends SocketThread {
        public void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
            super.processIncomingMessage(message);
        }

        public void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
            super.informAboutAddingNewUser(userName);
        }

        public void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
            super.informAboutDeletingNewUser(userName);
        }

        public void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
            super.notifyConnectionStatusChanged(clientConnected);
        }
    }
}
