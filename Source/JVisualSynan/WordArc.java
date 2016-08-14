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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.List;
/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
class WordArc {

	public WordArc(int FirstWord, int LastWord, boolean val) {
		m_FirstWord = FirstWord;
		m_LastWord = LastWord;
		m_ChildArcs = new Vector();
		m_ParentGroupLeg = new Point();
		m_bGroupArc = val;
		m_ChildArcs = new Vector();
		m_ParentGroupLeg = new Point();
	}

	protected void Init(String strArc) {
		StringTokenizer strTok = new StringTokenizer(strArc, "^");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		String ss;
		ss = strTok.nextToken();
		ss = ss.trim();
		//StringBuffer ssBuf = new StringBuffer(ss);
		{
			String[] nums = ss.split(",");
			if (nums.length < 2) {
				m_FirstWord = Integer.parseInt(ss);
				ss = strTok.nextToken().trim();
				m_LastWord = Integer.parseInt(ss);
			} else {
				m_FirstWord = Integer.parseInt(nums[0]);
				m_LastWord = Integer.parseInt(nums[1]);
			}
		}
		if (!strTok.hasMoreTokens()) {
			return;
		}
		m_strName = strTok.nextToken();
		if (!strTok.hasMoreTokens()) {
			return;
		}
		ss = strTok.nextToken();
		ss = ss.trim();
		if (ss.compareTo("gr") == 0) {
			m_bGroupArc = true;
			m_bIsSubj = false;
		} else if (ss.compareTo("cl") == 0) {
			m_bGroupArc = false;
			m_bIsSubj = false;
		} else {
			m_bIsSubj = true;
		}
	}

	public WordArc(String strArc) {
		m_ChildArcs = new Vector();
		m_ParentGroupLeg = new Point();
		Init(strArc);
	}

	public WordArc(int FirstWord, int LastWord) {
		m_FirstWord = FirstWord;
		m_LastWord = LastWord;
		m_ChildArcs = new Vector();
		m_ParentGroupLeg = new Point();
		m_bGroupArc = true;
		m_ChildArcs = new Vector();
		m_ParentGroupLeg = new Point();
	}

	public void addWordArc(WordArc arc) {
		m_ChildArcs.add(arc);
	}

	public void addWordArc(int FirstWord, int LastWord) {
		m_ChildArcs.add(new WordArc(FirstWord, LastWord));
	}

