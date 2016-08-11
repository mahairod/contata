//package JVisualSynAn;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.PopupMenu;
import java.applet.Applet;
import java.lang.Integer;
import java.lang.Float;
import java.io.*;
//import org.w3c.dom;
import java.awt.TextComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elliptica.ling.Коннектор;
//import javax.xml.parsers.*;

/*
 * VisualSynAn.java
 *
 * Created on 20 Ноябрь 2001 г., 21:42
 */

//package VisualSynAn;

/**
 *
 * @author  pankrat
 */
public class VisualSynAn extends java.applet.Applet {

	private final static String inputStr = "_Russian#Одновременно с этим на донецком направлении двумя группировками Украина намерена начать наступление севернее и южнее Донецка в направлении Иловайска, чтобы замкнуть кольцо вокруг столицы республики.";
	
    private VisualSynAnPanel m_VisualSynanPanel;    
    public Scrollbar ranger;
    /** Initializes the applet JVisualSynAn */
    public void init() 
    {
            String struct = "#words$дом@homonyms@дом^сущ$стоял@homonyms@стоить^гл@стоять^гл$на@homonyms@на^предлог$пригорке@homonyms@пригорок^сущ#groups$0,0,0,0 @ 0,1^подл^gr$0,0,0,0 @ 0,3^гл_личн^cl @ 0,1^подл^gr ";//getParameter("struct");
			struct = "#words$Одновременно@homonyms@ОДНОВРЕМЕННО$с@homonyms@С$этим@homonyms@ЭТО$на@homonyms@НА$донецком@homonyms@ДОНЕЦКИЙ$направлении@homonyms@НАПРАВЛЕНИЕ$двумя@homonyms@ДВУМЯЯ$группировками@homonyms@ГРУППИРОВКА$Украина@homonyms@УКРАИНА$намерена@homonyms@НАМЕРЕН$начать@homonyms@НАЧАТЬ$наступление@homonyms@НАСТУПЛЕНИЕ$севернее@homonyms@СЕВЕРНЫЙ$и@homonyms@И$южнее@homonyms@ЮЖНЫЙ$Донецка@homonyms@ДОНЕЦК$в@homonyms@В@В$направлении@homonyms@НАПРАВЛЕНИЕ@НАПРАВЛЕНИЕ$Иловайска@homonyms@ИЛОВАЙСК$,@homonyms@,$чтобы@homonyms@ЧТОБЫ$замкнуть@homonyms@ЗАМКНУТЬ$кольцо@homonyms@КОЛЬЦО$вокруг@homonyms@ВОКРУГ$столицы@homonyms@СТОЛИЦА$республики@homonyms@РЕСПУБЛИКА#groups$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr#topclause";
//			try {
//				struct = new Коннектор().запрос(inputStr);
//			} catch (IOException ex) {
//				Logger.getLogger(VisualSynAn.class.getName()).log(Level.SEVERE, null, ex);
//			}
//            String struct = getParameter("struct");
            m_VisualSynanPanel = new VisualSynAnPanel(this, struct);
            m_VisualSynanPanel.setBackground(Color.lightGray);
            initComponents();        
            add(m_VisualSynanPanel, java.awt.BorderLayout.CENTER);

            ranger = new Scrollbar(Scrollbar.VERTICAL);
            ranger.addAdjustmentListener(m_VisualSynanPanel);
            add(ranger, java.awt.BorderLayout.EAST); 
            Font oldFondt = GramInfoLabel.getFont();
            
            Font newFont = new Font(oldFondt.getName(), oldFondt.getStyle() + Font.BOLD, oldFondt.getSize());
            
            GramInfoLabel.setForeground(Color.blue);
            GramInfoLabel.setFont(newFont);
            GramInfoLabel.setText("");
    }

    public void updateLabel(String text) 
    {
        GramInfoLabel.setText(text);
    }
    
    
    public synchronized void paint(Graphics g) 
    {
    }
  
