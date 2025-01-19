/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author egorm
 */
public class AppFrame extends JFrame {
    private final SidePanelForm sidePanelForm;
    private final JPanel contentPanel;
    private final PageFactory pageFactory;

    public AppFrame() {
        setTitle("FileCalcula");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 590);

        setLayout(new BorderLayout());

        // Инициализируем фабрику и боковую панель
        pageFactory = new PageFactory();
        sidePanelForm = new SidePanelForm(e -> switchPage(e.getActionCommand()));
        add(sidePanelForm, BorderLayout.WEST);

        // Настраиваем центральную панель
        contentPanel = new JPanel(new CardLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Добавляем страницы в contentPanel
        addPage("Settings");
        addPage("Main");
        addPage("Home");
    }

    /**
     * Добавляет страницу в contentPanel через фабрику.
     *
     * @param pageName имя страницы.
     */
    private void addPage(String pageName) {
        JPanel page = pageFactory.getPage(pageName);
        contentPanel.add(page, pageName);
    }

    /**
     * Переключает видимую страницу.
     *
     * @param pageName имя страницы для отображения.
     */
    private void switchPage(String pageName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, pageName);

        // Обновляем содержимое текущей страницы, если это необходимо
        JPanel currentPage = pageFactory.getPage(pageName);

    }
}
