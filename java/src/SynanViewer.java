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
внеся java.awt.Dimension;
внеся java.awt.Font;
внеся java.awt.GraphicsEnvironment;
//-
внеся org.elliptica.ling.syntax.Синтаксис;
внеся org.elliptica.ling.syntax.СинтаксическоеДерево;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс SynanViewer расширяет javax.swing.JFrame {
	доступный статичный тщетный main(Строка[] args){
		Строка struct = "#words$дом@homonyms@дом^сущ$стоял@homonyms@стоить^гл@стоять^гл$на@homonyms@на^предлог$пригорке@homonyms@пригорок^сущ#groups$0,0,0,0 @ 0,1^подл^gr$0,0,0,0 @ 0,3^гл_личн^cl @ 0,1^подл^gr ";//getParameter("struct");
		struct = "#words$Одновременно@homonyms@ОДНОВРЕМЕННО$с@homonyms@С$этим@homonyms@ЭТО$на@homonyms@НА$донецком@homonyms@ДОНЕЦКИЙ$направлении@homonyms@НАПРАВЛЕНИЕ$двумя@homonyms@ДВУМЯЯ$группировками@homonyms@ГРУППИРОВКА$Украина@homonyms@УКРАИНА$намерена@homonyms@НАМЕРЕН$начать@homonyms@НАЧАТЬ$наступление@homonyms@НАСТУПЛЕНИЕ$севернее@homonyms@СЕВЕРНЫЙ$и@homonyms@И$южнее@homonyms@ЮЖНЫЙ$Донецка@homonyms@ДОНЕЦК$в@homonyms@В@В$направлении@homonyms@НАПРАВЛЕНИЕ@НАПРАВЛЕНИЕ$Иловайска@homonyms@ИЛОВАЙСК$,@homonyms@,$чтобы@homonyms@ЧТОБЫ$замкнуть@homonyms@ЗАМКНУТЬ$кольцо@homonyms@КОЛЬЦО$вокруг@homonyms@ВОКРУГ$столицы@homonyms@СТОЛИЦА$республики@homonyms@РЕСПУБЛИКА#groups$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^0= ; ^0= ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@16^17^оборот^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr$0= ; ^0= ; ^0= ср,тв,ед,; ^0=ПРЕДЛ ; ^0=П но,од,мр,пр,ед,; но,од,ср,пр,ед,; ^0=С ср,пр,ед,; ^0=С ср,жр,мр,пр,тв,вн,дт,рд,им,ед,мн,; ^0=С жр,тв,мн,; ^0=С жр,им,ед,; ^0=КР_ПРИЛ но,од,жр,ед,; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=П сравн,но,од,; ^0=СОЮЗ ; ^0=П сравн,но,од,; ^0=С мр,рд,ед,; ^1=ПРЕДЛ ; ^1=С ср,пр,ед,; ^0=С мр,рд,ед,; ^0= ^0=СОЮЗ ; ^0=ИНФИНИТИВ дст,; ^0=С ср,им,ед,; ср,вн,ед,; ^0=ПРЕДЛ ; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; ^0=С жр,рд,ед,; жр,им,мн,; жр,вн,мн,; @0^25^кр_прил^cl@0^2^оборот^gr@3^6^пг^gr@4^6^генит_иг^gr@4^5^прил_сущ^gr@8^9^sp^sp@10^11^пр_доп^gr@12^15^отсравн^gr@12^14^однор_прил^gr@16^18^пг^gr@17^18^генит_иг^gr@19^25^инф^cl@21^22^пр_доп^gr@23^25^пг^gr@24^25^генит_иг^gr#topclause";

		синтаксис = новый Синтаксис();
//		Строка текст = "В ходе поисков были обследованы более 70 тысяч квадратных километров, но никаких следов или обломков найти не удалось. С приходом зимы операцию пришлось прервать. Летом поиски возобновились. Работы осложнялись горным рельефом местности.";
		Строка текст = "С приходом зимы операцию пришлось прервать";
		СинтаксическоеДерево результат = синтаксис.разборТекста(текст);
		System.out.println(результат);

		SynanViewer synanViewer = новый SynanViewer(результат);
		synanViewer.pack();
		synanViewer.setLocationRelativeTo(ничто);
		synanViewer.setVisible(истина);
	}

	статичный личный Синтаксис синтаксис;

	/**
	 * Создаёт новую форму SynanViewer
	 */
	доступный SynanViewer() {
		initComponents();
		m_VisualSynanPanel.setMainView(это);
		m_VisualSynanPanel.setBackground(Color.lightGray);

		Font oldFondt = gramInfoLabel.getFont();

		Font newFont = новый Font(oldFondt.getName(), oldFondt.getStyle() + Font.BOLD, oldFondt.getSize());

		gramInfoLabel.setForeground(Color.blue);
		gramInfoLabel.setFont(newFont);
		gramInfoLabel.setText("Описание");

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setPreferredSize(новый Dimension(ge.getCenterPoint().x, ge.getCenterPoint().y));
	}

	доступный SynanViewer(Строка struct) {
		это();
		m_VisualSynanPanel.setStringArgs(struct);
	}
	
	доступный SynanViewer(СинтаксическоеДерево дерево) {
		это();
		m_VisualSynanPanel.setStringArgs(дерево);
	}
	
    доступный тщетный updateLabel(Строка text) {
        gramInfoLabel.setText(text);
    }

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@ЗамолчиПредупреждения("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    личный тщетный initComponents() {

        topPanel = новый javax.swing.JPanel();
        inpTextLabel = новый javax.swing.JLabel();
        inputTextField = новый javax.swing.JTextField();
        parseButton = новый javax.swing.JButton();
        scroller = новый javax.swing.JScrollPane();
        m_VisualSynanPanel = новый VisualSynAnPanelEx();
        bottomPanel = новый javax.swing.JPanel();
        gramInfoLabel = новый javax.swing.JLabel();
        variantSpinner = новый javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Синтаксис фразы");
        setBackground(новый java.awt.Color(255, 255, 255));
        setMinimumSize(новый java.awt.Dimension(400, 250));
        setPreferredSize(новый java.awt.Dimension(500, 300));

        topPanel.setLayout(новый java.awt.BorderLayout());

        inpTextLabel.setText("Текст: ");
        topPanel.add(inpTextLabel, java.awt.BorderLayout.WEST);

        inputTextField.setText("Пример текста");
        topPanel.add(inputTextField, java.awt.BorderLayout.CENTER);

        parseButton.setText("Разбор");
        parseButton.addActionListener(новый java.awt.event.ActionListener() {
            доступный тщетный actionPerformed(java.awt.event.ActionEvent evt) {
                parseButtonActionPerformed(evt);
            }
        });
        topPanel.add(parseButton, java.awt.BorderLayout.EAST);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        scroller.setViewportView(m_VisualSynanPanel);

        getContentPane().add(scroller, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(новый java.awt.BorderLayout());

        variantSpinner.addChangeListener(новый javax.swing.event.ChangeListener() {
            доступный тщетный stateChanged(javax.swing.event.ChangeEvent evt) {
                variantSpinnerStateChanged(evt);
            }
        });
        bottomPanel.add(variantSpinner, java.awt.BorderLayout.EAST);

        gramInfoLabel.setText("Описание");
        bottomPanel.add(gramInfoLabel, java.awt.BorderLayout.WEST);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    личный тщетный parseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseButtonActionPerformed
		m_VisualSynanPanel.clear();
        m_VisualSynanPanel.setStringArgs( синтаксис.разборТекста(inputTextField.getText()) );
    }//GEN-LAST:event_parseButtonActionPerformed

    личный тщетный variantSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_variantSpinnerStateChanged
        m_VisualSynanPanel.номерВарианта = (Integer) variantSpinner.getValue();
    }//GEN-LAST:event_variantSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    личный javax.swing.JPanel bottomPanel;
    личный javax.swing.JLabel gramInfoLabel;
    личный javax.swing.JLabel inpTextLabel;
    личный javax.swing.JTextField inputTextField;
    личный VisualSynAnPanel m_VisualSynanPanel;
    личный javax.swing.JButton parseButton;
    личный javax.swing.JScrollPane scroller;
    личный javax.swing.JPanel topPanel;
    личный javax.swing.JSpinner variantSpinner;
    // End of variables declaration//GEN-END:variables
}