    private void initComponents() {//GEN-BEGIN:initComponents
        GramInfoLabel = new java.awt.Label();
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(java.awt.Color.white);
        GramInfoLabel.setBackground(java.awt.Color.lightGray);
        GramInfoLabel.setText("label2");
        add(GramInfoLabel, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label GramInfoLabel;
    // End of variables declaration//GEN-END:variables

}

class VisualSynAnPanel  extends Panel                         
                        implements AdjustmentListener, ComponentListener
{
   static int ArcHeight = 20;

   static Font getSmallFont(Graphics g)
   {
       Font font = g.getFont();
       //float size = font.getSize()*((float)3)/((float)7);
       int size = font.getSize()*5/6;
       //int size = font.getSize();
       Font newFont = new Font(font.getName(), font.getStyle(), size);
       return newFont;
   }
   
    private void addWordArcs()
    {
        {
            m_ChildArcs = new Vector();        
            WordArc arc = new WordArc(0, 4);
            arc.addWordArc(0,1);        
            WordArc arc1 = new WordArc(2, 4, false);
            arc1.addWordArc(2,3);
            arc.addWordArc(arc1);
            m_ChildArcs.addElement(arc);                
        }
        
        {
            m_ChildArcs1 = new Vector();
            WordArc arc = new WordArc(0, 4);
            m_ChildArcs1.addElement(arc);                        
        }
    }
    
    private void addWordPanels()
    {
        for(int i = 0 ; i < m_WordPanels.size() ; i++ )
            add((WordPannel)m_WordPanels.elementAt(i));
    }
    
    protected void parseOneWord(String strWord)
    {
        StringTokenizer strTok = new StringTokenizer(strWord,"@");
        if( !strTok.hasMoreTokens() )
            return;
        String str = strTok.nextToken();
        
        WordPannel word = new WordPannel(str,this);
        if( !strTok.hasMoreTokens() )
            return;
        
        str = strTok.nextToken();
        
        if( str.compareTo("homonyms") != 0 )
            return;       
        while( strTok.hasMoreTokens() )
            word.addHomonym(strTok.nextToken());
        
        m_WordPanels.addElement(word);
    }
    
    protected void parseWords(String strWords)
    {
        StringTokenizer strTok = new StringTokenizer(strWords,"$");
        if( !strTok.hasMoreTokens() )
            return;
        String str = strTok.nextToken();
        if( str.compareTo( "words") != 0 )
            return;
        while( strTok.hasMoreTokens() )
            parseOneWord(strTok.nextToken());
    }
    
    protected void fillHomonymNumbers(HomonymNumbers homs, String str, int wordsCount) {
		String[] homonimStrings = str.split(" \\^");
        StringTokenizer strTok = new StringTokenizer(str,"; ");
        int ii = 0;
        int[] arr = new int[wordsCount];
        while( ii < homonimStrings.length && (ii < wordsCount)) {
            String strItem = homonimStrings[ii];
			strItem = strItem.substring(0, strItem.indexOf('='));
			int homNum = Integer.parseInt(strItem);
//            int homNum = Integer.decode(strItem).intValue();
            arr[ii] = homNum;
            ii++;
        }
        
        homs.m_HomNums = arr;
    }
    
    protected void parseOneVariant(String strVar)
    {
        StringTokenizer strTok = new StringTokenizer(strVar,"@");
        HomonymNumbers homs = new HomonymNumbers();
        if( !strTok.hasMoreTokens() )
            return;
        
        fillHomonymNumbers(homs,strTok.nextToken(), m_WordPanels.size());
        Vector arcs = new Vector();
        HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = new HomonymNumbers2WorddArcs();
        homonymNumbers2WorddArcs.m_SubjArcs = new Vector();
        
        while( strTok.hasMoreTokens() )
        {
            WordArc arc = new WordArc(strTok.nextToken());
            if( arc.m_bIsSubj )
                homonymNumbers2WorddArcs.m_SubjArcs.addElement(arc);
            else
                arcs.addElement(arc);
        }
        
        homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
        homonymNumbers2WorddArcs.m_Arcs = new Vector();
        orderArcs(arcs, homonymNumbers2WorddArcs.m_Arcs);
        m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
    }
    
    protected int orderArcsRec(Vector arcs, WordArc parentArc, int iCur)
    {        
        for(int i = iCur ; i < arcs.size() ;  )
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            if( arc.m_FirstWord > parentArc.m_LastWord )
                return i;
            i = orderArcsRec(arcs, arc, i + 1);            
            parentArc.addWordArc(arc);
        }
        return arcs.size();
    }
    
    protected void orderArcs(Vector arcs, Vector newVector)
    {
        for(int i = 0 ; i < arcs.size() ;  )
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            i = orderArcsRec(arcs, arc, i + 1 );
            newVector.addElement(arc);
        }
    }
    
    protected void parseVariants(String strVars)
    {
        StringTokenizer strTok = new StringTokenizer(strVars,"$");
        if( !strTok.hasMoreTokens() )
            return;
        String str = strTok.nextToken();
        if( str.compareTo("groups") != 0)
            return;
        while( strTok.hasMoreTokens() )
            parseOneVariant(strTok.nextToken());    
        setActiveHomonyms();
    }
    
