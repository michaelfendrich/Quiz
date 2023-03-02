package quiz.entity;

import java.lang.reflect.Array;
import java.util.List;

public enum TypeOfQuestion {
    CS_HISTORY {
        @Override
        public String getParam() {
            return "CS_HISTORY";
        }
        @Override
        public String quizName() {
            return "Czechoslovakian History";
        }
    },
    GENERAL {
        @Override
        public String getParam() {
            return "GENERAL";
        }
        @Override
        public String quizName() {
            return "General knowledge";
        }
    };
    public abstract String getParam();
    public abstract String quizName();

    public static List<TypeOfQuestion> getAllTypes() {
        return List.of(TypeOfQuestion.values());
    }
}
