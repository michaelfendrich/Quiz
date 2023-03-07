package quiz.entity;

import java.lang.reflect.Array;
import java.util.List;

public enum TypeOfQuestion {
    CS_HISTORY {
        @Override
        public String quizName() {
            return "Czechoslovakian History";
        }
    },
    GENERAL {
        @Override
        public String quizName() {
            return "General knowledge";
        }
    },
    GEOGRAPHY {
        @Override
        public String quizName() {
            return "Geography knowledge";
        }
    };
    public String getParam() {
        return this.name();
    }
    public abstract String quizName();

    public static List<TypeOfQuestion> getAllTypes() {
        return List.of(TypeOfQuestion.values());
    }
}
