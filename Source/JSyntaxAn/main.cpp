
#include <SyntaxHolder.h>
#include <iostream>

int main(){
	CSyntaxHolder syntaxHolder;
	bool res = syntaxHolder.LoadSyntax(morphRussian);
	using namespace std;
	if (!res){
		cout << "Syntax was not loaded!" << endl;
	}
	syntaxHolder.GetSentencesFromSynAn( "Подстановка иного слова", false );
}
