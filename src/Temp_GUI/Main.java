package Temp_GUI;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Container contains = getContentPane();
    JButton Browse = new JButton("BROWSE");
    JButton My_Wall = new JButton("MY WALLPAPER");

    Main(){
        setLayoutManager();
        setLocationSize();
        addComponentsToContainer();
        setTitle("Zero-Chan");
        setVisible(true);
        setBounds(0,0,1200,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }


    public void setLayoutManager(){
        contains.setLayout(null);
    }

    public void setLocationSize(){
     Browse.setBounds(90,20,250,30);
     My_Wall.setBounds(850, 20, 250, 30);
    }

    public void addComponentsToContainer(){
        contains.add(Browse);
        contains.add(My_Wall);
    }


    public static void main(String[] args) {
        new Main();
    }
}
