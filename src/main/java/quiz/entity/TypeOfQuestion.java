package quiz.entity;

import java.lang.reflect.Array;
import java.util.List;

public enum TypeOfQuestion {
    CS_HISTORY {
        @Override
        public String getParam() {
            return "cs_history";
        }
        @Override
        public String quizName() {
            return "Quiz from Czechoslovakian History";
        }
    },
    GENERAL {
        @Override
        public String getParam() {
            return "general";
        }
        @Override
        public String quizName() {
            return "Quiz from General knowledge";
        }
    };
    public abstract String getParam();
    public abstract String quizName();

    public static List<TypeOfQuestion> getAllTypes() {
        return List.of(TypeOfQuestion.values());
    }
}
