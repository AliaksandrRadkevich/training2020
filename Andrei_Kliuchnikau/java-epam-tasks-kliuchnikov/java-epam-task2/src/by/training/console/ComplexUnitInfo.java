package by.training.console;

import by.training.domain.PunctuationMark;
import by.training.domain.Sentence;
import by.training.domain.Text;
import by.training.domain.Word;
import by.training.domain.abstractions.ComplexUnit;
import by.training.domain.abstractions.LanguageUnit;

public class ComplexUnitInfo {
    public static String printFullInfo(ComplexUnit complexUnit) {
        StringBuilder unitFullInfo = new StringBuilder();
        if (complexUnit instanceof Sentence) {
            unitFullInfo.append("Предложение: ");
        }
        else if (complexUnit instanceof Text) {
            unitFullInfo.append("Текст (оригинал): ");
        }
        unitFullInfo.append(complexUnit.getUnitString()).append(System.lineSeparator()).append("Состоит из следующих языковых единиц:");
        for(LanguageUnit unit : complexUnit.getComponentsList()) {
            unitFullInfo.append(System.lineSeparator()).append(unit.getUnitString());
            if (unit instanceof Sentence) {
                unitFullInfo.append(" - Предложение.");
            }
            else if (unit instanceof Word) {
                unitFullInfo.append(" - Слово.");
            }
            else if (unit instanceof PunctuationMark) {
                unitFullInfo.append(" - Знак пунктуации.");
            }
        }
        unitFullInfo.append(System.lineSeparator());
        return unitFullInfo.toString();
    }
}