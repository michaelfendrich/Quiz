class QuizTemplate2 {

    async prepareTemplate(form) {
        this.startTime = Date.now(0);
        this.questions = await fetch('http://localhost:8080/questions').then(response => response.json());
        console.log(this.questions);
        for (let i = 0; i < this.questions.length; i++) {
            let question = document.createElement("form");
            question.id = this.questions[i].id;
            question.innerHTML += `<h4 class="${i}">${this.questions[i].questionText}<h4>`;
            question.innerHTML += `<label class="neutral"><input type="radio" name="fav_language" value="a">${this.questions[i].answerA}<label><br>
                                    <label class="neutral"><input type="radio" name="fav_language" value="b">${this.questions[i].answerB}<label><br>
                                    <label class="neutral"><input type="radio" name="fav_language" value="c">${this.questions[i].answerC}<label><br>`;
            form.appendChild(question);
        }
    }

    checkAnswer(data) {
        let time = Date.now() - this.startTime;
        for (let answer of data) {
            for (let question of this.questions) {
                if (answer.id == question.id) {
                    answer['correctAnswer'] = question.correctAnswer;
                    break;
                }
            }
        }
        let valueOfCorrectedAnswers = 0;
        for (let answer of data) {
            if (answer.selectedAnswer == answer.correctAnswer) {
                valueOfCorrectedAnswers++;
            }
        }
        let result = {
            "result": valueOfCorrectedAnswers * 100 / data.length,
            "time": Math.ceil(time / 1000),
            "questions": data
        }
        return result;
    }
}