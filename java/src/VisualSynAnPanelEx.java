
внеся java.util.logging.Level;
внеся java.util.logging.Logger;
внеся java.util.ПорядковыйСписок;
внеся java.util.Список;
внеся org.elliptica.ling.syntax.Группа;
внеся org.elliptica.ling.syntax.Омоним;
внеся org.elliptica.ling.syntax.Предложение;
внеся org.elliptica.ling.syntax.СинтаксическоеДерево;
внеся org.elliptica.ling.syntax.Слово;
внеся org.elliptica.ling.syntax.Фрагмент;
внеся org.elliptica.ling.syntax.Юнит;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mahairod
 */
доступный класс VisualSynAnPanelEx расширяет VisualSynAnPanel {

	защищённый тщетный разбериСлова(Список<Слово> слова) {
		для (Слово слово: слова){
			WordPannel wordPannel = новый WordPannel(слово.getЗначение(), this);
			для (Омоним омоним: слово.getОмонимы() ){
				wordPannel.m_Homonyms.add(омоним);
			}
			m_WordPanels.add(wordPannel);
		}
	}

	защищённый тщетный разбериВарианты(Список<Фрагмент> фрагменты) {
		разбериОдинВариант(фрагменты, номерВарианта);
		setActiveHomonyms();
	}

	защищённый тщетный разбериОдинВариант(Список<Фрагмент> фрагменты, цел номерВарианта) {
		HomonymNumbers2WorddArcs homonymNumbers2WorddArcs = новый HomonymNumbers2WorddArcs();
		Список<Юнит> юниты = новый ПорядковыйСписок<>( m_WordPanels.размер() );

		цел[] варианты = новый цел[фрагменты.размер()];
		варианты[0] = 1;
		для (цел и = 0; и < варианты.length - 1; и++){
			варианты[и+1] = фрагменты.дай(и).getВарианты().размер() * варианты[и];
		}
		для (цел и = варианты.length - 1; и >= 0 ; и--){
			цел дел = варианты[и];
			варианты[и] = номерВарианта / дел;
			номерВарианта = номерВарианта % дел;
		}

		Список<WordArc> arcs = новый ПорядковыйСписок<>();
		для (цел и = 0; и < варианты.length; и++){
			Фрагмент фрагмент = фрагменты.дай(и);
			цел вариант = варианты[и];
			для (Группа группа : фрагмент.getВарианты().дай(вариант).getГруппы()) {
				WordArc arc = новый WordArc(группа, фрагмент.getНачало());
				если (arc.m_bIsSubj) {
					homonymNumbers2WorddArcs.m_SubjArcs.add(arc);
				} иначе {
					arcs.add(arc);
				}
			}
			юниты.добавьВсе(фрагмент.getВарианты().дай(вариант).getЮниты());
		}
		заполниНомераОмонимов(homonymNumbers2WorddArcs.m_HomonymNumbers, юниты, m_WordPanels.size());

		orderArcs(arcs, homonymNumbers2WorddArcs.m_Arcs);
		m_HomNums2ArcVariant.add(homonymNumbers2WorddArcs);
	}

	защищённый тщетный заполниНомераОмонимов(HomonymNumbers homs, Список<Юнит> юниты, цел числоСлов) {
		цел[] arr = новый цел[числоСлов];
		цел ii = 0;
		для ( Юнит юнит : юниты) {
			arr[ii] = юнит.getНомер();
			ii++;
		}
		homs.m_HomNums = arr;
	}

	защищённый тщетный разбериГлавныйАргумент(СинтаксическоеДерево дерево) {
		Список<Предложение> предложения = дерево.getПредложения();
		если (предложения == ничто || предложения.пусто()) верни;
		Предложение предложение = предложения.дай(номерПредложения);
		разбериСлова(предложение.getСписокСлов());
		разбериВарианты(предложение.getСписокФрагментов());
	}

	доступный тщетный setStringArgs(СинтаксическоеДерево дерево) {
		разбериГлавныйАргумент(дерево);
		addWordPanels();
		обновиРазмеры();
	}

	статичный итоговый Logger LOG = Logger.getLogger(VisualSynAnPanelEx.класс.getName());
}
