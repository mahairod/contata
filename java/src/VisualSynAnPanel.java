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
внеся java.awt.Component;
внеся java.awt.Dimension;
внеся java.awt.FlowLayout;
внеся java.awt.Font;
внеся java.awt.Graphics;
внеся java.awt.Rectangle;
внеся java.awt.event.ComponentEvent;
внеся java.awt.event.ComponentListener;
внеся java.util.StringTokenizer;
внеся java.util.Список;
внеся java.util.ArrayList;
внеся java.util.Коллекции;
внеся javax.swing.JPanel;
внеся javax.swing.Scrollable;
внеся org.elliptica.ling.syntax.СинтаксическоеДерево;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс VisualSynAnPanel расширяет JPanel воплощает Scrollable, ComponentListener {

	статичный цел ArcHeight = 20;
	защищённый цел номерПредложения = 0;
	защищённый цел номерВарианта = 0;

	статичный Font getSmallFont(Graphics g) {
		Font font = g.getFont();
		//дроб size = font.getSize()*((дроб)3)/((дроб)7);
		цел size = font.getSize() * 5 / 6;
		//цел size = font.getSize();
		Font newFont = новый Font(font.getName(), font.getStyle(), size);
		верни newFont;
	}

	защищённый тщетный addWordPanels() {
		для (WordPannel pannel: m_WordPanels) {
			add(pannel);
		}
	}

	защищённый тщетный parseOneWord(Строка strWord) {
		StringTokenizer strTok = новый StringTokenizer(strWord, "@");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		Строка str = strTok.nextToken();
		WordPannel word = новый WordPannel(str, это);
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		str = strTok.nextToken();
		если (str.compareTo("homonyms") != 0) {
			верни;
		}
		пока (strTok.hasMoreTokens()) {
			word.addHomonym(strTok.nextToken());
		}
		m_WordPanels.add(word);
	}

	защищённый тщетный parseWords(Строка strWords) {
		StringTokenizer strTok = новый StringTokenizer(strWords, "$");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		Строка str = strTok.nextToken();
		если (str.compareTo("words") != 0) {
			верни;
		}
		пока (strTok.hasMoreTokens()) {
			parseOneWord(strTok.nextToken());
		}
	}

	защищённый тщетный fillHomonymNumbers(HomonymNumbers homs, Строка str, цел wordsCount) {
		Строка[] homonimStrings = str.split(" \\^");
		StringTokenizer strTok = новый StringTokenizer(str, "; ");
		цел ii = 0;
		цел[] arr = новый цел[wordsCount];
		пока (ii < homonimStrings.length && (ii < wordsCount)) {
			Строка strItem = homonimStrings[ii];
			strItem = strItem.substring(0, strItem.indexOf('='));
			цел homNum = Integer.parseInt(strItem);
			//            цел homNum = Integer.decode(strItem).intValue();
			arr[ii] = homNum;
			ii++;
		}
		homs.m_HomNums = arr;
	}

	защищённый тщетный parseOneVariant(Строка strVar) {
		StringTokenizer strTok = новый StringTokenizer(strVar, "@");
		HomonymNumbers homs = новый HomonymNumbers();
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		fillHomonymNumbers(homs, strTok.nextToken(), m_WordPanels.size());
		Список arcs = новый ArrayList<>();
		HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = новый HomonymNumbers2WorddArcs();
		homonymNumbers2WorddArcs.m_SubjArcs = новый ArrayList<>();
		пока (strTok.hasMoreTokens()) {
			WordArc arc = новый WordArc(strTok.nextToken());
			если (arc.m_bIsSubj) {
				homonymNumbers2WorddArcs.m_SubjArcs.add(arc);
			} иначе {
				arcs.add(arc);
			}
		}
		homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
		homonymNumbers2WorddArcs.m_Arcs = новый ArrayList();
		orderArcs(arcs, homonymNumbers2WorddArcs.m_Arcs);
		m_HomNums2ArcVariant.add(homonymNumbers2WorddArcs);
	}

	защищённый цел orderArcsRec(Список<WordArc> arcs, WordArc parentArc, цел iCur) {
		для (цел i = iCur; i < arcs.size();) {
			WordArc arc = arcs.get(i);
			если (arc.m_FirstWord > parentArc.m_LastWord) {
				верни i;
			}
			i = orderArcsRec(arcs, arc, i + 1);
			parentArc.addWordArc(arc);
		}
		верни arcs.size();
	}

	защищённый тщетный orderArcs(Список<WordArc> arcs, Список newVector) {
		для (цел i = 0; i < arcs.size();) {
			WordArc arc = arcs.get(i);
			i = orderArcsRec(arcs, arc, i + 1);
			newVector.add(arc);
		}
	}

	защищённый тщетный parseVariants(Строка strVars) {
		StringTokenizer strTok = новый StringTokenizer(strVars, "$");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		Строка str = strTok.nextToken();
		если (str.compareTo("groups") != 0) {
			верни;
		}
		пока (strTok.hasMoreTokens()) {
			parseOneVariant(strTok.nextToken());
		}
		setActiveHomonyms();
	}

	защищённый тщетный setActiveHomonyms() {
		если (m_HomNums2ArcVariant.isEmpty()) {
			верни;
		}
		HomonymNumbers2WorddArcs hom = m_HomNums2ArcVariant.get(0);
		для (цел i = 0; i < hom.m_HomonymNumbers.m_HomNums.length; i++) {
			WordPannel panel = getWordPannel(i);
			panel.m_iActiveHomonym = hom.m_HomonymNumbers.m_HomNums[i];
		}
	}

	защищённый тщетный parseMainArgumnet(Строка mainStr) {
		StringTokenizer strTok = новый StringTokenizer(mainStr, "#");
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		parseWords(strTok.nextToken());
		если (!strTok.hasMoreTokens()) {
			верни;
		}
		parseVariants(strTok.nextToken());
	}

	доступный VisualSynAnPanel(){
		m_bValidate = ложь;
		setLayout(новый FlowLayout(FlowLayout.LEFT, 10, 10));
		addComponentListener(это);
	}

	доступный тщетный setMainView(SynanViewer synanViewer) {
		это.synanViewer = synanViewer;
	}

	доступный тщетный setStringArgs(СинтаксическоеДерево дерево) {
	}

	доступный тщетный clear() {
		removeAll();
		m_WordPanels.clear();
		m_HomNums2ArcVariant.clear();
	}

	доступный тщетный setStringArgs(Строка strArg) {
		parseMainArgumnet(strArg);
		addWordPanels();
		обновиРазмеры();
	}

	личный тщетный setWordPannelsSize(Graphics g) {
		для (WordPannel wPanel: m_WordPanels) {
			wPanel.calcSize(g);
		}
	}

	личный тщетный drawCanvas(Graphics g) {
		Color bg = getBackground();
		g.setColor(bg);
		Dimension size = getSize();
		g.draw3DRect(0, 0, size.width - 1, size.height - 1, истина);
		g.draw3DRect(3, 3, size.width - 7, size.height - 7, ложь);
		g.setColor(Color.black);
	}

	личный цел calcMaxArcHeight(Список<WordArc> arcs) {
		цел height = 0;
		для (WordArc arc: arcs){
			цел curHeight = arc.getHeight();
			если (curHeight > height) {
				height = curHeight;
			}
		}
		верни height;
	}

	доступный синхронизированный тщетный validate() {
		reposeWordPanels();
		поверх.validate();
	}

	доступный синхронизированный тщетный update(Graphics g) {
		reposeWordPanels();
		поверх.update(g);
	}

	доступный синхронизированный тщетный paintComponents(Graphics g) {
		//reposeWordPanels();
		поверх.paintComponents(g);
	}

	доступный синхронизированный тщетный paint(Graphics g) {
		Список<WordArc> arcs = getCurArcs();
		если (m_bValidate) {
			цел height = calcMaxArcHeight(arcs);
			((FlowLayout) getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
			yScrolBarSpan = 0;
			yScrolBarVal = 0;
			validate();
			обновиРазмеры();
			setWordPanelsInitialY();
		}
		drawArcs(g, arcs);
		drawCanvas(g);
		drawSubjPredic(g);
	}

	личный тщетный drawSubjPredic(Graphics g) {
		если (m_iCurHoms < 0) {
			верни;
		}
		HomonymNumbers2WorddArcs homs = m_HomNums2ArcVariant.get(m_iCurHoms);
		для (WordArc arc : homs.m_SubjArcs) {
			WordPannel panel1 = m_WordPanels.get(arc.m_FirstWord);
			WordPannel panel2 = m_WordPanels.get(arc.m_LastWord);
			drawSubj(panel1, g);
			drawPredic(panel2, g);
		}
	}

	личный тщетный drawSubj(WordPannel panel, Graphics g) {
		Rectangle rect = panel.getBounds();
		g.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);
	}

	личный тщетный drawPredic(WordPannel panel, Graphics g) {
		Rectangle rect = panel.getBounds();
		g.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);
		g.drawLine(rect.x, rect.y + rect.height + 2, rect.x + rect.width, rect.y + rect.height + 2);
	}

	личный Список<WordArc> getCurArcs() {
		HomonymNumbers homs = getActiveHomonymNumbers();
		для (цел i = 0; i < m_HomNums2ArcVariant.size(); i++) {
			HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = m_HomNums2ArcVariant.get(i);
			если (homonymNumbers2WorddArcs.equals(homs)) {
				m_iCurHoms = i;
				верни homonymNumbers2WorddArcs.m_Arcs;
			}
		}
		m_iCurHoms = -1;
		верни Коллекции.ПУСТОЙ_СПИСОК;
	}

	личный тщетный drawArcs(Graphics g, Список<WordArc> arcs) {
		для (WordArc arc: arcs){
			arc.Draw(g, это);
		}
	}

	WordPannel getWordPannel(цел i) {
		верни m_WordPanels.get(i);
	}

	доступный тщетный setValidate(логическое val) {
		m_bValidate = val;
	}

	доступный логическое getValidate() {
		верни m_bValidate;
	}

	личный HomonymNumbers getActiveHomonymNumbers() {
		цел[] homs = новый цел[m_WordPanels.size()];
		для (цел i = 0; i < m_WordPanels.size(); i++) {
			homs[i] = getWordPannel(i).getActiveHomonym();
		}
		верни новый HomonymNumbers(homs);
	}

	доступный тщетный reposeWordPanels() {
		для (WordPannel word: m_WordPanels) {
			Rectangle oldRect = word.getBounds();
			//getWordPannel(i).setLocation(oldRect.x, oldRect.y + yScrolBarSpan);
			word.setLocation(oldRect.x, word.m_Y - yScrolBarVal);
		}
	}

	доступный тщетный setWordPanelsInitialY() {
		для (цел i = 0; i < m_WordPanels.size(); i++) {
			Rectangle Rect = getWordPannel(i).getBounds();
			getWordPannel(i).m_Y = Rect.y;
		}
	}

	доступный тщетный componentShown(ComponentEvent componentEvent) {
	}

	доступный тщетный componentMoved(ComponentEvent componentEvent) {
	}

	доступный тщетный componentResized(ComponentEvent componentEvent) {
		Список arcs = getCurArcs();
		Graphics g = getGraphics();
		yScrolBarSpan = 0;
		yScrolBarVal = 0;
		setWordPannelsSize(g);
		цел height = calcMaxArcHeight(arcs);
		((FlowLayout) getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
		validate();
		обновиРазмеры();
		setWordPanelsInitialY();
	}

	защищённый тщетный обновиРазмеры() {
		m_bValidate = ложь;
		Component comp = getComponent(getComponentCount() - 1);
		Dimension prefDim = getPreferredSize();
		prefDim.height = (цел) comp.getBounds().getMaxY() + 20;
		setPreferredSize(prefDim);
		revalidate();
	}

	доступный тщетный componentHidden(ComponentEvent componentEvent) {
	}

	SynanViewer synanViewer;

	личный Список<WordArc> m_ChildArcs;
	личный Список<WordArc> m_ChildArcs1;
	//личный TreeMap m_HomNums2ArcVariant;
	личный цел m_iCurHoms = 0;
	защищённый итоговый Список<WordPannel> m_WordPanels = новый ArrayList<>();
	защищённый итоговый Список<HomonymNumbers2WorddArcs> m_HomNums2ArcVariant = новый ArrayList<>();
	личный логическое m_bValidate;
	личный цел yScrolBarVal = 0;
	личный цел yScrolBarSpan = 0;

	@Подмени
	доступный Dimension getPreferredScrollableViewportSize() {
		верни getPreferredSize();
	}

	@Подмени
	доступный цел getScrollableUnitIncrement(Rectangle visibleRect, цел orientation, цел direction) {
		верни 10;
	}

	@Подмени
	доступный цел getScrollableBlockIncrement(Rectangle visibleRect, цел orientation, цел direction) {
		верни 10;
	}

	@Подмени
	доступный логическое getScrollableTracksViewportWidth() {
		верни истина;
	}

	@Подмени
	доступный логическое getScrollableTracksViewportHeight() {
		верни ложь;
	}

}
