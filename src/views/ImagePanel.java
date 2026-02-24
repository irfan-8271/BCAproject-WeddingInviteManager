package views;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 2L;
    private Image originalImage;
    private Image scaledImage;
    private int lastWidth = -1;
    private int lastHeight = -1;
    private String imagePath;

    public ImagePanel(String imagePath) {
        this.imagePath = imagePath;
        try {
            java.net.URL imgUrl = getClass().getClassLoader().getResource(imagePath);
            if (imgUrl != null) {
                this.originalImage = new ImageIcon(imgUrl).getImage();
            } else {
                System.err.println("Error loading image: Resource '" + imagePath + "' not found in classpath.");
                this.originalImage = null;
            }
        } catch (Exception e) {
            System.err.println("Exception loading image '" + imagePath + "': " + e.getMessage());
            e.printStackTrace();
            this.originalImage = null;
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int currentWidth = getWidth();
        int currentHeight = getHeight();
        if (originalImage != null && currentWidth > 0 && currentHeight > 0) {
            if (scaledImage == null || currentWidth != lastWidth || currentHeight != lastHeight) {
                scaledImage = originalImage.getScaledInstance(currentWidth, currentHeight, Image.SCALE_SMOOTH);
                lastWidth = currentWidth;
                lastHeight = currentHeight;
            }
            if (scaledImage != null) {
                g.drawImage(scaledImage, 0, 0, currentWidth, currentHeight, this);
            }
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, currentWidth, currentHeight);
            g2d.setColor(Color.RED);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            String errorText = "Image Not Found";
            if (imagePath != null)
                errorText += ": " + imagePath;
            FontMetrics fm = g2d.getFontMetrics();
            int stringWidth = fm.stringWidth(errorText);
            int stringAscent = fm.getAscent();
            int x = (currentWidth - stringWidth) / 2;
            int y = (currentHeight + stringAscent) / 2 - fm.getDescent();
            g2d.drawString(errorText, x, y);
        }
    }
}
