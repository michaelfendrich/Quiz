
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
        if (!selectedAnswer) {
            alert("You should answer all questions.");
            return;
        }
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
    console.log(response);
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
    button.parentNode.removeChild(button);
    const barContainer = document.createElement("div");
    barContainer.className = "bar-container";
    barContainer.innerHTML = `<div class='circular-progress'>
                                <div class='value-container'>0%</div>
                            </div>`
    document.body.appendChild(barContainer);
    let progressBar = document.querySelector(".circular-progress");
    let valueContainer = document.querySelector(".value-container");
    let progressValue = 0;
    let progressEndValue = response.result;
    let speed = 20;
    let progress = setInterval(() => {
      progressValue++;
      valueContainer.textContent = `${progressValue}%`;
      progressBar.style.background = `conic-gradient(
        #4d5bf9 ${progressValue * 3.6}deg,
        #cadcff ${progressValue * 3.6}deg
      )`;
      if (progressValue === progressEndValue) {
        clearInterval(progress);
      }
    }, speed);
    window.scrollTo(0, document.body.scrollHeight);
}

