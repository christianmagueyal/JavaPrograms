package model;

import view.GamePanel;

public class TeleportNormal implements Animate {
    public long lastExecution = 0;
    
    @Override
    public float teleport(float x) {

        if((System.currentTimeMillis() - lastExecution) >= 3000){
            x = (5+(int)(Math.random()* (GamePanel.width - 35)));
            lastExecution = System.currentTimeMillis();
        }
            return x;
    }
}