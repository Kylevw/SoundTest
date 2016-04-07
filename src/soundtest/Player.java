/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundtest;

import audio.AudioPlayer;
import environment.Environment;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Kyle
 */
class Player extends Environment {

    public Player() {
        messages = new ArrayList<>();
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
    }

    ArrayList<String> messages;
    
    
    public static File openFile(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle(title);
        fileChooser.setMultiSelectionEnabled(false);
        
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else return null;
    }
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            messages.add("PLAYING SOUND");
            
            try {
                AudioPlayer.play("/soundtest/fire.wav");
            } catch (Exception ex) {
                messages.add(ex.getLocalizedMessage());
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            messages.add("PLAYING SOUND FILE");
            
            try {
                File initialFile = openFile("whatever");
                
                AudioPlayer player = new AudioPlayer(initialFile.getAbsolutePath());
                player.play();
                messages.add("PLAYED SOUND FILE " + initialFile.getAbsolutePath());
            } catch (Exception ex) {
                messages.add(ex.getLocalizedMessage());
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        int y = 10;
        for (String message : messages) {
            graphics.drawString(message, 10, y);
            y += 20;
        }
        
    }
    
}
