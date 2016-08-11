/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
class WordPannel extends Panel implements MouseListener, ActionListener {
	public int m_Y = 0;
	private String m_Word;
	public int m_iActiveHomonym = 0;
	private boolean m_bSizeCalculated = false;
	VisualSynAnPanel m_visualSynAnPanel;
	Vector m_Homonyms;

	class VisualSynAnMouseMoveListener extends MouseAdapter implements MouseMotionListener {

		private VisualSynAn m_VisualSynAnApplet;

		public VisualSynAnMouseMoveListener(VisualSynAn VisualSynAnApplet) {
			m_VisualSynAnApplet = VisualSynAnApplet;
		}

		public void mouseMoved(MouseEvent e) {
			if ((m_iActiveHomonym >= 0) && (m_iActiveHomonym < m_Homonyms.size())) {
				Homonym hom = (Homonym) m_Homonyms.elementAt(m_iActiveHomonym);
				m_VisualSynAnApplet.updateLabel(hom.m_Lemma + " " + hom.m_strGram);
			}
		}

		public void mouseDragged(MouseEvent mouseEvent) {
		}
	}

	public void addHomonym(String str) {
		StringTokenizer strTok = new StringTokenizer(str, "^");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		Homonym homonym = new Homonym("");
		homonym.m_Lemma = strTok.nextToken();
		if (!strTok.hasMoreTokens()) {
			return;
		}
		homonym.m_strGram = strTok.nextToken();
		m_Homonyms.addElement(homonym);
	}

	public String Decode(String _Word, String mask, String Symbol) {
		boolean flog = true;
		String res = new String("");
		int prevIndex = 0;
		int index = _Word.indexOf(mask);
		while (index != -1) {
			res = res.concat(_Word.substring(prevIndex, index));
			res = res.concat(Symbol);
			prevIndex = index + mask.length();
			index = _Word.indexOf(mask, prevIndex);
		}
		res = res.concat(_Word.substring(prevIndex, _Word.length()));
		return res;
	}

	public String Decode(String _Word) {
		String res;
		res = Decode(_Word, ";lattice", "#");
		res = Decode(res, ";at", "@");
		res = Decode(res, ";buks", "$");
		res = Decode(res, ";cover", "^");
		res = Decode(res, ";semicolon", ";");
		return res;
	}

	public WordPannel(String _Word, VisualSynAnPanel visualSynAnPanel) {
		super();
		m_Word = Decode(_Word);
		m_visualSynAnPanel = visualSynAnPanel;
		m_Homonyms = new Vector();
		/*
		m_Homonyms.addElement(new Homonym(m_Word));
		if( m_Word == "$$")
		m_Homonyms.addElement(new Homonym(m_Word));            */
		addMouseMotionListener(new VisualSynAnMouseMoveListener(m_visualSynAnPanel.m_VisualSynAnApplet));
		addMouseListener(this);
	}

	public int getActiveHomonym() {
		return m_iActiveHomonym;
	}

	public Dimension getPreferredSize() {
		Dimension size = getSize();
		if ((size.height == 0) || (size.width == 0)) {
			setSize(new Dimension(10, 10));
			size = getSize();
		}
		return size;
	}

	public Dimension getMinimumSize() {
		return getSize();
	}

	public void calcSize(Graphics g) {
		Font font = g.getFont();
		FontMetrics font_metrics = getFontMetrics(font);
		int width = font_metrics.stringWidth(m_Word);
		int height = font_metrics.getHeight();
		setSize(new Dimension(width, height));
		m_visualSynAnPanel.setValidate(true);
	}

	private void drawCanvas(Graphics g) {
		Color bg = getBackground();
		g.setColor(bg);
		Dimension size = getSize();
		g.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
		g.draw3DRect(3, 3, size.width - 7, size.height - 7, false);
		g.setColor(Color.black);
	}

	public synchronized void paint(Graphics g) {
		Font ff = new Font("Arial", Font.PLAIN, 12);
		g.setFont(ff);
		Font font = g.getFont();
		Font oldFont = g.getFont();
		if (m_Homonyms.size() > 1) {
			font = new Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
		}
		FontMetrics font_metrics = getFontMetrics(font);
		int width = font_metrics.stringWidth(m_Word);
		int height = font_metrics.getHeight();
		if (!((width == getSize().width) && (height == getSize().height))) {
			setSize(new Dimension(width, height));
			m_visualSynAnPanel.setValidate(true);
		} else {
			FontMetrics font_metrics1 = getFontMetrics(g.getFont());
			g.setColor(Color.black);
			int ii = font_metrics1.getDescent();
			Dimension dim = getSize();
			int h = (int) dim.height;
			int y = h - font_metrics1.getDescent();
			if (m_Homonyms.size() > 1) {
				Font newFont = new Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
				g.setFont(newFont);
			}
			g.drawString(m_Word, 0, y);
			g.setFont(oldFont);
		}
	}

	public void mouseExited(MouseEvent mouseEvent) {
	}

	public void mouseReleased(MouseEvent mouseEvent) {
	}

	public void mousePressed(MouseEvent mouseEvent) {
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		if ((mouseEvent.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			PopupMenu P = new PopupMenu();
			for (int i = 0; i < m_Homonyms.size(); i++) {
				MenuItem item = new MenuItem(((Homonym) m_Homonyms.elementAt(i)).m_Lemma);
				item.setActionCommand(Integer.toString(i));
				P.add(item);
			}
			add(P);
			P.show(this, 0, getBounds().height);
			P.addActionListener(this);
		}
	}

	public void mouseEntered(MouseEvent mouseEvent) {
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() instanceof MenuItem) {
			String command = actionEvent.getActionCommand();
			int iHom = Integer.decode(command).intValue();
			if ((iHom != -1) && (iHom != m_iActiveHomonym)) {
				m_iActiveHomonym = iHom;
				m_visualSynAnPanel.setValidate(true);
				m_visualSynAnPanel.invalidate();
				m_visualSynAnPanel.repaint();
			}
		}
	}

}