    protected void setActiveHomonyms()
    {
        if( m_HomNums2ArcVariant.size() == 0 )
            return;
        HomonymNumbers2WorddArcs hom = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(0);
        
        for(int i = 0 ; i < hom.m_HomonymNumbers.m_HomNums.length ; i++ )
        {
            WordPannel panel = getWordPannel(i);            
            panel.m_iActiveHomonym = hom.m_HomonymNumbers.m_HomNums[i];
        }
    }
    
    protected void parseMainArgumnet(String mainStr)
    {
        StringTokenizer strTok = new StringTokenizer(mainStr,"#");
        if( !strTok.hasMoreTokens() )
            return;
        parseWords(strTok.nextToken());
        if( !strTok.hasMoreTokens() )
            return;
        parseVariants(strTok.nextToken());
    }
    
    public VisualSynAnPanel(VisualSynAn VisualSynAnApplet, String strArg)
    {
        super();                   
        m_WordPanels = new Vector();        
        m_HomNums2ArcVariant = new Vector();
        m_VisualSynAnApplet = VisualSynAnApplet;                
        m_bValidate = false;
        setLayout(new java.awt.FlowLayout(FlowLayout.LEFT,10, 10));                                      
        parseMainArgumnet(strArg);
        
         /*
        addWordArcs();
        
        
      
        {
            int[] arr = {0,0,0,0,0};        
            HomonymNumbers homs = new HomonymNumbers(arr);
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = new HomonymNumbers2WorddArcs();
            homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
            homonymNumbers2WorddArcs.m_Arcs = m_ChildArcs;
            m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
        }
        
        {
            int[] arr = {1,0,0,0,0};        
            HomonymNumbers homs = new HomonymNumbers(arr);
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = new HomonymNumbers2WorddArcs();
            homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
            homonymNumbers2WorddArcs.m_Arcs = m_ChildArcs1;            
            m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
        }
        m_bValidate = false;
        setLayout(new java.awt.FlowLayout(FlowLayout.LEFT,10, 10));                              
        
        m_WordPanels = new Vector();        
        
        
        m_WordPanels.addElement(new WordPannel("$$",this));
        m_WordPanels.addElement(new WordPannel("the_word2",this));
        m_WordPanels.addElement(new WordPannel("the_word3qy",this));
        m_WordPanels.addElement(new WordPannel("the_word4",this));
        m_WordPanels.addElement(new WordPannel("the",this));                                        
        */
        addWordPanels();
        addComponentListener(this);
    }  
    
    private void setWordPannelsSize(Graphics g)
    {
        int size = m_WordPanels.size();
        for(int i = 0 ; i < size ; i++ )
        {            
            WordPannel wPanel = (WordPannel)m_WordPanels.elementAt(i);
            wPanel.calcSize(g);
        }
    }
    
    private void drawCanvas(Graphics g)
    {
        Color bg = getBackground(); 
        g.setColor(bg);        
        Dimension size = getSize();              
        g.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
        g.draw3DRect(3, 3, size.width - 7, size.height - 7, false);          
        g.setColor(Color.black);     
    }
    
    private int calcMaxArcHeight(Vector arcs)
    {
        int height = 0;
        for(int i = 0 ; i < arcs.size() ; i++ )
        {
            int curHeight = ((WordArc)arcs.elementAt(i)).getHeight();
            if(  curHeight > height )
                height = curHeight;
        }
        return height;
    }
    
    public synchronized void validate()
    {
        reposeWordPanels();
        super.validate();
    }
    
    public synchronized void update(Graphics g) 
    {
        reposeWordPanels();
        super.update(g);
    }
    
    public synchronized void paintComponents(Graphics g)
    {
        //reposeWordPanels();
        super.paintComponents(g);
    }
    
