/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

внеся java.awt.Color;
внеся java.awt.FlowLayout;
внеся java.awt.Font;
внеся java.awt.Graphics;
внеся java.awt.Point;
внеся java.util.StringTokenizer;
внеся java.util.Vector;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
класс WordArc {

	доступный WordArc(цел FirstWord, цел LastWord, логическое val) {
		m_FirstWord = FirstWord;
		m_LastWord = LastWord;
		m_ChildArcs = новый Vector();
		m_ParentGroupLeg = новый Point();
		m_bGroupArc = val;
		m_ChildArcs = новый Vector();
		m_ParentGroupLeg = новый Point();
	}

	защищённый тщетный Init(Строка strArc) {
		StringTokenizer strTok = новый StringTokenizer(strArc, "^");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		Строка ss;
		ss = strTok.nextToken();
		ss = ss.trim();
		//СтрокаBuffer ssBuf = новый StringBuffer(ss);
		{
			Строка[] nums = ss.split(",");
			если (nums.length < 2) {
				m_FirstWord = Integer.parseInt(ss);
				ss = strTok.nextToken().trim();
				m_LastWord = Integer.parseInt(ss);
			} иначе {
				m_FirstWord = Integer.parseInt(nums[0]);
				m_LastWord = Integer.parseInt(nums[1]);
			}
		}
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		m_strName = strTok.nextToken();
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		ss = strTok.nextToken();
		ss = ss.trim();
		если (ss.compareTo("gr") == 0) {
			m_bGroupArc = истина;
			m_bIsSubj = ложь;
		} иначе если (ss.compareTo("cl") == 0) {
			m_bGroupArc = ложь;
			m_bIsSubj = ложь;
		} иначе {
			m_bIsSubj = истина;
		}
	}

	доступный WordArc(Строка strArc) {
		m_ChildArcs = новый Vector();
		m_ParentGroupLeg = новый Point();
		Init(strArc);
	}

	доступный WordArc(цел FirstWord, цел LastWord) {
		m_FirstWord = FirstWord;
		m_LastWord = LastWord;
		m_ChildArcs = новый Vector();
		m_ParentGroupLeg = новый Point();
		m_bGroupArc = истина;
		m_ChildArcs = новый Vector();
		m_ParentGroupLeg = новый Point();
	}

	доступный тщетный addWordArc(WordArc arc) {
		m_ChildArcs.addElement(arc);
	}

	доступный тщетный addWordArc(цел FirstWord, цел LastWord) {
		m_ChildArcs.addElement(новый WordArc(FirstWord, LastWord));
	}

	личный Point getLeftLegPoint(Graphics g, VisualSynAnPanel SynAnPanel) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		Point leftPoint = новый Point();
		логическое bSet = ложь;
		если (m_ChildArcs.size() > 0) {
			WordArc wordArcLeft = (WordArc) m_ChildArcs.elementAt(0);
			если ((wordArcLeft.m_FirstWord == m_FirstWord) && ((wordArcLeft.m_bGroupArc && m_bGroupArc) || (!wordArcLeft.m_bGroupArc && !m_bGroupArc) || (!wordArcLeft.m_bGroupArc && m_bGroupArc))) {
				leftPoint = wordArcLeft.getParentGroupLeg();
				bSet = истина;
			}
		}
		если (!bSet) {
			если (m_bGroupArc) {
				leftPoint.x = WordPannelLeft.getBounds().x + WordPannelLeft.getBounds().width / 2;
				leftPoint.y = WordPannelLeft.getBounds().y;
			} иначе {
				leftPoint.x = WordPannelLeft.getBounds().x;
				leftPoint.y = WordPannelLeft.getBounds().y;
			}
		}
		верни leftPoint;
	}

	личный Point getRightLegPoint(Graphics g, VisualSynAnPanel SynAnPanel) {
		Point rightPoint = новый Point();
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		логическое bSet = ложь;
		если (m_ChildArcs.size() >= 1) {
			WordArc wordArcRight = (WordArc) m_ChildArcs.elementAt(m_ChildArcs.size() - 1);
			если ((wordArcRight.m_LastWord == m_LastWord) && ((wordArcRight.m_bGroupArc && m_bGroupArc) || (!wordArcRight.m_bGroupArc && !m_bGroupArc) || (!wordArcRight.m_bGroupArc && m_bGroupArc))) {
				rightPoint = wordArcRight.getParentGroupLeg();
				bSet = истина;
			}
		}
		если (!bSet) {
			если (m_bGroupArc) {
				rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width / 2;
				rightPoint.y = WordPannelRight.getBounds().y;
			} иначе {
				rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width;
				rightPoint.y = WordPannelRight.getBounds().y;
			}
		}
		верни rightPoint;
	}

	личный тщетный drawOneLineArc(Graphics g, VisualSynAnPanel SynAnPanel, Point leftPoint, Point rightPoint) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		цел[] xs = новый цел[4];
		цел[] ys = новый цел[4];
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

	личный тщетный drawMultyLineArc(Graphics g, VisualSynAnPanel SynAnPanel, Point leftPoint, Point rightPoint) {
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		цел[] xs = новый цел[3];
		цел[] ys = новый цел[3];
		xs[0] = leftPoint.x;
		xs[1] = leftPoint.x;
		xs[2] = SynAnPanel.getBounds().width - 5;
		ys[0] = leftPoint.y;
		ys[1] = WordPannelLeft.getBounds().y - m_Height;
		ys[2] = WordPannelLeft.getBounds().y - m_Height;
		m_ParentGroupLeg.x = xs[1] + (xs[2] - xs[1]) / 2;
		m_ParentGroupLeg.y = ys[1];
		g.drawPolyline(xs, ys, 3);
		цел vGap = ((FlowLayout) SynAnPanel.getLayout()).getVgap();
		цел wordPanelHeight = WordPannelLeft.getSize().height;
		цел curY = WordPannelLeft.getBounds().y + vGap + wordPanelHeight;
		пока (rightPoint.y - curY > 0) {
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

	доступный тщетный Draw(Graphics g, VisualSynAnPanel SynAnPanel) {
		m_Height = getHeight();
		для (цел i = 0; i < m_ChildArcs.size(); i++) {
			WordArc wordArc = (WordArc) m_ChildArcs.elementAt(i);
			wordArc.Draw(g, SynAnPanel);
		}
		WordPannel WordPannelLeft = SynAnPanel.getWordPannel(m_FirstWord);
		WordPannel WordPannelRight = SynAnPanel.getWordPannel(m_LastWord);
		Point leftPoint;
		Point rightPoint;
		leftPoint = getLeftLegPoint(g, SynAnPanel);
		rightPoint = getRightLegPoint(g, SynAnPanel);
		если (m_bGroupArc) {
			g.setColor(Color.blue);
		} иначе {
			g.setColor(Color.red);
			/*    leftPoint = новый Point();
			rightPoint = новый Point();
			leftPoint.x = WordPannelLeft.getBounds().x ;
			leftPoint.y = WordPannelLeft.getBounds().y;
			rightPoint.x = WordPannelRight.getBounds().x + WordPannelRight.getBounds().width;
			rightPoint.y = WordPannelRight.getBounds().y;*/
		}
		цел vGap = ((FlowLayout) SynAnPanel.getLayout()).getVgap();
		если (rightPoint.y <= WordPannelLeft.getBounds().y) {
			drawOneLineArc(g, SynAnPanel, leftPoint, rightPoint);
		} иначе {
			drawMultyLineArc(g, SynAnPanel, leftPoint, rightPoint);
		}
		Font ff = новый Font("Arial", Font.PLAIN, 12);
		g.setFont(ff);
		Font oldFont = g.getFont();
		Font smallFont = VisualSynAnPanel.getSmallFont(g);
		g.setFont(smallFont);
		g.drawString(m_strName, m_ParentGroupLeg.x + 2, m_ParentGroupLeg.y - 2);
		g.setFont(oldFont);
	}

	доступный цел getHeight() {
		если (m_Height == 0) {
			m_Height = calculateHeight();
		}
		верни m_Height;
	}

	личный цел calculateHeight() {
		цел Height = 0;
		для (цел i = 0; i < m_ChildArcs.size(); i++) {
			WordArc wordArc = (WordArc) m_ChildArcs.elementAt(i);
			цел ii = wordArc.calculateHeight();
			если (ii > Height) {
				Height = ii;
			}
		}
		верни Height + VisualSynAnPanel.ArcHeight;
	}

	личный Point getParentGroupLeg() {
		верни m_ParentGroupLeg;
	}
	личный Строка m_strName;
	доступный цел m_FirstWord = 0;
	личный Point m_ParentGroupLeg;
	доступный цел m_LastWord = 0;
	личный цел m_Height = 0;
	личный логическое m_bGroupArc = истина;
	доступный логическое m_bIsSubj = ложь;
	личный Vector m_ChildArcs;
	личный логическое m_IsGroup;

}
