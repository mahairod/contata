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
import java.awt.GraphicsEnvironment;
//-

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class SynanViewer extends javax.swing.JFrame {
	public static void main(String[] args){
		String struct = "#words$дом@homonyms@дом^сущ$стоял@homonyms@стоить^гл@стоять^гл$на@homonyms@на^предлог$пригорке@homonyms@пригорок^сущ#groups$0,0,0,0 @ 0,1^подл^gr$0,0,0,0 @ 0,3^гл_личн^cl @ 0,1^подл^gr ";//getParameter("struct");
		struct = "#words$Одновременно@homonyms@ОДНОВРЕМЕННО$с@homonyms@С$этим@homonyms@ЭТО$на@homonyms@НА$донецком@homonyms@ДОНЕЦКИЙ$направлении@homonyms@НАПРАВЛЕНИЕ$двумя@homonyms@ДВУМЯЯ$группировками@homonyms@ГРУППИРОВКА$Украина@homonyms@УКРАИНА$намерена@homonyms@НАМЕРЕН$начать@homonyms@НАЧАТЬ$наступление@homonyms@НАСТУПЛЕНИЕ$севернее@homonyms@СЕВЕРНЫЙ$и@homonyms@И$южнее@homonyms@ЮЖНЫЙ$Донецка@homonyms@ДОНЕЦК$в@homonyms@В@В$направлении@homonyms@НАПРАВЛЕНИЕ@НАПРАВЛЕНИЕ$Иловайска@homonyms@ИЛОВАЙСК$,@homonyms@,$чтобы@homonyms@ЧТОБЫ$замкнуть@homonyms@ЗАМКНУТЬ$кольцо@homonyms@КОЛЬЦО$вокруг@homonyms@ВОКРУГ$столицы@homonyms@СТОЛИЦА$республики@homonyms@РЕСПУБЛИКА#groups$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr#topclause";

		SynanViewer synanViewer = new SynanViewer(struct);
		synanViewer.pack();
		synanViewer.setLocationRelativeTo(null);
		synanViewer.setVisible(true);
	}

	/**
	 * Создаёт новую форму SynanViewer
	 */
	public SynanViewer() {
		initComponents();
		m_VisualSynanPanel.setMainView(this);
		m_VisualSynanPanel.setBackground(Color.lightGray);

		Font oldFondt = gramInfoLabel.getFont();

		Font newFont = new Font(oldFondt.getName(), oldFondt.getStyle() + Font.BOLD, oldFondt.getSize());

		gramInfoLabel.setForeground(Color.blue);
		gramInfoLabel.setFont(newFont);
		gramInfoLabel.setText("Описание");

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setPreferredSize(new Dimension(ge.getCenterPoint().x, ge.getCenterPoint().y));
	}

	public SynanViewer(String struct) {
		this();
		m_VisualSynanPanel.setStringArgs(struct);
	}
	
    public void updateLabel(String text) {
        gramInfoLabel.setText(text);
    }

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        inpTextLabel = new javax.swing.JLabel();
        inputTextField = new javax.swing.JTextField();
        parseButton = new javax.swing.JButton();
        scroller = new javax.swing.JScrollPane();
        m_VisualSynanPanel = new VisualSynAnPanelEx();
        bottomPanel = new javax.swing.JPanel();
        gramInfoLabel = new javax.swing.JLabel();

        variantSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Синтаксис фразы");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 250));
        setPreferredSize(new java.awt.Dimension(500, 300));

        topPanel.setLayout(new java.awt.BorderLayout());

        inpTextLabel.setText("Текст: ");
        topPanel.add(inpTextLabel, java.awt.BorderLayout.WEST);

        inputTextField.setText("Пример текста");
        topPanel.add(inputTextField, java.awt.BorderLayout.CENTER);

        parseButton.setText("Разбор");
        parseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseButtonActionPerformed(evt);
            }
        });
        topPanel.add(parseButton, java.awt.BorderLayout.EAST);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        scroller.setViewportView(m_VisualSynanPanel);

        getContentPane().add(scroller, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(new java.awt.BorderLayout());

        variantSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                variantSpinnerStateChanged(evt);
            }
        });
        bottomPanel.add(variantSpinner, java.awt.BorderLayout.EAST);

        gramInfoLabel.setText("Описание");
        bottomPanel.add(gramInfoLabel, java.awt.BorderLayout.WEST);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void parseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseButtonActionPerformed
		m_VisualSynanPanel.clear();
		m_VisualSynanPanel.setStringArgs(inputTextField.getText());
    }//GEN-LAST:event_parseButtonActionPerformed

    private void variantSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_variantSpinnerStateChanged
        m_VisualSynanPanel.номерВарианта = (Integer) variantSpinner.getValue();
    }//GEN-LAST:event_variantSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel gramInfoLabel;
    private javax.swing.JLabel inpTextLabel;
    private javax.swing.JTextField inputTextField;
    private VisualSynAnPanel m_VisualSynanPanel;
    private javax.swing.JButton parseButton;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JPanel topPanel;

    private javax.swing.JSpinner variantSpinner;
    // End of variables declaration//GEN-END:variables
}
