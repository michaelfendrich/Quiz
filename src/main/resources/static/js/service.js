let result = {
    "result": 100.0,
    "questions": [
        {
            "id": 1,
            "selectedAnswer": "c",
            "correctedAnswer": "c"
        },
        {
            "id": 2,
            "selectedAnswer": "c",
            "correctedAnswer": "c"
        }
    ]
};

const form = document.getElementById("container");
const button = document.getElementById("button");
const quizTemplate = new QuizTemplate();
quizTemplate.prepareTemplate(form);
button.addEventListener('click', () => {
    perform();
});

function perform() {
    let data = [];
    let nodes = form.childNodes;
    for (let i = 1; i < nodes.length; i++) {
        let oneQuestion = document.getElementById(i);
        let id = oneQuestion.id;
        let selectedAnswer = oneQuestion.elements.fav_language.value;
        data.push({
            id,
            selectedAnswer
        });
    }
    getResponse(data);
}

async function getResponse(data) {
    const headers = {'Accept': 'application/json',
                    'Content-Type': 'application/json'};
    const response = await fetch('http://localhost:8080/questions', {method:"POST", headers, body: JSON.stringify(data)})
                        .then(res => res.json());
    for (let i = 0; i < response.questions.length; i++){
        let questionId = response.questions[i].id
        let form = document.getElementById(questionId);
        let answers = response.questions[i];
        if (answers.selectedAnswer === answers.correctedAnswer) {
            let inputOfAnswer = form.querySelector(`input[type='radio'][value=${answers.correctedAnswer}]`);
            inputOfAnswer.parentNode.className = "green";
        } else {
            let inputOfAnswer = form.querySelector(`input[type='radio'][value=${answers.correctedAnswer}]`);
            inputOfAnswer.parentNode.className = "green";
            inputOfAnswer = form.querySelector(`input[type='radio'][value=${answers.selectedAnswer}]`);
            inputOfAnswer.parentNode.className = "red";
        }
    }
}

