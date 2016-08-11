//пакет JVisualSynAn;

внеся java.util.*;
внеся java.awt.*;
внеся java.awt.event.*;
внеся java.awt.PopupMenu;
внеся java.applet.Applet;
внеся java.lang.Integer;
внеся java.lang.Float;
внеся java.io.*;
//внеся org.w3c.dom;
внеся java.awt.TextComponent;
внеся java.util.logging.Level;
внеся java.util.logging.Logger;
//внеся javax.xml.parsers.*;

/*
 * VisualSynAn.java
 *
 * Created on 20 Ноябрь 2001 г., 21:42
 */

//пакет VisualSynAn;

/**
 *
 * @author  pankrat
 */
доступный класс VisualSynAn расширяет java.applet.Applet {

	личный итоговый статичный Строка inputStr = "_Russian#Одновременно с этим на донецком направлении двумя группировками Украина намерена начать наступление севернее и южнее Донецка в направлении Иловайска, чтобы замкнуть кольцо вокруг столицы республики.";

    личный VisualSynAnPanel m_VisualSynanPanel;
    доступный Scrollbar ranger;
    /** Initializes the applet JVisualSynAn */
    доступный тщетный init()
    {
            Строка struct = "#words$дом@homonyms@дом^сущ$стоял@homonyms@стоить^гл@стоять^гл$на@homonyms@на^предлог$пригорке@homonyms@пригорок^сущ#groups$0,0,0,0 @ 0,1^подл^gr$0,0,0,0 @ 0,3^гл_личн^cl @ 0,1^подл^gr ";//getParameter("struct");
			struct = "#words$Одновременно@homonyms@ОДНОВРЕМЕННО$с@homonyms@С$этим@homonyms@ЭТО$на@homonyms@НА$донецком@homonyms@ДОНЕЦКИЙ$направлении@homonyms@НАПРАВЛЕНИЕ$двумя@homonyms@ДВУМЯЯ$группировками@homonyms@ГРУППИРОВКА$Украина@homonyms@УКРАИНА$намерена@homonyms@НАМЕРЕН$начать@homonyms@НАЧАТЬ$наступление@homonyms@НАСТУПЛЕНИЕ$севернее@homonyms@СЕВЕРНЫЙ$и@homonyms@И$южнее@homonyms@ЮЖНЫЙ$Донецка@homonyms@ДОНЕЦК$в@homonyms@В@В$направлении@homonyms@НАПРАВЛЕНИЕ@НАПРАВЛЕНИЕ$Иловайска@homonyms@ИЛОВАЙСК$,@homonyms@,$чтобы@homonyms@ЧТОБЫ$замкнуть@homonyms@ЗАМКНУТЬ$кольцо@homonyms@КОЛЬЦО$вокруг@homonyms@ВОКРУГ$столицы@homonyms@СТОЛИЦА$республики@homonyms@РЕСПУБЛИКА#groups$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr#topclause";
//			попробуй {
//				struct = новый Коннектор().запрос(inputStr);
//			} ловя (IOException ex) {
//				Logger.getLogger(VisualSynAn.класс.getName()).log(Level.SEVERE, ничто, ex);
//			}
//            Строка struct = getParameter("struct");
            m_VisualSynanPanel = новый VisualSynAnPanel(это, struct);
            m_VisualSynanPanel.setBackground(Color.lightGray);
            initComponents();        
            add(m_VisualSynanPanel, java.awt.BorderLayout.CENTER);

            ranger = новый Scrollbar(Scrollbar.VERTICAL);
            ranger.addAdjustmentListener(m_VisualSynanPanel);
            add(ranger, java.awt.BorderLayout.EAST); 
            Font oldFondt = GramInfoLabel.getFont();
            
            Font newFont = новый Font(oldFondt.getName(), oldFondt.getStyle() + Font.BOLD, oldFondt.getSize());
            
            GramInfoLabel.setForeground(Color.blue);
            GramInfoLabel.setFont(newFont);
            GramInfoLabel.setText("");
    }

    доступный тщетный updateLabel(Строка text) 
    {
        GramInfoLabel.setText(text);
    }
    
    
    доступный синхронизированный тщетный paint(Graphics g) 
    {
    }
  
    личный тщетный initComponents() {//GEN-BEGIN:initComponents
        GramInfoLabel = новый java.awt.Label();
        
        setLayout(новый java.awt.BorderLayout());
        
        setBackground(java.awt.Color.white);
        GramInfoLabel.setBackground(java.awt.Color.lightGray);
        GramInfoLabel.setText("label2");
        add(GramInfoLabel, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents


    // Variables declaration - делай not modify//GEN-BEGIN:variables
    личный java.awt.Label GramInfoLabel;
    // End of variables declaration//GEN-END:variables

}

класс VisualSynAnPanel  расширяет Panel                         
                        воплощает AdjustmentListener, ComponentListener
{
   статичный цел ArcHeight = 20;

   статичный Font getSmallFont(Graphics g)
   {
       Font font = g.getFont();
       //дроб size = font.getSize()*((дроб)3)/((дроб)7);
       цел size = font.getSize()*5/6;
       //цел size = font.getSize();
       Font newFont = новый Font(font.getName(), font.getStyle(), size);
       верни newFont;
   }
   
    личный тщетный addWordArcs()
    {
        {
            m_ChildArcs = новый Vector();        
            WordArc arc = новый WordArc(0, 4);
            arc.addWordArc(0,1);        
            WordArc arc1 = новый WordArc(2, 4, ложь);
            arc1.addWordArc(2,3);
            arc.addWordArc(arc1);
            m_ChildArcs.addElement(arc);                
        }
        
        {
            m_ChildArcs1 = новый Vector();
            WordArc arc = новый WordArc(0, 4);
            m_ChildArcs1.addElement(arc);                        
        }
    }
    
    личный тщетный addWordPanels()
    {
        для(цел i = 0 ; i < m_WordPanels.size() ; i++ )
            add((WordPannel)m_WordPanels.elementAt(i));
    }
    
    защищённый тщетный parseOneWord(Строка strWord)
    {
        StringTokenizer strTok = новый StringTokenizer(strWord,"@");
        если( !strTok.hasMoreTokens() )
            верни;
        Строка str = strTok.nextToken();
        
        WordPannel word = новый WordPannel(str,это);
        если( !strTok.hasMoreTokens() )
            верни;
        
        str = strTok.nextToken();
        
        если( str.compareTo("homonyms") != 0 )
            верни;       
        пока( strTok.hasMoreTokens() )
            word.addHomonym(strTok.nextToken());
        
        m_WordPanels.addElement(word);
    }
    
    защищённый тщетный parseWords(Строка strWords)
    {
        StringTokenizer strTok = новый StringTokenizer(strWords,"$");
        если( !strTok.hasMoreTokens() )
            верни;
        Строка str = strTok.nextToken();
        если( str.compareTo( "words") != 0 )
            верни;
        пока( strTok.hasMoreTokens() )
            parseOneWord(strTok.nextToken());
    }
    
    защищённый тщетный fillHomonymNumbers(HomonymNumbers homs, Строка str, цел wordsCount) {
		Строка[] homonimStrings = str.split(" \\^");
        StringTokenizer strTok = новый StringTokenizer(str,"; ");
        цел ii = 0;
        цел[] arr = новый цел[wordsCount];
        пока( ii < homonimStrings.length && (ii < wordsCount)) {
            Строка strItem = homonimStrings[ii];
			strItem = strItem.substring(0, strItem.indexOf('='));
			цел homNum = Integer.parseInt(strItem);
//            цел homNum = Integer.decode(strItem).intValue();
            arr[ii] = homNum;
            ii++;
        }
        
        homs.m_HomNums = arr;
    }
    
    защищённый тщетный parseOneVariant(Строка strVar)
    {
        StringTokenizer strTok = новый StringTokenizer(strVar,"@");
        HomonymNumbers homs = новый HomonymNumbers();
        если( !strTok.hasMoreTokens() )
            верни;
        
        fillHomonymNumbers(homs,strTok.nextToken(), m_WordPanels.size());
        Vector arcs = новый Vector();
        HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = новый HomonymNumbers2WorddArcs();
        homonymNumbers2WorddArcs.m_SubjArcs = новый Vector();
        
        пока( strTok.hasMoreTokens() )
        {
            WordArc arc = новый WordArc(strTok.nextToken());
            если( arc.m_bIsSubj )
                homonymNumbers2WorddArcs.m_SubjArcs.addElement(arc);
            иначе
                arcs.addElement(arc);
        }
        
        homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
        homonymNumbers2WorddArcs.m_Arcs = новый Vector();
        orderArcs(arcs, homonymNumbers2WorddArcs.m_Arcs);
        m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
    }
    
    защищённый цел orderArcsRec(Vector arcs, WordArc parentArc, цел iCur)
    {        
        для(цел i = iCur ; i < arcs.size() ;  )
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            если( arc.m_FirstWord > parentArc.m_LastWord )
                верни i;
            i = orderArcsRec(arcs, arc, i + 1);            
            parentArc.addWordArc(arc);
        }
        верни arcs.size();
    }
    
    защищённый тщетный orderArcs(Vector arcs, Vector newVector)
    {
        для(цел i = 0 ; i < arcs.size() ;  )
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            i = orderArcsRec(arcs, arc, i + 1 );
            newVector.addElement(arc);
        }
    }
    
    защищённый тщетный parseVariants(Строка strVars)
    {
        StringTokenizer strTok = новый StringTokenizer(strVars,"$");
        если( !strTok.hasMoreTokens() )
            верни;
        Строка str = strTok.nextToken();
        если( str.compareTo("groups") != 0)
            верни;
        пока( strTok.hasMoreTokens() )
            parseOneVariant(strTok.nextToken());    
        setActiveHomonyms();
    }
    
    защищённый тщетный setActiveHomonyms()
    {
        если( m_HomNums2ArcVariant.size() == 0 )
            верни;
        HomonymNumbers2WorddArcs hom = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(0);
        
        для(цел i = 0 ; i < hom.m_HomonymNumbers.m_HomNums.length ; i++ )
        {
            WordPannel panel = getWordPannel(i);            
            panel.m_iActiveHomonym = hom.m_HomonymNumbers.m_HomNums[i];
        }
    }
    
    защищённый тщетный parseMainArgumnet(Строка mainStr)
    {
        StringTokenizer strTok = новый StringTokenizer(mainStr,"#");
        если( !strTok.hasMoreTokens() )
            верни;
        parseWords(strTok.nextToken());
        если( !strTok.hasMoreTokens() )
            верни;
        parseVariants(strTok.nextToken());
    }
    
    доступный VisualSynAnPanel(VisualSynAn VisualSynAnApplet, Строка strArg)
    {
        поверх();                   
        m_WordPanels = новый Vector();        
        m_HomNums2ArcVariant = новый Vector();
        m_VisualSynAnApplet = VisualSynAnApplet;                
        m_bValidate = ложь;
        setLayout(новый java.awt.FlowLayout(FlowLayout.LEFT,10, 10));                                      
        parseMainArgumnet(strArg);
        
         /*
        addWordArcs();
        
        
      
        {
            цел[] arr = {0,0,0,0,0};        
            HomonymNumbers homs = новый HomonymNumbers(arr);
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = новый HomonymNumbers2WorddArcs();
            homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
            homonymNumbers2WorddArcs.m_Arcs = m_ChildArcs;
            m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
        }
        
        {
            цел[] arr = {1,0,0,0,0};        
            HomonymNumbers homs = новый HomonymNumbers(arr);
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = новый HomonymNumbers2WorddArcs();
            homonymNumbers2WorddArcs.m_HomonymNumbers = homs;
            homonymNumbers2WorddArcs.m_Arcs = m_ChildArcs1;            
            m_HomNums2ArcVariant.addElement(homonymNumbers2WorddArcs);
        }
        m_bValidate = ложь;
        setLayout(новый java.awt.FlowLayout(FlowLayout.LEFT,10, 10));                              
        
        m_WordPanels = новый Vector();        
        
        
        m_WordPanels.addElement(новый WordPannel("$$",это));
        m_WordPanels.addElement(новый WordPannel("the_word2",это));
        m_WordPanels.addElement(новый WordPannel("the_word3qy",это));
        m_WordPanels.addElement(новый WordPannel("the_word4",это));
        m_WordPanels.addElement(новый WordPannel("the",это));                                        
        */
        addWordPanels();
        addComponentListener(это);
    }  
    
    личный тщетный setWordPannelsSize(Graphics g)
    {
        цел size = m_WordPanels.size();
        для(цел i = 0 ; i < size ; i++ )
        {            
            WordPannel wPanel = (WordPannel)m_WordPanels.elementAt(i);
            wPanel.calcSize(g);
        }
    }
    
    личный тщетный drawCanvas(Graphics g)
    {
        Color bg = getBackground(); 
        g.setColor(bg);        
        Dimension size = getSize();              
        g.draw3DRect(0, 0, size.width - 1, size.height - 1, истина);
        g.draw3DRect(3, 3, size.width - 7, size.height - 7, ложь);          
        g.setColor(Color.black);     
    }
    
    личный цел calcMaxArcHeight(Vector arcs)
    {
        цел height = 0;
        для(цел i = 0 ; i < arcs.size() ; i++ )
        {
            цел curHeight = ((WordArc)arcs.elementAt(i)).getHeight();
            если(  curHeight > height )
                height = curHeight;
        }
        верни height;
    }
    
    доступный синхронизированный тщетный validate()
    {
        reposeWordPanels();
        поверх.validate();
    }
    
    доступный синхронизированный тщетный update(Graphics g) 
    {
        reposeWordPanels();
        поверх.update(g);
    }
    
    доступный синхронизированный тщетный paintComponents(Graphics g)
    {
        //reposeWordPanels();
        поверх.paintComponents(g);
    }
    
    доступный синхронизированный тщетный paint(Graphics g) 
    {        
        
        Vector arcs = getCurArcs();                
        
        если( m_bValidate )
        {   
            цел height = calcMaxArcHeight(arcs);
            ((FlowLayout)getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
            yScrolBarSpan = 0;
            yScrolBarVal = 0;
            validate();                     
            m_bValidate = ложь;        
            Component comp = getComponent( getComponentCount() - 1 );
            m_VisualSynAnApplet.ranger.setValues(0, getBounds().height,0, comp.getBounds().y + comp.getBounds().height + 20);                        
            setWordPanelsInitialY();
        }         
        drawArcs(g, arcs);    
        drawCanvas(g);        
        drawSubjPredic(g);
    }    

    личный тщетный drawSubjPredic(Graphics g)
    {
        если( m_iCurHoms < 0 )
            верни;
        HomonymNumbers2WorddArcs homs = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(m_iCurHoms);
        для(цел i = 0 ; i < homs.m_SubjArcs.size() ; i++ )
        {
            WordArc arc = (WordArc)homs.m_SubjArcs.elementAt(i);
            WordPannel panel1 = (WordPannel)m_WordPanels.elementAt(arc.m_FirstWord);
            WordPannel panel2 = (WordPannel)m_WordPanels.elementAt(arc.m_LastWord);
            drawSubj(panel1, g);
            drawPredic(panel2, g);
            
        }
    }

     личный тщетный drawSubj(WordPannel panel, Graphics g)
     {
        Rectangle rect = panel.getBounds();
        g.drawLine(rect.x, rect.y + rect.height , rect.x + rect.width, rect.y + rect.height );
     }
     
     личный тщетный drawPredic(WordPannel panel, Graphics g)
     {
        Rectangle rect = panel.getBounds();
        g.drawLine(rect.x, rect.y + rect.height , rect.x + rect.width, rect.y + rect.height );
        g.drawLine(rect.x, rect.y + rect.height + 2, rect.x + rect.width, rect.y + rect.height + 2);
     }

    
    личный Vector getCurArcs()
    {
        HomonymNumbers homs = getActiveHomonymNumbers();
        
        для(цел i = 0 ; i < m_HomNums2ArcVariant.size() ; i++ )
        {
            HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = (HomonymNumbers2WorddArcs)m_HomNums2ArcVariant.elementAt(i);            
            если( homonymNumbers2WorddArcs.equals(homs) )
            {
                m_iCurHoms = i;
                верни homonymNumbers2WorddArcs.m_Arcs;
            }
            
        }
        
        m_iCurHoms = -1;
        верни ничто;
    }

    личный тщетный drawArcs(Graphics g, Vector arcs)
    {
        для(цел i = 0 ; i < arcs.size() ; i++ )                        
        {
            WordArc arc = (WordArc)arcs.elementAt(i);
            arc.Draw(g, это);
        }
    }

    
    доступный WordPannel getWordPannel(цел i )
    {
        верни  (WordPannel)(m_WordPanels.elementAt(i));
    }
    
    доступный тщетный setValidate(логическое val)
    {
        m_bValidate = val;
    }
    
    личный HomonymNumbers getActiveHomonymNumbers()
    {
        цел[] homs = новый цел[m_WordPanels.size()];
        для(цел i = 0 ; i < m_WordPanels.size() ; i++ )
            homs[i] = getWordPannel(i).getActiveHomonym();
        
        верни новый HomonymNumbers(homs);
    }
    
    доступный тщетный reposeWordPanels()
    {
        для(цел i = 0 ; i < m_WordPanels.size() ; i++ )
        {
            WordPannel word = getWordPannel(i);
            Rectangle oldRect = word.getBounds();            
            //getWordPannel(i).setLocation(oldRect.x, oldRect.y + yScrolBarSpan);                              
            word.setLocation(oldRect.x, word.m_Y - yScrolBarVal);                    
        }
    }
    
   
    доступный тщетный setWordPanelsInitialY()
    {
        для(цел i = 0 ; i < m_WordPanels.size() ; i++ )
        {
            Rectangle Rect = getWordPannel(i).getBounds();            
            getWordPannel(i).m_Y = Rect.y;
        }
    }
   
    доступный тщетный adjustmentValueChanged(AdjustmentEvent adjustmentEvent) 
    {
        цел val = adjustmentEvent.getValue();
        yScrolBarSpan =  yScrolBarVal - val;
        yScrolBarVal = val;
        repaint();
   }
    
    доступный тщетный componentShown(java.awt.event.ComponentEvent componentEvent) {
    }
    
    доступный тщетный componentMoved(java.awt.event.ComponentEvent componentEvent) {
    }
    
    доступный тщетный componentResized(ComponentEvent componentEvent) 
    {
        Vector arcs = getCurArcs();
        Graphics g = getGraphics();
        
        yScrolBarSpan = 0;
        yScrolBarVal = 0;

        setWordPannelsSize(g);                
        цел height = calcMaxArcHeight(arcs);
        ((FlowLayout)getLayout()).setVgap(height + getSmallFont(g).getSize() + 10);
        validate();            
        m_bValidate = ложь;        
        Component comp = getComponent( getComponentCount() - 1 );
        m_VisualSynAnApplet.ranger.setValues(0, getBounds().height,0, comp.getBounds().y + comp.getBounds().height + 20);        
        setWordPanelsInitialY();
    }
    
    доступный тщетный componentHidden(java.awt.event.ComponentEvent componentEvent) {
    }
    
    доступный VisualSynAn m_VisualSynAnApplet;           
    личный Vector m_ChildArcs;
    личный Vector m_ChildArcs1;
    //личный TreeMap m_HomNums2ArcVariant;
    личный Vector m_HomNums2ArcVariant;
    личный цел m_iCurHoms = 0;
    личный Vector m_WordPanels;
    личный логическое m_bValidate;
    личный Scrollbar ranger;
    личный цел yScrolBarVal = 0;
    личный цел yScrolBarSpan = 0;
}
