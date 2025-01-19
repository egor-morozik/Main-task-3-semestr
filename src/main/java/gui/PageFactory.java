/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author egorm
 */
import gui.MainPageForm;
import gui.HomePageForm;
import gui.SettingsPageForm;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class PageFactory {
    private final Map<String, JPanel> pages;

    public PageFactory() {
        pages = new HashMap<>();
    }

    /**
     * Возвращает или создает экземпляр страницы.
     * 
     * @param pageName имя страницы (например, "Settings", "Main", "Home").
     * @return экземпляр страницы.
     */
    public JPanel getPage(String pageName) {
        if (!pages.containsKey(pageName)) {
            switch (pageName) {
                case "Settings" -> pages.put(pageName, new SettingsPageForm());
                case "Main" -> pages.put(pageName, new MainPageForm());
                case "Home" -> pages.put(pageName, new HomePageForm());
                default -> throw new IllegalArgumentException("Unknown page: " + pageName);
            }
        }
        return pages.get(pageName);
    }
}