    public synchronized void paint(Graphics g) 
    {        
        
        Vector arcs = getCurArcs();                
        
        if( m_bValidate )
        {   
            int height = calcMaxArcHeight(arcs);
            ((FlowLayout)getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
            yScrolBarSpan = 0;
            yScrolBarVal = 0;
            validate();                     
            m_bValidate = false;        
            Component comp = getComponent( getComponentCount() - 1 );
            m_VisualSynAnApplet.ranger.setValues(0, getBounds().height,0, comp.getBounds().y + comp.getBounds().height + 20);                        
            setWordPanelsInitialY();
        }         
        drawArcs(g, arcs);    
        drawCanvas(g);        
        drawSubjPredic(g);
    }    

    private void drawSubjPredic(Graphics g)
    {
        if( m_iCurHoms < 0 )
            return;
        HomonymNumbers2WorddArcs homs = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(m_iCurHoms);
        for(int i = 0 ; i < homs.m_SubjArcs.size() ; i++ )
        {
            WordArc arc = (WordArc)homs.m_SubjArcs.elementAt(i);
            WordPannel panel1 = (WordPannel)m_WordPanels.elementAt(arc.m_FirstWord);
            WordPannel panel2 = (WordPannel)m_WordPanels.elementAt(arc.m_LastWord);
            drawSubj(panel1, g);
            drawPredic(panel2, g);
            
        }
    }

     private void drawSubj(WordPannel panel, Graphics g)
     {
        Rectangle rect = panel.getBounds();
        g.drawLine(rect.x, rect.y + rect.height , rect.x + rect.width, rect.y + rect.height );
     }
     
     private void drawPredic(WordPannel panel, Graphics g)
     {
        Rectangle rect = panel.getBounds();
        g.drawLine(rect.x, rect.y + rect.height , rect.x + rect.width, rect.y + rect.height );
        g.drawLine(rect.x, rect.y + rect.height + 2, rect.x + rect.width, rect.y + rect.height + 2);
     }

    
    private Vector getCurArcs()
    {
        HomonymNumbers homs = getActiveHomonymNumbers();
        
        for(int i = 0 ; i < m_HomNums2ArcVariant.size() ; i++ )
        {
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(i);            
            if( homonymNumbers2WorddArcs.equals(homs) )
            {
                m_iCurHoms = i;
                return homonymNumbers2WorddArcs.m_Arcs;
            }
            
        }
        
        m_iCurHoms = -1;
        return null;
    }

    private void drawArcs(Graphics g, Vector arcs)
    {
        for(int i = 0 ; i < arcs.size() ; i++ )                        
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            arc.Draw(g, this);
        }
    }

    
    public WordPannel getWordPannel(int i )
    {
        return  (WordPannel)(m_WordPanels.elementAt(i));
    }
    
    public void setValidate(boolean val)
    {
        m_bValidate = val;
    }
    
    private HomonymNumbers getActiveHomonymNumbers()
    {
        int[] homs = new int[m_WordPanels.size()];
        for(int i = 0 ; i < m_WordPanels.size() ; i++ )
            homs[i] = getWordPannel(i).getActiveHomonym();
        
        return new HomonymNumbers(homs);
    }
    
    public void reposeWordPanels()
    {
        for(int i = 0 ; i < m_WordPanels.size() ; i++ )
        {
            WordPannel word = getWordPannel(i);
            Rectangle oldRect = word.getBounds();            
            //getWordPannel(i).setLocation(oldRect.x, oldRect.y + yScrolBarSpan);                              
            word.setLocation(oldRect.x, word.m_Y - yScrolBarVal);                    
        }
    }
    
   
    public void setWordPanelsInitialY()
    {
        for(int i = 0 ; i < m_WordPanels.size() ; i++ )
        {
            Rectangle Rect = getWordPannel(i).getBounds();            
            getWordPannel(i).m_Y = Rect.y;
        }
    }
   
    public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) 
    {
        int val = adjustmentEvent.getValue();
        yScrolBarSpan =  yScrolBarVal - val;
        yScrolBarVal = val;
        repaint();
   }
    
    public void componentShown(java.awt.event.ComponentEvent componentEvent) {
    }
    
    public void componentMoved(java.awt.event.ComponentEvent componentEvent) {
    }
    
    public void componentResized(ComponentEvent componentEvent) 
    {
        Vector arcs = getCurArcs();
        Graphics g = getGraphics();
        
        yScrolBarSpan = 0;
        yScrolBarVal = 0;

        setWordPannelsSize(g);                
        int height = calcMaxArcHeight(arcs);
        ((FlowLayout)getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
        validate();            
        m_bValidate = false;        
        Component comp = getComponent( getComponentCount() - 1 );
        m_VisualSynAnApplet.ranger.setValues(0, getBounds().height,0, comp.getBounds().y + comp.getBounds().height + 20);        
        setWordPanelsInitialY();
    }
    
    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {
    }
    
    public VisualSynAn m_VisualSynAnApplet;           
    private Vector m_ChildArcs;
    private Vector m_ChildArcs1;
    //private TreeMap m_HomNums2ArcVariant;
    private Vector m_HomNums2ArcVariant;
    private int m_iCurHoms = 0;
    private Vector m_WordPanels;
    private boolean m_bValidate;
    private Scrollbar ranger;
    private int yScrolBarVal = 0;
    private int yScrolBarSpan = 0;
}