	private Point getLeftLegPoint(Graphics g, VisualSynAnPanel SynAnPanel) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		Point leftPoint = new Point();
		boolean bSet = false;
		if (m_ChildArcs.size() > 0) {
			WordArc wordArcLeft = m_ChildArcs.get(0);
			if ((wordArcLeft.m_FirstWord == m_FirstWord) && ((wordArcLeft.m_bGroupArc && m_bGroupArc) || (!wordArcLeft.m_bGroupArc && !m_bGroupArc) || (!wordArcLeft.m_bGroupArc && m_bGroupArc))) {
				leftPoint = wordArcLeft.getParentGroupLeg();
				bSet = true;
			}
		}
		if (!bSet) {
			if (m_bGroupArc) {
				leftPoint.x = WordPannelLeft.getBounds().x + WordPannelLeft.getBounds().width / 2;
				leftPoint.y = WordPannelLeft.getBounds().y;
			} else {
				leftPoint.x = WordPannelLeft.getBounds().x;
				leftPoint.y = WordPannelLeft.getBounds().y;
			}
		}
		return leftPoint;
	}

	private Point getRightLegPoint(Graphics g, VisualSynAnPanel SynAnPanel) {
		Point rightPoint = new Point();
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		boolean bSet = false;
		if (m_ChildArcs.size() >= 1) {
			WordArc wordArcRight = m_ChildArcs.get(m_ChildArcs.size() - 1);
			if ((wordArcRight.m_LastWord == m_LastWord) && ((wordArcRight.m_bGroupArc && m_bGroupArc) || (!wordArcRight.m_bGroupArc && !m_bGroupArc) || (!wordArcRight.m_bGroupArc && m_bGroupArc))) {
				rightPoint = wordArcRight.getParentGroupLeg();
				bSet = true;
			}
		}
		if (!bSet) {
			if (m_bGroupArc) {
				rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width / 2;
				rightPoint.y = WordPannelRight.getBounds().y;
			} else {
				rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width;
				rightPoint.y = WordPannelRight.getBounds().y;
			}
		}
		return rightPoint;
	}

	private void drawOneLineArc(Graphics g, VisualSynAnPanel SynAnPanel, Point leftPoint, Point rightPoint) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		int[] xs = new int[4];
		int[] ys = new int[4];
		xs[0] = leftPoint.x;
		xs[1] = leftPoint.x;
		xs[2] = rightPoint.x;
		xs[3] = rightPoint.x;
		ys[0] = leftPoint.y;
		ys[1] = WordPannelLeft.getBounds().y - m_Height;
		ys[2] = WordPannelLeft.getBounds().y - m_Height;
		ys[3] = rightPoint.y;
		m_ParentGroupLeg.x = leftPoint.x + (rightPoint.x - leftPoint.x) / 2;
		m_ParentGroupLeg.y = WordPannelLeft.getBounds().y - m_Height;
		g.drawPolyline(xs, ys, 4);
		g.setColor(Color.black);
	}

	private void drawMultyLineArc(Graphics g, VisualSynAnPanel SynAnPanel, Point leftPoint, Point rightPoint) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		int[] xs = new int[3];
		int[] ys = new int[3];
		xs[0] = leftPoint.x;
		xs[1] = leftPoint.x;
		xs[2] = SynAnPanel.getBounds().width - 5;
		ys[0] = leftPoint.y;
		ys[1] = WordPannelLeft.getBounds().y - m_Height;
		ys[2] = WordPannelLeft.getBounds().y - m_Height;
		m_ParentGroupLeg.x = xs[1] + (xs[2] - xs[1]) / 2;
		m_ParentGroupLeg.y = ys[1];
		g.drawPolyline(xs, ys, 3);
		int vGap = ((FlowLayout) SynAnPanel.getLayout()).getVgap();
		int wordPanelHeight = WordPannelLeft.getSize().height;
		int curY = WordPannelLeft.getBounds().y + vGap + wordPanelHeight;
		while (rightPoint.y - curY > 0) {
			g.drawLine(5, curY - m_Height, SynAnPanel.getBounds().width - 5, curY - m_Height);
			curY += vGap;
			curY += wordPanelHeight;
		}
		xs[0] = 5;
		xs[1] = rightPoint.x;
		xs[2] = rightPoint.x;
		ys[0] = curY - m_Height;
		ys[1] = curY - m_Height;
		ys[2] = rightPoint.y;
		g.drawPolyline(xs, ys, 3);
		g.setColor(Color.black);
	}

	public void Draw(Graphics g, VisualSynAnPanel SynAnPanel) {
		m_Height = getHeight();
		for (int i = 0; i < m_ChildArcs.size(); i++) {
			WordArc wordArc = m_ChildArcs.get(i);
			wordArc.Draw(g, SynAnPanel);
		}
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		Point leftPoint;
		Point rightPoint;
		leftPoint = getLeftLegPoint(g, SynAnPanel);
		rightPoint = getRightLegPoint(g, SynAnPanel);
		if (m_bGroupArc) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.red);
			/*    leftPoint = new Point();
			rightPoint = new Point();
			leftPoint.x = WordPannelLeft.getBounds().x ;
			leftPoint.y = WordPannelLeft.getBounds().y;
			rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width;
			rightPoint.y = WordPannelRight.getBounds().y;*/
		}
		int vGap = ((FlowLayout) SynAnPanel.getLayout()).getVgap();
		if (rightPoint.y <= WordPannelLeft.getBounds().y) {
			drawOneLineArc(g, SynAnPanel, leftPoint, rightPoint);
		} else {
			drawMultyLineArc(g, SynAnPanel, leftPoint, rightPoint);
		}
		Font ff = new Font("Arial", Font.PLAIN, 12);
		g.setFont(ff);
		Font oldFont = g.getFont();
		Font smallFont = VisualSynAnPanel.getSmallFont(g);
		g.setFont(smallFont);
		g.drawString(m_strName, m_ParentGroupLeg.x + 2, m_ParentGroupLeg.y - 2);
		g.setFont(oldFont);
	}

	public int getHeight() {
		if (m_Height == 0) {
			m_Height = calculateHeight();
		}
		return m_Height;
	}

	private int calculateHeight() {
		int Height = 0;
		for (int i = 0; i < m_ChildArcs.size(); i++) {
			WordArc wordArc = m_ChildArcs.get(i);
			int ii = wordArc.calculateHeight();
			if (ii > Height) {
				Height = ii;
			}
		}
		return Height + VisualSynAnPanel.ArcHeight;
	}

	private Point getParentGroupLeg() {
		return m_ParentGroupLeg;
	}
	private String m_strName;
	public int m_FirstWord = 0;
	private Point m_ParentGroupLeg;
	public int m_LastWord = 0;
	private int m_Height = 0;
	private boolean m_bGroupArc = true;
	public boolean m_bIsSubj = false;
	private List<WordArc> m_ChildArcs;
	private boolean m_IsGroup;

}
