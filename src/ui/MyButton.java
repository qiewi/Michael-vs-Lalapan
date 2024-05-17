package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Font;


public class MyButton {

	public int x, y, width, height, id;
	private String text, name;
	private Rectangle bounds;
	private boolean mouseHover, mousePressed, enable, potentialSwapInventory, potentialSwapDeck, firstSwap, isJustText;
	private Image image;
	
	// For Inventory Buttons
	public MyButton(int x, int y, int width, int height, Image image, boolean enable, boolean potentialSwapInventory, boolean firstSwap) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.image = image;
		this.enable = enable;
		this.potentialSwapInventory = potentialSwapInventory;
		this.firstSwap = firstSwap;

		initBounds();
	}

	// For Deck Buttons
	public MyButton(int x, int y, int width, int height, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.image = image;

		initBounds();
	}

	// For Ingame Deck Buttons
	public MyButton(String name, int x, int y, int width, int height, Image image) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.image = image;

		initBounds();
	}

	// For Panel Buttons
	public MyButton(String text, int x, int y, int width, int height, boolean enable) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.enable = enable;

		initBounds();
	}
	
	// For Normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	// For Tile Buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	// For Text Buttons
	public MyButton(boolean isJustText, String text, int x, int y, int width, int height) {
		this.isJustText = isJustText;
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		// Body
		drawBody(g);

		// Border
		drawBorder(g);

		// Text
		drawText(g);
	}

	private void drawBorder(Graphics g) {
		// g.setColor(Color.black);

		if (isJustText != true) {
			if (text != null) {
				g.setColor(new Color(52,120,8));
			}
			g.drawRect(x, y, width, height);
			
			if (mousePressed && enable) {
				g.drawRect(x + 1, y + 1, width - 2, height - 2);
				g.drawRect(x + 2, y + 2, width - 4, height - 4);
			}

			if ((mouseHover || mousePressed) && enable) {
				g.setColor(new Color(206, 151, 38));
				g.drawRect(x + 1, y + 1, width - 2, height - 2);
				g.drawRect(x + 2, y + 2, width - 4, height - 4);
			}
		
			if (potentialSwapInventory) {
				g.setColor(Color.YELLOW);
				g.drawRect(x - 2, y - 2, width + 4, height + 4);
			} 
		
			if (potentialSwapDeck) {
				g.setColor(Color.YELLOW);
				g.drawRect(x - 2, y - 2, width + 4, height + 4);
			}
		
			if (firstSwap) {
				g.setColor(Color.RED);
				g.drawRect(x - 2, y - 2, width + 4, height + 4);
			}
		}
		
	}
	

	private void drawBody(Graphics g) {
		if (isJustText != true) {
			g.setColor(new Color(90, 43, 20));
			g.fillRect(x, y, width, height);
		}
		
		if (image != null) {
			int imgX = x + (width - image.getWidth(null)) / 2;
			int imgY = y + (height - image.getHeight(null)) / 2;
			g.drawImage(image, imgX, imgY, null);
			if (!enable) {
				g.setColor(new Color(0, 0, 0, 128));
				g.fillRect(x, y, width, height);
			}
		}
	}
	

	private void drawText(Graphics g) {
		if (text != null) {
			// Set font
			Font font = new Font("Times New Roman", Font.BOLD, 21); // Change "Arial" to your desired font name, adjust size and style as needed
			g.setFont(font);
			
			// Set color
			g.setColor(new Color(52,120,8));

			if (mouseHover || mousePressed) {
				g.setColor(new Color(206, 151, 38));
			}

			if (isJustText == true) {
				g.setColor(Color.BLACK);
			}

			// Draw text
			int textWidth = g.getFontMetrics(font).stringWidth(text);
			int textHeight = g.getFontMetrics(font).getHeight();
			g.drawString(text, x + (width - textWidth) / 2, y + (height + textHeight - 9) / 2);
			
		}
	}
	

	public boolean isEnabled() {
		return enable;
	}
	
	public void setEnabled(boolean enable) {
		this.enable = enable;
	}

	public boolean getFirstSwap() {
		return firstSwap;
	}
	
	public void setFirstSwap(boolean firstSwap) {
		this.firstSwap = firstSwap;
	}

	public void setPotentialSwapInventory(boolean potentialSwapInventory) {
		this.potentialSwapInventory = potentialSwapInventory;
	}

	public void setPotentialSwapDeck(boolean potentialSwapDeck) {
		this.potentialSwapDeck = potentialSwapDeck;
	}

	public void resetBooleans() {
		this.mouseHover = false;
		this.mousePressed = false;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseHover(boolean mouseHover) {
		this.mouseHover = mouseHover;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}
}