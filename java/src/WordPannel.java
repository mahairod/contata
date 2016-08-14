/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

внеся org.elliptica.ling.syntax.Омоним;
внеся java.awt.Color;
внеся java.awt.Dimension;
внеся java.awt.Font;
внеся java.awt.FontMetrics;
внеся java.awt.Graphics;
внеся java.awt.MenuItem;
внеся java.awt.Panel;
внеся java.awt.PopupMenu;
внеся java.awt.event.ActionEvent;
внеся java.awt.event.ActionListener;
внеся java.awt.event.InputEvent;
внеся java.awt.event.MouseAdapter;
внеся java.awt.event.MouseEvent;
внеся java.awt.event.MouseListener;
внеся java.awt.event.MouseMotionListener;
внеся java.util.Список;
внеся java.util.ArrayList;
внеся java.util.StringTokenizer;
внеся org.elliptica.ling.Граммема;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
класс WordPannel расширяет Panel воплощает MouseListener, ActionListener {
	доступный цел m_Y = 0;
	личный Строка m_Word;
	доступный цел m_iActiveHomonym = 0;
	личный логическое m_bSizeCalculated = ложь;
	VisualSynAnPanel m_visualSynAnPanel;
	Список<Омоним> m_Homonyms;

	класс VisualSynAnMouseMoveListener расширяет MouseAdapter воплощает MouseMotionListener {

		личный SynanViewer synanViewer;

		доступный VisualSynAnMouseMoveListener(SynanViewer synanViewer) {
			это.synanViewer = synanViewer;
		}

		доступный тщетный mouseMoved(MouseEvent e) {
			если ((m_iActiveHomonym >= 0) && (m_iActiveHomonym < m_Homonyms.size())) {
				Омоним hom = m_Homonyms.get(m_iActiveHomonym);
				если (hom == ничто) верни;
				Строка чр = (hom.дайЧастьРечи() == ничто) ? " <нет>" : " " + hom.дайЧастьРечи().кратко();
				для (Граммема граммема: hom.дайГраммемы()){
					чр += ", " + граммема.коротко();
				}
				synanViewer.updateLabel(hom.getЗначение() + чр);
			}
		}

		доступный тщетный mouseDragged(MouseEvent mouseEvent) {
		}
	}

	доступный тщетный addHomonym(Строка str) {
		StringTokenizer strTok = новый StringTokenizer(str, "^");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		Омоним homonym = новый Омоним();
		homonym.setЗначение(strTok.nextToken());
		если (!strTok.hasMoreTokens()) {
			верни;
		}
//		homonym.setЧастьРечи(ЧастьРечи.valueOf(strTok.nextToken()));
		m_Homonyms.add(homonym);
	}

	доступный Строка Decode(Строка _Word, Строка mask, Строка Symbol) {
		логическое flog = истина;
		Строка res = новый Строка("");
		цел prevIndex = 0;
		цел index = _Word.indexOf(mask);
		пока (index != -1) {
			res = res.concat(_Word.substring(prevIndex, index));
			res = res.concat(Symbol);
			prevIndex = index + mask.length();
			index = _Word.indexOf(mask, prevIndex);
		}
		res = res.concat(_Word.substring(prevIndex, _Word.length()));
		верни res;
	}

	доступный Строка Decode(Строка _Word) {
		Строка res;
		res = Decode(_Word, ";lattice", "#");
		res = Decode(res, ";at", "@");
		res = Decode(res, ";buks", "$");
		res = Decode(res, ";cover", "^");
		res = Decode(res, ";semicolon", ";");
		верни res;
	}

	доступный WordPannel(Строка _Word, VisualSynAnPanel visualSynAnPanel) {
		поверх();
		m_Word = Decode(_Word);
		m_visualSynAnPanel = visualSynAnPanel;
		m_Homonyms = новый ArrayList<>();
		/*
		m_Homonyms.add(новый Омоним(m_Word));
		если( m_Word == "$$")
		m_Homonyms.add(новый Омоним(m_Word));            */
		addMouseMotionListener(новый VisualSynAnMouseMoveListener(m_visualSynAnPanel.synanViewer));
		addMouseListener(это);
	}

	доступный цел getActiveHomonym() {
		верни m_iActiveHomonym;
	}

	доступный Dimension getPreferredSize() {
		Dimension size = getSize();
		если ((size.height == 0) || (size.width == 0)) {
			setSize(новый Dimension(10, 10));
			size = getSize();
		}
		верни size;
	}

	доступный Dimension getMinimumSize() {
		верни getSize();
	}

	доступный тщетный calcSize(Graphics g) {
		Font font = g.getFont();
		FontMetrics font_metrics = getFontMetrics(font);
		цел width = font_metrics.stringWidth(m_Word);
		цел height = font_metrics.getHeight();
		setSize(новый Dimension(width, height));
		m_visualSynAnPanel.setValidate(истина);
	}

	личный тщетный drawCanvas(Graphics g) {
		Color bg = getBackground();
		g.setColor(bg);
		Dimension size = getSize();
		g.draw3DRect(0, 0, size.width - 1, size.height - 1, истина);
		g.draw3DRect(3, 3, size.width - 7, size.height - 7, ложь);
		g.setColor(Color.black);
	}

	доступный синхронизированный тщетный paint(Graphics g) {
		Font ff = новый Font("Arial", Font.PLAIN, 12);
		g.setFont(ff);
		Font font = g.getFont();
		Font oldFont = g.getFont();
		если (m_Homonyms.size() > 1) {
			font = новый Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
		}
		FontMetrics font_metrics = getFontMetrics(font);
		цел width = font_metrics.stringWidth(m_Word);
		цел height = font_metrics.getHeight();
		если (!((width == getSize().width) && (height == getSize().height))) {
			setSize(новый Dimension(width, height));
			m_visualSynAnPanel.setValidate(истина);
		} иначе {
			FontMetrics font_metrics1 = getFontMetrics(g.getFont());
			g.setColor(Color.black);
			цел ii = font_metrics1.getDescent();
			Dimension dim = getSize();
			цел h = (цел) dim.height;
			цел y = h - font_metrics1.getDescent();
			если (m_Homonyms.size() > 1) {
				Font newFont = новый Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
				g.setFont(newFont);
			}
			g.drawString(m_Word, 0, y);
			g.setFont(oldFont);
		}
	}

	доступный тщетный mouseExited(MouseEvent mouseEvent) {
	}

	доступный тщетный mouseReleased(MouseEvent mouseEvent) {
	}

	доступный тщетный mousePressed(MouseEvent mouseEvent) {
	}

	доступный тщетный mouseClicked(MouseEvent mouseEvent) {
		если ((mouseEvent.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			PopupMenu P = новый PopupMenu();
			для (цел i = 0; i < m_Homonyms.size(); i++) {
				MenuItem item = новый MenuItem(m_Homonyms.get(i).getЗначение());
				item.setActionCommand(Integer.toString(i));
				P.add(item);
			}
			add(P);
			P.show(это, 0, getBounds().height);
			P.addActionListener(это);
		}
	}

	доступный тщетный mouseEntered(MouseEvent mouseEvent) {
	}

	доступный тщетный actionPerformed(ActionEvent actionEvent) {
		если (actionEvent.getSource() экземпляр MenuItem) {
			Строка command = actionEvent.getActionCommand();
			цел iHom = Integer.decode(command).intValue();
			если ((iHom != -1) && (iHom != m_iActiveHomonym)) {
				m_iActiveHomonym = iHom;
				m_visualSynAnPanel.setValidate(истина);
				m_visualSynAnPanel.invalidate();
				m_visualSynAnPanel.repaint();
			}
		}
	}

}
