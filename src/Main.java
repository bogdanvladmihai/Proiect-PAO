import DatabaseManager.DatabaseManager;
import modules.User;
import modules.Admin;
import java.sql.*;
import Menu.Menu;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();
        Menu menu = new Menu(db);
        menu.start();
    }
}