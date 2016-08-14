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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class VisualSynAnPanel extends JPanel implements Scrollable, ComponentListener {

	static int ArcHeight = 20;

	static Font getSmallFont(Graphics g) {
		Font font = g.getFont();
		//float size = font.getSize()*((float)3)/((float)7);
		int size = font.getSize() * 5 / 6;
		//int size = font.getSize();
		Font newFont = new Font(font.getName(), font.getStyle(), size);
		return newFont;
	}

	private void addWordPanels() {
		for (WordPannel pannel: m_WordPanels) {
			add(pannel);
		}
	}

	protected void parseOneWord(String strWord) {
		StringTokenizer strTok = new StringTokenizer(strWord, "@");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		String str = strTok.nextToken();
		WordPannel word = new WordPannel(str, this);
		if (!strTok.hasMoreTokens()) {
			return;
		}
		str = strTok.nextToken();
		if (str.compareTo("homonyms") != 0) {
			return;
		}
		while (strTok.hasMoreTokens()) {
			word.addHomonym(strTok.nextToken());
		}
		m_WordPanels.add(word);
	}

	protected void parseWords(String strWords) {
		StringTokenizer strTok = new StringTokenizer(strWords, "$");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		String str = strTok.nextToken();
		if (str.compareTo("words") != 0) {
			return;
		}
		while (strTok.hasMoreTokens()) {
			parseOneWord(strTok.nextToken());
		}
	}

	protected void fillHomonymNumbers(HomonymNumbers homs, String str, int wordsCount) {
		String[] homonimStrings = str.split(" \\^");
		StringTokenizer strTok = new StringTokenizer(str, "; ");
		int ii = 0;
		int[] arr = new int[wordsCount];
		while (ii < homonimStrings.length && (ii < wordsCount)) {
			String strItem = homonimStrings[ii];
			strItem = strItem.substring(0, strItem.indexOf('='));
			int homNum = Integer.parseInt(strItem);
			//            int homNum = Integer.decode(strItem).intValue();
			arr[ii] = homNum;
			ii++;
		}
		homs.m_HomNums = arr;
	}

	protected void parseOneVariant(String strVar) {
		StringTokenizer strTok = new StringTokenizer(strVar, "@");
		HomonymNumbers homs = new HomonymNumbers();
		if (!strTok.hasMoreTokens()) {
			return;
		}
		fillHomonymNumbers(homs, strTok.nextToken(), m_WordPanels.size());
		List arcs = new ArrayList<>();
		HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = new HomonymNumbers2WorddArcs();
		homonymNumbers2WorddArcs.m_SubjArcs = new ArrayList<>();
		while (strTok.hasMoreTokens()) {
			WordArc arc = new WordArc(strTok.nextToken());
			if (arc.m_bIsSubj) {
				homonymNumbers2WorddArcs.m_SubjArcs.add(arc);
			} else {
				arcs.add(arc);
			}
		}
		homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
		homonymNumbers2WorddArcs.m_Arcs = new ArrayList();
		orderArcs(arcs, homonymNumbers2WorddArcs.m_Arcs);
		m_HomNums2ArcVariant.add(homonymNumbers2WorddArcs);
	}

	protected int orderArcsRec(List<WordArc> arcs, WordArc parentArc, int iCur) {
		for (int i = iCur; i < arcs.size();) {
			WordArc arc = arcs.get(i);
			if (arc.m_FirstWord > parentArc.m_LastWord) {
				return i;
			}
			i = orderArcsRec(arcs, arc, i + 1);
			parentArc.addWordArc(arc);
		}
		return arcs.size();
	}

	protected void orderArcs(List<WordArc> arcs, List newVector) {
		for (int i = 0; i < arcs.size();) {
			WordArc arc = arcs.get(i);
			i = orderArcsRec(arcs, arc, i + 1);
			newVector.add(arc);
		}
	}

	protected void parseVariants(String strVars) {
		StringTokenizer strTok = new StringTokenizer(strVars, "$");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		String str = strTok.nextToken();
		if (str.compareTo("groups") != 0) {
			return;
		}
		while (strTok.hasMoreTokens()) {
			parseOneVariant(strTok.nextToken());
		}
		setActiveHomonyms();
	}

	protected void setActiveHomonyms() {
		if (m_HomNums2ArcVariant.isEmpty()) {
			return;
		}
		HomonymNumbers2WorddArcs hom = m_HomNums2ArcVariant.get(0);
		for (int i = 0; i < hom.m_HomonymNumbers.m_HomNums.length; i++) {
			WordPannel panel = getWordPannel(i);
			panel.m_iActiveHomonym = hom.m_HomonymNumbers.m_HomNums[i];
		}
	}

	protected void parseMainArgumnet(String mainStr) {
		StringTokenizer strTok = new StringTokenizer(mainStr, "#");
		if (!strTok.hasMoreTokens()) {
			return;
		}
		parseWords(strTok.nextToken());
		if (!strTok.hasMoreTokens()) {
			return;
		}
		parseVariants(strTok.nextToken());
	}

	public VisualSynAnPanel(){
	}

	public void setMainView(SynanViewer synanViewer) {
		this.synanViewer = synanViewer;
	}

	public void clear() {
		removeAll();
		m_WordPanels.clear();
		m_HomNums2ArcVariant.clear();
	}

	public void setStringArgs(String strArg) {
		m_bValidate = false;
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		parseMainArgumnet(strArg);
		addWordPanels();
		addComponentListener(this);
	}

	private void setWordPannelsSize(Graphics g) {
		for (WordPannel wPanel: m_WordPanels) {
			wPanel.calcSize(g);
		}
	}

	private void drawCanvas(Graphics g) {
		Color bg = getBackground();
		g.setColor(bg);
		Dimension size = getSize();
		g.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
		g.draw3DRect(3, 3, size.width - 7, size.height - 7, false);
		g.setColor(Color.black);
	}

	private int calcMaxArcHeight(List<WordArc> arcs) {
		int height = 0;
		for (WordArc arc: arcs){
			int curHeight = arc.getHeight();
			if (curHeight > height) {
				height = curHeight;
			}
		}
		return height;
	}

	public synchronized void validate() {
		reposeWordPanels();
		super.validate();
	}

	public synchronized void update(Graphics g) {
		reposeWordPanels();
		super.update(g);
	}

	public synchronized void paintComponents(Graphics g) {
		//reposeWordPanels();
		super.paintComponents(g);
	}

	public synchronized void paint(Graphics g) {
		List<WordArc> arcs = getCurArcs();
		if (m_bValidate) {
			int height = calcMaxArcHeight(arcs);
			((FlowLayout) getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
			yScrolBarSpan = 0;
			yScrolBarVal = 0;
			validate();
			m_bValidate = false;
			Component comp = getComponent(getComponentCount() - 1);
//			synanViewer.ranger.setValues(0, getBounds().height, 0, comp.getBounds().y + comp.getBounds().height + 20);
			setWordPanelsInitialY();
		}
		drawArcs(g, arcs);
		drawCanvas(g);
		drawSubjPredic(g);
	}

	private void drawSubjPredic(Graphics g) {
		if (m_iCurHoms < 0) {
			return;
		}
		HomonymNumbers2WorddArcs homs = m_HomNums2ArcVariant.get(m_iCurHoms);
		for (WordArc arc : homs.m_SubjArcs) {
			WordPannel panel1 = m_WordPanels.get(arc.m_FirstWord);
			WordPannel panel2 = m_WordPanels.get(arc.m_LastWord);
			drawSubj(panel1, g);
			drawPredic(panel2, g);
		}
	}

	private void drawSubj(WordPannel panel, Graphics g) {
		Rectangle rect = panel.getBounds();
		g.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);
	}

	private void drawPredic(WordPannel panel, Graphics g) {
		Rectangle rect = panel.getBounds();
		g.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);
		g.drawLine(rect.x, rect.y + rect.height + 2, rect.x + rect.width, rect.y + rect.height + 2);
	}

	private List<WordArc> getCurArcs() {
		HomonymNumbers homs = getActiveHomonymNumbers();
		for (int i = 0; i < m_HomNums2ArcVariant.size(); i++) {
			HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = m_HomNums2ArcVariant.get(i);
			if (homonymNumbers2WorddArcs.equals(homs)) {
				m_iCurHoms = i;
				return homonymNumbers2WorddArcs.m_Arcs;
			}
		}
		m_iCurHoms = -1;
		return Collections.EMPTY_LIST;
	}

	private void drawArcs(Graphics g, List<WordArc> arcs) {
		for (WordArc arc: arcs){
			arc.Draw(g, this);
		}
	}

	WordPannel getWordPannel(int i) {
		return m_WordPanels.get(i);
	}

	public void setValidate(boolean val) {
		m_bValidate = val;
	}

	public boolean getValidate() {
		return m_bValidate;
	}

	private HomonymNumbers getActiveHomonymNumbers() {
		int[] homs = new int[m_WordPanels.size()];
		for (int i = 0; i < m_WordPanels.size(); i++) {
			homs[i] = getWordPannel(i).getActiveHomonym();
		}
		return new HomonymNumbers(homs);
	}

	public void reposeWordPanels() {
		for (WordPannel word: m_WordPanels) {
			Rectangle oldRect = word.getBounds();
			//getWordPannel(i).setLocation(oldRect.x, oldRect.y + yScrolBarSpan);
			word.setLocation(oldRect.x, word.m_Y - yScrolBarVal);
		}
	}

	public void setWordPanelsInitialY() {
		for (int i = 0; i < m_WordPanels.size(); i++) {
			Rectangle Rect = getWordPannel(i).getBounds();
			getWordPannel(i).m_Y = Rect.y;
		}
	}

	public void componentShown(ComponentEvent componentEvent) {
	}

	public void componentMoved(ComponentEvent componentEvent) {
	}

	public void componentResized(ComponentEvent componentEvent) {
		List arcs = getCurArcs();
		Graphics g = getGraphics();
		yScrolBarSpan = 0;
		yScrolBarVal = 0;
		setWordPannelsSize(g);
		int height = calcMaxArcHeight(arcs);
		((FlowLayout) getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
		validate();
		m_bValidate = false;
		Component comp = getComponent(getComponentCount() - 1);
		Dimension prefDim = getPreferredSize();
		prefDim.height = (int) comp.getBounds().getMaxY() + 20;
		setPreferredSize(prefDim);
		revalidate();
//		synanViewer.ranger.setValues(0, getBounds().height, 0, comp.getBounds().y + comp.getBounds().height + 20);
		setWordPanelsInitialY();
	}

	public void componentHidden(ComponentEvent componentEvent) {
	}

	SynanViewer synanViewer;

	//private TreeMap m_HomNums2ArcVariant;
	private int m_iCurHoms = 0;
	private final List<WordPannel> m_WordPanels = new ArrayList<>();
	private final List<HomonymNumbers2WorddArcs> m_HomNums2ArcVariant = new ArrayList<>();
	private boolean m_bValidate;
	private int yScrolBarVal = 0;
	private int yScrolBarSpan = 0;

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

}
